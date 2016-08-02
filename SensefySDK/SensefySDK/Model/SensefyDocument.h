//
//  SensefyDocument.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SensefyDocument : NSObject

@property(nonatomic, copy) NSString *documentID;
@property(nonatomic, copy) NSString *version;
@property(nonatomic, copy) NSString *title;
@property(nonatomic, copy) NSString *name;
@property(nonatomic, copy) NSString *dataSource;
@property(nonatomic, copy) NSString *dataSourceType;
@property(nonatomic, copy) NSString *creator;
@property(nonatomic, copy) NSDate *creationDate;
@property(nonatomic, copy) NSDate *lastModified;
@property(nonatomic, copy) NSString *lastModifier;
@property(nonatomic, copy) NSString *language;
@property(nonatomic, copy) NSURL *url;
@property(nonatomic, copy) NSString *size;
@property(nonatomic, copy) NSString *path;
@property(nonatomic, copy) NSString *mimeType;

@end
