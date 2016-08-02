//
//  SuggestionsResult.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/12/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SuggestionsResult : NSObject

/**
 *  Array of suggestion strings
 */
@property(nonatomic, strong) NSArray *suggestions;

/**
 *  Array of suggested document titles
 */
@property(nonatomic, strong) NSArray *titles;

/**
 *  Number of suggestions
 */
@property(nonatomic) NSNumber *numberOfSuggestions;

@end
