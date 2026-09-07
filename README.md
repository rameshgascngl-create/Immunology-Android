# Immunology Companion — Android app (v2.13.0)

Offline WebView wrapper around the **v2.13.0** bilingual Immunology Learning Companion
(M.Sc. Zoology, Units I–V, Department of Zoology, GASC Nagercoil).

**Repository:** https://github.com/rameshgascngl-create/Immunology-Android

Package: `edu.gascnagercoil.immunology`  
versionName `2.13.0` / versionCode `2`  
minSdk 24 / targetSdk 36 / AndroidX WebKit

## Open and run

1. Android Studio → Open → this repository folder.
2. Accept the Gradle wrapper prompt if asked.
3. Sync, then Run on an emulator or phone.

Course assets:

```
app/src/main/assets/immunology/
  index.html
  css/style.css
  js/data-units-1.js
  js/data-units-2.js
  js/data-*.js          (MCQs, cases, glossary, atlas, …)
  js/app-engine.js      v2.13.0 engine
```

No internet permission. Pages load through WebViewAssetLoader.

GitHub Actions still builds a debug APK on each push to `main`.
