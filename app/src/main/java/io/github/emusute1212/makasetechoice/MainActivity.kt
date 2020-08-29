package io.github.emusute1212.makasetechoice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.android.support.DaggerAppCompatActivity
import io.github.emusute1212.makasetechoice.groups.GroupsViewModel
import io.github.emusute1212.makasetechoice.members.MembersViewModel
import io.github.emusute1212.makasetechoice.splash.SplashScreenFragment
import io.github.emusute1212.makasetechoice.util.combine
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val membersViewModel: MembersViewModel by viewModels {
        viewModelFactory
    }

    private val groupsViewModel: GroupsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigation.setupWithNavController(navHostFragment.navController)
        val fragment = SplashScreenFragment().also {
            it.show(supportFragmentManager, SplashScreenFragment.FRAGMENT_TAG)
        }
        combine(false, membersViewModel.members, groupsViewModel.groups) { _, _, _ ->
            true
        }.observe(this, Observer {
            if (!it) return@Observer
            fragment.dismissAllowingStateLoss()
        })
        groupsViewModel.init()
    }
}
