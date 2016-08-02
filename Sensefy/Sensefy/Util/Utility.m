//
//  Utility.m
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 5/28/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "Constants.h"
#import "NSString+FontAwesome.h"
#import "SearchTableView.h"
#import "Utility.h"

@implementation Utility

+ (void)showEmptyLabelForTableView:(UITableView *)tableView
                           NibName:(NSString *)nib {
  // Display a message when the table is empty

  SearchTableView *emptyView =
      [[[NSBundle mainBundle] loadNibNamed:nib owner:self options:nil]
          objectAtIndex:0];
  [emptyView.titleLabel
      setText:NSLocalizedString(kNoDocumentsToShow, kNoDocumentsToShow)];
  [emptyView.detailedLabel
      setText:NSLocalizedString(kNoResultsToShow, kNoResultsToShow)];

  tableView.backgroundView = emptyView;
  tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
}

+ (void)hideEmptyLabelForTableView:(UITableView *)tableView {
  tableView.backgroundView = [UIView new];
  tableView.separatorStyle = UITableViewCellSeparatorStyleSingleLine;
}

+ (id)transformedSizeValue:(id)value {

  double convertedValue = [value doubleValue];
  int multiplyFactor = 0;

  NSArray *tokens =
      [NSArray arrayWithObjects:@"bytes", @"KB", @"MB", @"GB", @"TB", nil];

  while (convertedValue > 1024) {
    convertedValue /= 1024;
    multiplyFactor++;
  }

  NSString *numberFormat = @"%4.2f %@";
  if (multiplyFactor == 0) {
    numberFormat = @"%3.f %@";
  }

  return [NSString stringWithFormat:numberFormat, convertedValue,
                                    [tokens objectAtIndex:multiplyFactor]];
}

+ (void)fontAwesomeIcon:(UILabel *)label forType:(NSString *)mimeType {
  if (nil == mimeType) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAFileO]];
    [label setTextColor:[UIColor grayColor]];
  } else if ([mimeType isEqualToString:kText]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAFileTextO]];
  } else if ([mimeType isEqualToString:kPdf]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfilePdfO]];
    [label setTextColor:[UIColor redColor]];
  } else if ([mimeType isEqualToString:kPpt] ||
             [mimeType isEqualToString:kPptx]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfilePowerpointO]];
    [label setTextColor:[UIColor orangeColor]];
  } else if ([mimeType isEqualToString:kXml] ||
             [mimeType isEqualToString:kHtml]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfileCodeO]];
    [label setTextColor:[UIColor grayColor]];
  } else if ([mimeType isEqualToString:kCss] ||
             [mimeType isEqualToString:kJs]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfileCodeO]];
    [label setTextColor:[UIColor orangeColor]];
  } else if ([mimeType containsString:kImage]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfileImageO]];
    [label setTextColor:[UIColor purpleColor]];
  } else if ([mimeType containsString:kVideo]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfileMovieO]];
    [label setTextColor:[UIColor purpleColor]];
  } else if ([mimeType containsString:kAudio]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfileAudioO]];
    [label setTextColor:[UIColor purpleColor]];
  } else if ([mimeType isEqualToString:kZip] ||
             [mimeType isEqualToString:kZipx]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfileArchiveO]];
    [label setTextColor:[UIColor grayColor]];
  } else if ([mimeType isEqualToString:kDoc] ||
             [mimeType isEqualToString:kDocx]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfileWordO]];
    [label setTextColor:[UIColor blueColor]];
  } else if ([mimeType isEqualToString:kXls] ||
             [mimeType isEqualToString:kXlsx]) {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAfileExcelO]];
    [label setTextColor:[Utility colorWithHexString:@"259073"]];
  } else {
    [label setText:[NSString fontAwesomeIconStringForEnum:FAFileO]];
    [label setTextColor:[UIColor grayColor]];
  }
}

