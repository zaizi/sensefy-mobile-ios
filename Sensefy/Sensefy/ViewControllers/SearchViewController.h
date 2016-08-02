//
//  ViewController.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 5/27/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "MBProgressHUD.h"
#import "WelcomeView.h"
#import <UIKit/UIKit.h>

@interface SearchViewController
    : UIViewController <UITableViewDataSource, UITableViewDelegate,
                        UIPopoverPresentationControllerDelegate,
                        MBProgressHUDDelegate, AccountViewControllerDelegate,
                        WelcomeViewDelegate>

@end
