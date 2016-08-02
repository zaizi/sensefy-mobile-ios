//
//  ViewController.m
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 5/27/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "AccountViewController.h"
#import "Constants.h"
#import "DocumentPreviewController.h"
#import "FacetSelectController.h"
#import "FacetTableViewCell.h"
#import "MBProgressHUD.h"
#import "NSMutableArray+Custom.h"
#import "NSString+FontAwesome.h"
#import "NSString+FontAwesome.h"
#import "Reachability.h"
#import "SearchFilters.h"
#import "SearchTableViewCell.h"
#import "SearchViewController.h"
#import "SourceSelectController.h"
#import "SourceTableViewCell.h"
#import "UIColor+Custom.h"
#import "Utility.h"

@interface SearchViewController () <UISearchBarDelegate>

@property(weak, nonatomic) IBOutlet UISearchBar *searchBar;
@property(weak, nonatomic) IBOutlet UIButton *dataSourceLabel;
@property(weak, nonatomic) IBOutlet UITableView *tableView;
@property(weak, nonatomic) IBOutlet UIBarButtonItem *statusLabel;
@property(weak, nonatomic) IBOutlet UIBarButtonItem *backButton;
@property(weak, nonatomic) IBOutlet UILabel *footerView;
@property(weak, nonatomic) IBOutlet UIBarButtonItem *sortByButton;
@property(weak, nonatomic) IBOutlet UIBarButtonItem *sortOrderButton;
@property(weak, nonatomic) IBOutlet NSLayoutConstraint *spacingConstraint;
@property(weak, nonatomic) IBOutlet NSLayoutConstraint *facetViewConstraint;
@property(weak, nonatomic) IBOutlet UITableView *facetTable;

@property(nonatomic, strong) NSArray *documentList;
@property(nonatomic, strong) NSMutableArray *facets;
@property(nonatomic, strong) NSMutableArray *datasources;
@property(nonatomic, strong) NSDictionary *colorDictionary;
@property(nonatomic, strong) SearchFilters *searchFilters;
@property(nonatomic) long documentCount;
@property(nonatomic, strong) Reachability *internetReachability;
@property(nonatomic, strong) NSArray *suggessions;

@property(nonatomic, strong)
    UIPopoverPresentationController *sourcePopOverController;

@property SortOrder sortOrder;
@property SortByItem sortByItem;
@property BOOL autoComplete;
@property int selectedDatasource;
@property BOOL connectionInProgress;
@property BOOL panelOpen;
@property BOOL missingToken;
@property NSMutableArray *hidePreference;
@property WelcomeView *welcomeView;

@end

NSString *const kSearchCellIdentifier = @"SearchCell";
NSString *const kAutoCompleteCellIdentifier = @"autoCompleteCell";

int const kFacetShowCount = 3;
CGFloat const kFooterHeight = 44.0f;
CGFloat const kFooterMargin = 10.0f;

@implementation SearchViewController

- (void)initiateHidePreference {
  self.hidePreference = [[NSMutableArray alloc] init];
  for (SensefyFacet *facet in self.facets) {
    NSInteger facetCount = [facet.facetItems count];
    [self.hidePreference
        addObject:[NSNumber numberWithBool:(facetCount > kFacetShowCount)]];
  }
}

- (void)updateConstraintsForMode {
  if (self.panelOpen) {
    self.spacingConstraint.constant = 0.0;
    self.facetViewConstraint.priority = UILayoutPriorityDefaultHigh - 1;
    self.panelOpen = NO;
  } else {
    self.spacingConstraint.constant = 300.0;
    self.facetViewConstraint.priority = UILayoutPriorityDefaultHigh + 1;
    self.panelOpen = YES;
  }
}

- (void)hideAndDisableFormattingButtons {
  self.navigationItem.rightBarButtonItem.title = @"";
  [self.navigationItem.rightBarButtonItem setEnabled:NO];
  [self.sortByButton setTintColor:[UIColor clearColor]];
  [self.sortByButton setEnabled:NO];
  [self.sortOrderButton setTintColor:[UIColor clearColor]];
  [self.sortOrderButton setEnabled:NO];

  if (IS_IPAD && self.panelOpen) {
    [self.view layoutIfNeeded];
    [UIView animateWithDuration:.5
                     animations:^{
                       [self updateConstraintsForMode];
                       [self.view layoutIfNeeded];
                     }];
  }
}

- (void)showAndEnableFormattingButtons {
  self.navigationItem.rightBarButtonItem.title =
      [NSString fontAwesomeIconStringForEnum:FAListAlt];
  [self.navigationItem.rightBarButtonItem setEnabled:YES];
  [self.sortByButton setTintColor:[UIColor whiteColor]];
  [self.sortByButton setEnabled:YES];
  [self.sortOrderButton setTintColor:[UIColor whiteColor]];
  [self.sortOrderButton setEnabled:YES];
}

- (void)facetsChanged:(NSNotification *)notiofiocation {
  if ([self hasInternetConnection]) {
    [self performSearch];
  } else {
    [Utility showConnectionErrorOn:self];
  }
}

- (void)tokenHasExpired:(NSNotification *)notification {
  NSLog(@"You are not logged in");
  self.missingToken = YES;
}

- (void)tokenNotFound:(NSNotification *)notification {
  NSLog(@"You are not logged in");
  self.missingToken = YES;
}

- (void)tokenRecieved:(NSNotification *)notification {
  NSLog(@"You are logged in");
  self.missingToken = NO;
  if (nil != self.welcomeView) {
    [self.welcomeView removeFromSuperview];
  }
}

