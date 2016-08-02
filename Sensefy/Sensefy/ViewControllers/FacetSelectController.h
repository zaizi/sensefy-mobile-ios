//
//  FacetSelectController.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 6/8/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "SearchFilters.h"
#import <UIKit/UIKit.h>

@interface FacetSelectController : UITableViewController

/*!
 * @brief facets Array of facets
 */
@property(nonatomic, strong) NSArray *facets;
/*!
 * @brief filters Selected filter dictionary
 */
@property(nonatomic, strong) SearchFilters *filters;

@end
