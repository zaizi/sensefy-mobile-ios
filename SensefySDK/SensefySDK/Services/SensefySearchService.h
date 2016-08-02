//
//  SensefySearchService.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/10/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SensefySearchService : NSObject

+ (instancetype)sharedInstance;

/**
 *  Search sensefy documents
 *
 *  @param keyword         Term that will be used to search the documents.
 *  @param fields          Fields that needs to be returned with search results.
 *  @param orderBy         Attribute that will be used to sort the results.
 *  @param order           Ascending or descending order to be used.
 *  @param rows            Limit of the search results.
 *  @param start           Starting index of the results or skip count.
 *  @param facet           Need facets with search results.
 *  @param clustering      Need clustering information with search results.
 *  @param clusteringSort  Enable clustering sort
 *  @param spellcheck      Enable spellcheck
 *  @param filters         Filters that will be used to filter the results.
 *  @param completionBlock Block that will be used to return the results.
 */
- (void)searchWithKeyword:(NSString *)keyword
                   fields:(NSString *)fields
                  orderBy:(SortByItem)orderBy
                    order:(SortOrder)order
                     rows:(int)rows
                    start:(int)start
                    facet:(BOOL)facet
               clustering:(BOOL)clustering
           clusteringSort:(BOOL)clusteringSort
               spellcheck:(BOOL)spellcheck
                  filters:(NSString *)filters
          completionBlock:(SensefySearchCompletionBlock)completionBlock;

/**
 *  Search and get list of autocomplete suggestions.
 *
 *  @param term                Term to complete.
 *  @param numberOfSuggestions Number of suggestions or Limit.
 *  @param semantic            Enable semantic search.
 *  @param completionBlock     Block that will be used to return the results.
 */
- (void)autoCompleteWithTerm:(NSString *)term
         numberOfSuggestions:(int)numberOfSuggestions
                    semantic:(BOOL)semantic
             completionBlock:(SensefyArrayCompletionBlock)completionBlock;

@end
