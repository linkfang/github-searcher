package amber.link

/*
* Created by Amber Yiyao Zhou & Link Zhou Fang on December 02, 2019
*/

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import okhttp3.*
import java.io.IOException

class DetailsActivity : AppCompatActivity() {

    // region method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.title = intent.getStringExtra(CustomViewHolderClass.TITLE_KEY)

        val data = intent.getSerializableExtra(CustomViewHolderClass.OBJECT_KEY) as Users

        // set underline text on TextView control
        val text = data.html_url //"Underlined Text"
        val content = SpannableString(text)
        content.setSpan(UnderlineSpan(), 0, text.length, 0)
        htmlURLTextView.text = content

        htmlURLTextView.setOnClickListener{
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(getString(R.string.url_key), data.html_url)
            this.startActivity(intent)
        }

        fetchJson(data.url)

    }

    private fun fetchJson(url: String){

        // We are using okhttp client here, not Retrofit2
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback { // can't execute from main thread!
            override fun onFailure(call: Call, e: IOException) {
                toast("Request Failed!")
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response.body()?.string()

                val gson = GsonBuilder().create()
                val result = gson.fromJson(body, UserDetails::class.java)

                runOnUiThread {
                    Picasso.get().load(result.avatar_url).into(avatarImageView)

                    nameTextView.text =  getString(R.string.user_name, result?.name ?: "unknown")

                    //add more details

                    locationTextView.text =  getString(R.string.user_location, result?.location ?: "unknown")
                    companyTextView.text =  getString(R.string.user_company, result?.company ?: "unknown")
                    followersTextView.text =  getString(R.string.user_followers, result?.followers ?: "unknown")
                    gistsTextView.text =  getString(R.string.user_gists, result?.public_gists ?: "unknown")
                    reposTextView.text =  getString(R.string.user_repos, result?.public_repos ?: "unknown")
                    createdTextView.text =  getString(R.string.user_created, result?.created_at?.replace("T"," ")?.replace("Z", "") ?: "unknown")
                    updatedTextView.text =  getString(R.string.user_updated, result?.updated_at?.replace("T"," ")?.replace("Z", "") ?: "unknown")
                }
            }
        })
    }
    //endregion

    //region toast
    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    //endregion

}
