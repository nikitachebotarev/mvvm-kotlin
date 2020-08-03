package ru.cnv.paxfultestapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_my_joke.view.*
import ru.cnv.paxfultestapp.R
import ru.cnv.paxfultestapp.repository.entity.Joke

class MyJokesAdapter(val onDelete: (id: Int) -> Unit) :
    RecyclerView.Adapter<MyJokesAdapter.ViewHolder>() {

    var jokes: List<Joke> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_joke, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val joke = jokes[position]
        holder.itemView.text_tv.text = joke.text
        holder.itemView.delete_tv.setOnClickListener { onDelete(jokes[position].id) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}