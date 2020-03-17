import { NativeModules, processColor } from "react-native";

const { ButtonSdk } = NativeModules;

const { version } = require("./package.json");
export const VERSION = version;

export function setIdentifier(id) {
  ButtonSdk.setIdentifier(id);
}

export function clearAllData() {
  ButtonSdk.clearAllData();
}

export function startPurchasePath(options) {
  ButtonSdk.startPurchasePath({
    ...options,
    headerTitleColor: processColor(options.headerTitleColor),
    headerSubtitleColor: processColor(options.headerSubtitleColor),
    headerBackgroundColor: processColor(options.headerBackgroundColor),
    headerTintColor: processColor(options.headerTintColor),
    footerBackgroundColor: processColor(options.footerBackgroundColor),
    footerTintColor: processColor(options.footerTintColor)
  });
}

export default {
  VERSION,
  setIdentifier,
  clearAllData,
  startPurchasePath
};
