package youmeee.co.jp.hatenarssreaderapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import youmeee.co.jp.hatenarssreaderapp.R

class TutorialTabDescView(context: Context, descText: String? = null) : LinearLayout(context) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.tutorial_description, this)
        view.findViewById<TextView>(R.id.desc_text).text = descText ?: ""
    }

}