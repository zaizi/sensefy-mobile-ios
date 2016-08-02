//
//  NSMutableArray+Custom.m
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 7/3/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "NSMutableArray+Custom.h"

@implementation NSMutableArray (Custom)

- (void)moveObjectFromIndex:(NSUInteger)from toIndex:(NSUInteger)to
{
    if (to != from) {
        id obj = [self objectAtIndex:from];
        [self removeObjectAtIndex:from];
        if (to >= [self count]) {
            [self addObject:obj];
        } else {
            [self insertObject:obj atIndex:to];
        }
    }
}

@end
