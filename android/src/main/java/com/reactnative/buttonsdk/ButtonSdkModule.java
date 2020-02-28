package com.reactnative.buttonsdk;

import javax.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.usebutton.sdk.Button;
import com.usebutton.sdk.purchasepath.PurchasePath;
import com.usebutton.sdk.purchasepath.PurchasePathRequest;
import com.usebutton.sdk.purchasepath.PurchasePathListener;

public class ButtonSdkModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public ButtonSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "ButtonSdk";
  }

  @ReactMethod
  public void setIdentifier(String identifier) {
    Button.user().setIdentifier(identifier);
  }

  @ReactMethod
  public void clearAllData() {
    Button.clearAllData();
  }

  @ReactMethod
  public void startPurchasePath(ReadableMap options) {
    PurchasePathRequest request = new PurchasePathRequest(options.getString("url"));

    if (options.hasKey("token") && options.getString("token").length() > 0) {
      request.setPubRef(options.getString("token"));
    }

    Button.purchasePath().fetch(request, new PurchasePathListener() {
      @Override
      public void onComplete(@Nullable PurchasePath purchasePath, @Nullable Throwable throwable) {
        if (purchasePath != null) {
          purchasePath.start(reactContext);
        }
      }
    });
  }
}