- (void)sortOrderChanged:(UIBarButtonItem *)button {
  if ([self hasInternetConnection]) {
    if (self.sortOrder == SORT_DESC) {
      self.sortOrder = SORT_ASC;
      [self.sortOrderButton setImage:[UIImage imageNamed:@"SortAscending"]];
    } else {
      self.sortOrder = SORT_DESC;
      [self.sortOrderButton setImage:[UIImage imageNamed:@"SortDescending"]];
    }
    [self performSearch];
  } else {
    [Utility showConnectionErrorOn:self];
  }
}

- (void)sortByItemChanged {
  if ([self hasInternetConnection]) {
    [self performSearch];
  } else {
    [Utility showConnectionErrorOn:self];
  }
}

- (void)showSortByActionSheet:(UIBarButtonItem *)button {

  UIAlertController *sortByAlert = [UIAlertController
      alertControllerWithTitle:
          [NSString
              stringWithFormat:@"%@ : %@",
                               NSLocalizedString(@"Sorted By", @"Sorted By"),
                               NSLocalizedString(
                                   kSortByTitles[self.sortByItem],
                                   kSortByTitles[self.sortByItem])]
                       message:NSLocalizedString(@"Select an item to sort with",
                                                 @"Select an item to sort with")
                preferredStyle:UIAlertControllerStyleActionSheet];

  UIAlertAction *relavanceAction = [UIAlertAction
      actionWithTitle:NSLocalizedString(@"Relevance", @"Relevance")
                style:UIAlertActionStyleDefault
              handler:^(UIAlertAction *action) {
                self.sortByItem = SORT_BY_RELEVANCE;
                self.sortOrder = SORT_DESC;
                [self.sortOrderButton
                    setImage:[UIImage imageNamed:@"SortDescending"]];
                [self sortByItemChanged];
              }];
  UIAlertAction *nameAction = [UIAlertAction
      actionWithTitle:NSLocalizedString(@"Name", @"Name")
                style:UIAlertActionStyleDefault
              handler:^(UIAlertAction *action) {
                self.sortByItem = SORT_BY_NAME;
                self.sortOrder = SORT_ASC;
                [self.sortOrderButton
                    setImage:[UIImage imageNamed:@"SortAscending"]];
                [self sortByItemChanged];
              }];
  UIAlertAction *titleAction = [UIAlertAction
      actionWithTitle:NSLocalizedString(@"Title", @"Title")
                style:UIAlertActionStyleDefault
              handler:^(UIAlertAction *action) {
                self.sortByItem = SORT_BY_TITLE;
                self.sortOrder = SORT_ASC;
                [self.sortOrderButton
                    setImage:[UIImage imageNamed:@"SortAscending"]];
                [self sortByItemChanged];
              }];
  UIAlertAction *createdAction = [UIAlertAction
      actionWithTitle:NSLocalizedString(@"Created Date", @"Created Date")
                style:UIAlertActionStyleDefault
              handler:^(UIAlertAction *action) {
                self.sortByItem = SORT_BY_CREATED_DATE;
                self.sortOrder = SORT_DESC;
                [self.sortOrderButton
                    setImage:[UIImage imageNamed:@"SortDescending"]];
                [self sortByItemChanged];
              }];
  UIAlertAction *modifiedAction = [UIAlertAction
      actionWithTitle:NSLocalizedString(@"Modified Date", @"Modified Date")
                style:UIAlertActionStyleDefault
              handler:^(UIAlertAction *action) {
                self.sortByItem = SORT_BY_MODIFIED_DATE;
                self.sortOrder = SORT_DESC;
                [self.sortOrderButton
                    setImage:[UIImage imageNamed:@"SortDescending"]];
                [self sortByItemChanged];
              }];
  UIAlertAction *creatorAction = [UIAlertAction
      actionWithTitle:NSLocalizedString(@"Creator", @"Creator")
                style:UIAlertActionStyleDefault
              handler:^(UIAlertAction *action) {
                self.sortByItem = SORT_BY_CREATOR;
                self.sortOrder = SORT_ASC;
                [self.sortOrderButton
                    setImage:[UIImage imageNamed:@"SortAscending"]];
                [self sortByItemChanged];
              }];
  UIAlertAction *modifierAction = [UIAlertAction
      actionWithTitle:NSLocalizedString(@"Modifier", @"Modifier")
                style:UIAlertActionStyleDefault
              handler:^(UIAlertAction *action) {
                self.sortByItem = SORT_BY_MODIFIER;
                self.sortOrder = SORT_ASC;
                [self.sortOrderButton
                    setImage:[UIImage imageNamed:@"SortAscending"]];
                [self sortByItemChanged];
              }];

  UIAlertAction *cancelAction =
      [UIAlertAction actionWithTitle:NSLocalizedString(@"Cancel", @"Cancel")
                               style:UIAlertActionStyleCancel
                             handler:^(UIAlertAction *action) {
                               [sortByAlert removeFromParentViewController];
                             }];

  [sortByAlert addAction:relavanceAction];
  [sortByAlert addAction:nameAction];
  [sortByAlert addAction:titleAction];
  [sortByAlert addAction:createdAction];
  [sortByAlert addAction:modifiedAction];
  [sortByAlert addAction:creatorAction];
  [sortByAlert addAction:modifierAction];
  [sortByAlert addAction:cancelAction];

  UIPopoverPresentationController *popover =
      sortByAlert.popoverPresentationController;
  if (popover) {
    popover.barButtonItem = self.sortByButton;
    popover.permittedArrowDirections = UIPopoverArrowDirectionAny;
  }

  [self presentViewController:sortByAlert animated:YES completion:nil];
}

