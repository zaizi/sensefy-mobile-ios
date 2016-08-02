//
//  SensefyUtils.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/7/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SensefyUtils : NSObject

/**
 *  Generate URL using Base URL, API path and Query string.
 *
 *  @param baseURL      Server host
 *  @param extensionURL API path
 *  @param queryString  Query parameters
 *
 *  @return Generated URL
 */
+ (NSURL *)createURLFromBaseURLString:(NSString *)baseURL
                         extensionURL:(NSString *)extensionURL
                          queryString:(NSString *)queryString;

/**
 *  Generate query string using parameter pairs
 *
 *  @param parameters Parameter dictionary
 *
 *  @return Generated query string
 */
+ (NSString *)stringWithEncodedQueryParameters:(NSDictionary *)parameters;

/**
 *  Extract query parameter from URL
 *
 *  @param key Parameter name
 *  @param url URL
 *
 *  @return Value of the parameter
 */
+ (NSString *)valueForKey:(NSString *)key fromURL:(NSURL *)url;

/**
 *  Encode string using URLHostAllowedCharacters
 *
 *  @param string String to encode
 *
 *  @return Encoded string
 */
+ (NSString *)encodedString:(NSString *)string;

@end
