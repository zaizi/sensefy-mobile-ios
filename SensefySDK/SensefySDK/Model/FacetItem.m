//
//  FacetItem.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "FacetItem.h"

@implementation FacetItem

- (id)copy {
  FacetItem *facetCopy = [[FacetItem alloc] init];

  facetCopy.value = self.value;
  facetCopy.filter = self.filter;
  facetCopy.occurrence = self.occurrence;

  return facetCopy;
}

@end
