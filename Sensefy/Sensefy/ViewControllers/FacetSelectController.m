//
//  FacetSelectController.m
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 6/8/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "Constants.h"
#import "FacetSelectController.h"
#import "FacetTableViewCell.h"
#import "NSMutableArray+Custom.h"
#import "NSString+FontAwesome.h"
#import "UIColor+Custom.h"
#import "Utility.h"

@interface FacetSelectController ()

@property NSMutableArray *hidePreference;
@property BOOL missingToken;

@end

int const kDefaultFacetShowCount = 3;
CGFloat const kDefaultFooterHeight = 44.0f;
CGFloat const kDefaultFooterMargin = 10.0f;

@implementation FacetSelectController

- (void)viewDidLoad {
  [super viewDidLoad];
  [self.navigationItem.rightBarButtonItem
      setTitleTextAttributes:[Utility titleTextAttributes]
                    forState:UIControlStateNormal];
  [self.navigationController.navigationBar
      setTitleTextAttributes:[Utility titleTextAttributes]];

  [[NSNotificationCenter defaultCenter]
      addObserver:self
         selector:@selector(facetsChanged:)
             name:kSensefyUpdateFacetsNotification
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
  if (IS_IPAD) {
    [self.navigationController setNavigationBarHidden:YES];
  }
  [self arrangeSelectedFacets];
  [self initiateHidePreference];
  self.navigationItem.title = NSLocalizedString(@"Facets", @"Facets");
  [self.tableView reloadData];
}

- (void)tokenNotFound:(NSNotification *)notification {
  NSLog(@"You are not logged in");
  self.missingToken = YES;
}
- (void)tokenHasExpired:(NSNotification *)notification {
  NSLog(@"You are not logged in");
  self.missingToken = YES;
}
- (void)tokenRecieved:(NSNotification *)notification {
  NSLog(@"You are logged in");
  self.missingToken = NO;
}

- (void)facetsChanged:(NSNotification *)notiofiocation {
  self.facets = (NSArray *)notiofiocation.object;
  [self arrangeSelectedFacets];
  [self initiateHidePreference];
  [self.tableView reloadData];
}

- (void)viewWillAppear:(BOOL)animated {
  [super viewWillAppear:animated];
  self.preferredContentSize = self.tableView.contentSize;
}

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

- (void)initiateHidePreference {
  self.hidePreference = [[NSMutableArray alloc] init];
  for (SensefyFacet *dictionary in self.facets) {
    NSInteger facetCount = [dictionary.facetItems count];
    [self.hidePreference
        addObject:[NSNumber
                      numberWithBool:(facetCount > kDefaultFacetShowCount)]];
  }
}

- (void)arrangeSelectedFacets {
  NSMutableArray *facets = [[NSMutableArray alloc] init];
  for (SensefyFacet *dictionary in self.facets) {
    SensefyFacet *facetCopy = [dictionary copy];
    NSMutableArray *indexArray = [[NSMutableArray alloc] init];
    NSMutableArray *facetItems = [dictionary.facetItems mutableCopy];
    for (FacetItem *facet in facetItems) {
      if ([self.filters hasFilter:facet.filter]) {
        NSUInteger index = [facetItems indexOfObject:facet];
        if (index != NSNotFound) {
          [indexArray addObject:[NSNumber numberWithInt:(int)index]];
        }
      }
    }
    for (NSNumber *number in indexArray) {
      [facetItems moveObjectFromIndex:number.integerValue toIndex:0];
    }

    facetCopy.facetItems = facetItems;
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
    [self.tableView reloadSections:[NSIndexSet indexSetWithIndex:section]
                  withRowAnimation:UITableViewRowAnimationFade];
  }
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
  // Return the number of sections.
  return self.facets.count;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
  // Return the number of rows in the section.
  SensefyFacet *facetSection = self.facets[section];
  return [facetSection.facetItems count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView
         cellForRowAtIndexPath:(NSIndexPath *)indexPath {

  FacetTableViewCell *cell =
      [tableView dequeueReusableCellWithIdentifier:@"FacetTableCell"
                                      forIndexPath:indexPath];

  SensefyFacet *facetSection = self.facets[indexPath.section];
  FacetItem *facetItem = facetSection.facetItems[indexPath.row];
  cell.facetLabel.text = NSLocalizedString(facetItem.value, facetItem.value);
  cell.occurrencesLabel.text =
      [NSString stringWithFormat:@"%@ ", facetItem.occurrence];
  if ([self.filters hasFilter:facetItem.filter]) {
    cell.checkMarkLabel.text =
        [NSString fontAwesomeIconStringForEnum:FACheckSquareO];
  } else {
    cell.checkMarkLabel.text =
        [NSString fontAwesomeIconStringForEnum:FASquareO];
  }
  BOOL hidden =
      [[self.hidePreference objectAtIndex:indexPath.section] boolValue];
  if (indexPath.row > kDefaultFacetShowCount - 1 && hidden) {
    cell.hidden = YES;
  }

  return cell;
}
- (IBAction)doneButtonAction:(id)sender {
  [self dismissViewControllerAnimated:YES completion:nil];
}

- (void)tableView:(UITableView *)tableView
    didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
  SensefyFacet *facetSection = self.facets[indexPath.section];
  FacetItem *facetItem = facetSection.facetItems[indexPath.row];
  FacetTableViewCell *cell =
      (FacetTableViewCell *)[tableView cellForRowAtIndexPath:indexPath];
  if ([cell.checkMarkLabel.text
          isEqualToString:[NSString
                              fontAwesomeIconStringForEnum:FACheckSquareO]]) {
    [self.filters removeFilter:facetItem.filter];
    cell.checkMarkLabel.text =
        [NSString fontAwesomeIconStringForEnum:FASquareO];
  } else {
    [self.filters addFilter:facetItem.filter];
    cell.checkMarkLabel.text =
        [NSString fontAwesomeIconStringForEnum:FACheckSquareO];
  }
  [[NSNotificationCenter defaultCenter]
      postNotificationName:kSensefyFacetsChangedNotification
                    object:indexPath
                  userInfo:nil];
}

- (NSString *)tableView:(UITableView *)tableView
titleForHeaderInSection:(NSInteger)section {
  SensefyFacet *facetSection = self.facets[section];
  return NSLocalizedString(facetSection.label, facetSection.label);
}

- (CGFloat)tableView:(UITableView *)tableView
    heightForRowAtIndexPath:(NSIndexPath *)indexPath {
  BOOL hidden =
      [[self.hidePreference objectAtIndex:indexPath.section] boolValue];
  if (indexPath.row > kDefaultFacetShowCount - 1 && hidden) {
    return 0;
  }
  return kDefaultFooterHeight;
}

- (UIView *)tableView:(UITableView *)tableView
    viewForFooterInSection:(NSInteger)section {
  BOOL hidden = [[self.hidePreference objectAtIndex:section] boolValue];
  SensefyFacet *dictionary = self.facets[section];
  NSInteger facetCount = [dictionary.facetItems count];

  if (hidden) {
    UIView *moreView = [[UIView alloc]
        initWithFrame:CGRectMake(0, 0, CGRectGetWidth(tableView.frame),
                                 kDefaultFooterHeight)];
    moreView.autoresizingMask = UIViewAutoresizingFlexibleWidth;
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
        initWithTarget:self
                action:@selector(toggleFacets:)];
    UILabel *moreLabel =
        [[UILabel alloc] initWithFrame:CGRectInset(moreView.bounds, 0, 0)];
    moreLabel.text = NSLocalizedString(@"More", @"More");
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
  } else if (facetCount > kDefaultFacetShowCount) {
    UIView *moreView = [[UIView alloc]
        initWithFrame:CGRectMake(0, 0, CGRectGetWidth(tableView.frame),
                                 kDefaultFooterHeight)];
    moreView.autoresizingMask = UIViewAutoresizingFlexibleWidth;
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
        initWithTarget:self
                action:@selector(toggleFacets:)];
    UILabel *moreLabel =
        [[UILabel alloc] initWithFrame:CGRectInset(moreView.bounds, 0, 0)];
    moreLabel.text = NSLocalizedString(@"Less", @"Less");
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
}

- (CGFloat)tableView:(UITableView *)tableView
    heightForFooterInSection:(NSInteger)section {
  BOOL hidden = [[self.hidePreference objectAtIndex:section] boolValue];
  SensefyFacet *dictionary = self.facets[section];
  NSInteger facetCount = [dictionary.facetItems count];
  if (!hidden && facetCount <= kDefaultFacetShowCount) {
    return 0;
  }
  return kDefaultFooterHeight + kDefaultFooterMargin;
}

@end
