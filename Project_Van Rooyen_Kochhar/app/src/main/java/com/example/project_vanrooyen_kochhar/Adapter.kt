package com.example.project_vanrooyen_kochhar

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

// Setting up the custom adapter for the recycle view
class Adapter(private var data : ArrayList<Student>, var context : Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    // The Viewholder for the individual rows
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    // Moving the view into the Viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row, parent, false)
        Log.d("Tag", data.size.toString()) // size of the list
        return ViewHolder(view)
    }

    // Setting the rows to unique pictures, names and capitals
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.id_tv.text = data[position].id.toString()
        holder.itemView.scoreRow_tv.text = data[position].score.toString()
        holder.itemView.commentRow_tv.text = data[position].comment
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T{
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

    // Return size of the adapter
    override fun getItemCount(): Int {
        return data.size
    }
}


