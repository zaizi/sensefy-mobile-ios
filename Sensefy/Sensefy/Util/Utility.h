//
//  Utility.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 5/28/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "MBProgressHUD.h"
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

typedef NS_ENUM(NSInteger, AuEvent) { AuCreated, AuModified };

@interface Utility : NSObject

/*!
 * @discussion Show empty message for tableview if no any items avalable for
 * showing.
 * @param tableView Tableview reference
 * @param message Message to show
 */
+ (void)showEmptyLabelForTableView:(UITableView *)tableView
                           NibName:(NSString *)nib;
/*!
 * @discussion Hide empty label for tableview if there are items available for
 * showing.
 * @param tableView Tableview reference
 */
+ (void)hideEmptyLabelForTableView:(UITableView *)tableView;
/*!
 * @discussion Get transformed size value for document. Input should be given in
 * bytes. Output will be in conventional format "bytes", "KB", "MB", "GB",
 * "TB".
 * @param value Document size in bytes
 * @return id Document size in conventional format
 */
+ (id)transformedSizeValue:(id)value;
/*!
 * @discussion Get UIColor from hex code
 * @param hex Hex color code
 * @return UIColor with respect to hex code
 */
+ (UIColor *)colorWithHexString:(NSString *)hex;
/*!
 * @discussion Save file in documents directory and return url
 * @param fileName Name for the file
 * @param data NSData to be saved to file
 * @return File url
 */
+ (NSURL *)saveAndReturnFile:(NSString *)fileName ForData:(NSData *)data;
/*!
 * @discussion Get FontAwesome icon for mime type.
 * @param mimeType Type of document
 * @return FontAwesome text
 */

+ (void)fontAwesomeIcon:(UILabel *)label forType:(NSString *)mimeType;
/**
 *  Attributed text for creatod and modified events
 *
 *  @param author Creator or Modifier
 *  @param date   Event date
 *  @param event  Event type
 *
 *  @return Attributed string
 */
+ (NSAttributedString *)authoredTextWithAuthor:(NSString *)author
                                          date:(NSDate *)date
                                         event:(AuEvent)event;
/*!
 * @discussion Attributed text for creatod and modified events
 * @param author Creator or Modifier
 * @param date Event date
 * @return Attributed string
 */
+ (NSAttributedString *)authoredTextWithAuthor:(NSString *)author
                                    dateString:(NSString *)date
                                         event:(AuEvent)event;
/*!
 * @discussion Get default text attributes for AttributedString
 * @return Dictionary with attributes
 */
+ (NSDictionary *)titleTextAttributes;
/*!
 * @discussion Get default font as a UIFont
 * @return UIFont object with default font
 */
+ (UIFont *)defaultFont;
/*!
 * @discussion Get fontAwesome text attributes
 * @return Dictionary with attributes
 */
+ (NSDictionary *)fontAwesomeTitleTextAttributes;
/*!
 * @discussion Get fontAwesome font as a UIFont
 * @return UIFont object with fontAwesome
 */
+ (UIFont *)fontAwesomeFont;
/*!
 * @discussion Display MBProgressHUD connection success view on controller
 * @param controller View controller
 * @return MBProgressHUD instance
 */
+ (MBProgressHUD *)showConnectionSuccessOn:(UIViewController *)controller;
/*!
 * @discussion Display MBProgressHUD connection failed view on controller
 * @param controller View controller
 * @return MBProgressHUD instance
 */
+ (MBProgressHUD *)showConnectionFailedOn:(UIViewController *)controller;
/*!
 * @discussion Display MBProgressHUD connection error view on controller
 * @param controller View controller
 * @return MBProgressHUD instance
 */
+ (MBProgressHUD *)showConnectionErrorOn:(UIViewController *)controller;
/*!
 * @discussion Display MBProgressHUD login error view on controller
 * @param controller View controller
 * @return MBProgressHUD instance
 */
+ (MBProgressHUD *)showLoginErrorOn:(UIViewController *)controller;

@end