- (void)dataSourceChanged:(NSNotification *)notification {

  // Data source changed. So we should reset all the filters to avoid
  // inconsistencies.
  FacetItem *dataSource = self.datasources[self.selectedDatasource];
  NSString *filterString; // = dataSource.filter;
  [self.searchFilters
          removeAllFilters]; // [self.searchFilters removeFilter:filterString];
  int index = (int)[notification.object integerValue];
  self.selectedDatasource = index;
  NSMutableAttributedString *dataSourceTitle;
  if (index != 0) {
    dataSource = self.datasources[index];
    filterString = dataSource.filter;
    dataSourceTitle = [[NSMutableAttributedString alloc]
        initWithString:[NSString
                           stringWithFormat:@"%@ %@", dataSource.value,
                                            [NSString
                                                fontAwesomeIconStringForEnum:
                                                    FAChevronCircleDown]]];
    [dataSourceTitle addAttribute:NSFontAttributeName
                            value:[UIFont fontWithName:kFontAwesome size:17]
                            range:NSMakeRange(dataSourceTitle.length - 1, 1)];
    [self.dataSourceLabel setAttributedTitle:dataSourceTitle
                                    forState:UIControlStateNormal];
    [self.searchFilters addFilter:filterString];
  } else {
    dataSourceTitle = [[NSMutableAttributedString alloc]
        initWithString:[NSString
                           stringWithFormat:@"%@ %@", NSLocalizedString(
                                                          kDataSourceLabelAll,
                                                          kDataSourceLabelAll),
                                            [NSString
                                                fontAwesomeIconStringForEnum:
                                                    FAChevronCircleDown]]];
    [dataSourceTitle addAttribute:NSFontAttributeName
                            value:[UIFont fontWithName:kFontAwesome size:17]
                            range:NSMakeRange(dataSourceTitle.length - 1, 1)];
    [self.dataSourceLabel setAttributedTitle:dataSourceTitle
                                    forState:UIControlStateNormal];
  }
  if ([self hasInternetConnection]) {
    [self performSearch];
  } else {
    [Utility showConnectionErrorOn:self];
  }
}

- (void)viewDidLoad {

  [super viewDidLoad];
  self.tableView.delegate = self;
  self.tableView.dataSource = self;

  self.sortOrder = SORT_DESC;
  self.sortByItem = SORT_BY_RELEVANCE;

  self.panelOpen = YES;
  [self updateConstraintsForMode];

  self.autoComplete = NO;
  self.footerView.hidden = YES;
  self.tableView.contentInset = UIEdgeInsetsMake(-100, 0, -58, 0);
  self.footerView.tintColor = [UIColor lightGrayColor];

  [self.sortOrderButton setAction:@selector(sortOrderChanged:)];
  [self.sortOrderButton setTarget:self];

  [self.sortByButton setAction:@selector(showSortByActionSheet:)];
  [self.sortByButton setTarget:self];

  [self.statusLabel setTitleTextAttributes:[Utility titleTextAttributes]
                                  forState:UIControlStateNormal];
  self.dataSourceLabel.titleLabel.font = [Utility defaultFont];

  [[UITextField
      appearanceWhenContainedInInstancesOfClasses:@[ [UISearchBar class] ]]
      setDefaultTextAttributes:@{
        NSFontAttributeName : [Utility defaultFont],
      }];

  [self.backButton setTitleTextAttributes:[Utility titleTextAttributes]
                                 forState:UIControlStateNormal];

  [self.navigationController.navigationBar
      setTitleTextAttributes:[Utility titleTextAttributes]];

  [self.navigationItem.rightBarButtonItem
      setTitleTextAttributes:[Utility fontAwesomeTitleTextAttributes]
                    forState:UIControlStateNormal];
  self.navigationItem.rightBarButtonItem.title =
      [NSString fontAwesomeIconStringForEnum:FAListAlt];
  [self hideAndDisableFormattingButtons];

  self.searchBar.delegate = self;
  self.searchFilters = [[SearchFilters alloc] init];
  [self.dataSourceLabel setTitle:NSLocalizedString(@"Sensefy", @"Sensefy")
                        forState:UIControlStateNormal];

  self.internetReachability = [Reachability reachabilityForInternetConnection];
  [self.internetReachability startNotifier];

  SensefyAccessToken *token =
      [[SensefyLoginService sharedInstance] getAccessToken];
  if (nil == token) {
    // Get main window reference
    UIWindow *mainWindow =
        (([UIApplication sharedApplication].delegate).window);

    // Create a full-screen subview
    self.welcomeView =
        [[[NSBundle mainBundle] loadNibNamed:@"WelcomeView"
                                       owner:self
                                     options:nil] objectAtIndex:0];
    self.welcomeView.delegate = self;
    self.welcomeView.frame = mainWindow.frame;
    self.welcomeView.center = mainWindow.center;
    // Add the subview to the main window
    [mainWindow addSubview:self.welcomeView];
  }

  // start listening to reachability notification
  [[NSNotificationCenter defaultCenter]
      addObserver:self
         selector:@selector(reachabilityChanged:)
             name:kReachabilityChangedNotification
           object:nil];
  [[NSNotificationCenter defaultCenter]
      addObserver:self
         selector:@selector(dataSourceChanged:)
             name:kSensefyDataSourceChangedNotification
           object:nil];
  [[NSNotificationCenter defaultCenter]
      addObserver:self
         selector:@selector(facetsChanged:)
             name:kSensefyFacetsChangedNotification
           object:nil];

  [[NSNotificationCenter defaultCenter] addObserver:self
                                           selector:@selector(tokenNotFound:)
                                               name:TOKEN_NOT_FOUND_NOTIFICATION
                                             object:nil];

  [[NSNotificationCenter defaultCenter] addObserver:self
                                           selector:@selector(tokenHasExpired:)
                                               name:TOKEN_EXPIRED_NOTIFICATION
                                             object:nil];

  [[NSNotificationCenter defaultCenter] addObserver:self
                                           selector:@selector(tokenRecieved:)
                                               name:TOKEN_RECEIVED_NOTIFICATION
                                             object:nil];

  if ([self hasInternetConnection]) {

    // Check for token expiration. and inform.

  } else {
    [Utility showConnectionErrorOn:self];
  }
  [Utility showEmptyLabelForTableView:self.tableView NibName:kNibSearchTable];

  if (IS_IPAD) {
    self.facetTable.delegate = self;
    self.facetTable.dataSource = self;
    [self arrangeSelectedFacets];
    [self initiateHidePreference];
  }
}

