# Immunology Companion — Android wrapper

Offline WebView wrapper for the GASC Nagercoil Immunology Learning Companion
(M.Sc. Zoology, Units I–V, v2.11.0).

**Repository:** https://github.com/rameshgascngl-create/Immunology-Android

Package: `edu.gascnagercoil.immunology`  
minSdk 24 / targetSdk 36 / AndroidX WebKit

## GitHub can build the APK. It cannot run the phone app.

GitHub Actions (tab **Actions**) compiles a debug APK on every push to `main`, and when you click **Run workflow**.

After the run is green:

1. Open the workflow run.
2. Download the artifact **immunology-companion-debug**.
3. Unzip it and copy the `.apk` to a phone.
4. Allow install from that source, then open **Immunology Companion**.

That is a *build*, not an emulator. Students still install the APK on a real device (or you run the project in Android Studio).

## Course file still required for a useful APK

Copy `app.js` from `ImmunologyAndroid.zip` to:

```
app/src/main/assets/immunology/js/app.js
```

GitHub web UI: **Add file → Upload files** into that folder. Without it the APK installs but lessons are empty.

## Local build (Android Studio)

1. Open this folder (or the unzipped `ImmunologyAndroid` project).
2. Accept the Gradle wrapper prompt.
3. Run on an emulator or device.

Maintained for the Department of Zoology, Government Arts and Science College, Nagercoil.
