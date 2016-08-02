//
//  SensefyUserService.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/14/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyLoginService.h"
#import "SensefyUserService.h"

@implementation SensefyUserService

static dispatch_once_t onceTokenForSharedInstance = 0;

static SensefyUserService *sharedInstance = nil;

+ (SensefyUserService *)sharedInstance {
  dispatch_once(&onceTokenForSharedInstance, ^{
    if (sharedInstance == nil) {
      sharedInstance = [[self alloc] init];
    }
  });
  [sharedInstance initializeObjectManagers];
  return sharedInstance;
}

- (SensefyUser *)getUser {

  SensefyUser *user =
      [[FXKeychain defaultKeychain] objectForKey:SENSEFY_OAUTH2_USER];
  return user;
}

- (void)updateCurrnetUserDetails {

  [[RKObjectManager sharedManager] getObjectsAtPath:USER_API
      parameters:nil
      success:^(RKObjectRequestOperation *operation,
                RKMappingResult *mappingResult) {

        SensefyUser *result = [mappingResult firstObject];
        [[FXKeychain defaultKeychain] setObject:result
                                         forKey:SENSEFY_OAUTH2_USER];

      }
      failure:^(RKObjectRequestOperation *operation, NSError *error) {
        DDLogError(@"Error retrieving user details");
      }];
}

- (void)removeCurrentUser {
  [[FXKeychain defaultKeychain] setObject:nil forKey:SENSEFY_OAUTH2_USER];
}

- (void)initializeObjectManagers {

  NSString *path =
      [[NSBundle mainBundle] pathForResource:CONFIG_FILE ofType:@"plist"];
  NSDictionary *settings = [[NSDictionary alloc] initWithContentsOfFile:path];

  SensefyAccessToken *token =
      [[SensefyLoginService sharedInstance] getAccessToken];

  if (nil != token) {

    // Configure the object manager.
    RKObjectManager *objectManager = [RKObjectManager
        managerWithBaseURL:[NSURL
                               URLWithString:[settings valueForKey:BASE_URL]]];
    objectManager.requestSerializationMIMEType = RKMIMETypeJSON;
    [[objectManager HTTPClient]
        setDefaultHeader:AUTHORIZE_HEADER
                   value:[NSString stringWithFormat:@"%@ %@", OAUTH_TOKEN_TYPE,
                                                    token.accessToken]];

    [RKObjectManager setSharedManager:objectManager];

    // -- Declare routes -- //

    // Now configure the User mapping
    RKObjectMapping *userMapping =
        [RKObjectMapping mappingForClass:[SensefyUser class]];

    [userMapping addAttributeMappingsFromDictionary:@{
      KEY_USERNAME : @"username",
      KEY_TIMEZONE : @"timezone",
      KEY_ACCOUNT_NON_EXPIRED : @"accountNonExpired",
      KEY_ACCOUNT_NON_LOCKED : @"accountNonLocked",
      KEY_CREDENTIALS_NON_EXPIRED : @"credentialsNonExpired",
      KEY_ENABLED : @"enabled"
    }];

    RKResponseDescriptor *userResponseDescriptor = [RKResponseDescriptor
        responseDescriptorWithMapping:userMapping
                               method:RKRequestMethodGET
                          pathPattern:USER_API
                              keyPath:KEY_USER_PATH
                          statusCodes:RKStatusCodeIndexSetForClass(
                                          RKStatusCodeClassSuccessful)];

    [objectManager addResponseDescriptor:userResponseDescriptor];
  } else {

    DDLogError(@"Access token is not available");
    [[NSNotificationCenter defaultCenter]
        postNotificationName:TOKEN_NOT_FOUND_NOTIFICATION
                      object:nil];
  }
}

@end