#pragma mark - UISearchBarDelegate

- (void)searchBar:(UISearchBar *)searchBar
    textDidChange:(NSString *)searchText {
  self.autoComplete = YES;
  [self.statusLabel setTitle:@""];
  if ([self hasInternetConnection]) {
    if (![self missingToken]) {
      [self performAutocomplete];
    } else {
      [self handleMissingToken];
      //[Utility showLoginErrorOn:self];
    }

  } else {
    [Utility showConnectionErrorOn:self];
  }
  if ([searchBar.text isEqualToString:@""]) {
    self.footerView.hidden = YES;
    self.datasources = nil;
    self.facets = nil;
    [self hideAndDisableFormattingButtons];
    self.selectedDatasource = 0;
    [self.dataSourceLabel setAttributedTitle:nil forState:UIControlStateNormal];
    [self.dataSourceLabel setTitle:NSLocalizedString(@"Sensefy", @"Sensefy")
                          forState:UIControlStateNormal];
  }
}

- (void)handleMissingToken {
  UIAlertController *alert =
      [UIAlertController alertControllerWithTitle:kInvalidSession
                                          message:kInvalidSessionMessage
                                   preferredStyle:UIAlertControllerStyleAlert];

  UIAlertAction *defaultAction =
      [UIAlertAction actionWithTitle:@"YES"
                               style:UIAlertActionStyleDefault
                             handler:^(UIAlertAction *action) {
                               //[self
                               // performSegueWithIdentifier:@"createAccount"
                               // sender:self];
                               [self loginButtonClicked];

                             }];
  UIAlertAction *cancelAction =
      [UIAlertAction actionWithTitle:@"NO"
                               style:UIAlertActionStyleDefault
                             handler:^(UIAlertAction *action){
                             }];
  [alert addAction:cancelAction];
  [alert addAction:defaultAction];
  [self presentViewController:alert animated:YES completion:nil];
}

- (void)clearSearchView {
  self.sortByItem = SORT_BY_RELEVANCE;
  self.sortOrder = SORT_DESC;
  [self.sortOrderButton setImage:[UIImage imageNamed:@"SortDescending"]];
  [self.searchBar setText:@""];
  self.footerView.hidden = YES;
  [self.statusLabel setTitle:@""];
  self.datasources = nil;
  self.facets = nil;
  [self hideAndDisableFormattingButtons];
  self.selectedDatasource = 0;
  self.documentList = nil;
  [self.tableView reloadData];
  [self.dataSourceLabel setAttributedTitle:nil forState:UIControlStateNormal];
  [self.dataSourceLabel setTitle:NSLocalizedString(@"Sensefy", @"Sensefy")
                        forState:UIControlStateNormal];
  [Utility showEmptyLabelForTableView:self.tableView NibName:kNibSearchTable];
}

- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar {
  [searchBar resignFirstResponder];

  [self.searchFilters removeAllFilters];
  if (self.datasources) {
    FacetItem *dataSource = self.datasources[self.selectedDatasource];
    NSString *filterString = dataSource.filter;
    if (filterString) {
      [self.searchFilters addFilter:filterString];
    }
  }

  if ([self hasInternetConnection]) {
    if (![self missingToken]) {
      NSMutableAttributedString *dataSourceTitle;
      if (self.selectedDatasource == 0) {

        dataSourceTitle = [[NSMutableAttributedString alloc]
            initWithString:[NSString stringWithFormat:
                                         @"%@ %@",
                                         NSLocalizedString(kDataSourceLabelAll,
                                                           kDataSourceLabelAll),
                                         [NSString fontAwesomeIconStringForEnum:
                                                       FAChevronCircleDown]]];
        [dataSourceTitle
            addAttribute:NSFontAttributeName
                   value:[UIFont fontWithName:kFontAwesome size:17]
                   range:NSMakeRange(dataSourceTitle.length - 1, 1)];
        [self.dataSourceLabel setAttributedTitle:dataSourceTitle
                                        forState:UIControlStateNormal];
      } else {
        FacetItem *dataSource = self.datasources[self.selectedDatasource];

        dataSourceTitle = [[NSMutableAttributedString alloc]
            initWithString:[NSString stringWithFormat:
                                         @"%@ %@", dataSource.value,
                                         [NSString fontAwesomeIconStringForEnum:
                                                       FAChevronCircleDown]]];
        [dataSourceTitle
            addAttribute:NSFontAttributeName
                   value:[UIFont fontWithName:kFontAwesome size:17]
                   range:NSMakeRange(dataSourceTitle.length - 1, 1)];
        [self.dataSourceLabel setAttributedTitle:dataSourceTitle
                                        forState:UIControlStateNormal];
      }

      [self performSearch];
    } else {
      [self handleMissingToken];
      //[Utility showLoginErrorOn:self];
    }

  } else {
    [Utility showConnectionErrorOn:self];
  }
}

