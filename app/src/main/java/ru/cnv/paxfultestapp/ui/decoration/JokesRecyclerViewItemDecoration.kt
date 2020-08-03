package ru.cnv.paxfultestapp.ui.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.cnv.paxfultestapp.R

class JokesRecyclerViewItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        val dp = context.resources.getDimension(R.dimen.jokes_list_margin).toInt()

        outRect.top = dp

        if (position == state.itemCount - 1) {
            outRect.bottom = dp
        }
    }
}