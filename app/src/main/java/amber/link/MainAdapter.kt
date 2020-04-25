package amber.link

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_row.view.*

/*
* Created by Amber Yiyao Zhou & Link Zhou Fang on December 02, 2019
*/

//region classes
class CustomViewHolderClass(val view: View, var login: String = "",
                            var user: Users? = null) // a single Users object from ResponseDataClass/Users classes
    : RecyclerView.ViewHolder(view){

    init {
        view.setOnClickListener {

            val intent = Intent(view.context, DetailsActivity::class.java )

            intent.putExtra(TITLE_KEY, login)
            intent.putExtra(OBJECT_KEY, user)
            view.context.startActivity(intent)
        }
    }

    companion object{
        val TITLE_KEY = "ACTIONBAR_TITLE"
        val OBJECT_KEY = "ITEM_DATA"
    }
}

class MainAdapter(private val responseDataClass: ArrayList<Users>) :
    RecyclerView.Adapter<CustomViewHolderClass>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderClass {

        // A LayoutInflater reads an XML file in which we describe how we want a UI layout to be.
        // It then creates actual View objects for UI from that XML.
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.data_row, parent, false)
        return CustomViewHolderClass(cellForRow)
    }

    override fun getItemCount(): Int {
        return responseDataClass.size
    }

    override fun onBindViewHolder(holder: CustomViewHolderClass, position: Int) {

        val login = responseDataClass[position].login
        holder.view.loginTextView.text = TheApp.context.getString(R.string.user_login, responseDataClass[position].login)
        holder.view.scoreTextView.text = TheApp.context.getString(R.string.user_score, (responseDataClass[position].score + 0.5).toInt().toString())
        holder.view.idTextView.text = TheApp.context.getString(R.string.user_id, responseDataClass[position].id.toString())

        Picasso.get().load(responseDataClass[position].avatar_url).into(holder.view.imageView)

        holder.user = responseDataClass[position]

        holder.login = login
    }

}
//endregion