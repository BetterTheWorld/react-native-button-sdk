#import "PurchasePathExtension.h"
#import "React/RCTConvert.h"
#ifdef DEBUG
  #import <React/RCTLog.h>
#endif

@implementation PurchasePathExtension

-(id)initWithOptions:(NSDictionary *)options {
  self = [super init];
  if (self) {
    self.headerTitle           = [RCTConvert NSString:options[@"headerTitle"]];
    self.headerSubtitle        = [RCTConvert NSString:options[@"headerSubtitle"]];
    self.headerTitleColor      = [RCTConvert UIColor:options[@"headerTitleColor"]];
    self.headerSubtitleColor   = [RCTConvert UIColor:options[@"headerSubtitleColor"]];
    self.headerBackgroundColor = [RCTConvert UIColor:options[@"headerBackgroundColor"]];
    self.headerTintColor       = [RCTConvert UIColor:options[@"headerTintColor"]];
    self.footerBackgroundColor = [RCTConvert UIColor:options[@"footerBackgroundColor"]];
    self.footerTintColor       = [RCTConvert UIColor:options[@"footerTintColor"]];
  }
  return self;
}

- (void)browserDidInitialize:(id<BTNBrowserInterface>)browser {
  browser.header.title.text      = self.headerTitle;
  browser.header.subtitle.text   = self.headerSubtitle;
  browser.header.title.color     = self.headerTitleColor;
  browser.header.subtitle.color  = self.headerSubtitleColor;
  browser.header.backgroundColor = self.headerBackgroundColor;
  browser.header.tintColor       = self.headerTintColor;
  browser.footer.backgroundColor = self.footerBackgroundColor;
  browser.footer.tintColor       = self.footerTintColor;
}

- (void)browserWillNavigate:(id<BTNBrowserInterface>)browser {
  [browser hideTopCard];
}

- (void)browserDidClose {
  #ifdef DEBUG
    RCTLogInfo(@"react-native-button-sdk browserDidClose");
  #endif
}

@end
