#import "ButtonSdk.h"
#import "React/RCTConvert.h"
#import <React/RCTLog.h>
@import Button;

@implementation ButtonSdk

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(sampleMethod:(NSString *)stringArgument numberParameter:(nonnull NSNumber *)numberArgument callback:(RCTResponseSenderBlock)callback) {
  callback(@[[NSString stringWithFormat: @"numberArgument: %@ stringArgument: %@", numberArgument, stringArgument]]);
}

RCT_EXPORT_METHOD(setIdentifier:(NSString *)identifier) {
  RCTLogInfo(@"setIdentifier %@", identifier);
  [Button.user setIdentifier:identifier];
}

RCT_EXPORT_METHOD(clearAllData) {
  // RCTLogInfo(@"clearAllData");
  [Button clearAllData];
}

// RCT_EXPORT_METHOD(startPurchasePath:(NSString *)urlString token:(NSString *)token) {
//   // NSURL *thing = [RCTConvert NSURL:urlString];
//   NSURL *url = [NSURL URLWithString:urlString];
//   // RCTLogInfo(@"startPurchasePath urlString: %@", urlString);
//   // RCTLogInfo(@"startPurchasePath thing: %@", thing);
//   // RCTLogInfo(@"startPurchasePath url: %@", url);

//   BTNPurchasePathRequest *request = [BTNPurchasePathRequest requestWithURL:url];

//   // Optionally associate a unique token (e.g. campaign Id)
//   // request.pubRef = token;

//   [Button.purchasePath fetchWithRequest:request purchasePathHandler: ^(BTNPurchasePath *purchasePath, NSError *error) {
//     [purchasePath start];
//   }];
// }

// + (BOOL)requiresMainQueueSetup {
//   return YES;
// }

@end
