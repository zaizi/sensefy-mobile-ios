//
//  SearchTableViewCell.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 5/29/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface SearchTableViewCell : UITableViewCell
/*!
 * @brief colorLabel Color strip indicationg the data source
 */
@property (weak, nonatomic) IBOutlet UILabel *colorLabel;
/*!
 * @brief thumbImage Document thumbnail image
 */
@property (weak, nonatomic) IBOutlet UILabel *thumbImage;
/*!
 * @brief titleLabel Document title label
 */
@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
/*!
 * @brief authorLabel Document author label
 */
@property (weak, nonatomic) IBOutlet UILabel *creatorLabel;
/*!
 * @brief authorLabel Document author label
 */
@property (weak, nonatomic) IBOutlet UILabel *modifierLabel;
/*!
 * @brief sizeLabel Document size label
 */
@property (weak, nonatomic) IBOutlet UILabel *sizeLabel;
/*!
 * @brief descriptionLabel Document description label
 */
@property (weak, nonatomic) IBOutlet UILabel *pathLabel;
/*!
 * @brief similaSearchButton Open similar search button
 */
@property (weak, nonatomic) IBOutlet UIButton *similaSearchButton;


@end
