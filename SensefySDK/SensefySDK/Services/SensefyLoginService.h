//
//  SensefyAccountService.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/12/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyAccessToken.h"
#import <Foundation/Foundation.h>

@interface SensefyLoginService : NSObject <UIWebViewDelegate>

+ (instancetype)sharedInstance;

/**
 *  Redirect user to Sensefy authentication end point.
 */
- (void)loginWithSensefy;

/**
 *  Logout current user.
 */
- (void)logoutFromSensefy;

/**
 *  Retrieve current access token
 *
 *  @return SensefyAccessToken instance
 */
- (SensefyAccessToken *)getAccessToken;

/**
 *  Handle redirect url recieved from Sensefy.
 *
 *  @param redirectURL recieved url
 */
- (void)handleRedirectURL:(NSURL *)redirectURL;

/**
 *  Check if the token is expired
 *
 *  @return State
 */
- (BOOL)tokenExpired;

@end
