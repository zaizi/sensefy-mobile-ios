//
//  SensefyUtils.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/7/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyAccessToken.h"
#import "SensefyUtils.h"

@implementation SensefyUtils

+ (NSURL *)createURLFromBaseURLString:(NSString *)baseURL
                         extensionURL:(NSString *)extensionURL
                          queryString:(NSString *)queryString {

  NSMutableString *mutableRequestString = [NSMutableString string];
  if ([baseURL hasSuffix:@"/"] && [extensionURL hasPrefix:@"/"]) {
    [mutableRequestString
        appendString:[baseURL substringToIndex:baseURL.length - 1]];
    [mutableRequestString appendString:extensionURL];
  } else {
    NSString *separator =
        ([baseURL hasSuffix:@"/"] || [extensionURL hasPrefix:@"/"]) ? @""
                                                                    : @"/";
    [mutableRequestString appendString:baseURL];
    [mutableRequestString appendString:separator];
    [mutableRequestString appendString:extensionURL];
  }
  if (nil != queryString) {
    [mutableRequestString
        appendString:[NSString stringWithFormat:@"?%@", queryString]];
  }
  return [NSURL URLWithString:mutableRequestString];
}

+ (NSString *)stringWithEncodedQueryParameters:(NSDictionary *)parameters {

  NSMutableArray *parameterPairs = [NSMutableArray array];
  for (NSString *key in [parameters allKeys]) {
    NSString *pair = [NSString
        stringWithFormat:@"%@=%@", [self encodedString:key],
                         [self encodedString:[parameters objectForKey:key]]];
    [parameterPairs addObject:pair];
  }
  return [parameterPairs componentsJoinedByString:@"&"];
}

+ (NSString *)valueForKey:(NSString *)key fromURL:(NSURL *)url {

  NSURLComponents *urlComponents =
      [NSURLComponents componentsWithURL:url resolvingAgainstBaseURL:NO];
  NSArray *queryItems = urlComponents.queryItems;

  NSPredicate *predicate = [NSPredicate predicateWithFormat:@"name=%@", key];
  NSURLQueryItem *queryItem =
      [[queryItems filteredArrayUsingPredicate:predicate] firstObject];
  return queryItem.value;
}

#pragma mark URLEncoding

+ (NSString *)encodedString:(NSString *)string {

  return [string stringByAddingPercentEncodingWithAllowedCharacters:
                     [NSCharacterSet URLHostAllowedCharacterSet]];
}
@end
