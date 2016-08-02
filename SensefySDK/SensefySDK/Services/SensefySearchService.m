//
//  SensefySearchService.m
//  Sensefy
//
//  Created by Krishantha Jayathilake  on 1/10/16.
//  Copyright © 2016 zaizi. All rights reserved.
//

#import "AutocompleteResult.h"
#import "FacetItem.h"
#import "KeywordSearchResult.h"
#import "ResponseHeader.h"
#import "SearchResult.h"
#import "SensefyAccessToken.h"
#import "SensefyDocument.h"
#import "SensefyFacet.h"
#import "SensefyLoginService.h"
#import "SensefySearchService.h"
#import "SensefyUtils.h"
#import "SuggestionsResult.h"

@implementation SensefySearchService

static dispatch_once_t onceTokenForSharedInstance = 0;

static SensefySearchService *sharedInstance = nil;

+ (SensefySearchService *)sharedInstance {
  dispatch_once(&onceTokenForSharedInstance, ^{
    if (sharedInstance == nil) {
      sharedInstance = [[self alloc] init];
    }
  });
  [sharedInstance initializeObjectManagers];
  return sharedInstance;
}

#pragma mark-- Service Methods --
// Search documents with keyword

- (void)searchWithKeyword:(NSString *)keyword
                   fields:(NSString *)fields
                  orderBy:(SortByItem)orderBy
                    order:(SortOrder)order
                     rows:(int)rows
                    start:(int)start
                    facet:(BOOL)facet
               clustering:(BOOL)clustering
           clusteringSort:(BOOL)clusteringSort
               spellcheck:(BOOL)spellcheck
                  filters:(NSString *)filters
          completionBlock:(SensefySearchCompletionBlock)completionBlock {

  NSLog(@"Filters are : %@", filters);

  NSString *sOrder = [NSString
      stringWithFormat:@"%@ %@", SORT_BY_ITEMS[orderBy], SORT_ORDER[order]];
  NSString *sRows = [NSString stringWithFormat:@"%d", rows];
  NSString *sStart = [NSString stringWithFormat:@"%d", start];
  NSString *sFacet = facet ? @"true" : @"false";
  NSString *sClustering = clustering ? @"true" : @"false";
  NSString *sClusteringSort = clusteringSort ? @"true" : @"false";
  NSString *sSpellcheck = spellcheck ? @"true" : @"false";

  NSDictionary *params = @{
    PARAM_QUERY : keyword,
    PARAM_FIELDS : fields,
    PARAM_ORDER : sOrder,
    PARAM_ROWS : sRows,
    PARAM_START : sStart,
    PARAM_FACET : sFacet,
    PARAM_CLUSTERING : sClustering,
    PARAM_CLUSTERING_SORT : sClusteringSort,
    PARAM_SPELLCHECK : sSpellcheck,
    PARAM_FILTERS : filters
  };

  [[RKObjectManager sharedManager] getObjectsAtPath:KEYWORD_SEARCH_API
      parameters:params
      success:^(RKObjectRequestOperation *operation,
                RKMappingResult *mappingResult) {

        KeywordSearchResult *result = [mappingResult firstObject];
        completionBlock(result, nil);

      }
      failure:^(RKObjectRequestOperation *operation, NSError *error) {

        SensefyError *errorMessage = [[error.userInfo
            objectForKey:RKObjectMapperErrorObjectsKey] firstObject];
        completionBlock(nil, errorMessage);

      }];
}

// Get list of suggestions based on term entered

- (void)autoCompleteWithTerm:(NSString *)term
         numberOfSuggestions:(int)numberOfSuggestions
                    semantic:(BOOL)semantic
             completionBlock:(SensefyArrayCompletionBlock)completionBlock {

  NSString *sSuggestions =
      [NSString stringWithFormat:@"%d", numberOfSuggestions];
  NSString *sSemantic = semantic ? @"true" : @"false";

  NSDictionary *params = @{
    PARAM_TERM : term,
    PARAM_COUNT : sSuggestions,
    PARAM_SEMANTIC : sSemantic
  };

  [[RKObjectManager sharedManager] getObjectsAtPath:AUTOCOMPLETE_API
      parameters:params
      success:^(RKObjectRequestOperation *operation,
                RKMappingResult *mappingResult) {

        AutocompleteResult *result = [mappingResult firstObject];
        completionBlock(result, nil);

      }
      failure:^(RKObjectRequestOperation *operation, NSError *error) {

        SensefyError *errorMessage = [[error.userInfo
            objectForKey:RKObjectMapperErrorObjectsKey] firstObject];

        completionBlock(nil, errorMessage);

      }];
}

