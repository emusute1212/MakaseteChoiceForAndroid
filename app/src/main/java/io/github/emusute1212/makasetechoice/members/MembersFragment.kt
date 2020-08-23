package io.github.emusute1212.makasetechoice.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.github.emusute1212.makasetechoice.databinding.FragmentMemberBinding
import io.github.emusute1212.makasetechoice.members.add.AddMemberDialogFragment
import javax.inject.Inject

class MembersFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MembersViewModel by activityViewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMessenger()
        return FragmentMemberBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    private fun initMessenger() {
        viewModel.messenger.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MemberMessenger.OnClickAddButton -> {
                    AddMemberDialogFragment().show(
                        parentFragmentManager,
                        AddMemberDialogFragment.FRAGMENT_TAG
                    )
                }
                else -> Unit
            }.let {}
        })
    }

}