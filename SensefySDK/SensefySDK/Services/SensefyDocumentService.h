//
//  SensefyDocumentService.h
//  SensefySDK
//
//  Created by Krishantha Jayathilake  on 2/11/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyDocument.h"
#import <Foundation/Foundation.h>

@interface SensefyDocumentService : NSObject

+ (instancetype)sharedInstance;

/**
 *  Open document using releven viewer
 *
 *  @param document Sensefy document instance
 */
- (void)openDocument:(SensefyDocument *)document;

/**
 *  Check if a third party app is installed in system
 *
 *  @param url_scheme url_scheme for the app
 *
 *  @return Status whether the app is available
 */
- (BOOL)isAppAvailable:(NSString *)url_scheme;

/**
 *  Open file with third party app
 *
 *  @param url_scheme with parameters
 */
- (void)openInApp:(NSString *)url_scheme;

@end
