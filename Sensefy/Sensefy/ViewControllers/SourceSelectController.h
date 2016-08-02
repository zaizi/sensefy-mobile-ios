//
//  SourceSelectController.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 6/5/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface SourceSelectController : UITableViewController

/*!
 * @brief datasources Array of datasources
 */
@property (nonatomic, strong) NSArray *datasources;

/*!
 * @brief selectedIndex Selected datasource index
 */
@property int selectedIndex;

@end