- (void)performSearch {

  self.autoComplete = NO;

  MBProgressHUD *hud =
      [MBProgressHUD showHUDAddedTo:self.navigationController.view
                           animated:YES];

  [[SensefySearchService sharedInstance]
      searchWithKeyword:self.searchBar.text
                 fields:kSearchFields
                orderBy:self.sortByItem
                  order:self.sortOrder
                   rows:20
                  start:0
                  facet:YES
             clustering:YES
         clusteringSort:YES
             spellcheck:NO
                filters:[self.searchFilters stringWithFilters]
        completionBlock:^(KeywordSearchResult *result, SensefyError *error) {

          [hud hide:YES];
          [hud removeFromSuperview];

          if (nil != error) {
            [self validateAccount];
          } else {

            self.documentList = result.searchResults.documents;
            self.documentCount = [result.searchResults.resultsFound intValue];
            [self.statusLabel
                setTitle:[NSString stringWithFormat:@"%ld %@",
                                                    self.documentCount,
                                                    NSLocalizedString(
                                                        kDocumentsFound,
                                                        kDocumentsFound)]];
            self.facets = [[NSMutableArray alloc] initWithArray:result.facets];

            if (self.facets.count > 0) {

              SensefyFacet *facet = self.facets[0];

              if ([facet.label isEqualToString:kDataSource]) {

                NSMutableArray *datasources = [[NSMutableArray alloc] init];
                FacetItem *datasourceItem = [[FacetItem alloc] init];
                [datasourceItem
                    setValue:NSLocalizedString(kDataSourceLabelAll,
                                               kDataSourceLabelAll)];
                NSNumber *totalItems =
                    [facet.facetItems valueForKeyPath:@"@sum.occurrence"];
                [datasourceItem setOccurrence:totalItems];
                [datasources addObject:datasourceItem];
                [datasources addObjectsFromArray:facet.facetItems];
                [self updateSelectedDatasource:datasources];
                self.datasources = datasources;
                [self.facets removeObjectAtIndex:0];

                self.colorDictionary = [[NSMutableDictionary alloc] init];
                for (int i = 0; i < self.datasources.count; i++) {
                  FacetItem *datasource = self.datasources[i];
                  [self.colorDictionary setValue:kColorsArray[i]
                                          forKey:datasource.value];
                }
              }
            }

            [[NSNotificationCenter defaultCenter]
                postNotificationName:kSensefyUpdateFacetsNotification
                              object:self.facets
                            userInfo:nil];
            [[NSNotificationCenter defaultCenter]
                postNotificationName:kSensefyUpdateDataSourceNotification
                              object:self.datasources
                            userInfo:nil];
            if (IS_IPAD) {
              [self arrangeSelectedFacets];
              [self initiateHidePreference];
              [self.facetTable reloadData];
            }

            if (self.documentList.count == 0) {
              self.footerView.hidden = YES;
              [self hideAndDisableFormattingButtons];
              [Utility showEmptyLabelForTableView:self.tableView
                                          NibName:kNibSearchTable];
            } else {
              if (self.documentCount > self.documentList.count) {
                self.footerView.hidden = NO;
              } else {
                self.footerView.hidden = YES;
              }
              [self showAndEnableFormattingButtons];
              [Utility hideEmptyLabelForTableView:self.tableView];
              [self.tableView reloadData];
            }
            [self.tableView reloadData];
          }
        }];
}

- (void)getMoreSearchresults {

  self.autoComplete = NO;
  if (self.documentList.count < self.documentCount) {
    int skipCount = (int)self.documentList.count;

    [[SensefySearchService sharedInstance]
        searchWithKeyword:self.searchBar.text
                   fields:kSearchFields
                  orderBy:self.sortByItem
                    order:self.sortOrder
                     rows:20
                    start:skipCount
                    facet:YES
               clustering:YES
           clusteringSort:YES
               spellcheck:NO
                  filters:[self.searchFilters stringWithFilters]
          completionBlock:^(KeywordSearchResult *result, SensefyError *error) {
            self.documentList = [self.documentList
                arrayByAddingObjectsFromArray:result.searchResults.documents];

            if (self.documentList.count == 0) {
              self.footerView.hidden = YES;
              [Utility showEmptyLabelForTableView:self.tableView
                                          NibName:kNibSearchTable];
            } else {
              if (self.documentCount > self.documentList.count) {
                self.footerView.hidden = NO;
              } else {
                self.footerView.hidden = YES;
              }
              [Utility hideEmptyLabelForTableView:self.tableView];
              [self.tableView reloadData];
            }
            [self.tableView reloadData];
          }];
  }
}

- (void)performAutocomplete {

  self.autoComplete = YES;

  [[SensefySearchService sharedInstance]
      autoCompleteWithTerm:self.searchBar.text
       numberOfSuggestions:10
                  semantic:YES
           completionBlock:^(AutocompleteResult *result, SensefyError *error) {

             self.suggessions = result.response.suggestions;
             self.footerView.hidden = YES;
             if (self.suggessions.count == 0) {
               [Utility showEmptyLabelForTableView:self.tableView
                                           NibName:kNibSearchTable];
             } else {
               [Utility hideEmptyLabelForTableView:self.tableView];
             }
             [self.tableView reloadData];

           }];
}

