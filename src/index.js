import {Platform, TurboModuleRegistry, NativeModules} from 'react-native';

const BadgeModule =
  TurboModuleRegistry.get('BadgeModule') ?? NativeModules.BadgeModule;

const Badge = {
  setBadge(count) {
    if (Platform.OS !== 'android') return;
    if (!Number.isInteger(count) || count < 0) {
      throw new Error('Badge count must be a non-negative integer.');
    }
    BadgeModule?.setBadgeCount(count);
  },

  clearBadge() {
    if (Platform.OS !== 'android') return;
    BadgeModule?.clearBadge();
  },

  async getBadgeCount() {
    if (Platform.OS !== 'android') return 0;
    const count = await BadgeModule?.getBadgeCount();
    return typeof count === 'number' ? count : 0;
  },
};

export default Badge;