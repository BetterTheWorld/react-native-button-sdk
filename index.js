import { NativeModules } from "react-native";

// const { ButtonSdk: NativeButtonSdk } = NativeModules;

const { version } = require("./package.json");
export const VERSION = version;

console.info("Initializing react-native-button-sdk v. " + VERSION);

class ButtonSdk {
  // static setIdentifier(id) {
  //   // console.log("---setIdentifier", id, ButtonSdk);
  //   ButtonSdk.setIdentifier(id);
  // }

  // static startPurchasePath(url, token) {
  //   // console.log("NativeModules", NativeModules);
  //   // console.log("---startPurchasePath", url, token);
  //   NativeButtonSdk.startPurchasePath(url, token);
  //   return null;
  // }

  static setup() {
    NativeModules.ButtonSdk.setup();
  }

  static clearAllData() {
    NativeModules.ButtonSdk.clearAllData();
  }
}

export default ButtonSdk;