- (void)updateSelectedDatasource:(NSArray *)datasources {
  FacetItem *datasource = self.datasources[self.selectedDatasource];

  NSUInteger index = NSNotFound;
  NSPredicate *predicate =
      [NSPredicate predicateWithFormat:@"value MATCHES %@", datasource.value];
  index = [datasources
      indexOfObjectPassingTest:^(id obj, NSUInteger idx, BOOL *stop) {
        return [predicate evaluateWithObject:obj];
      }];
  if (index != NSNotFound) {
    self.selectedDatasource = (int)index;
  }
}

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView
    didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
  if (tableView == self.facetTable) {
    SensefyFacet *facetSection = self.facets[indexPath.section];
    FacetItem *facetItem = facetSection.facetItems[indexPath.row];
    FacetTableViewCell *cell =
        (FacetTableViewCell *)[tableView cellForRowAtIndexPath:indexPath];
    if ([cell.checkMarkLabel.text
            isEqualToString:[NSString
                                fontAwesomeIconStringForEnum:FACheckSquareO]]) {
      [self.searchFilters removeFilter:facetItem.filter];
      cell.checkMarkLabel.text =
          [NSString fontAwesomeIconStringForEnum:FASquareO];
    } else {
      [self.searchFilters addFilter:facetItem.filter];
      cell.checkMarkLabel.text =
          [NSString fontAwesomeIconStringForEnum:FACheckSquareO];
    }
    [[NSNotificationCenter defaultCenter]
        postNotificationName:kSensefyFacetsChangedNotification
                      object:indexPath
                    userInfo:nil];
  } else {
    if (self.autoComplete) {
      self.searchBar.text = self.suggessions[indexPath.row];
      [self searchBarSearchButtonClicked:self.searchBar];
    }
  }
}

- (CGFloat)tableView:(UITableView *)tableView
    heightForRowAtIndexPath:(NSIndexPath *)indexPath {
  if (tableView == self.facetTable) {
    BOOL hidden =
        [[self.hidePreference objectAtIndex:indexPath.section] boolValue];
    if (indexPath.row > kFacetShowCount - 1 && hidden) {
      return 0;
    }
    return kFooterHeight;
  } else {
    if (self.autoComplete) {
      return 44.f;
    } else {
      return 88.f;
    }
  }
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
  if (tableView == self.facetTable) {
    return self.facets.count;
  } else {
    return 1;
  }
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
  if (tableView == self.facetTable) {
    @try {
      SensefyFacet *facetSection = self.facets[section];
      return [facetSection.facetItems count];
    } @catch (NSException *exception) {
      return 0;
    }
  } else {
    if (self.autoComplete) {
      return self.suggessions.count;
    } else {
      return self.documentList.count;
    }
  }
}

- (UITableViewCell *)tableView:(UITableView *)tableView
         cellForRowAtIndexPath:(NSIndexPath *)indexPath {

  if (tableView == self.facetTable) {
    FacetTableViewCell *cell =
        [tableView dequeueReusableCellWithIdentifier:@"FacetTableCell"
                                        forIndexPath:indexPath];

    @try {
      SensefyFacet *facetSection = self.facets[indexPath.section];
      FacetItem *facetItem = facetSection.facetItems[indexPath.row];
      cell.facetLabel.text =
          NSLocalizedString(facetItem.value, facetItem.value);
      cell.occurrencesLabel.text =
          [NSString stringWithFormat:@"%@ ", facetItem.occurrence];
      if ([self.searchFilters hasFilter:facetItem.filter]) {
        cell.checkMarkLabel.text =
            [NSString fontAwesomeIconStringForEnum:FACheckSquareO];
      } else {
        cell.checkMarkLabel.text =
            [NSString fontAwesomeIconStringForEnum:FASquareO];
      }
      BOOL hidden =
          [[self.hidePreference objectAtIndex:indexPath.section] boolValue];
      if (indexPath.row > kFacetShowCount - 1 && hidden) {
        cell.hidden = YES;
      }
    } @catch (NSException *exception) {
    }

    return cell;
  } else {
    if (self.autoComplete) {
      UITableViewCell *cell = [self.tableView
          dequeueReusableCellWithIdentifier:kAutoCompleteCellIdentifier];
      if (nil == cell) {
        cell =
            [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault
                                   reuseIdentifier:kAutoCompleteCellIdentifier];
      }
      cell.textLabel.font = [Utility defaultFont];
      cell.textLabel.text = self.suggessions[indexPath.row];
      return cell;

    } else {
      SearchTableViewCell *cell = (SearchTableViewCell *)[self.tableView
          dequeueReusableCellWithIdentifier:kSearchCellIdentifier
                               forIndexPath:indexPath];

      SensefyDocument *document = self.documentList[indexPath.row];

      if (document.dataSource) {
        NSString *color =
            [self.colorDictionary objectForKey:document.dataSource];
        if (color) {
          [cell.colorLabel
              setBackgroundColor:[Utility colorWithHexString:color]];
          [cell.pathLabel setTextColor:[Utility colorWithHexString:color]];
        } else {
          [cell.colorLabel setBackgroundColor:[UIColor grayColor]];
          [cell.pathLabel setTextColor:[UIColor grayColor]];
        }
      }
      [Utility fontAwesomeIcon:cell.thumbImage forType:document.mimeType];

      if (document.name) {
        [cell.titleLabel setText:document.name];
      } else {
        [cell.titleLabel
            setText:NSLocalizedString(kUntitledDocument, kUntitledDocument)];
      }
      if (document.path) {
        NSMutableAttributedString *attributedString =
            [[NSMutableAttributedString alloc] initWithString:document.path];
        [attributedString addAttribute:NSUnderlineStyleAttributeName
                                 value:@(1)
                                 range:NSMakeRange(0, document.path.length)];
        [cell.pathLabel setAttributedText:attributedString];

      } else {
        [cell.pathLabel setText:NSLocalizedString(kNoPath, kNoPath)];
      }
      if (document.size) {
        [cell.sizeLabel
            setText:[NSString
                        stringWithFormat:@"%@",
                                         [Utility
                                             transformedSizeValue:document
                                                                      .size]]];
      } else {
        [cell.sizeLabel setText:kNoSize];
      }
      if (document.creator) {
        [cell.creatorLabel
            setAttributedText:[Utility
                                  authoredTextWithAuthor:document.creator
                                                    date:document.creationDate
                                                   event:AuCreated]];
      } else {
        [cell.creatorLabel setText:NSLocalizedString(kNoCreator, kNoCreator)];
      }
      if (document.lastModifier) {
        [cell.modifierLabel
            setAttributedText:[Utility
                                  authoredTextWithAuthor:document.lastModifier
                                                    date:document.lastModified
                                                   event:AuModified]];
      } else {
        [cell.modifierLabel
            setText:NSLocalizedString(kNoModifier, kNoModifier)];
      }
      return cell;
    }
  }
}

