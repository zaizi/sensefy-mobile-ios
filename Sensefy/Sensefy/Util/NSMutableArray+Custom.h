//
//  NSMutableArray+Custom.h
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 7/3/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSMutableArray (Custom)

/*!
 * @discussion Move object between two given indexes within a mutable array.
 * Indexes must be available within the array.
 * @param from Source index
 * @param to Destination index
 */
- (void)moveObjectFromIndex:(NSUInteger)from toIndex:(NSUInteger)to;

@end
