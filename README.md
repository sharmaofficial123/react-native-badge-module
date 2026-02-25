# react-native-badge-module

React Native native module to set, clear, and read Android app icon badge count.

## Features

- Android badge update using `ShortcutBadger`
- JS API: `setBadge`, `clearBadge`, `getBadgeCount`
- Android autolinking support
- iOS is intentionally a no-op in JS (returns `0` for `getBadgeCount`)

## Install

```bash
npm install react-native-badge-module
```

## Usage

```js
import Badge from 'react-native-badge-module';

Badge.setBadge(5);
await Badge.getBadgeCount();
Badge.clearBadge();
```

## Android setup

Autolinking handles package registration. Do **not** manually add `BadgePackage()` in `MainApplication`.

After install, rebuild your app:

```bash
cd android
./gradlew clean
cd ..
npx react-native run-android
```

## API

- `setBadge(count: number): void`
- `clearBadge(): void`
- `getBadgeCount(): Promise<number>`

## Local package validation

```bash
npm pack
```

This creates a `.tgz` file so you can test install locally in another app.

## Publish to npm

1. Update `package.json` fields (`name`, `version`, `description`, `repository`, `author`).
2. Login to npm:

```bash
npm login
```

3. Publish:

```bash
npm publish --access public
```

4. For every update, bump version first:

```bash
npm version patch
npm publish
```

## Notes

Badge behavior depends on launcher/manufacturer support on Android devices.
