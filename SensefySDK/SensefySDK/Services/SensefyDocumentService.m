//
//  SensefyDocumentService.m
//  SensefySDK
//
//  Created by Krishantha Jayathilake  on 2/11/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyDocumentService.h"

@implementation SensefyDocumentService

static dispatch_once_t onceTokenForSharedInstance = 0;

static SensefyDocumentService *sharedInstance = nil;

+ (SensefyDocumentService *)sharedInstance {
  dispatch_once(&onceTokenForSharedInstance, ^{
    if (sharedInstance == nil) {
      sharedInstance = [[self alloc] init];
    }
  });
  return sharedInstance;
}

#pragma mark-- Service Methods --

- (void)openDocument:(SensefyDocument *)document {
  if ([document.dataSource isEqualToString:DATASOURCE_ALFRESCO]) {
    if ([self isAppAvailable:@"alfresco://"]) {
      [self openInApp:[NSString stringWithFormat:@"alfresco://document/id/%@",
                                                 document.documentID]];
    } else {
      [[UIApplication sharedApplication] openURL:document.url];
    }
  } else if (document.url &&
             ![document.url.absoluteString isEqualToString:@""]) {
    [self openInApp:document.url.absoluteString];
  } else {
    [[UIApplication sharedApplication] openURL:document.url];
  }
}

- (BOOL)isAppAvailable:(NSString *)url_scheme {
  if ([[UIApplication sharedApplication]
          canOpenURL:[NSURL URLWithString:url_scheme]]) {
    return YES;
  } else {
    return NO;
  }
}

- (void)openInApp:(NSString *)url_scheme {
  if ([self isAppAvailable:url_scheme]) {
    [[UIApplication sharedApplication]
        openURL:[NSURL URLWithString:url_scheme]];
  } else {
    NSLog(@"url_scheme is not available");
  }
}

@end
