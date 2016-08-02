//
//  SourceTableViewCell.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 6/4/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface SourceTableViewCell : UITableViewCell
/*!
 * @brief dataSourceLabel Data source name
 */
@property (weak, nonatomic) IBOutlet UILabel *dataSourceLabel;
/*!
 * @brief documentCountLabel No of documents related to data source
 */
@property (weak, nonatomic) IBOutlet UILabel *documentCountLabel;
/*!
 * @brief checkMarkLabel Data source selection checkbox
 */
@property (weak, nonatomic) IBOutlet UILabel *checkMarkLabel;

@end
