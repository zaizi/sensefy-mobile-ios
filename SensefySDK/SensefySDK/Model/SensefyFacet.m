//
//  SensefyFacet.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/8/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyFacet.h"

@implementation SensefyFacet

- (id)copy {
  SensefyFacet *facetCopy = [[SensefyFacet alloc] init];

  facetCopy.label = self.label;
  facetCopy.facetItems = self.facetItems;

  return facetCopy;
}

@end
