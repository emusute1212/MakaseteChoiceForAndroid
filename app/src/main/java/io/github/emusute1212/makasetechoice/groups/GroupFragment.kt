package io.github.emusute1212.makasetechoice.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.github.emusute1212.makasetechoice.databinding.FragmentGroupBinding
import io.github.emusute1212.makasetechoice.groups.choice.ChoiceDialogFragment
import kotlinx.android.synthetic.main.fragment_group.view.*
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
        return FragmentGroupBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root.also {
            it.init()
        }
    }

    private fun View.init() {
        choice_button_fab.setOnClickListener {
            ChoiceDialogFragment().show(parentFragmentManager, ChoiceDialogFragment.FRAGMENT_TAG)
        }
    }
}