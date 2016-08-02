//
//  Constants.m
//  SensefyMobile
//
//  Created by Krishantha Jayathilake  on 5/28/15.
//  Copyright (c) 2015 zaizi. All rights reserved.
//

#import "Constants.h"

// Request Handler
NSInteger const kRequestTimeOutInterval = 60;
NSString *const kProtocolHTTP = @"http";
NSString *const kProtocolHTTPS = @"https";
NSString *const kHTTPMethodPOST = @"POST";
NSString *const kHTTPMethodGET = @"GET";

// Notifications
NSString *const kSensefyDataSourceChangedNotification =
    @"SensefyDataSourceChangedNotification";
NSString *const kSensefyFacetsChangedNotification =
    @"SensefyFacetsChangedNotification";
NSString *const kSensefyUpdateFacetsNotification =
    @"SensefyUpdateFacetsNotification";
NSString *const kSensefyUpdateDataSourceNotification =
    @"SensefyUpdateDataSourceNotification";

// User constants
NSString *const kUserAccount = @"account";
NSString *const kUserAccountID = @"accountID";
NSString *const kDataSourceLabelAll = @"All";

// Datasource colors
NSString *const kColorsArray[] = {@"cd392f", @"4f9d97", @"58c0ff", @"d1b6f1",
                                  @"f36e4b", @"8b0a50", @"ef5674", @"f56560",
                                  @"1e3e4a", @"639d41"};

// Unicode characters
NSString *const kUnicodeDownArrow = @"▼";
NSString *const kUnicodeCheckMarkChecked = @"\u2611";
NSString *const kUnicodeCheckMarkUnchecked = @"\u2B1C";

// App constants
NSString *const kUntitledDocument = @"Untitled Document";
NSString *const kNoDescription = @"Description not available";
NSString *const kNoPath = @"Path not available";
NSString *const kNoAuthor = @"Author not available";
NSString *const kNoCreator = @"Creator not available";
NSString *const kNoModifier = @"Modifier not available";
NSString *const kNoSize = @"0 bytes";

NSString *const kDataSource = @"Data Source";

NSString *const kNoDocuments = @"No Documents";
NSString *const kNotAvailable = @"Not Available";
NSString *const kNibSearchTable = @"SearchTableView";

NSString *const kDocumentsFound = @"documents found";

NSString *const kUnknownDate = @"unknown date";
NSString *const kCreatedOn = @"created on";
NSString *const kModifiedOn = @"modified on";

NSString *const kBarButtonSave = @"Save";
NSString *const kBarButtonCancel = @"Cancel";

NSString *const kAccountDetails = @"Account Details";

NSString *const kSectionAccountName = @"Account Name";
NSString *const kSectionUsername = @"Username";
NSString *const kSectionPassword = @"Password";
NSString *const kSectionHost = @"Host";
NSString *const kSectionProtocol = @"Protocol";
NSString *const kSectionPort = @"Port";

NSString *const kDocumentDetails = @"Document Details";
NSString *const kOpenDocument = @"Open Document";

NSString *const kInvalidSession = @"Invalid Session";
NSString *const kInvalidSessionMessage =
    @"Your session has either expired or not valid. Would you like to Login?";

// JSON Date Format
NSString *const kJSONDateFormat = @"yyyy-MM-dd'T'HH:mm:ssZ";

// Similar Search
NSString *const kSimilarSearchFields =
    @"id,score,name,title,mimeType,author,size,thumbnail_base64,data_source";

// Document Search
NSString *const kSearchFields = @"name,title,author,data_source,data_source_"
                                @"type,ds_creation_date,ds_creator,ds_last_"
                                @"modified,ds_last_modifier,language,mimetype,"
                                @"size,id,url,_version_,path";

// Document Meta Data
NSString *const kMetaData = @"name,title,dataSource,dataSourceType,"
                            @"creationDate,creator,lastModified,lastModifier,"
                            @"language,mimeType,size";
NSString *const kMetaDataID = @"id";
NSString *const kMetaDataName = @"name";
NSString *const kMetaDataTitle = @"title";
NSString *const kMetaDataAuthor = @"author";
NSString *const kMetaDataSource = @"dataSource";
NSString *const kMetaDataSourceType = @"dataSourceType";
NSString *const kMetaDataCreationDate = @"creationDate";
NSString *const kMetaDataCreator = @"creator";
NSString *const kMetaDataModifiedDate = @"lastModified";
NSString *const kMetaDataModifier = @"lastModifier";
NSString *const kMetaDataLanguage = @"language";
NSString *const kMetaDataMIMEType = @"mimeType";
NSString *const kMetaDataSize = @"size";
NSString *const kMetaDataScore = @"score";
NSString *const kMetaDataURL = @"url";

// Messages
NSString *const kConnectionError = @"Connection Error";
NSString *const kConnectionSuccess = @"Connection Successful";
NSString *const kConnectionFailed = @"Connection Failed";
NSString *const kNoNetworkConnection = @"No network connection";
NSString *const kNotLoggedIn = @"You are not logged in";
NSString *const kInvalidAccountDetails = @"Invalid account details";
NSString *const kAccountConnected = @"Account connected";
NSString *const kValidationError = @"Validation Error";

NSString *const kNoDocumentsToShow = @"No documents to show";
NSString *const kNoResultsToShow = @"Your search results will appear here";

NSString *const kReleaseForMore = @"Release for more results";

// MIME Types
NSString *const kDoc = @"application/msword";
NSString *const kDocx =
    @"application/vnd.openxmlformats-officedocument.wordprocessingml.document";
NSString *const kHtml = @"text/html";
NSString *const kJs = @"application/x-javascript";
NSString *const kPdf = @"application/pdf";
NSString *const kPpt = @"application/vnd.ms-powerpoint";
NSString *const kPptx = @"application/"
                        @"vnd.openxmlformats-officedocument.presentationml."
                        @"presentation";
NSString *const kXls = @"application/vnd.ms-excel";
NSString *const kXlsx =
    @"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
NSString *const kXml = @"application/xml";
NSString *const kZip = @"application/zip";
NSString *const kZipx = @"application/x-compressed-zip";
NSString *const kImage = @"image/";
NSString *const kVideo = @"video/";
NSString *const kAudio = @"audio/";
NSString *const kText = @"text/plain";
NSString *const kCss = @"text/css";

// Fonts
NSString *const kFontAwesome = @"FontAwesome";
NSString *const kLatoRegular = @"Lato-Regular";
NSString *const kLatoItalic = @"Lato-Italic";
NSString *const kLatoBold = @"Lato-Bold";
NSString *const kLatoBoldItalic = @"Lato-BoldItalic";
NSString *const kLatoLight = @"Lato-Light";
NSString *const kLatoLightItalic = @"Lato-LightItalic";
NSString *const kLatoHairline = @"Lato-Hairline";
NSString *const kLatoHairlineItalic = @"Lato-HairlineItalic";

NSInteger const kDefaultFontSize = 15;
NSInteger const kFontAwesomeFontSize = 24;

// Sort By Titles
NSString *const kSortByTitles[] = {
    @"Relevance",     @"Name",    @"Title",   @"Created Date",
    @"Modified Date", @"Creator", @"Modifier"};
