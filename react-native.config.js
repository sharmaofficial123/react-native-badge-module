module.exports = {
  dependency: {
    platforms: {
      ios: null,
      android: {
        sourceDir: './android',
        packageImportPath: 'import com.reactnativebadgemodule.BadgePackage;',
        packageInstance: 'new BadgePackage()'
      }
    }
  }
};
