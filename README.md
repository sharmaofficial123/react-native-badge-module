# react-native-badge-module

React Native native module to set, clear, and read Android app icon badge count.

## What this library does

- Sets app icon badge count on Android launchers that support badges
- Clears app icon badge count
- Returns the last stored badge count
- Uses `ShortcutBadger` under the hood for Android badge support

## Platform behavior

- Android: fully supported (`setBadge`, `clearBadge`, `getBadgeCount`)
- iOS: this package is a no-op (returns `0` for `getBadgeCount`)

## Install

```bash
npm install react-native-badge-module
```

## Usage

```js
import Badge from 'react-native-badge-module';

Badge.setBadge(5);

const count = await Badge.getBadgeCount();

Badge.clearBadge();
```

## Android notes

- React Native autolinking handles package registration
- Do not manually add `BadgePackage()` in `MainApplication`
- Badge behavior depends on launcher/manufacturer support

## iOS badge handling (Notifee)

If you need iOS badge support, use `@notifee/react-native`:

```js
import notifee from '@notifee/react-native';

await notifee.requestPermission({ badge: true });
await notifee.setBadgeCount(5);
const iosBadgeCount = await notifee.getBadgeCount();
await notifee.setBadgeCount(0);
```

## API

- `setBadge(count: number): void`
- `clearBadge(): void`
- `getBadgeCount(): Promise<number>`
