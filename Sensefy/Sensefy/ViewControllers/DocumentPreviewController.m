//
//  DocumentPreviewController.m
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 6/15/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "Constants.h"
#import "DocumentPreviewController.h"
#import "Utility.h"

@interface DocumentPreviewController ()
@property(weak, nonatomic) IBOutlet UITableView *tableView;
@property(nonatomic, strong) NSMutableArray *tableData;
@property(nonatomic, strong) NSDateFormatter *dateFormatter;
@property(weak, nonatomic) IBOutlet UIBarButtonItem *openButton;
@property(nonatomic, strong)
    UIDocumentInteractionController *documentController;

@property BOOL missingToken;

@end

NSString *const kPropertyCellIdentifier = @"PropertyCell";

@implementation DocumentPreviewController

- (IBAction)openDocument:(id)sender {

  [[SensefyDocumentService sharedInstance] openDocument:self.document];
}

- (void)viewDidLoad {
  [super viewDidLoad];

  [self.openButton setTitleTextAttributes:[Utility titleTextAttributes]
                                 forState:UIControlStateNormal];
  [self.openButton setTitle:NSLocalizedString(kOpenDocument, kOpenDocument)];

  self.navigationItem.title =
      NSLocalizedString(kDocumentDetails, kDocumentDetails);

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

  self.dateFormatter = [[NSDateFormatter alloc] init];
  self.tableView.delegate = self;
  self.tableView.dataSource = self;
  self.tableData = [[NSMutableArray alloc] init];
  if (self.document) {
    NSArray *keys = [kMetaData componentsSeparatedByString:@","];
    for (NSString *key in keys) {
      if ([self.document valueForKey:key]) {
        [self.tableData addObject:key];
      }
    }
    [self.tableView reloadData];
  }
  // Do any additional setup after loading the view.
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

- (void)previewDocument {
  //  SensefyDocumentService *documentService =
  //      [[SensefyDocumentService alloc] initWithSession:self.session];
  //  [documentService
  //      previewDocument:[self.document valueForKey:kMetaDataID]
  //      completionBlock:^(SensefyDocument *document, NSError *error) {
  //        NSString *dataString = document.content;
  //        NSData *documentData =
  //            [dataString dataUsingEncoding:NSUTF8StringEncoding];
  //        NSURL *url =
  //            [Utility saveAndReturnFile:@"SensefyDocument"
  //            ForData:documentData];
  //        self.documentController =
  //            [UIDocumentInteractionController
  //            interactionControllerWithURL:url];
  //        [self.documentController setDelegate:self];
  //        [self.documentController presentPreviewAnimated:YES];
  //      }];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
  return 1;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
  return self.tableData.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView
         cellForRowAtIndexPath:(NSIndexPath *)indexPath {

  UITableViewCell *cell = [self.tableView
      dequeueReusableCellWithIdentifier:kPropertyCellIdentifier];
  if (nil == cell) {
    cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleValue1
                                  reuseIdentifier:kPropertyCellIdentifier];
  }
  cell.textLabel.font = [Utility defaultFont];
  cell.detailTextLabel.font = [Utility defaultFont];
  cell.textLabel.text =
      NSLocalizedString(self.tableData[indexPath.row], self.tableData[indexPath.row]);
  if (nil != [self.document valueForKey:self.tableData[indexPath.row]]) {
    if ([[self.document valueForKey:self.tableData[indexPath.row]]
            isKindOfClass:[NSArray class]]) {
      NSArray *array =
          [self.document valueForKey:self.tableData[indexPath.row]];
      cell.detailTextLabel.text = [array objectAtIndex:0];
    } else if ([[self.document valueForKey:self.tableData[indexPath.row]]
                   isKindOfClass:[NSDate class]]) {
      if ([self.tableData[indexPath.row]
              isEqualToString:kMetaDataCreationDate] ||
          [self.tableData[indexPath.row]
              isEqualToString:kMetaDataModifiedDate]) {
        NSDate *date =
            [self.document valueForKey:self.tableData[indexPath.row]];

        cell.detailTextLabel.text =
            [NSDateFormatter localizedStringFromDate:date
                                           dateStyle:NSDateFormatterFullStyle
                                           timeStyle:NSDateFormatterNoStyle];
      }
    } else if ([[self.document valueForKey:self.tableData[indexPath.row]]
                   isKindOfClass:[NSString class]]) {

      if ([self.tableData[indexPath.row] isEqualToString:kMetaDataSize]) {
        cell.detailTextLabel.text = [NSString
            stringWithFormat:
                @"%@",
                [Utility transformedSizeValue:
                             [self.document
                                 valueForKey:self.tableData[indexPath.row]]]];
      } else {
        cell.detailTextLabel.text = NSLocalizedString(
            [self.document valueForKey:self.tableData[indexPath.row]],
            [self.document valueForKey:self.tableData[indexPath.row]]);
      }

    } else {
      cell.detailTextLabel.text = kNotAvailable;
    }
  } else {
    cell.detailTextLabel.text = kNotAvailable;
  }

  return cell;
}
- (IBAction)dismissViewController:(id)sender {
  [self dismissViewControllerAnimated:YES completion:nil];
}

- (void)didReceiveMemoryWarning {
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

@end
