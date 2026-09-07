# Immunology Companion — Android wrapper

Offline WebView wrapper for the GASC Nagercoil Immunology Learning Companion
(M.Sc. Zoology, Units I–V, v2.11.0).

**Repository:** https://github.com/rameshgascngl-create/Immunology-Android

Package: `edu.gascnagercoil.immunology`  
minSdk 24 / targetSdk 36 / AndroidX WebKit (`WebViewAssetLoader`)

## Open in Android Studio

1. Clone this repo (or unzip `ImmunologyAndroid.zip` and open that folder).
2. Android Studio → Open → this project root.
3. Accept the prompt to generate the missing Gradle wrapper JAR (`gradle-wrapper.properties` already points at Gradle 8.7).
4. Sync / Make Project. First sync downloads AGP 8.5.2, Kotlin 1.9.24, and AndroidX.

## One file still to drop in

`app/src/main/assets/immunology/js/app.js` is the bilingual course engine + dataset (~600 KB). Copy it from the attached `ImmunologyAndroid.zip` into that path before you run the app:

```
app/src/main/assets/immunology/js/app.js
```

On GitHub: **Add file → Upload files** and drop `app.js` into `app/src/main/assets/immunology/js/`.

Adaptive launcher icons (XML) are already in the repo. Density-specific PNG fallbacks are optional.

## Design choices

- No `INTERNET` permission — the app is fully offline.
- WebView loads `https://appassets.androidplatform.net/assets/immunology/index.html` so the page CSP `'self'` works.
- `domStorageEnabled = true` so progress, bookmarks, notes and quiz history persist.
- Back button uses WebView history first, then leaves the Activity.
- Status / nav bar colours match the web theme to avoid a flash on cold start.

## Next steps

1. Add `app.js`, generate the Gradle wrapper, build.
2. Run on an emulator or device.
3. Kill the app from Recents and confirm localStorage still holds progress.
4. Check Atlas-tab scroll performance on a low-memory phone.
5. Replace the placeholder IM monogram icon before a Play Store listing.

Maintained for the Department of Zoology, Government Arts and Science College, Nagercoil.
