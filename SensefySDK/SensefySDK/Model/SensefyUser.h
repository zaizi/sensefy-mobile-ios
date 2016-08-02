//
//  SensefyUser.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SensefyUser : NSObject <NSCoding>

/**
 *  Username of the user
 */
@property(nonatomic, copy) NSString *username;

/**
 *  Timezone of the user
 */
@property(nonatomic, copy) NSString *timezone;

/**
 *  Whether account is expired or not
 */
@property(nonatomic) NSNumber *accountNonExpired;

/**
 *  Whether account is locked or not
 */
@property(nonatomic) NSNumber *accountNonLocked;

/**
 *  Whether account credentials are expired or not
 */
@property(nonatomic) NSNumber *credentialsNonExpired;

/**
 *  Whether account is enabled
 */
@property(nonatomic) NSNumber *enabled;

@end
