# React Native Button SDK plugin for Android and iOS

[![npm version](https://badge.fury.io/js/react-native-button-sdk.svg)](https://badge.fury.io/js/react-native-button-sdk) 

## Getting started

```bash
yarn add react-native-button-sdk
```
or
```bash
npm install --save react-native-button-sdk
````

### iOS Setup

1. Install the CocoaPod:
```bash
cd ios && pod install
```

2. Update `ios/YOUR_PROJECT/AppDelegate.m`:

Add this import to the top:
```objective-c
@import Button;
```

Setup iOS SDK with your application key in the `didFinishLaunchingWithOptions` method:

```objective-c
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
  // ... other Objective-C code
  
  #ifdef DEBUG
    // Enable debug logging (optional)
    Button.debug.loggingEnabled = YES;
  #endif
  
  [Button configureWithApplicationId:@"YOUR iOS APPLICATION ID" completion:nil];
  
  // ... other Objective-C code
  return YES;
}  
```

3. If you want to open other apps with the Button browser, you may need to add their app schemes to the `LSApplicationQueriesSchemes` key in your `Info.plist` file. Take a look at the [Button iOS integration guide](https://developer.usebutton.com/guides/publishers/ios/application-tap-integration) for how to do that.

### Android Setup

1. Update `android/app/build.gradle` with the Button SDK dependency:

```gradle
dependencies {
  // ...other Gradle dependencies
  implementation 'com.usebutton:android-sdk:6.+'
  
  implementation "com.facebook.react:react-native:+"
  
  // ...other Gradle dependencies
}
```


2. Update `android/app/src/main/java/YOUR/APP/IDENTIFIER/MainApplication.java`:

Add Button SDK import at the top:

```java
import com.usebutton.sdk.Button;
```

Update the `onCreate` method:
```java
@Override
public void onCreate() {
  super.onCreate();
  // ...other Java code
  
  if (BuildConfig.DEBUG) {
    // Enable debug logging (optional)
    Button.debug().setLoggingEnabled(true);
  }
  Button.configure(this, "YOUR ANDROID APPLICATION ID");

  // ...other Java code
}  
```

2. If you have Proguard enabled, please see the [Android SDK integration guide](https://developer.usebutton.com/guides/publishers/android/application-tap-integration) for the rules to include.


### Javascript Usage

```javascript
import ButtonSDK from "react-native-button-sdk";

// Fetch a purchase path from Button and start the browser
ButtonSDK.startPurchasePath({
  url:                   "https://the.button.url",
  token:                 "my-tracking-token",
  headerTitle:           "My Button Browser Title",
  headerSubtitle:        "My Button Browser Subtitle",
  headerTitleColor:      "#FFFFF",
  headerSubtitleColor:   "#FFFFF",
  headerBackgroundColor: "#FFFFF",
  headerTintColor:       "#FFFFF",
  footerBackgroundColor: "#FFFFF",
  footerTintColor:       "#FFFFF"
});

// Open the browser with a non-Button merchant URL
ButtonSDK.openURL({
  url:      "https://www.google.com",
  title:    "My Browser Title",
  subtitle: "My Browser Subtitle"
});

// On user login
ButtonSDK.setIdentifier(id);

// On user logout
ButtonSDK.clearAllData();
```

## Troubleshooting

Take a look at the [Button iOS integration guide](https://developer.usebutton.com/guides/publishers/ios/application-tap-integration) or [Android SDK integration guide](https://developer.usebutton.com/guides/publishers/android/application-tap-integration) for native integration issues and if there is anything missed please submit a PR to update the above instructions!