#pragma mark-- Private Methods --

// Configuration of RestKit object manager and mapping of network objects to
// model objects using RestKit object modelling.

- (void)initializeObjectManagers {

  // Uncomment below to enable rest kit logging.
  // RKLogConfigureByName("RestKit/Network", RKLogLevelTrace);
  // RKLogConfigureByName("Restkit/Network", RKLogLevelDebug);
  // RKLogConfigureByName("RestKit/ObjectMapping", RKLogLevelTrace);
  // RKLogConfigureByName("Restkit/ObjectMapping", RKLogLevelDebug);

  NSString *path =
      [[NSBundle mainBundle] pathForResource:CONFIG_FILE ofType:@"plist"];
  NSDictionary *settings = [[NSDictionary alloc] initWithContentsOfFile:path];

  SensefyAccessToken *token =
      [[SensefyLoginService sharedInstance] getAccessToken];

  if (nil != token) {

    if ([[SensefyLoginService sharedInstance] tokenExpired]) {
      [[NSNotificationCenter defaultCenter]
          postNotificationName:TOKEN_EXPIRED_NOTIFICATION
                        object:nil];
      [[SensefyLoginService sharedInstance] logoutFromSensefy];
    }

    // Configure the object manager.
    RKObjectManager *objectManager = [RKObjectManager
        managerWithBaseURL:[NSURL
                               URLWithString:[settings valueForKey:BASE_URL]]];
    objectManager.requestSerializationMIMEType = RKMIMETypeJSON;
    [[objectManager HTTPClient]
        setDefaultHeader:AUTHORIZE_HEADER
                   value:[NSString stringWithFormat:@"%@ %@", OAUTH_TOKEN_TYPE,
                                                    token.accessToken]];

    [RKObjectManager setSharedManager:objectManager];

    // -- Declare routes -- //

    // Now configure the FacetItems mapping
    RKObjectMapping *facetItemsMapping =
        [RKObjectMapping mappingForClass:[FacetItem class]];
    [facetItemsMapping addAttributeMappingsFromDictionary:@{
      ATTRIBUTE_VALUE : @"value",
      ATTRIBUTE_FILTER : @"filter",
      ATTRIBUTE_OCCURRENCE : @"occurrence"
    }];

    // Now configure the SensefyFacet mapping
    RKObjectMapping *sensefyFacetMapping =
        [RKObjectMapping mappingForClass:[SensefyFacet class]];
    [sensefyFacetMapping addAttributeMappingsFromDictionary:@{
      ATTRIBUTE_LABEL : @"label"
    }];

    // Define the relationship mapping
    [sensefyFacetMapping
        addPropertyMapping:
            [RKRelationshipMapping
                relationshipMappingFromKeyPath:ATTRIBUTE_FACET_ITEMS
                                     toKeyPath:@"facetItems"
                                   withMapping:facetItemsMapping]];

    RKBlockValueTransformer *numberToDateValueTransformer =
        [RKBlockValueTransformer
            valueTransformerWithValidationBlock:^BOOL(
                __unsafe_unretained Class sourceClass,
                __unsafe_unretained Class destinationClass) {
              // This transformer handles `NSNumber` <-> `NSDate`
              // transformations
              return (([sourceClass isSubclassOfClass:[NSNumber class]] &&
                       [destinationClass isSubclassOfClass:[NSDate class]]) ||
                      ([sourceClass isSubclassOfClass:[NSDate class]] &&
                       [destinationClass isSubclassOfClass:[NSNumber class]]));
            }
            transformationBlock:^BOOL(
                id inputValue, __autoreleasing id *outputValue,
                __unsafe_unretained Class outputValueClass,
                NSError *__autoreleasing *error) {
              RKValueTransformerTestInputValueIsKindOfClass(
                  inputValue, (@[ [NSNumber class], [NSDate class] ]), error);
              RKValueTransformerTestOutputValueClassIsSubclassOfClass(
                  outputValueClass, (@[ [NSNumber class], [NSDate class] ]),
                  error);
              if ([outputValueClass isSubclassOfClass:[NSDate class]]) {
                if ([inputValue isKindOfClass:[NSNumber class]]) {
                  *outputValue = [NSDate
                      dateWithTimeIntervalSince1970:[inputValue doubleValue] /
                                                    1000];
                }
              } else if ([outputValueClass
                             isSubclassOfClass:[NSNumber class]]) {
                *outputValue = @([inputValue timeIntervalSince1970]);
              }
              return YES;
            }];

    // Now configure the SensefyDocument mapping
    RKObjectMapping *sensefyDocumentMapping =
        [RKObjectMapping mappingForClass:[SensefyDocument class]];
    [sensefyDocumentMapping addAttributeMappingsFromDictionary:@{
      ATTRIBUTE_ID : @"documentID",
      ATTRIBUTE_VERSION : @"version",
      ATTRIBUTE_DATA_SOURCE : @"dataSource",
      ATTRIBUTE_DATA_SOURCE_TYPE : @"dataSourceType",
      ATTRIBUTE_CREATOR : @"creator",
      ATTRIBUTE_MODIFIER : @"lastModifier",
      ATTRIBUTE_LANGUAGE : @"language",
      ATTRIBUTE_MIME_TYPE : @"mimeType",
      ATTRIBUTE_NAME : @"name",
      ATTRIBUTE_PATH : @"path",
      ATTRIBUTE_SIZE : @"size",
      ATTRIBUTE_TITLE : @"title",
      ATTRIBUTE_URL : @"url",
    }];

    RKAttributeMapping *creationDateMapping =
        [RKAttributeMapping attributeMappingFromKeyPath:ATTRIBUTE_CREATION_DATE
                                              toKeyPath:@"creationDate"];
    creationDateMapping.valueTransformer = numberToDateValueTransformer;
    [sensefyDocumentMapping addPropertyMapping:creationDateMapping];

    RKAttributeMapping *modifiedDateMapping =
        [RKAttributeMapping attributeMappingFromKeyPath:ATTRIBUTE_MODIFIED_DATE
                                              toKeyPath:@"lastModified"];
    modifiedDateMapping.valueTransformer = numberToDateValueTransformer;
    [sensefyDocumentMapping addPropertyMapping:modifiedDateMapping];

    // Now configure the SearchResults mapping
    RKObjectMapping *searchResutsMapping =
        [RKObjectMapping mappingForClass:[SearchResult class]];
    [searchResutsMapping addAttributeMappingsFromDictionary:@{
      ATTRIBUTE_RESULTS_FOUND : @"resultsFound",
      ATTRIBUTE_START_INDEX : @"startIndex"
    }];

    // Define the relationship mapping
    [searchResutsMapping
        addPropertyMapping:
            [RKRelationshipMapping
                relationshipMappingFromKeyPath:ATTRIBUTE_DOCUMENTS
                                     toKeyPath:@"documents"
                                   withMapping:sensefyDocumentMapping]];

    // Now configure the SearchResults mapping
    RKObjectMapping *keywordSearchResutsMapping =
        [RKObjectMapping mappingForClass:[KeywordSearchResult class]];
    [keywordSearchResutsMapping addAttributeMappingsFromDictionary:@{
      ATTRIBUTE_ERROR : @"error"
    }];

    // Now configure the Header mapping
    RKObjectMapping *responseHeaderMapping =
        [RKObjectMapping mappingForClass:[ResponseHeader class]];
    [responseHeaderMapping addAttributeMappingsFromDictionary:@{
      ATTRIBUTE_STATUS : @"status",
      ATTRIBUTE_QUERY : @"query",
      ATTRIBUTE_QUERY_TIME : @"queryTime"
    }];

    // Define the relationship mapping
    [keywordSearchResutsMapping
        addPropertyMapping:
            [RKRelationshipMapping
                relationshipMappingFromKeyPath:ATTRIBUTE_FACETS
                                     toKeyPath:@"facets"
                                   withMapping:sensefyFacetMapping]];
    // Define the relationship mapping
    [keywordSearchResutsMapping
        addPropertyMapping:
            [RKRelationshipMapping
                relationshipMappingFromKeyPath:ATTRIBUTE_SEARCH_RESULTS
                                     toKeyPath:@"searchResults"
                                   withMapping:searchResutsMapping]];

    // Define the relationship mapping
    [keywordSearchResutsMapping
        addPropertyMapping:
            [RKRelationshipMapping
                relationshipMappingFromKeyPath:ATTRIBUTE_HEADER
                                     toKeyPath:@"header"
                                   withMapping:responseHeaderMapping]];

    RKResponseDescriptor *keywordSearchResponseDescriptor =
        [RKResponseDescriptor
            responseDescriptorWithMapping:keywordSearchResutsMapping
                                   method:RKRequestMethodGET
                              pathPattern:KEYWORD_SEARCH_API
                                  keyPath:nil
                              statusCodes:RKStatusCodeIndexSetForClass(
                                              RKStatusCodeClassSuccessful)];

    [objectManager addResponseDescriptor:keywordSearchResponseDescriptor];

    // Now configure the SuggestionResults mapping
    RKObjectMapping *sugestionResutsMapping =
        [RKObjectMapping mappingForClass:[SuggestionsResult class]];
    [sugestionResutsMapping addAttributeMappingsFromDictionary:@{
      ATTRIBUTE_SUGGESTIONS : @"suggestions",
      ATTRIBUTE_TITLES : @"titles",
      ATTRIBUTE_SUGGESTIONS_COUNT : @"numberOfSuggestions"
    }];

    // Now configure the SuggestionResults mapping
    RKObjectMapping *autocompleteSearchResutsMapping =
        [RKObjectMapping mappingForClass:[AutocompleteResult class]];
    [autocompleteSearchResutsMapping addAttributeMappingsFromDictionary:@{
      ATTRIBUTE_ERROR : @"error"
    }];

    // Define the relationship mapping
    [autocompleteSearchResutsMapping
        addPropertyMapping:
            [RKRelationshipMapping
                relationshipMappingFromKeyPath:ATTRIBUTE_RESPONSE
                                     toKeyPath:@"response"
                                   withMapping:sugestionResutsMapping]];

    // Define the relationship mapping
    [autocompleteSearchResutsMapping
        addPropertyMapping:
            [RKRelationshipMapping
                relationshipMappingFromKeyPath:ATTRIBUTE_HEADER
                                     toKeyPath:@"header"
                                   withMapping:responseHeaderMapping]];

    RKResponseDescriptor *autocompleteResponseDescriptor = [RKResponseDescriptor
        responseDescriptorWithMapping:autocompleteSearchResutsMapping
                               method:RKRequestMethodGET
                          pathPattern:AUTOCOMPLETE_API
                              keyPath:nil
                          statusCodes:RKStatusCodeIndexSetForClass(
                                          RKStatusCodeClassSuccessful)];

    [objectManager addResponseDescriptor:autocompleteResponseDescriptor];

    RKObjectMapping *errorMapping =
        [RKObjectMapping mappingForClass:[SensefyError class]];

    [errorMapping addAttributeMappingsFromDictionary:@{
      ATTRIBUTE_TIMESTAMP : @"timestamp",
      ATTRIBUTE_STATUS : @"status",
      ATTRIBUTE_ERROR : @"error",
      ATTRIBUTE_EXCEPTION : @"exception",
      ATTRIBUTE_MESSAGE : @"message",
      ATTRIBUTE_PATH : @"path"
    }];

    NSIndexSet *statusCodes =
        RKStatusCodeIndexSetForClass(RKStatusCodeClassClientError);

    RKResponseDescriptor *errorDescriptor =
        [RKResponseDescriptor responseDescriptorWithMapping:errorMapping
                                                     method:RKRequestMethodAny
                                                pathPattern:nil
                                                    keyPath:@"errors"
                                                statusCodes:statusCodes];

    [objectManager addResponseDescriptor:errorDescriptor];

    // Enable Activity Indicator Spinner
    [AFNetworkActivityIndicatorManager sharedManager].enabled = YES;
  } else {
    DDLogError(@"Access token is not available");
    [[NSNotificationCenter defaultCenter]
        postNotificationName:TOKEN_NOT_FOUND_NOTIFICATION
                      object:nil];
  }
}

@end
