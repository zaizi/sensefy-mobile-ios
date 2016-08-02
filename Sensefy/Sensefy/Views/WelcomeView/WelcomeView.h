//
//  WelcomeView.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 2/19/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol WelcomeViewDelegate
- (void)loginButtonClicked;
@end

@interface WelcomeView : UIView

@property(nonatomic, weak) id<WelcomeViewDelegate> delegate;
- (IBAction)loginButtonClicked:(id)sender;

@end
