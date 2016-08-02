//
//  SensefyFacet.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "FacetItem.h"
#import <Foundation/Foundation.h>

@interface SensefyFacet : NSObject

/**
 *  Facet label
 */
@property(nonatomic, copy) NSString *label;

/**
 *  Facet items array
 */
@property(nonatomic, strong) NSArray *facetItems;

/**
 *  Create a copy of currnt object
 *
 *  @return Copy of the object
 */
- (id)copy;

@end
