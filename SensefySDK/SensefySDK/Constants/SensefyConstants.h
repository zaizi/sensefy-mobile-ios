//
//  SensefyConstants.h
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/7/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "AutocompleteResult.h"
#import "KeywordSearchResult.h"
#import "SensefyError.h"
#import "SensefyUser.h"
#import <Foundation/Foundation.h>

@interface SensefyConstants : NSObject

// TYPEDEF
typedef void (^SensefySearchCompletionBlock)(KeywordSearchResult *result,
                                             SensefyError *error);

typedef void (^SensefyArrayCompletionBlock)(AutocompleteResult *result,
                                            SensefyError *error);

typedef void (^SensefyUserCompletionBlock)(SensefyUser *result,
                                           SensefyError *error);

typedef NS_ENUM(NSUInteger, SortByItem) {
  SORT_BY_RELEVANCE = 0,
  SORT_BY_NAME,
  SORT_BY_TITLE,
  SORT_BY_CREATED_DATE,
  SORT_BY_MODIFIED_DATE,
  SORT_BY_CREATOR,
  SORT_BY_MODIFIER
};

typedef NS_ENUM(NSUInteger, SortOrder) { SORT_ASC = 0, SORT_DESC };

extern NSString *const CONFIG_FILE;
extern NSString *const BASE_URL;

// AUTH SERVER
extern NSString *const AUTHORIZE_HEADER;

extern NSString *const SENSEFY_OAUTH2_ACCOUNT_TYPE;
extern NSString *const OAUTH_REDIRECT_URL;
extern NSString *const OAUTH_CLIENT_ID;
extern NSString *const OAUTH_CLIENT_SECRET;
extern NSString *const OAUTH_TOKEN_TYPE;
extern NSString *const OAUTH_RESPONSE_TYPE;
extern NSString *const OAUTH_GRANT_TYPE;
extern NSString *const OAUTH_SCOPES;
extern NSString *const AUTHORIZE_API;
extern NSString *const TOKEN_API;
extern NSString *const LOGOUT_API;

extern NSString *const KEY_ACCESS_TOKEN;
extern NSString *const KEY_REFRESH_TOKEN;
extern NSString *const KEY_TOKEN_TYPE;
extern NSString *const KEY_SCOPE;
extern NSString *const KEY_EXPIRES_IN;
extern NSString *const KEY_GRANT_TYPE;
extern NSString *const KEY_CLIENT_ID;
extern NSString *const KEY_CLIENT_SECRET;
extern NSString *const KEY_REDIRECT_URL;
extern NSString *const KEY_RESPONSE_TYPE;
extern NSString *const KEY_CODE;

// USER
extern NSString *const USER_API;
extern NSString *const KEY_USER_PATH;
extern NSString *const KEY_USERNAME;
extern NSString *const KEY_TIMEZONE;
extern NSString *const KEY_ACCOUNT_NON_EXPIRED;
extern NSString *const KEY_ACCOUNT_NON_LOCKED;
extern NSString *const KEY_CREDENTIALS_NON_EXPIRED;
extern NSString *const KEY_ENABLED;
extern NSString *const SENSEFY_OAUTH2_USER;

// NOTIFICATIONS
extern NSString *const TOKEN_RECEIVED_NOTIFICATION;
extern NSString *const TOKEN_NOT_FOUND_NOTIFICATION;
extern NSString *const TOKEN_EXPIRED_NOTIFICATION;

// API SERVER
extern NSString *const KEYWORD_SEARCH_API;
extern NSString *const AUTOCOMPLETE_API;

// DOCUMENT
extern NSString *const ATTRIBUTE_ID;
extern NSString *const ATTRIBUTE_VERSION;
extern NSString *const ATTRIBUTE_TITLE;
extern NSString *const ATTRIBUTE_NAME;
extern NSString *const ATTRIBUTE_DATA_SOURCE;
extern NSString *const ATTRIBUTE_DATA_SOURCE_TYPE;
extern NSString *const ATTRIBUTE_CREATOR;
extern NSString *const ATTRIBUTE_CREATION_DATE;
extern NSString *const ATTRIBUTE_MODIFIER;
extern NSString *const ATTRIBUTE_MODIFIED_DATE;
extern NSString *const ATTRIBUTE_LANGUAGE;
extern NSString *const ATTRIBUTE_URL;
extern NSString *const ATTRIBUTE_SIZE;
extern NSString *const ATTRIBUTE_PATH;
extern NSString *const ATTRIBUTE_MIME_TYPE;

// ERROR
extern NSString *const ATTRIBUTE_TIMESTAMP;
extern NSString *const ATTRIBUTE_ERROR;
extern NSString *const ATTRIBUTE_EXCEPTION;
extern NSString *const ATTRIBUTE_MESSAGE;
extern NSString *const ATTRIBUTE_ERROR_DESCRIPTION;

// FACET
extern NSString *const ATTRIBUTE_LABEL;
extern NSString *const ATTRIBUTE_FACET_ITEMS;

// FACET ITEM
extern NSString *const ATTRIBUTE_VALUE;
extern NSString *const ATTRIBUTE_OCCURRENCE;
extern NSString *const ATTRIBUTE_FILTER;

// SEARCH RESULT
extern NSString *const ATTRIBUTE_DOCUMENTS;
extern NSString *const ATTRIBUTE_RESULTS_FOUND;
extern NSString *const ATTRIBUTE_START_INDEX;

// SUGGESTION RESULT
extern NSString *const ATTRIBUTE_SUGGESTIONS;
extern NSString *const ATTRIBUTE_TITLES;
extern NSString *const ATTRIBUTE_SUGGESTIONS_COUNT;

// KEYWORD SEARCH RESULT
extern NSString *const ATTRIBUTE_FACETS;
extern NSString *const ATTRIBUTE_SEARCH_RESULTS;

// AUTOCOMPLETE SEARCH RESULT
extern NSString *const ATTRIBUTE_RESPONSE;

// RESULTS HEADER
extern NSString *const ATTRIBUTE_STATUS;
extern NSString *const ATTRIBUTE_QUERY_TIME;
extern NSString *const ATTRIBUTE_QUERY;

// COMMON
extern NSString *const ATTRIBUTE_HEADER;

// KEYWORD SEARCH PARAMETERS
extern NSString *const PARAM_QUERY;
extern NSString *const PARAM_FIELDS;
extern NSString *const PARAM_ORDER;
extern NSString *const PARAM_ROWS;
extern NSString *const PARAM_START;
extern NSString *const PARAM_FACET;
extern NSString *const PARAM_CLUSTERING;
extern NSString *const PARAM_CLUSTERING_SORT;
extern NSString *const PARAM_SPELLCHECK;
extern NSString *const PARAM_FILTERS;

// AUTOCOMPLETE PARAMETERS
extern NSString *const PARAM_TERM;
extern NSString *const PARAM_COUNT;
extern NSString *const PARAM_SEMANTIC;

// SORT
extern NSString *const SORT_BY_ITEMS[];
extern NSString *const SORT_ORDER[];

// DATA SOURCES
extern NSString *const DATASOURCE_ALFRESCO;
@end
