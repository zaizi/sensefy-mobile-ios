//
//  SensefyAccessToken.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/12/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SensefyAccessToken : NSObject <NSCoding>

/**
 *  Access token string
 */
@property(nonatomic, copy) NSString *accessToken;

/**
 *  Refresh token string
 */
@property(nonatomic, copy) NSString *refreshToken;

/**
 *  Token type
 */
@property(nonatomic, copy) NSString *tokenType;

/**
 *  Token expiration date
 */
@property(nonatomic, copy) NSDate *expiresAt;

/**
 *  Scope
 */
@property(nonatomic, copy) NSString *scope;

@end
