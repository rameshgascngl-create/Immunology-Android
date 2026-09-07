# Minify is disabled in the release buildType by default (isMinifyEnabled = false)
# because this app has no code worth shrinking beyond WebView + a single Activity,
# and the content lives in assets/ (untouched by R8 either way). Flip
# isMinifyEnabled to true in app/build.gradle.kts if you want a smaller APK later;
# no rules are needed for that unless you add reflection-based libraries.
