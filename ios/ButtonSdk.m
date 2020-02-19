#import "ButtonSdk.h"
#import "React/RCTConvert.h"
#import <React/RCTLog.h>
@import Button;

@implementation ButtonSdk

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(setIdentifier:(NSString *)identifier) {
  RCTLogInfo(@"setIdentifier %@", identifier);
  [Button.user setIdentifier:identifier];
}

RCT_EXPORT_METHOD(clearAllData) {
  RCTLogInfo(@"clearAllData");
  [Button clearAllData];
}

RCT_EXPORT_METHOD(startPurchasePath:(NSDictionary *)options) {
  NSURL *url = [RCTConvert NSURL:options[@"url"]];

  RCTLogInfo(@"startPurchasePath: url: %@", url);

  BTNPurchasePathRequest *request = [BTNPurchasePathRequest requestWithURL:url];

  if (options[@"token"]) {
    NSString *token = [RCTConvert NSString:options[@"token"]];
    RCTLogInfo(@"startPurchasePath: token: %@", token);
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
