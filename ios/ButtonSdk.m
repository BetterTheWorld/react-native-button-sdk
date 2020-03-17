#import "ButtonSdk.h"
#import "React/RCTConvert.h"
#ifdef DEBUG
  #import <React/RCTLog.h>
#endif
#import "PurchasePathExtension.h"
@import Button;

@implementation ButtonSdk

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(setIdentifier:(NSString *)identifier) {
  #ifdef DEBUG
    RCTLogInfo(@"react-native-button-sdk setIdentifier %@", identifier);
  #endif
  [Button.user setIdentifier:identifier];
}

RCT_EXPORT_METHOD(clearAllData) {
  #ifdef DEBUG
    RCTLogInfo(@"react-native-button-sdk clearAllData");
  #endif
  [Button clearAllData];
}

RCT_EXPORT_METHOD(startPurchasePath:(NSDictionary *)options) {
  NSURL *url = [RCTConvert NSURL:options[@"url"]];

  #ifdef DEBUG
    RCTLogInfo(@"react-native-button-sdk startPurchasePath: url: %@", url);
  #endif

  PurchasePathExtension *purchasePathExtension = [[PurchasePathExtension alloc] initWithOptions:options];
  Button.purchasePath.extension = purchasePathExtension;

  BTNPurchasePathRequest *request = [BTNPurchasePathRequest requestWithURL:url];

  if (options[@"token"]) {
    NSString *token = [RCTConvert NSString:options[@"token"]];
    #ifdef DEBUG
      RCTLogInfo(@"react-native-button-sdk startPurchasePath: token: %@", token);
    #endif
    request.pubRef = token;
  }

  [Button.purchasePath fetchWithRequest:request purchasePathHandler: ^(BTNPurchasePath *purchasePath, NSError *error) {
    [purchasePath start];
  }];
}

+ (BOOL)requiresMainQueueSetup {
  return NO;
}

@end
