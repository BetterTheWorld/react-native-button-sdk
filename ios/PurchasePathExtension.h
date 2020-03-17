#import <UIKit/UIKit.h>
#import <Button/Button.h>

@interface PurchasePathExtension : NSObject <BTNPurchasePathExtension>

@property (nonatomic, nullable) NSString *headerTitle;
@property (nonatomic, nullable) NSString *headerSubtitle;
@property (nonatomic, nullable) UIColor *headerTitleColor;
@property (nonatomic, nullable) UIColor *headerSubtitleColor;
@property (nonatomic, nullable) UIColor *headerBackgroundColor;
@property (nonatomic, nullable) UIColor *headerTintColor;
@property (nonatomic, nullable) UIColor *footerBackgroundColor;
@property (nonatomic, nullable) UIColor *footerTintColor;

-(id)initWithOptions:(NSDictionary *)options;

@end
