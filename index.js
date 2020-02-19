import { NativeModules } from "react-native";

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
  ButtonSdk.startPurchasePath(options);
}

export default {
  VERSION,
  setIdentifier,
  clearAllData,
  startPurchasePath
};
