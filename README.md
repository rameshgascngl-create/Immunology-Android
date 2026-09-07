# Immunology Companion — Android wrapper

A minimal WebViewAssetLoader-based wrapper around the Immunology Companion
web app, matching the pattern used in the Environmental Sciences Android app
(minSdk 24 / targetSdk 36, `androidx.webkit`).

## What's in here

```
ImmunologyAndroid/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/edu/gascnagercoil/immunology/MainActivity.kt
│       ├── assets/immunology/        ← the web app (index.html, css/, js/)
│       └── res/                      ← icon, layout, strings, themes
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── gradle/wrapper/gradle-wrapper.properties
```

`applicationId` is `edu.gascnagercoil.immunology`, following the same
`edu.gascnagercoil.*` pattern as your Environmental Sciences package.
Change it in `app/build.gradle.kts` before you build if you want something
different.

## Opening it

1. Android Studio → Open → select the `ImmunologyAndroid` folder (this repository root).
2. Let it sync. **The Gradle wrapper jar itself isn't included** — only `gradle-wrapper.properties` is included and points at Gradle 8.7. Android Studio detects the missing jar on first open and offers to regenerate it automatically; accept that prompt, or run `gradle wrapper` once yourself if you have a system Gradle install.
3. Build → Make Project. First sync will download AGP 8.5.2 / Kotlin 1.9.24 / the AndroidX and Material dependencies listed in `app/build.gradle.kts` — needs an internet connection for that step even though the app itself is fully offline at runtime.

## Design choices

- **No `INTERNET` permission declared.** The app has no network calls.
- **`allowFileAccess = false`** in WebView settings — content is served through `https://appassets.androidplatform.net/`.
- **Back button** delegates to `webView.goBack()` first, then exits the Activity.
- Status bar / nav bar colours match the web app theme to avoid a colour flash.
- Icon is a placeholder: navy background, teal ring, "IM" monogram.

## Next steps

1. Open in Android Studio, resolve the Gradle wrapper prompt, sync, build.
2. Run on an emulator or device.
3. Confirm `localStorage` persistence survives a real app restart.
4. Check Atlas-tab scroll performance on a low-memory device.
5. Replace the placeholder launcher icon before a Play Store release.

Maintained for the Department of Zoology, Government Arts and Science College, Nagercoil.
