package com.example.wistron_interview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.Navigation
import com.example.wistron_interview.databinding.FragmentWebviewBinding

class WebViewFragment : BaseFragment() {

    private var mUrl: String? = null
    private var rootView :View? = null
    private var clearHistory = false
    private lateinit var binding: FragmentWebviewBinding
    private val mWebviewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            request?.apply {
            }
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            if(clearHistory){
                binding.webView.clearHistory()
                clearHistory = false
            }
            super.onPageFinished(view, url)
            activity?.apply {
                binding.loading.visibility = View.GONE
            }
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.loading.visibility = View.VISIBLE
        }

        override fun onReceivedError(
            view: WebView,
            errorCode: Int,
            description: String,
            failingUrl: String
        ) {
            binding.webView.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(rootView == null){
            arguments?.apply {
                val args = WebViewFragmentArgs.fromBundle(this)
                mUrl = args.url
            }
            binding = FragmentWebviewBinding.inflate(inflater, container, false)
            setHasOptionsMenu(true)
            binding.back.visibility = View.VISIBLE
            setupViews()
            rootView = binding.root
        }
        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mUrl?.let { binding.webView.loadUrl(it) }
            clearHistory = true
    }

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    private fun setupViews() {
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().removeAllCookies { }
        CookieManager.getInstance().flush()

        val webSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.textZoom = 100
        binding.webView.webViewClient = mWebviewClient

        mUrl?.let { binding.webView.loadUrl(it) }
        binding.webView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action != KeyEvent.ACTION_DOWN)
                return@OnKeyListener true
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                backButton()
                return@OnKeyListener true
            }
            false
        })

        binding.back.setOnClickListener {
            backButton()
        }
    }

    private fun backButton() {
        if(binding.webView.canGoBack()){
            binding.webView.goBack()
        }else{
            Navigation.findNavController(binding.webView).popBackStack()
        }
    }
}
