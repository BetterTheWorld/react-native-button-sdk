/**
 * setIdentifier
 * @param id
 */
export function setIdentifier(id: string): void;

/**
 * clearAllData
 */
export function clearAllData(): void;

/**
 * startPurchasePath
 * @param options
 */
export function startPurchasePath(options: {
  url: string;
  token?: string;
  headerTitle?: string;
  headerSubtitle?: string;
  headerTitleColor?: string;
  headerSubtitleColor?: string;
  headerBackgroundColor?: string;
  headerTintColor?: string;
  footerBackgroundColor?: string;
  footerTintColor?: string;
}): void;

/**
 * openURL
 * @param options
 */
export function openURL(options: {
  url: string;
  title?: string;
  subtitle?: string;
}): void;
