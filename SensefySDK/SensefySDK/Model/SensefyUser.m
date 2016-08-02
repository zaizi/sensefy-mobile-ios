//
//  SensefyUser.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyUser.h"

@implementation SensefyUser

- (void)encodeWithCoder:(NSCoder *)aCoder {
  [aCoder encodeObject:self.username forKey:KEY_USERNAME];
  [aCoder encodeObject:self.timezone forKey:KEY_TIMEZONE];
  [aCoder encodeObject:self.accountNonExpired forKey:KEY_ACCOUNT_NON_EXPIRED];
  [aCoder encodeObject:self.accountNonLocked forKey:KEY_ACCOUNT_NON_LOCKED];
  [aCoder encodeObject:self.credentialsNonExpired
                forKey:KEY_CREDENTIALS_NON_EXPIRED];
  [aCoder encodeObject:self.enabled forKey:KEY_ENABLED];
}

- (id)initWithCoder:(NSCoder *)aDecoder {
  self = [super init];
  if (self) {
    self.username = [aDecoder decodeObjectForKey:KEY_USERNAME];
    self.timezone = [aDecoder decodeObjectForKey:KEY_TIMEZONE];
    self.accountNonExpired =
        [aDecoder decodeObjectForKey:KEY_ACCOUNT_NON_EXPIRED];
    self.accountNonLocked =
        [aDecoder decodeObjectForKey:KEY_ACCOUNT_NON_LOCKED];
    self.credentialsNonExpired =
        [aDecoder decodeObjectForKey:KEY_CREDENTIALS_NON_EXPIRED];
    self.enabled = [aDecoder decodeObjectForKey:KEY_ENABLED];
  }
  return self;
}

@end
