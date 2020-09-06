package io.github.emusute1212.makasetechoice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.android.support.DaggerAppCompatActivity
import io.github.emusute1212.makasetechoice.groups.GroupsViewModel
import io.github.emusute1212.makasetechoice.members.MembersViewModel
import io.github.emusute1212.makasetechoice.splash.SplashScreenFragment
import io.github.emusute1212.makasetechoice.splash.SplashScreenViewModel
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

    private val splashScreenViewModel: SplashScreenViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigation.setupWithNavController(navHostFragment.navController)
        AppBarConfiguration(setOf(R.id.member, R.id.choice, R.id.setting)).also {
            setupActionBarWithNavController(this, navHostFragment.navController, it)
        }

        val fragment = SplashScreenFragment().also {
            it.show(supportFragmentManager, SplashScreenFragment.FRAGMENT_TAG)
        }
        combine(
            false,
            membersViewModel.members,
            groupsViewModel.groups,
            splashScreenViewModel.canSplashClose
        ) { _, _, _, canSplashClose ->
            canSplashClose
        }.observe(this, Observer {
            if (!it) return@Observer
            fragment.dismissAllowingStateLoss()
        })
        splashScreenViewModel.shouldSplashClose.observe(this, Observer {
            if (!it) return@Observer
            fragment.dismissAllowingStateLoss()
        })
        splashScreenViewModel.init()
    }
}
