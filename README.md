# Introduction to Sensefy

Sensefy is a **Enterprise Semantic Search** Engine developed by Zaizi. Built on top of cutting edge open source software, it will provide the users with advanced enterprise search functionalities.

Sensefy is **Semantic** because it enriches the documents semantically to extract entities such as people, organizations and places and improve the Search user experience by giving semantic search functionalities.

Sensefy is **Enterprise** because it offers federated search, allowing the user to index the content from heterogeneous data sources, search all of them from a unified search endpoint keeping ACLs of the documents to allow only permitted users to access relevant documents.

# Sensefy mobile

This is the iOS mobile SDK for integrating Sensefy into your iOS app. Sensefy SDK will provide set of functionalities to work with your Sensefy server instance.

## Project Structure

Sensefy mobile SDK project consists of the following components:

 - Sensefy-Mobile-SDK : An iOS framework project with set of Sensefy functionalities
 - Sensefy-Mobile-App : An application demonstrating the usage of Sensefy mobile SDK

## Sensefy Dependencies

Sensefy mobile uses several open-source libraries. These libraries are integrated as Cocoa Pods:

#####  Sensefy SDK

- RestKit : 
    RestKit is a modern Objective-C framework for implementing RESTful web services clients on iOS and Mac OS X. It provides a powerful object mapping engine that seamlessly integrates with Core Data and a simple set of networking primitives for mapping HTTP requests and responses built on top of AFNetworking. 

- FXKeychain : 
    FXKeychain is a lightweight wrapper around the Apple keychain APIs that exposes the commonly used functionality whilst hiding the horrific complexity and ugly interface of the underlying APIs.

- CocoaLumberjack : 
    CocoaLumberjack is a fast & simple, yet powerful & flexible logging framework for Mac and iOS.

#####  Sensefy App

- MBProgressHUD :
    MBProgressHUD is an iOS drop-in class that displays a translucent HUD with an indicator and/or labels while work is being done in a background thread. The HUD is meant as a replacement for the undocumented, private UIKit UIProgressHUD with some additional features.

- FontAwesomeKit :
    Icon font library for iOS. Currently supports Font-Awesome, Foundation icons, Zocial, and ionicons.
 

## Build and Run Sensefy Mobile App

#####  Prerequisites
* Xcode 7.1 or higher 
* Cocoa Pods 0.39.0 or higher

#### Configure Sensefy SDK
Double click on Sensefy.xcworkspace and it will open up the Sensefy project. Before we run the project do the following configurations. Expand Sensefy and open Config.plist file. Update following constants to match with your server configurations.

```sh
// CONFIGURATIONS
BASE_URL = "IP ADDRESS OR BASE URL FOR SENSEFY:PORT NUMBER"
CLIENT_ID = "CLIENT ID"
CLIENT_SECRET = "CLIENT SECRET"
```

#### Build and Run App
Once you are done with the configurations build and run Sensefy. This will run the Sensefy app with given configurations. Note that if you want to run the app in real device there must be a valid provisioning profile installed in your mac and configured with sensefy mobile app. 

## Integrating SDK in to your project
Create new Xcode iOS project. Copy and paste the SensefySDK folder to the same directory where your project is in. Now create a pod file with following syntax. Replace TestProject with your project name

```sh
workspace 'TestProject.xcworkspace'
xcodeproj 'TestProject/TestProject.xcodeproj'
xcodeproj 'SensefySDK/SensefySDK.xcodeproj'


target 'TestProject' do
platform :ios, '7.0'
xcodeproj 'TestProject/TestProject.xcodeproj'
pod 'MBProgressHUD' // Add any other pods you wish to use in your project
end


target 'SensefySDK' do
platform :ios, '7.0'
xcodeproj 'SensefySDK/SensefySDK.xcodeproj'
pod 'RestKit'
pod 'FXKeychain'
pod 'CocoaLumberjack'
end
```

Open terminal cd in to the directory where the podfile is located. Run command ```pod install```.
This will download necessary libraries needed for the both projects and create a workspace.
Now open the ProjectName.xcworkspace file. Here onwards you should use this file to open the project.

#### Configure App

Creaate a new property list file in your app bundle and name it as Config.plist. Add following strings with given keys.

```sh
// CONFIGURATIONS
BASE_URL = "IP ADDRESS OR BASE URL FOR SENSEFY:PORT NUMBER"
CLIENT_ID = "CLIENT ID"
CLIENT_SECRET = "CLIENT SECRET"
```

Open your AppDelegate.h file. Add following method.

```sh
- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url {
[[SensefyLoginService sharedInstance] handleRedirectURL:url];
return true;
}
```

Next open project properties select Target -> Info and add the URL Types as follows. The URL Scheme should be match with above mentioned callback URL.


## Using the Client API

#### Authenticate with Sensefy server

Use the following code block to authenticate with your Sensefy server instance. At this point the user will be redirected to authentication end point.

```sh
#import <SensefySDK/SensefySDK.h>
[[SensefyLoginService sharedInstance] loginWithSensefy];
```

#### Search documents

Use the following method to search documents with sensefy server. 

```sh

#import <SensefySDK/SensefySDK.h>
[[SensefySearchService sharedInstance]
searchWithKeyword:@"SEARCH TERM"
fields:@"LIST OF SEARCH FIELDS"
orderBy:@"SORT BY ITEM"
order:SortDESC
rows:20
start:0
facet:YES
clustering:YES
clusteringSort:YES
spellcheck:NO
filters:@"LIST OF SEARCH FILTERS"
completionBlock:^(KeywordSearchResult *result, NSError *error) {
// SEARCH RESULTS
}];
```

#### Autocomplete search

Use the following method to retrieve autocomplete search suggestions.

```sh
#import <SensefySDK/SensefySDK.h>
[[SensefySearchService sharedInstance]
autoCompleteWithTerm:@"SEARCH TERM"
numberOfSuggestions:10
semantic:YES
completionBlock:^(AutocompleteResult *result, NSError *error) {
// SEARCH SUGGESTIONS
}];
```
#### Open document

This method can be used to view documents. If the document comes from an Alfresco repository and if the Alfresco mobile app is available in device, then SDK will try to open it up using Alfresco mobile app. All the other documents will be opened using Safari. 

```sh
#import <SensefySDK/SensefySDK.h>
[[SensefyDocumentService sharedInstance] openDocument:self.document];
```
#### Get current user details

This method can be used to retrieve user details. Invoke the getUser method.

```sh
#import <SensefySDK/SensefySDK.h>
SensefyUser *user = [[SensefyUserService sharedInstance] getUser];
```

#### Copyright

© Zaizi Limited. Code for this SDK is licensed under the GNU Lesser General Public License (LGPL).

Any trademarks and logos included in these plugins are property of their respective owners and should not be reused, redistributed, modified, repurposed, or otherwise altered or used outside of this SDK.