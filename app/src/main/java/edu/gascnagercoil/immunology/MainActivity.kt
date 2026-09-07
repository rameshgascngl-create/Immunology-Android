package edu.gascnagercoil.immunology

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat

/**
 * Loads the Immunology Companion (index.html + css/app.js under
 * assets/immunology/) through WebViewAssetLoader, which serves the files
 * over https://appassets.androidplatform.net/ instead of file://.
 *
 * This matters for this specific app: the CSP in index.html sets
 * script-src/style-src to 'self', and file:// URLs are treated as a
 * unique opaque origin by WebView, which breaks that same-origin
 * assumption (and breaks relative fetches generally on modern WebView
 * versions). appassets.androidplatform.net gives the page a real,
 * consistent origin, so 'self' behaves the way the CSP expects.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private val startUrl = "https://appassets.androidplatform.net/assets/immunology/index.html"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)

        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        webView.webViewClient = object : WebViewClientCompat() {
            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? = assetLoader.shouldInterceptRequest(request.url)
        }

        webView.settings.apply {
            javaScriptEnabled = true
            // The app reads/writes localStorage for progress, bookmarks,
            // notes and quiz history. Without this, all of that silently
            // fails to persist.
            domStorageEnabled = true
            allowFileAccess = false
            allowContentAccess = false
            cacheMode = WebSettings.LOAD_DEFAULT
            // Lets students pinch-zoom the SVG diagrams if they want to,
            // without the app's own font-size control feeling mandatory.
            builtInZoomControls = true
            displayZoomControls = false
            textZoom = 100
        }

        if (savedInstanceState == null) {
            webView.loadUrl(startUrl)
        }

        // Back button should navigate the app's own view history (the
        // web app pushes to the HTML5 history API on every section
        // change) before falling back to closing the Activity.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Kept only as a defensive fallback for devices/launchers that
        // still deliver a raw KEYCODE_BACK instead of going through the
        // OnBackPressedDispatcher above.
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
