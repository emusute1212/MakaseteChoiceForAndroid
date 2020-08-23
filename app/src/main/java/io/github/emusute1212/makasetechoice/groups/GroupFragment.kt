package io.github.emusute1212.makasetechoice.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.github.emusute1212.makasetechoice.databinding.FragmentGroupBinding
import io.github.emusute1212.makasetechoice.groups.choice.ChoiceDialogFragment
import javax.inject.Inject

class GroupFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GroupsViewModel by activityViewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMessenger()
        return FragmentGroupBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    private fun initMessenger() {
        viewModel.messenger.observe(viewLifecycleOwner, Observer {
            when (it) {
                is GroupMessenger.OnClickChoiceButton -> {
                    ChoiceDialogFragment().show(
                        parentFragmentManager,
                        ChoiceDialogFragment.FRAGMENT_TAG
                    )
                }
                else -> Unit
            }.let {}
        })
    }
}