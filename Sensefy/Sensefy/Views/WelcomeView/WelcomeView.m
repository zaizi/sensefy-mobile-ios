//
//  WelcomeView.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 2/19/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "WelcomeView.h"

@implementation WelcomeView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

- (IBAction)loginButtonClicked:(id)sender {
  [self.delegate loginButtonClicked];
}
@end
