//
//  SearchResults.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SearchResult : NSObject

/**
 *  Array of documents
 */
@property(nonatomic, strong) NSArray *documents;

/**
 *  Number of results found
 */
@property(nonatomic) NSNumber *resultsFound;

/**
 *  Skip count or the starting index
 */
@property(nonatomic) NSNumber *startIndex;

@end