- (NSString *)tableView:(UITableView *)tableView
titleForHeaderInSection:(NSInteger)section {
  if (tableView == self.facetTable) {
    SensefyFacet *facetSection = self.facets[section];
    return NSLocalizedString(facetSection.label, facetSection.label);
  } else {
    return nil;
  }
}

- (UIView *)tableView:(UITableView *)tableView
    viewForFooterInSection:(NSInteger)section {

  if (tableView == self.facetTable) {
    BOOL hidden = [[self.hidePreference objectAtIndex:section] boolValue];
    SensefyFacet *dictionary = self.facets[section];
    NSInteger facetCount = [dictionary.facetItems count];

    if (hidden) {
      UIView *moreView = [[UIView alloc]
          initWithFrame:CGRectMake(0, 0, CGRectGetWidth(tableView.frame),
                                   kFooterHeight)];
      moreView.autoresizingMask = UIViewAutoresizingFlexibleWidth;
      UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
          initWithTarget:self
                  action:@selector(toggleFacets:)];
      UILabel *moreLabel =
          [[UILabel alloc] initWithFrame:CGRectInset(moreView.bounds, 0, 0)];
      moreLabel.text = @"More";
      moreLabel.font = [Utility defaultFont];
      moreLabel.textAlignment = NSTextAlignmentCenter;
      moreLabel.autoresizingMask = UIViewAutoresizingFlexibleWidth;
      moreLabel.backgroundColor = [UIColor whiteColor];
      moreLabel.textColor = [UIColor appTintColor];
      moreLabel.tag = section;
      [moreView addSubview:moreLabel];

      moreLabel.userInteractionEnabled = YES;
      [moreLabel addGestureRecognizer:tap];

      return moreView;
    } else if (facetCount > kFacetShowCount) {
      UIView *moreView = [[UIView alloc]
          initWithFrame:CGRectMake(0, 0, CGRectGetWidth(tableView.frame),
                                   kFooterHeight)];
      moreView.autoresizingMask = UIViewAutoresizingFlexibleWidth;
      UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
          initWithTarget:self
                  action:@selector(toggleFacets:)];
      UILabel *moreLabel =
          [[UILabel alloc] initWithFrame:CGRectInset(moreView.bounds, 0, 0)];
      moreLabel.text = @"Less";
      moreLabel.font = [Utility defaultFont];
      moreLabel.textAlignment = NSTextAlignmentCenter;
      moreLabel.autoresizingMask = UIViewAutoresizingFlexibleWidth;
      moreLabel.backgroundColor = [UIColor whiteColor];
      moreLabel.textColor = [UIColor appTintColor];
      moreLabel.tag = section;
      [moreView addSubview:moreLabel];

      moreLabel.userInteractionEnabled = YES;
      [moreLabel addGestureRecognizer:tap];

      return moreView;
    } else {
      return nil;
    }
  } else {
    return nil;
  }
}

