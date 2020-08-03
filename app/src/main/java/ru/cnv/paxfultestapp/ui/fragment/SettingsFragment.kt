package ru.cnv.paxfultestapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_settings.*
import ru.cnv.paxfultestapp.R
import ru.cnv.paxfultestapp.repository.entity.Settings
import ru.cnv.paxfultestapp.vm.SettingsViewModel
import java.util.*
import java.util.concurrent.TimeUnit

class SettingsFragment : Fragment() {

    private val viewModel: SettingsViewModel by viewModels()

    private var controlsDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.settings.observe(viewLifecycleOwner, Observer { render(it) })
    }

    private fun render(settings: Settings?) {
        controlsDisposable.clear()

        if (settings == null) {
            setControlsEnabled(false)
        } else {
            setControlsEnabled(true)

            if (firstname_et.text.isEmpty()) {
                firstname_et.setText(settings.firstName)
            }

            if (lastname_et.text.isEmpty()){
                lastname_et.setText(settings.lastName)
            }

            if (offline_sw.isChecked != settings.isOffline) {
                offline_sw.isChecked = settings.isOffline
            }

            RxTextView.textChanges(firstname_et)
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribe { viewModel.save(settings.copy(firstName = it.toString())) }
                .addTo(controlsDisposable)

            RxTextView.textChanges(lastname_et)
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribe { viewModel.save(settings.copy(lastName = it.toString())) }
                .addTo(controlsDisposable)

            RxCompoundButton.checkedChanges(offline_sw)
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribe { viewModel.save(settings.copy(isOffline = it)) }
                .addTo(controlsDisposable)
        }
    }

    private fun setControlsEnabled(isEnabled: Boolean) {
        firstname_et.isEnabled = isEnabled
        lastname_et.isEnabled = isEnabled
        offline_sw.isEnabled = isEnabled
    }
}