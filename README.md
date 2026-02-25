# React Native Badge Module

## Comprehensive Documentation

### Android Setup
1. Open your `android/app/build.gradle` file.
2. Add the following dependencies:
   ```gradle
   dependencies {
       implementation 'com.some.library:example:1.0'
   }
   ```
3. Sync your project with Gradle files.

### iOS Notifee Integration
1. Install Notifee using CocoaPods:
   ```bash
   cd ios && pod install && cd ..
   ```
2. Import Notifee in your AppDelegate.m:
   ```objective-c
   #import <Notifee/Notifee.h>
   ```
   Make sure to initialize Notifee in your application.

### Features
- Badge count display
- Custom badge styling
- Integration with notifications

### Installation
To install the package, run:
```bash
npm install react-native-badge-module
```

### Usage Examples
```javascript
import Badge from 'react-native-badge-module';

const App = () => {
    return (
        <Badge count={5} />
    );
};
```

### API Reference
| Prop          | Type   | Description                    |
|---------------|--------|--------------------------------|
| `count`      | number | The number to display on badge.|
| `color`      | string | Badge background color.       |
| `style`      | object | Additional style for the badge.|

### Publishing to npm
1. Make sure you have an npm account.
2. Run the following command to publish:
   ```bash
   npm publish
   ```
3. Ensure you're versioning your package properly before each publish.

---

For more details, refer to the official documentation.