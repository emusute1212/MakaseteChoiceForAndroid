package io.github.emusute1212.makasetechoice.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.github.emusute1212.makasetechoice.databinding.FragmentMemberBinding
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
        return FragmentMemberBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}