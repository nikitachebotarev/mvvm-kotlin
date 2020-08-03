package ru.cnv.paxfultestapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_jokes_list.jokes_rv
import kotlinx.android.synthetic.main.fragment_my_jokes.*
import ru.cnv.paxfultestapp.R
import ru.cnv.paxfultestapp.repository.entity.Joke
import ru.cnv.paxfultestapp.ui.AddNewJokeDialog
import ru.cnv.paxfultestapp.ui.adapter.MyJokesAdapter
import ru.cnv.paxfultestapp.ui.decoration.JokesRecyclerViewItemDecoration
import ru.cnv.paxfultestapp.vm.MyJokesViewModel

class MyJokesFragment : Fragment() {

    private val viewModel: MyJokesViewModel by viewModels()

    private lateinit var myJokesAdapter: MyJokesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.addFragmentOnAttachListener { fm, fragment ->
            if (fragment is AddNewJokeDialog) {
                fragment.onSaveNewJoke = this::onSaveNewJoke
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_jokes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jokes_rv.addItemDecoration(JokesRecyclerViewItemDecoration(requireContext()))

        myJokesAdapter = MyJokesAdapter(this::onDelete)
        jokes_rv.adapter = myJokesAdapter

        viewModel.jokes.observe(viewLifecycleOwner, Observer { onItemsLoaded(it) })

        add_fab.setOnClickListener { onAddNewJoke() }
    }

    fun onItemsLoaded(list: List<Joke>) {
        myJokesAdapter.jokes = list
        myJokesAdapter.notifyDataSetChanged()
    }

    fun onDelete(id: Int) {
        viewModel.deleteJoke(id)
    }

    fun onAddNewJoke() {
        val tag = AddNewJokeDialog::class.simpleName

        var fragment: AddNewJokeDialog? =
            childFragmentManager.findFragmentByTag(tag) as? AddNewJokeDialog

        if (fragment == null) {
            fragment = AddNewJokeDialog()
        }
        fragment.show(childFragmentManager, tag)
    }

    fun onSaveNewJoke(text: String) {
        viewModel.saveJoke(text)
    }
}