package io.github.emusute1212.makasetechoice.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.android.support.DaggerFragment
import io.github.emusute1212.makasetechoice.databinding.FragmentSettingsBinding
import io.github.emusute1212.makasetechoice.util.messageObserver
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
    ): View? {
        initMessenger()
        return FragmentSettingsBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    private fun initMessenger() {
        viewModel.message.observe(viewLifecycleOwner, messageObserver {
            when (it) {
                SettingMessenger.OssLib -> startOssLibActivity()
            }.let {}
        })
    }

    private fun startOssLibActivity() {
        Intent(requireContext(), OssLicensesMenuActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }
}