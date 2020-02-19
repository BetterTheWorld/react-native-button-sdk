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
}): void;
