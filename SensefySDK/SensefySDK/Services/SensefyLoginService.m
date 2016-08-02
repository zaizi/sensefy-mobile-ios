//
//  SensefyAccountService.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/12/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyError.h"
#import "SensefyLoginService.h"
#import "SensefyUserService.h"
#import "SensefyUtils.h"

@interface SensefyLoginService ()

@property(strong, nonatomic) UIWebView *loginWebView;
@property(strong, nonatomic) UIActivityIndicatorView *spinner;

@end

@implementation SensefyLoginService

static dispatch_once_t onceTokenForSharedInstance = 0;

static SensefyLoginService *sharedInstance = nil;

+ (SensefyLoginService *)sharedInstance {
  dispatch_once(&onceTokenForSharedInstance, ^{
    if (sharedInstance == nil) {
      sharedInstance = [[self alloc] init];
    }
  });
  [sharedInstance initializeObjectManagers];
  return sharedInstance;
}

#pragma mark-- Service Methods --

- (void)loginWithSensefy {

  NSString *path =
      [[NSBundle mainBundle] pathForResource:CONFIG_FILE ofType:@"plist"];
  NSDictionary *settings = [[NSDictionary alloc] initWithContentsOfFile:path];

  NSDictionary *dictionary = @{
    KEY_CLIENT_ID : [settings valueForKey:OAUTH_CLIENT_ID],
    KEY_SCOPE : OAUTH_SCOPES,
    KEY_REDIRECT_URL : OAUTH_REDIRECT_URL,
    KEY_RESPONSE_TYPE : OAUTH_RESPONSE_TYPE
  };
  NSString *query = [SensefyUtils stringWithEncodedQueryParameters:dictionary];
  NSURL *authURL =
      [SensefyUtils createURLFromBaseURLString:[settings valueForKey:BASE_URL]
                                  extensionURL:AUTHORIZE_API
                                   queryString:query];
  // Initially we used Safari to login
  // [[UIApplication sharedApplication] openURL:authURL];
  [self showLoginViewWithURL:authURL];
}

- (void)logoutFromSensefy {
  [[FXKeychain defaultKeychain] setObject:nil
                                   forKey:SENSEFY_OAUTH2_ACCOUNT_TYPE];
  [[SensefyUserService sharedInstance] removeCurrentUser];

  [[RKObjectManager sharedManager] postObject:nil
      path:LOGOUT_API
      parameters:nil
      success:^(RKObjectRequestOperation *operation,
                RKMappingResult *mappingResult) {

      }
      failure:^(RKObjectRequestOperation *operation, NSError *error){

      }];

  //  NSURL *authURL = [SensefyUtils createURLFromBaseURLString:BASE_URL
  //                                               extensionURL:LOGOUT_API
  //                                                queryString:nil];
  //  [[UIApplication sharedApplication] openURL:authURL];
  //
  //  [self performSelector:@selector(loginWithSensefy)
  //             withObject:nil
  //             afterDelay:5.];
}

- (void)requestAccessToken:(NSString *)authCode {

  NSString *path =
      [[NSBundle mainBundle] pathForResource:CONFIG_FILE ofType:@"plist"];
  NSDictionary *settings = [[NSDictionary alloc] initWithContentsOfFile:path];

  NSDictionary *params = @{
    KEY_GRANT_TYPE : OAUTH_GRANT_TYPE,
    KEY_CLIENT_ID : [settings valueForKey:OAUTH_CLIENT_ID],
    KEY_CLIENT_SECRET : [settings valueForKey:OAUTH_CLIENT_SECRET],
    KEY_REDIRECT_URL : OAUTH_REDIRECT_URL,
    KEY_CODE : authCode,
    KEY_SCOPE : OAUTH_SCOPES
  };

  [[RKObjectManager sharedManager] getObjectsAtPath:TOKEN_API
      parameters:params
      success:^(RKObjectRequestOperation *operation,
                RKMappingResult *mappingResult) {

        SensefyAccessToken *result = [mappingResult firstObject];
        [[FXKeychain defaultKeychain] setObject:result
                                         forKey:SENSEFY_OAUTH2_ACCOUNT_TYPE];
        [[NSNotificationCenter defaultCenter]
            postNotificationName:TOKEN_RECEIVED_NOTIFICATION
                          object:result];

        [[SensefyUserService sharedInstance] updateCurrnetUserDetails];

      }
      failure:^(RKObjectRequestOperation *operation, NSError *error) {

        NSLog(@"errorMessage: %@",
              [[error userInfo] objectForKey:RKObjectMapperErrorObjectsKey]);

      }];

  [self hideLoginView];
}

