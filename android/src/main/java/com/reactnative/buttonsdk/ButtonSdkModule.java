package com.reactnative.buttonsdk;

import android.util.Log;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.usebutton.sdk.Button;
import com.usebutton.sdk.purchasepath.PurchasePath;
import com.usebutton.sdk.purchasepath.BrowserInterface;
import com.usebutton.sdk.purchasepath.PurchasePathRequest;
import com.usebutton.sdk.purchasepath.PurchasePathListener;
import com.usebutton.sdk.purchasepath.PurchasePathExtension;
import com.usebutton.sdk.purchasepath.ProductPage;
import com.usebutton.sdk.purchasepath.PurchasePage;
import com.usebutton.sdk.purchasepath.BrowserPage;

public class ButtonSdkModule extends ReactContextBaseJavaModule {

  public static final String REACT_CLASS = "ButtonSdk";

  private final ReactApplicationContext reactContext;

  public ButtonSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @ReactMethod
  public void setIdentifier(String identifier) {
    Log.d(REACT_CLASS, "setIdentifier: " + identifier);
    Button.user().setIdentifier(identifier);
  }

  @ReactMethod
  public void clearAllData() {
    Log.d(REACT_CLASS, "clearAllData");
    Button.clearAllData();
  }

  @ReactMethod
  public void startPurchasePath(final ReadableMap options) {
    Log.d(REACT_CLASS, "startPurchasePath, url:" + options.getString("url"));
    PurchasePathRequest request = new PurchasePathRequest(options.getString("url"));

    if (options.hasKey("token") && options.getString("token").length() > 0) {
      Log.d(REACT_CLASS, "startPurchasePath, token: " + options.getString("url"));
      request.setPubRef(options.getString("token"));
    }

    Button.purchasePath().fetch(request, new PurchasePathListener() {
      @Override
      public void onComplete(@Nullable PurchasePath purchasePath, @Nullable Throwable throwable) {
        Button.purchasePath().setExtension(new CustomPurchasePathExtension(options));
        if (purchasePath != null) {
          purchasePath.start(reactContext.getApplicationContext());
        } else {
          Log.e(REACT_CLASS, "Error fetching purchasePath", throwable);
        }
      }
    });
  }

  private static class CustomPurchasePathExtension implements PurchasePathExtension {

    private final ReadableMap purchasePathOptions;

    CustomPurchasePathExtension(final ReadableMap purchasePathOptions) {
      this.purchasePathOptions = purchasePathOptions;
    }

    @Override
    public void onInitialized(@NonNull BrowserInterface browser) {
      browser.getHeader().getTitle().setText(purchasePathOptions.getString("headerTitle"));
      browser.getHeader().getSubtitle().setText(purchasePathOptions.getString("headerSubtitle"));
      browser.getHeader().getTitle().setColor(purchasePathOptions.getInt("headerTitleColor"));
      browser.getHeader().getSubtitle().setColor(purchasePathOptions.getInt("headerSubtitleColor"));
      browser.getHeader().setBackgroundColor(purchasePathOptions.getInt("headerBackgroundColor"));
      browser.getHeader().setTintColor(purchasePathOptions.getInt("headerTintColor"));
      browser.getFooter().setBackgroundColor(purchasePathOptions.getInt("footerBackgroundColor"));
      browser.getFooter().setTintColor(purchasePathOptions.getInt("footerTintColor"));
    }

    @Override
    public void onStartNavigate(@NonNull BrowserInterface browser) {}

    @Override
    public void onPageNavigate(@NonNull BrowserInterface browser, @NonNull BrowserPage page) {}

    @Override
    public void onProductNavigate(@NonNull BrowserInterface browser, @NonNull ProductPage page) {}

    @Override
    public void onPurchaseNavigate(@NonNull BrowserInterface browser, @NonNull PurchasePage page) {}

    @Override
    public void onClosed() {}
  }
}
