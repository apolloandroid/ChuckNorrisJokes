package com.example.chucknorrisjokes.ui.api_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentApiInfoBinding


class ApiInfoFragment : Fragment() {
    private lateinit var binding: FragmentApiInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initBinding(inflater, container)
        setUpWebView()
        loadApiInfoPage()
        return binding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentApiInfoBinding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_api_info, container, false)

    private fun setUpWebView() {
        binding.webApiInfo.settings.apply {
            loadWithOverviewMode
            useWideViewPort = true
            builtInZoomControls = true
            displayZoomControls = false
            setSupportZoom(true)
        }

        binding.webApiInfo.apply {
            setInitialScale(1)
            setPadding(0, 0, 0, 0)
        }
    }

    private fun loadApiInfoPage() {
        binding.webApiInfo.webViewClient = WebViewClient()
        binding.webApiInfo.loadUrl(getString(R.string.jokes_api_url))
    }
}