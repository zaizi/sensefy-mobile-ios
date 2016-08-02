//
//  SearchTableView.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 6/30/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface SearchTableView : UIView
/*!
 * @brief titleLabel Title label
 */
@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
/*!
 * @brief detailedLabel Detailed message
 */
@property (weak, nonatomic) IBOutlet UILabel *detailedLabel;
/*!
 * @brief imageView Image view
 */
@property (weak, nonatomic) IBOutlet UIImageView *imageView;

@end