- (SensefyAccessToken *)getAccessToken {
  SensefyAccessToken *accessToken =
      [[FXKeychain defaultKeychain] objectForKey:SENSEFY_OAUTH2_ACCOUNT_TYPE];

  return accessToken;
}

- (void)handleRedirectURL:(NSURL *)redirectURL {

  NSString *code = [SensefyUtils valueForKey:KEY_CODE fromURL:redirectURL];
  [self requestAccessToken:code];
}

#pragma mark-- Private Methods --

// Configuration of RestKit object manager and mapping of network objects to
// model objects using RestKit object modelling.

- (void)initializeObjectManagers {

  NSString *path =
      [[NSBundle mainBundle] pathForResource:CONFIG_FILE ofType:@"plist"];
  NSDictionary *settings = [[NSDictionary alloc] initWithContentsOfFile:path];

  NSString *clientAuthString = [NSString
      stringWithFormat:@"%@:%@", [settings valueForKey:OAUTH_CLIENT_ID],
                       [settings valueForKey:OAUTH_CLIENT_SECRET]];
  NSString *authString = [NSString
      stringWithFormat:@"Basic %@", [[clientAuthString
                                        dataUsingEncoding:NSUTF8StringEncoding]
                                        base64EncodedStringWithOptions:0]];

  // Configure the object manager.
  RKObjectManager *objectManager = [RKObjectManager
      managerWithBaseURL:[NSURL URLWithString:[settings valueForKey:BASE_URL]]];
  objectManager.requestSerializationMIMEType = RKMIMETypeJSON;
  [[objectManager HTTPClient] setDefaultHeader:AUTHORIZE_HEADER
                                         value:authString];

  [RKObjectManager setSharedManager:objectManager];

  // -- Declare routes -- //

  RKBlockValueTransformer *numberToDateValueTransformer =
      [RKBlockValueTransformer valueTransformerWithValidationBlock:^BOOL(
                                   __unsafe_unretained Class sourceClass,
                                   __unsafe_unretained Class destinationClass) {
        // This transformer handles `NSNumber` <-> `NSDate`
        // transformations
        return (([sourceClass isSubclassOfClass:[NSNumber class]] &&
                 [destinationClass isSubclassOfClass:[NSDate class]]) ||
                ([sourceClass isSubclassOfClass:[NSDate class]] &&
                 [destinationClass isSubclassOfClass:[NSNumber class]]));
      }
          transformationBlock:^BOOL(id inputValue,
                                    __autoreleasing id *outputValue,
                                    __unsafe_unretained Class outputValueClass,
                                    NSError *__autoreleasing *error) {
            RKValueTransformerTestInputValueIsKindOfClass(
                inputValue, (@[ [NSNumber class], [NSDate class] ]), error);
            RKValueTransformerTestOutputValueClassIsSubclassOfClass(
                outputValueClass, (@[ [NSNumber class], [NSDate class] ]),
                error);
            if ([outputValueClass isSubclassOfClass:[NSDate class]]) {
              if ([inputValue isKindOfClass:[NSNumber class]]) {
                NSDate *date = [NSDate date];
                NSTimeInterval secondsInInterval = [inputValue doubleValue];
                *outputValue =
                    [date dateByAddingTimeInterval:secondsInInterval];
              }
            } else if ([outputValueClass isSubclassOfClass:[NSNumber class]]) {
              *outputValue = @([inputValue timeIntervalSince1970]);
            }
            return YES;
          }];

  // Now configure the FacetItems mapping
  RKObjectMapping *accessTokenMapping =
      [RKObjectMapping mappingForClass:[SensefyAccessToken class]];

  [accessTokenMapping addAttributeMappingsFromDictionary:@{
    KEY_ACCESS_TOKEN : @"accessToken",
    KEY_REFRESH_TOKEN : @"refreshToken",
    KEY_TOKEN_TYPE : @"tokenType",
    KEY_SCOPE : @"scope"
  }];

  RKAttributeMapping *expireDateMapping =
      [RKAttributeMapping attributeMappingFromKeyPath:KEY_EXPIRES_IN
                                            toKeyPath:@"expiresAt"];
  expireDateMapping.valueTransformer = numberToDateValueTransformer;
  [accessTokenMapping addPropertyMapping:expireDateMapping];

  RKResponseDescriptor *tokenResponseDescriptor = [RKResponseDescriptor
      responseDescriptorWithMapping:accessTokenMapping
                             method:RKRequestMethodGET
                        pathPattern:TOKEN_API
                            keyPath:nil
                        statusCodes:RKStatusCodeIndexSetForClass(
                                        RKStatusCodeClassSuccessful)];

  [objectManager addResponseDescriptor:tokenResponseDescriptor];

  RKObjectMapping *errorMapping =
      [RKObjectMapping mappingForClass:[SensefyError class]];

  [errorMapping addAttributeMappingsFromDictionary:@{
    ATTRIBUTE_ERROR : @"error",
    ATTRIBUTE_ERROR_DESCRIPTION : @"errorDescription"
  }];

  RKResponseDescriptor *errorResponseDescriptor = [RKResponseDescriptor
      responseDescriptorWithMapping:errorMapping
                             method:RKRequestMethodGET
                        pathPattern:TOKEN_API
                            keyPath:nil
                        statusCodes:RKStatusCodeIndexSetForClass(
                                        RKStatusCodeClassClientError)];
  [objectManager addResponseDescriptor:errorResponseDescriptor];
}

