package com.reactnative.buttonsdk;

import android.util.Log;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.usebutton.sdk.Button;
import com.usebutton.sdk.purchasepath.PurchasePath;
import com.usebutton.sdk.purchasepath.PurchasePathRequest;
import com.usebutton.sdk.purchasepath.PurchasePathListener;

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
  public void startPurchasePath(ReadableMap options) {
    Log.d(REACT_CLASS, "startPurchasePath, url:" + options.getString("url"));
    PurchasePathRequest request = new PurchasePathRequest(options.getString("url"));

    if (options.hasKey("token") && options.getString("token").length() > 0) {
      Log.d(REACT_CLASS, "startPurchasePath, token: " + options.getString("url"));
      request.setPubRef(options.getString("token"));
    }

    Button.purchasePath().fetch(request, new PurchasePathListener() {
      @Override
      public void onComplete(@Nullable PurchasePath purchasePath, @Nullable Throwable throwable) {
        Button.purchasePath().setExtension(null);
        if (purchasePath != null) {
          purchasePath.start(reactContext.getApplicationContext());
        } else {
          Log.e(REACT_CLASS, "Error fetching purchasePath", throwable);
        }
      }
    });
  }
}
