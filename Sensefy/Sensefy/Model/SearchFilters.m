//
//  SearchFilters.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/14/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SearchFilters.h"

@interface SearchFilters ()
@property(nonatomic, strong, readwrite) NSMutableArray *filterDictionary;
@end

@implementation SearchFilters

- (instancetype)init {
  self = [super init];
  if (self) {
    self.filterDictionary = [NSMutableArray array];
  }
  return self;
}

- (NSArray *)filters {
  return [NSArray arrayWithArray:self.filterDictionary];
}

- (void)addFilter:(NSString *)filter {
  if (![self hasFilter:filter]) {
    [self.filterDictionary addObject:filter];
  }
}

- (void)removeFilter:(NSString *)filter {
  NSUInteger index = [self indexOfFilter:filter];
  if (index != NSNotFound) {
    [self.filterDictionary removeObjectAtIndex:index];
  }
}

- (void)removeAllFilters {
  [self.filterDictionary removeAllObjects];
}

- (BOOL)hasFilter:(NSString *)filter {
  NSUInteger index = [self indexOfFilter:filter];
  if (index == NSNotFound) {
    return NO;
  }
  return YES;
}

- (NSUInteger)indexOfFilter:(NSString *)filter {

  // Escape speacial characters
  filter = [filter stringByReplacingOccurrencesOfString:@"[" withString:@"\\["];
  filter = [filter stringByReplacingOccurrencesOfString:@"]" withString:@"\\]"];
  filter = [filter stringByReplacingOccurrencesOfString:@"{" withString:@"\\{"];
  filter = [filter stringByReplacingOccurrencesOfString:@"}" withString:@"\\}"];
  filter = [filter stringByReplacingOccurrencesOfString:@"+" withString:@"\\+"];
  filter = [filter stringByReplacingOccurrencesOfString:@"-" withString:@"\\-"];

  NSUInteger index = NSNotFound;
  NSPredicate *predicate =
      [NSPredicate predicateWithFormat:@"SELF MATCHES %@", filter];
  index = [self.filterDictionary
      indexOfObjectPassingTest:^(id obj, NSUInteger idx, BOOL *stop) {
        return [predicate evaluateWithObject:obj];
      }];

  return index;
}

- (NSString *)stringWithFilters {
  NSString *filterString = @"";
  if (self.filterDictionary) {
    int filterCount = (int)self.filterDictionary.count;

    for (int i = 0; i < filterCount; i++) {
      filterString =
          [filterString stringByAppendingString:self.filterDictionary[i]];
      if (i < filterCount - 1) {
        filterString = [filterString stringByAppendingString:@","];
      }
    }
  }
  return filterString;
}

@end
