package id.zlz.crudroom.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.zlz.crudroom.R

class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val title = itemView.findViewById<TextView>(R.id.text_title)

}
