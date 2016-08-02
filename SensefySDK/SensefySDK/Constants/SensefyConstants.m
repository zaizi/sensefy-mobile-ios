//
//  SensefyConstants.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/7/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "SensefyConstants.h"

@implementation SensefyConstants

// CONFIGURATIONS
NSString *const CONFIG_FILE = @"Config";
NSString *const BASE_URL = @"BASE_URL";
NSString *const OAUTH_REDIRECT_URL = @"sensefy://authorize";
NSString *const OAUTH_CLIENT_ID = @"CLIENT_ID";
NSString *const OAUTH_CLIENT_SECRET = @"CLIENT_SECRET";
NSString *const OAUTH_TOKEN_TYPE = @"Bearer";
NSString *const OAUTH_RESPONSE_TYPE = @"code";
NSString *const OAUTH_GRANT_TYPE = @"authorization_code";
NSString *const OAUTH_SCOPES = @"openid";

// AUTH SERVER
NSString *const AUTHORIZE_HEADER = @"Authorization";
NSString *const SENSEFY_OAUTH2_ACCOUNT_TYPE = @"SensefyOAuthAccountType";
NSString *const AUTHORIZE_API = @"/auth/oauth/authorize";
NSString *const TOKEN_API = @"/auth/oauth/token";
NSString *const LOGOUT_API = @"auth/logout";

NSString *const KEY_ACCESS_TOKEN = @"access_token";
NSString *const KEY_REFRESH_TOKEN = @"refresh_token";
NSString *const KEY_TOKEN_TYPE = @"token_type";
NSString *const KEY_SCOPE = @"scope";
NSString *const KEY_EXPIRES_IN = @"expires_in";
NSString *const KEY_GRANT_TYPE = @"grant_type";
NSString *const KEY_CLIENT_ID = @"client_id";
NSString *const KEY_CLIENT_SECRET = @"client_secret";
NSString *const KEY_REDIRECT_URL = @"redirect_uri";
NSString *const KEY_RESPONSE_TYPE = @"response_type";
NSString *const KEY_CODE = @"code";

// USER
NSString *const USER_API = @"/auth/user";
NSString *const KEY_USER_PATH = @"principal";
NSString *const KEY_USERNAME = @"username";
NSString *const KEY_TIMEZONE = @"timezone";
NSString *const KEY_ACCOUNT_NON_EXPIRED = @"accountNonExpired";
NSString *const KEY_ACCOUNT_NON_LOCKED = @"accountNonLocked";
NSString *const KEY_CREDENTIALS_NON_EXPIRED = @"credentialsNonExpired";
NSString *const KEY_ENABLED = @"enabled";
NSString *const SENSEFY_OAUTH2_USER = @"SensefyOAuthUser";

// NOTIFICATIONS
NSString *const TOKEN_RECEIVED_NOTIFICATION = @"sensefy_token_received";
NSString *const TOKEN_NOT_FOUND_NOTIFICATION = @"sensefy_token_not_available";
NSString *const TOKEN_EXPIRED_NOTIFICATION = @"sensefy_token_has_expired";

// API SERVER
NSString *const KEYWORD_SEARCH_API = @"/api/keywordSearch";
NSString *const AUTOCOMPLETE_API = @"/api/autocomplete/1";

// SENSEFY DOCUMENT
NSString *const ATTRIBUTE_ID = @"id";
NSString *const ATTRIBUTE_VERSION = @"_version_";
NSString *const ATTRIBUTE_TITLE = @"title";
NSString *const ATTRIBUTE_NAME = @"name";
NSString *const ATTRIBUTE_DATA_SOURCE = @"data_source";
NSString *const ATTRIBUTE_DATA_SOURCE_TYPE = @"data_source_type";
NSString *const ATTRIBUTE_CREATOR = @"ds_creator";
NSString *const ATTRIBUTE_CREATION_DATE = @"ds_creation_date";
NSString *const ATTRIBUTE_MODIFIER = @"ds_last_modifier";
NSString *const ATTRIBUTE_MODIFIED_DATE = @"ds_last_modified";
NSString *const ATTRIBUTE_LANGUAGE = @"language";
NSString *const ATTRIBUTE_URL = @"url";
NSString *const ATTRIBUTE_SIZE = @"size";
NSString *const ATTRIBUTE_PATH = @"path";
NSString *const ATTRIBUTE_MIME_TYPE = @"mimetype";

