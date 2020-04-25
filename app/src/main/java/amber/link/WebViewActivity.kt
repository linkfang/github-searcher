package amber.link

/*
* Created by Amber Yiyao Zhou & Link Zhou Fang on December 02, 2019
*/

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    //region method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url =  intent.getStringExtra(getString(R.string.url_key))

        webViewGitHub.settings.javaScriptEnabled
        webViewGitHub.settings.loadWithOverviewMode
        webViewGitHub.settings.useWideViewPort

        webViewGitHub.loadUrl(url)
    }
    //endregion
}
