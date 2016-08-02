//
//  AuthViewController.m
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 9/1/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "AccountViewController.h"
#import "Utility.h"

@interface AccountViewController ()

@property(weak, nonatomic) IBOutlet UILabel *lblUsername;
@property(weak, nonatomic) IBOutlet UIButton *btnAuthentication;
@property(nonatomic) BOOL isLoggedIn;
@property BOOL missingToken;

@end

@implementation AccountViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  // Hide navigation bar in iPad
  if (IS_IPAD) {
    [self.navigationController setNavigationBarHidden:YES];
  }

  if ([self hasInternetConnection]) {
    [self displayUserInformation];
  } else {
    [Utility showConnectionErrorOn:self];
  }

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

- (void)displayUserInformation {
  SensefyUser *user = [[SensefyUserService sharedInstance] getUser];

  if (nil != user) {
    self.isLoggedIn = YES;
    self.lblUsername.text = user.username;
    [self.btnAuthentication setTitle:NSLocalizedString(@"Logout", @"Logout")
                            forState:UIControlStateNormal];
    [self.btnAuthentication setTintColor:[UIColor redColor]];
  } else {
    self.isLoggedIn = NO;
    self.lblUsername.text = NSLocalizedString(@"Anonymous", @"Anonymous");
    [self.btnAuthentication setTitle:NSLocalizedString(@"Login", @"Login")
                            forState:UIControlStateNormal];
    [self.btnAuthentication setTintColor:[UIColor blueColor]];
  }
}

- (IBAction)loginButtonClicked:(id)sender {
  if (self.isLoggedIn) {

    self.isLoggedIn = NO;
    self.lblUsername.text = NSLocalizedString(@"Anonymous", @"Anonymous");
    [self.btnAuthentication setTitle:NSLocalizedString(@"Login", @"Login")
                            forState:UIControlStateNormal];
    [self.btnAuthentication setTintColor:[UIColor blueColor]];
    [self.delegate clearSearchView];
    [[SensefyLoginService sharedInstance] logoutFromSensefy];

  } else {
    [[SensefyLoginService sharedInstance] loginWithSensefy];
    [self dismissViewControllerAnimated:YES completion:nil];
  }
}

- (IBAction)closeAccountController:(id)sender {
  [self dismissViewControllerAnimated:YES completion:nil];
}

#pragma mark - Private Functions

- (BOOL)hasInternetConnection {
  NetworkStatus currentState =
      [self.internetReachability currentReachabilityStatus];
  if (currentState != NotReachable) {
    return YES;
  }
  return NO;
}

@end
