//
//  FacetItem.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FacetItem : NSObject

/**
 *  Facet item value
 */
@property(nonatomic, copy) NSString *value;

/**
 *  Filter string
 */
@property(nonatomic, copy) NSString *filter;

/**
 *  Number of occurrence
 */
@property(nonatomic) NSNumber *occurrence;

/**
 *  Create a copy of currnt object
 *
 *  @return Copy of the object
 */
- (id)copy;

@end
