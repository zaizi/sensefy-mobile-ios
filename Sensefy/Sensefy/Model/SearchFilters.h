//
//  SearchFilters.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/14/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SearchFilters : NSObject

/*!
 * @brief filters Array of filters
 */
@property(nonatomic, strong, readonly) NSArray *filters;

/*!
 * @discussion Initialize filters array
 * @return Instance of filters
 */
- (instancetype)init;

/*!
 * @discussion Add filter to array
 * @param filter Name of filter
 */
- (void)addFilter:(NSString *)filter;

/*!
 * @discussion Check if filter is already available
 * @param filter Name of filter
 * @return Return if filter is available
 */
- (BOOL)hasFilter:(NSString *)filter;

/*!
 * @discussion Remove a filter from array
 * @param filter Name of filter
 */
- (void)removeFilter:(NSString *)filter;

/*!
 * @discussion Remove all filters from array
 */
- (void)removeAllFilters;

/*!
 * @discussion Generate a comma seperated string with filters
 * @return Comma seperated string with filters
 */
- (NSString *)stringWithFilters;

@end
