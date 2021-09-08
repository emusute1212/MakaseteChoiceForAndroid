package io.github.emusute1212.makasetechoice.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.android.support.DaggerFragment
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.databinding.FragmentSettingsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SettingsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SettingsViewModel by activityViewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initMessenger()
        return FragmentSettingsBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    private fun initMessenger() {
        viewModel.message
            .onEach { onSettingMessage(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onSettingMessage(
        message: SettingMessenger
    ): Any = when (message) {
        SettingMessenger.AboutApp -> startAboutAppFragment()
        SettingMessenger.OssLib -> startOssLibActivity()
    }

    private fun startAboutAppFragment() {
        findNavController(this).navigate(R.id.action_setting_to_about_app)
    }

    private fun startOssLibActivity() {
        Intent(requireContext(), OssLicensesMenuActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }
}