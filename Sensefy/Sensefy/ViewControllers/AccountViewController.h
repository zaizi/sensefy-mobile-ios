//
//  AuthViewController.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 9/1/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol AccountViewControllerDelegate
- (void)clearSearchView;
@end

@interface AccountViewController : UITableViewController

/*!
 * @brief internetReachability Reachability flag
 */
@property(nonatomic, strong) Reachability *internetReachability;
@property(nonatomic, weak) id<AccountViewControllerDelegate> delegate;

@end