- (CGFloat)tableView:(UITableView *)tableView
    heightForFooterInSection:(NSInteger)section {
  if (tableView == self.facetTable) {
    BOOL hidden = [[self.hidePreference objectAtIndex:section] boolValue];
    SensefyFacet *dictionary = self.facets[section];
    NSInteger facetCount = [dictionary.facetItems count];
    if (!hidden && facetCount <= kFacetShowCount) {
      return 0;
    }
    return kFooterHeight + kFooterMargin;
  } else {
    return 0;
  }
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView
                  willDecelerate:(BOOL)decelerate {
  NSInteger currentOffset = scrollView.contentOffset.y;
  NSInteger maximumOffset =
      scrollView.contentSize.height - scrollView.frame.size.height;

  if (maximumOffset - currentOffset <= 15) {
    if (!self.autoComplete && scrollView.frame.size.width > 305) {
      [self getMoreSearchresults];
    }
  }
}

- (void)arrangeSelectedFacets {
  NSMutableArray *facets = [[NSMutableArray alloc] init];
  for (SensefyFacet *facet in self.facets) {
    SensefyFacet *facetCopy = [facet copy];
    NSMutableArray *indexArray = [[NSMutableArray alloc] init];
    NSMutableArray *facetItemsArray = [facet.facetItems mutableCopy];
    for (FacetItem *facetItem in facetItemsArray) {
      if ([self.searchFilters hasFilter:facetItem.filter]) {
        NSUInteger index = [facetItemsArray indexOfObject:facetItem];
        if (index != NSNotFound) {
          [indexArray addObject:[NSNumber numberWithInt:(int)index]];
        }
      }
    }
    for (NSNumber *number in indexArray) {
      [facetItemsArray moveObjectFromIndex:number.integerValue toIndex:0];
    }
    facetCopy.facetItems = facetItemsArray;
    [facets addObject:facetCopy];
  }
  self.facets = facets;
}

- (void)toggleFacets:(UIGestureRecognizer *)recognizer {
  // Only respond if we're in the ended state (similar to touchupinside)
  if ([recognizer state] == UIGestureRecognizerStateEnded) {
    // the label that was tapped
    UILabel *label = (UILabel *)[recognizer view];
    NSInteger section = label.tag;
    if ([label.text isEqualToString:NSLocalizedString(@"More", @"More")]) {
      [self.hidePreference replaceObjectAtIndex:section
                                     withObject:[NSNumber numberWithBool:NO]];
      label.text = NSLocalizedString(@"Less", @"Less");
    } else {
      [self.hidePreference replaceObjectAtIndex:section
                                     withObject:[NSNumber numberWithBool:YES]];
      label.text = NSLocalizedString(@"More", @"More");
    }
    [self.facetTable reloadSections:[NSIndexSet indexSetWithIndex:section]
                   withRowAnimation:UITableViewRowAnimationFade];
  }
}

#pragma mark - Navigation

- (BOOL)shouldPerformSegueWithIdentifier:(NSString *)identifier
                                  sender:(id)sender {
  if ([identifier isEqualToString:@"dataSourceSelector"]) {
    if (nil == self.datasources || self.datasources.count == 0) {
      return NO;
    }
  } else if ([identifier isEqualToString:@"facetSelector"]) {
    if (nil == self.facets || self.facets.count == 0) {
      return NO;
    }
    if (IS_IPAD) {
      [self.view layoutIfNeeded];
      [UIView animateWithDuration:.5
                       animations:^{
                         [self updateConstraintsForMode];
                         [self.view layoutIfNeeded];
                       }];
      return NO;
    }
  }
  return YES;
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
  UINavigationController *navigationController =
      segue.destinationViewController;
  if ([[[navigationController viewControllers] objectAtIndex:0]
          isKindOfClass:[SourceSelectController class]]) {
    SourceSelectController *viewController =
        [[navigationController viewControllers] objectAtIndex:0];
    viewController.datasources = self.datasources;
    viewController.selectedIndex = self.selectedDatasource;
    self.sourcePopOverController =
        segue.destinationViewController.popoverPresentationController;
    self.sourcePopOverController.delegate = self;
    self.sourcePopOverController.sourceView = self.dataSourceLabel;
    self.sourcePopOverController.sourceRect = self.dataSourceLabel.frame;

  } else if ([[[navigationController viewControllers] objectAtIndex:0]
                 isKindOfClass:[FacetSelectController class]]) {
    FacetSelectController *viewController =
        [[navigationController viewControllers] objectAtIndex:0];
    viewController.facets = self.facets;
    viewController.filters = self.searchFilters;
  } else if ([[[navigationController viewControllers] objectAtIndex:0]
                 isKindOfClass:[DocumentPreviewController class]]) {
    DocumentPreviewController *viewController =
        [[navigationController viewControllers] objectAtIndex:0];
    SearchTableViewCell *tableViewcell = (SearchTableViewCell *)sender;
    NSIndexPath *indexPath = [self.tableView indexPathForCell:tableViewcell];
    SensefyDocument *document = self.documentList[indexPath.row];
    viewController.document = document;
  } else if ([[[navigationController viewControllers] objectAtIndex:0]
                 isKindOfClass:[AccountViewController class]]) {
    AccountViewController *viewController =
        [[navigationController viewControllers] objectAtIndex:0];
    viewController.delegate = self;
    viewController.internetReachability = self.internetReachability;
  }
}

#pragma mark - Private Functions

- (void)reachabilityChanged:(NSNotification *)notification {
  self.internetReachability = [Reachability reachabilityForInternetConnection];
  NetworkStatus currentState =
      [self.internetReachability currentReachabilityStatus];
  //  if (currentState != NotReachable && self.currentRequest != nil) {
  //    //[self.currentRequest cancel];
  //  }
}

- (BOOL)hasInternetConnection {
  NetworkStatus currentState =
      [self.internetReachability currentReachabilityStatus];
  if (currentState != NotReachable) {
    return YES;
  }
  return NO;
}

- (void)openLoginUI {
  AccountViewController *accountController =
      [[AccountViewController alloc] init];
  accountController.internetReachability = self.internetReachability;
  [self.navigationController presentViewController:accountController
                                          animated:YES
                                        completion:nil];
}

- (void)validateAccount {
  SensefyAccessToken *token =
      [[SensefyLoginService sharedInstance] getAccessToken];
  if (nil != token) {
    BOOL expired = [[SensefyLoginService sharedInstance] tokenExpired];
    if (expired) {
      NSLog(@"Token is expired");
      [self openLoginUI];
    }
  } else {
    NSLog(@"Token not available");
    [self openLoginUI];
  }
}

#pragma mark - Welcome view delegate

- (void)loginButtonClicked {
  [[SensefyLoginService sharedInstance] loginWithSensefy];
}

@end