+ (NSAttributedString *)authoredTextWithAuthor:(NSString *)author
                                          date:(NSDate *)date

                                         event:(AuEvent)event {
  NSString *timeString = @"";
  if (date) {
    NSDictionary *timeScale = @{
      @"second" : @1,
      @"minute" : @60,
      @"hour" : @3600,
      @"Yesterday" : @86400
    };
    NSString *scale;
    int timeAgo = 0 - (int)[date timeIntervalSinceNow];
    if (timeAgo < 60) {
      scale = @"second";
    } else if (timeAgo < 3600) {
      scale = @"minute";
    } else if (timeAgo < 86400) {
      scale = @"hour";
    } else if (timeAgo < 172800) {
      scale = @"Yesterday";
    } else {
      scale = @"date";
    }

    if ([scale isEqualToString:@"date"]) {
      timeString =
          [NSDateFormatter localizedStringFromDate:date
                                         dateStyle:NSDateFormatterFullStyle
                                         timeStyle:NSDateFormatterNoStyle];
    } else if ([scale isEqualToString:@"Yesterday"]) {
      timeString = NSLocalizedString(@"Yesterday", @"Yesterday");
    } else {
      timeAgo = timeAgo / [[timeScale objectForKey:scale] integerValue];
      NSString *s = @"";
      if (timeAgo > 1) {
        s = @"s";
      }
      timeString =
          [NSString stringWithFormat:@"%d %@%@ ago", timeAgo,
                                     NSLocalizedString(scale, scale), s];
    }
  } else {
    timeString = NSLocalizedString(kUnknownDate, kUnknownDate);
  }

  NSString *eventText = NSLocalizedString(kCreatedOn, kCreatedOn);
  if (event == AuModified) {
    eventText = NSLocalizedString(kModifiedOn, kModifiedOn);
  }

  NSMutableAttributedString *string = [[NSMutableAttributedString alloc]
      initWithString:[NSString stringWithFormat:@"%@ %@ %@.", author, eventText,
                                                timeString]];
  [string addAttribute:NSUnderlineStyleAttributeName
                 value:@(1)
                 range:NSMakeRange(0, author.length)];
  return string;
}

+ (NSAttributedString *)authoredTextWithAuthor:(NSString *)author
                                    dateString:(NSString *)date
                                         event:(AuEvent)event {
  NSString *timeString = @"";
  if (date) {

    NSDate *dateObject;
    @try {
      dateObject =
          [NSDate dateWithTimeIntervalSince1970:[date doubleValue] / 1000];
    } @catch (NSException *exception) {
      NSLog(@"Error in converting UNIX date to NSDate - Exception : %@",
            exception);
    } @finally {
      if (nil == dateObject) {
        dateObject = [NSDate date];
      }
    }
    NSDictionary *timeScale = @{
      @"second" : @1,
      @"minute" : @60,
      @"hour" : @3600,
      @"Yesterday" : @86400
    };
    NSString *scale;
    int timeAgo = 0 - (int)[dateObject timeIntervalSinceNow];
    if (timeAgo < 60) {
      scale = @"second";
    } else if (timeAgo < 3600) {
      scale = @"minute";
    } else if (timeAgo < 86400) {
      scale = @"hour";
    } else if (timeAgo < 172800) {
      scale = @"Yesterday";
    } else {
      scale = @"date";
    }

    if ([scale isEqualToString:@"date"]) {
      timeString =
          [NSDateFormatter localizedStringFromDate:dateObject
                                         dateStyle:NSDateFormatterFullStyle
                                         timeStyle:NSDateFormatterNoStyle];
    } else if ([scale isEqualToString:@"Yesterday"]) {
      timeString = NSLocalizedString(@"Yesterday", @"Yesterday");
    } else {
      timeAgo = timeAgo / [[timeScale objectForKey:scale] integerValue];
      NSString *s = @"";
      if (timeAgo > 1) {
        s = @"s";
      }
      timeString =
          [NSString stringWithFormat:@"%d %@%@ ago", timeAgo,
                                     NSLocalizedString(scale, scale), s];
    }
  } else {
    timeString = NSLocalizedString(kUnknownDate, kUnknownDate);
  }

  NSString *eventText = NSLocalizedString(kCreatedOn, kCreatedOn);
  if (event == AuModified) {
    eventText = NSLocalizedString(kModifiedOn, kModifiedOn);
  }

  NSMutableAttributedString *string = [[NSMutableAttributedString alloc]
      initWithString:[NSString stringWithFormat:@"%@ %@ %@.", author, eventText,
                                                timeString]];
  [string addAttribute:NSUnderlineStyleAttributeName
                 value:@(1)
                 range:NSMakeRange(0, author.length)];
  return string;
}

