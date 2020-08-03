package ru.cnv.paxfultestapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_add_new_joke.*
import ru.cnv.paxfultestapp.R

class AddNewJokeDialog : DialogFragment() {

    lateinit var onSaveNewJoke: (text: String) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_new_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        save_tv.setOnClickListener {
            if (text_et.text.isNotEmpty()) {
                edit_til.error = null
                if (this::onSaveNewJoke.isInitialized) {
                    onSaveNewJoke(text_et.text.toString())
                }
                dismiss()
            } else {
                edit_til.error = getString(R.string.field_is_empty)
            }
        }

        cancel_tv.setOnClickListener { dismiss() }
    }
}