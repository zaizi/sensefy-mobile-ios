//
//  DocumentPreviewController.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 6/15/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DocumentPreviewController
    : UIViewController <UITableViewDelegate, UITableViewDataSource,
                        UIDocumentInteractionControllerDelegate>

/*!
 * @brief Dictionary with document informatoin
 */
@property(nonatomic, strong) SensefyDocument *document;

@end
