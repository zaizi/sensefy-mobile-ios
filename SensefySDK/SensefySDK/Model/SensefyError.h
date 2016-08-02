//
//  SensefyError.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/18/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SensefyError : NSObject

@property(nonatomic) long timestamp;
@property(nonatomic) int status;
@property(nonatomic, copy) NSString *error;
@property(nonatomic, copy) NSString *exception;
@property(nonatomic, copy) NSString *message;
@property(nonatomic, copy) NSString *path;

@end
