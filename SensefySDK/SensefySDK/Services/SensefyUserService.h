//
//  SensefyUserService.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/14/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyUser.h"
#import <Foundation/Foundation.h>

@interface SensefyUserService : NSObject

+ (instancetype)sharedInstance;

/**
 *  Get current user
 *
 *  @return Sensefy user instance
 */
- (SensefyUser *)getUser;

/**
 *  Retrieve and save user details from server. By default called on each
 * successful login.
 */
- (void)updateCurrnetUserDetails;

/**
 *  Remove current user.
 */
- (void)removeCurrentUser;

@end
