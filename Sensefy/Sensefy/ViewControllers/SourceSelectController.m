//
//  SourceSelectController.m
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 6/5/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "Constants.h"
#import "NSString+FontAwesome.h"
#import "SourceSelectController.h"
#import "SourceTableViewCell.h"
#import "Utility.h"

@interface SourceSelectController ()

@property BOOL missingToken;

@end

@implementation SourceSelectController

- (void)viewDidLoad {
  [super viewDidLoad];
  // Set navigation bar font
  [self.navigationItem.rightBarButtonItem
      setTitleTextAttributes:[Utility titleTextAttributes]
                    forState:UIControlStateNormal];

  [self.navigationController.navigationBar
      setTitleTextAttributes:[Utility titleTextAttributes]];
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
  [[NSNotificationCenter defaultCenter]
      addObserver:self
         selector:@selector(dataSourceChanged:)
             name:kSensefyUpdateDataSourceNotification
           object:nil];
  // Hide navigation bar in iPad
  if (IS_IPAD) {
    [self.navigationController setNavigationBarHidden:YES];
  }
  // Set navigation bar title
  self.navigationItem.title = NSLocalizedString(@"Data source", @"Data source");
  // Load table data
  [self.tableView reloadData];
}

- (void)dataSourceChanged:(NSNotification *)notiofiocation {
  self.datasources = (NSArray *)notiofiocation.object;
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

- (void)viewWillAppear:(BOOL)animated {
  [super viewWillAppear:animated];
  self.preferredContentSize = self.tableView.contentSize;
}

- (IBAction)doneButtonClicked:(id)sender {
  [self dismissViewControllerAnimated:YES completion:nil];
}

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
  return 1;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
  return self.datasources.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView
         cellForRowAtIndexPath:(NSIndexPath *)indexPath {
  SourceTableViewCell *cell =
      [tableView dequeueReusableCellWithIdentifier:@"DataSourceCell"
                                      forIndexPath:indexPath];
  FacetItem *datasource = self.datasources[indexPath.row];
  cell.dataSourceLabel.text =
      NSLocalizedString(datasource.value, datasource.value);
  cell.documentCountLabel.text =
      [NSString stringWithFormat:@"%@ ", datasource.occurrence];
  if (self.selectedIndex == indexPath.row) {
    cell.checkMarkLabel.text =
        [NSString fontAwesomeIconStringForEnum:FADotCircleO];

  } else {
    cell.checkMarkLabel.text =
        [NSString fontAwesomeIconStringForEnum:FACircleO];
  }

  return cell;
}

- (void)tableView:(UITableView *)tableView
    didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
  self.selectedIndex = (int)indexPath.row;
  [self.tableView reloadData];
  [[NSNotificationCenter defaultCenter]
      postNotificationName:kSensefyDataSourceChangedNotification
                    object:[NSNumber numberWithInt:self.selectedIndex]
                  userInfo:nil];
}

@end
