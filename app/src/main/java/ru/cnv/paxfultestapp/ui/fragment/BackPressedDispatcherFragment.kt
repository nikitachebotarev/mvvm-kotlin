package ru.cnv.paxfultestapp.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

open class BackPressedDispatcherFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    isEnabled = onBackPressed()
                }
            })
    }

    protected fun onBackPressed(): Boolean {
        return false
    }
}