// ERROR
NSString *const ATTRIBUTE_TIMESTAMP = @"timestamp";
NSString *const ATTRIBUTE_ERROR = @"error";
NSString *const ATTRIBUTE_EXCEPTION = @"exception";
NSString *const ATTRIBUTE_MESSAGE = @"message";
NSString *const ATTRIBUTE_ERROR_DESCRIPTION = @"error_description";

// SENSEFY FACET
NSString *const ATTRIBUTE_LABEL = @"label";
NSString *const ATTRIBUTE_FACET_ITEMS = @"facetItems";

// SENSEFY FACET ITEM
NSString *const ATTRIBUTE_VALUE = @"value";
NSString *const ATTRIBUTE_FILTER = @"filter";
NSString *const ATTRIBUTE_OCCURRENCE = @"occurrence";

// SENSEFY SEARCH RESULT
NSString *const ATTRIBUTE_DOCUMENTS = @"documents";
NSString *const ATTRIBUTE_RESULTS_FOUND = @"numFound";
NSString *const ATTRIBUTE_START_INDEX = @"start";

// SUGGESTION RESULT
NSString *const ATTRIBUTE_SUGGESTIONS = @"suggestions";
NSString *const ATTRIBUTE_TITLES = @"titles";
NSString *const ATTRIBUTE_SUGGESTIONS_COUNT = @"numberOfSuggestions";

// SENSEFY KEYWORD SEARCH RESULT
NSString *const ATTRIBUTE_FACETS = @"facets";
NSString *const ATTRIBUTE_SEARCH_RESULTS = @"searchResults";

// AUTOCOMPLETE SEARCH RESULT
NSString *const ATTRIBUTE_RESPONSE = @"responseContent";

// SENSEFY HEADER
NSString *const ATTRIBUTE_STATUS = @"status";
NSString *const ATTRIBUTE_QUERY_TIME = @"qtime";
NSString *const ATTRIBUTE_QUERY = @"query";

// COMMON
NSString *const ATTRIBUTE_HEADER = @"header";

// KEYWORD SEARCH PARAMETERS
NSString *const PARAM_QUERY = @"query";
NSString *const PARAM_FIELDS = @"fields";
NSString *const PARAM_ORDER = @"order";
NSString *const PARAM_ROWS = @"rows";
NSString *const PARAM_START = @"start";
NSString *const PARAM_FACET = @"facet";
NSString *const PARAM_CLUSTERING = @"clustering";
NSString *const PARAM_CLUSTERING_SORT = @"clusteringSort";
NSString *const PARAM_SPELLCHECK = @"spellcheck";
NSString *const PARAM_FILTERS = @"filters";

// AUTOCOMPLETE PARAMETERS
NSString *const PARAM_TERM = @"termToComplete";
NSString *const PARAM_COUNT = @"numberOfSuggestions";
NSString *const PARAM_SEMANTIC = @"semantic";

NSString *const SORT_BY_ITEMS[] = {@"score",
                                   @"name_sort",
                                   @"title_sort",
                                   @"ds_creation_date",
                                   @"ds_last_modified",
                                   @"ds_creator_sort",
                                   @"ds_last_modifier_sort"};

NSString *const SORT_ORDER[] = {@"ASC", @"DESC"};

// DATA SOURCES
NSString *const DATASOURCE_ALFRESCO = @"Alfresco";

@end
