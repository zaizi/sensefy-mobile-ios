//
//  SensefyAccessToken.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/12/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyAccessToken.h"

@implementation SensefyAccessToken

- (void)encodeWithCoder:(NSCoder *)aCoder {
  [aCoder encodeObject:self.accessToken forKey:KEY_ACCESS_TOKEN];
  [aCoder encodeObject:self.refreshToken forKey:KEY_REFRESH_TOKEN];
  [aCoder encodeObject:self.tokenType forKey:KEY_TOKEN_TYPE];
  [aCoder encodeObject:self.expiresAt forKey:KEY_EXPIRES_IN];
  [aCoder encodeObject:self.scope forKey:KEY_SCOPE];
}

- (id)initWithCoder:(NSCoder *)aDecoder {
  self = [super init];
  if (self) {
    self.accessToken = [aDecoder decodeObjectForKey:KEY_ACCESS_TOKEN];
    self.refreshToken = [aDecoder decodeObjectForKey:KEY_REFRESH_TOKEN];
    self.tokenType = [aDecoder decodeObjectForKey:KEY_TOKEN_TYPE];
    self.expiresAt = [aDecoder decodeObjectForKey:KEY_EXPIRES_IN];
    self.scope = [aDecoder decodeObjectForKey:KEY_SCOPE];
  }
  return self;
}

@end
