//
//  FacetTableViewCell.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 6/8/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FacetTableViewCell : UITableViewCell

/*!
 * @brief facetLabel Facet Name
 */
@property (weak, nonatomic) IBOutlet UILabel *facetLabel;
/*!
 * @brief checkMarkLabel Facet selection checkbox
 */
@property (weak, nonatomic) IBOutlet UILabel *checkMarkLabel;
/*!
 * @brief occurrencesLabel No of documents related to facet
 */
@property (weak, nonatomic) IBOutlet UILabel *occurrencesLabel;

@end