+ (UIColor *)colorWithHexString:(NSString *)hex {
  NSString *cString = [[hex
      stringByTrimmingCharactersInSet:[NSCharacterSet
                                          whitespaceAndNewlineCharacterSet]]
      uppercaseString];

  // String should be 6 or 8 characters
  if ([cString length] < 6)
    return [UIColor grayColor];

  // strip 0X if it appears
  if ([cString hasPrefix:@"0X"])
    cString = [cString substringFromIndex:2];

  if ([cString length] != 6)
    return [UIColor grayColor];

  // Separate into r, g, b substrings
  NSRange range;
  range.location = 0;
  range.length = 2;
  NSString *rString = [cString substringWithRange:range];

  range.location = 2;
  NSString *gString = [cString substringWithRange:range];

  range.location = 4;
  NSString *bString = [cString substringWithRange:range];

  // Scan values
  unsigned int r, g, b;
  [[NSScanner scannerWithString:rString] scanHexInt:&r];
  [[NSScanner scannerWithString:gString] scanHexInt:&g];
  [[NSScanner scannerWithString:bString] scanHexInt:&b];

  return [UIColor colorWithRed:((float)r / 255.0f)
                         green:((float)g / 255.0f)
                          blue:((float)b / 255.0f)
                         alpha:1.0f];
}

+ (NSURL *)saveAndReturnFile:(NSString *)fileName ForData:(NSData *)data {

  NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,
                                                       NSUserDomainMask, YES);
  NSString *docsDirectory = [paths objectAtIndex:0];
  NSString *filePath = [docsDirectory stringByAppendingPathComponent:fileName];
  [data writeToFile:filePath atomically:YES];
  NSURL *url = [NSURL fileURLWithPath:filePath];
  return url;
}

+ (NSDictionary *)titleTextAttributes {
  return [NSDictionary
      dictionaryWithObjectsAndKeys:[self defaultFont], NSFontAttributeName,
                                   [UIColor whiteColor],
                                   NSForegroundColorAttributeName, nil];
}

+ (UIFont *)defaultFont {
  return [UIFont fontWithName:kLatoRegular size:kDefaultFontSize];
}

+ (NSDictionary *)fontAwesomeTitleTextAttributes {
  return [NSDictionary
      dictionaryWithObjectsAndKeys:[self fontAwesomeFont], NSFontAttributeName,
                                   [UIColor whiteColor],
                                   NSForegroundColorAttributeName, nil];
}

+ (UIFont *)fontAwesomeFont {
  return [UIFont fontWithName:kFontAwesome size:kFontAwesomeFontSize];
}

+ (MBProgressHUD *)showLoginErrorOn:(UIViewController *)controller {
  return [self hudWithTitle:kConnectionError
                       text:kNotLoggedIn
                       icon:@"cross-check.png"
                      delay:3
                    context:controller];
}

+ (MBProgressHUD *)showConnectionErrorOn:(UIViewController *)controller {
  return [self hudWithTitle:kConnectionError
                       text:kNoNetworkConnection
                       icon:@"cross-check.png"
                      delay:3
                    context:controller];
}

+ (MBProgressHUD *)showConnectionFailedOn:(UIViewController *)controller {
  return [self hudWithTitle:kConnectionFailed
                       text:kInvalidAccountDetails
                       icon:@"cross-check.png"
                      delay:3
                    context:controller];
}

+ (MBProgressHUD *)showConnectionSuccessOn:(UIViewController *)controller {
  return [self hudWithTitle:kConnectionSuccess
                       text:kAccountConnected
                       icon:@"Checkmark.png"
                      delay:3
                    context:controller];
}

+ (MBProgressHUD *)hudWithTitle:(NSString *)title
                           text:(NSString *)detailText
                           icon:(NSString *)imageName
                          delay:(NSTimeInterval)delay
                        context:(UIViewController *)controller {
  MBProgressHUD *HUD =
      [[MBProgressHUD alloc] initWithView:controller.navigationController.view];
  [controller.navigationController.view addSubview:HUD];
  if (nil != imageName) {
    HUD.customView =
        [[UIImageView alloc] initWithImage:[UIImage imageNamed:imageName]];
    HUD.mode = MBProgressHUDModeCustomView;
  }
  if (nil != title) {
    HUD.labelText = NSLocalizedString(title, title);
  }
  if (nil != detailText) {
    HUD.detailsLabelText = NSLocalizedString(detailText, detailText);
  }
  [HUD show:YES];
  if (0 != delay) {
    HUD.removeFromSuperViewOnHide = YES;
    [HUD hide:YES afterDelay:delay];
  }
  return HUD;
}

@end