- (BOOL)tokenExpired {
  NSDate *date = [NSDate new];
  SensefyAccessToken *accessToken =
      [[FXKeychain defaultKeychain] objectForKey:SENSEFY_OAUTH2_ACCOUNT_TYPE];
  if ([date compare:accessToken.expiresAt] == NSOrderedAscending) {
    return NO;
  } else {
    return YES;
  }
}

- (void)showActivityIndicator {
  self.spinner = [[UIActivityIndicatorView alloc]
      initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
  [self.loginWebView addSubview:self.spinner];
  self.spinner.center = self.loginWebView.center;
  self.spinner.hidesWhenStopped = YES;
  [self.spinner setColor:[UIColor blackColor]];
  [self.spinner startAnimating];
}

- (void)hideActivityIndicator {
  [self.spinner stopAnimating];
  self.spinner.hidden = YES;
  [self.spinner removeFromSuperview];
}

- (void)showLoginViewWithURL:(NSURL *)url {
  UIApplication *app = [UIApplication sharedApplication];
  self.loginWebView = [[UIWebView alloc] initWithFrame:app.keyWindow.frame];
  self.loginWebView.delegate = self;
  NSURLRequest *req = [NSURLRequest requestWithURL:url];
  [self.loginWebView loadRequest:req];
  [app.keyWindow addSubview:self.loginWebView];
  app.statusBarStyle = UIStatusBarStyleDefault;
}

- (void)hideLoginView {
  UIApplication *app = [UIApplication sharedApplication];
  app.statusBarStyle = UIStatusBarStyleLightContent;
  [self.loginWebView removeFromSuperview];
}

#pragma mark-- WebView Delegate Methods --

- (void)webViewDidStartLoad:(UIWebView *)webView {
  [self showActivityIndicator];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView {
  [self hideActivityIndicator];
}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error {
  [self hideActivityIndicator];
  [self hideLoginView];
}

@end
