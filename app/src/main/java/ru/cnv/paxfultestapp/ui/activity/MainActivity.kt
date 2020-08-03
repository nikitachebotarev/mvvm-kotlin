package ru.cnv.paxfultestapp.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.cnv.paxfultestapp.R
import ru.cnv.paxfultestapp.app.App
import ru.cnv.paxfultestapp.ui.fragment.JokesListFragment
import ru.cnv.paxfultestapp.ui.fragment.MyJokesFragment
import ru.cnv.paxfultestapp.ui.fragment.SettingsFragment
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_vg, JokesListFragment(), JokesListFragment::class.simpleName)
                .commit()
        }

        bottom_navigation_view.setOnNavigationItemSelectedListener(
            this::onNavigationItemSelected
        )
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentClass = when (item.itemId) {
            R.id.menu_jokes_list -> JokesListFragment::class
            R.id.menu_my_jokes -> MyJokesFragment::class
            R.id.menu_settings -> SettingsFragment::class
            else -> throw IllegalStateException()
        }

        var fragment = supportFragmentManager.findFragmentByTag(fragmentClass.simpleName)
        if (fragment == null) {
            fragment = fragmentClass.java.newInstance()
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_vg, fragment, fragmentClass.simpleName)
            .commit()

        return true
    }
}