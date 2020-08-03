package ru.cnv.paxfultestapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_joke.view.*
import ru.cnv.paxfultestapp.R
import ru.cnv.paxfultestapp.repository.entity.Joke

class JokesListAdapter : RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {

    var onShare: (text: String) -> Unit = {}
    var onLike: (id: Int, isLike: Boolean) -> Unit = { _, _ -> }

    var jokes: List<Joke> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_joke, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val joke = jokes[position]
        holder.itemView.text_tv.text = joke.text
        holder.itemView.like_tv.text =
            if (joke.isFavorite) context.getString(R.string.dislike) else context.getString(R.string.like)
        holder.itemView.like_tv.setOnClickListener { onLike(jokes[position].id, joke.isFavorite.not()) }
        holder.itemView.share_tv.setOnClickListener { onShare(jokes[position].text) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}