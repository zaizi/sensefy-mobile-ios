//
//  AutocompleteSearchResult.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/12/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "ResponseHeader.h"
#import "SuggestionsResult.h"
#import <Foundation/Foundation.h>

@interface AutocompleteResult : NSObject

/**
 *  List of strings with suggestions
 */
@property(nonatomic) SuggestionsResult *response;

/**
 *  Error message if available
 */
@property(nonatomic, copy) NSString *error;

/**
 *  Response header
 */
@property(nonatomic) ResponseHeader *header;

@end
