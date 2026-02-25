import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  "The native module 'BadgeModule' is not linked. Rebuild the app after installing the package.";

const BadgeModule = NativeModules.BadgeModule;

function getAndroidModule() {
  if (!BadgeModule) {
    throw new Error(LINKING_ERROR);
  }

  return BadgeModule;
}

const Badge = {
  setBadge(count) {
    if (Platform.OS !== 'android') {
      return;
    }

    if (!Number.isInteger(count) || count < 0) {
      throw new Error('Badge count must be a non-negative integer.');
    }

    getAndroidModule().setBadgeCount(count);
  },

  clearBadge() {
    if (Platform.OS !== 'android') {
      return;
    }

    getAndroidModule().clearBadge();
  },

  async getBadgeCount() {
    if (Platform.OS !== 'android') {
      return 0;
    }

    const count = await getAndroidModule().getBadgeCount();
    return typeof count === 'number' ? count : 0;
  }
};

export default Badge;
