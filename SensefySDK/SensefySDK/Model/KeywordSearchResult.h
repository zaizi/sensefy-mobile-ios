//
//  KeywordSearchResult.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "ResponseHeader.h"
#import "SearchResult.h"
#import <Foundation/Foundation.h>

@interface KeywordSearchResult : NSObject

/**
 *  Array of Facets
 */
@property(nonatomic, strong) NSArray *facets;

/**
 *  Search results
 */
@property(nonatomic) SearchResult *searchResults;

/**
 *  Error if available
 */
@property(nonatomic, copy) NSString *error;

/**
 *  Response header
 */
@property(nonatomic) ResponseHeader *header;

@end
