//
//  ReponseHeader.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ResponseHeader : NSObject

/**
 *  Header status
 */
@property(nonatomic) NSNumber *status;

/**
 *  Response query
 */
@property(nonatomic, copy) NSString *query;

/**
 *  Query execution time
 */
@property(nonatomic) NSNumber *queryTime;

@end
