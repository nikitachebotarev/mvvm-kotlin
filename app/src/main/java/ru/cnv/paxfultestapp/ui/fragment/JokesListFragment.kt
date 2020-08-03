package ru.cnv.paxfultestapp.ui.fragment

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.seismic.ShakeDetector
import kotlinx.android.synthetic.main.fragment_jokes_list.*
import ru.cnv.paxfultestapp.R
import ru.cnv.paxfultestapp.repository.entity.Joke
import ru.cnv.paxfultestapp.ui.UiUtils
import ru.cnv.paxfultestapp.ui.adapter.JokesListAdapter
import ru.cnv.paxfultestapp.ui.decoration.JokesRecyclerViewItemDecoration
import ru.cnv.paxfultestapp.vm.JokesListViewModel

class JokesListFragment : BackPressedDispatcherFragment() {

    private val viewModel: JokesListViewModel by viewModels()

    private lateinit var jokesListAdapter: JokesListAdapter
    private lateinit var shakeDetector: ShakeDetector

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jokes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jokes_rv.addItemDecoration(JokesRecyclerViewItemDecoration(requireContext()))

        jokesListAdapter = JokesListAdapter().apply {
            onLike = this@JokesListFragment::onLike
            onShare = this@JokesListFragment::onShare
        }

        jokes_rv.adapter = jokesListAdapter

        viewModel.jokes.observe(viewLifecycleOwner, Observer { onItemsLoaded(it) })

        shakeDetector = ShakeDetector {viewModel.shake()}
        shakeDetector.start(context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        shakeDetector.stop()
    }

    private fun onItemsLoaded(list: List<Joke>) {
        jokesListAdapter.jokes = list
        jokesListAdapter.notifyDataSetChanged()
    }

    private fun onLike(id: Int, isLike: Boolean) {
        viewModel.like(id, isLike)
    }

    private fun onShare(text: String) {
        UiUtils.share(requireContext(), text)
    }
}