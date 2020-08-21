package io.github.emusute1212.makasetechoice.groups.choice

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerDialogFragment
import io.github.emusute1212.makasetechoice.databinding.DialogChoiceGroupBinding
import io.github.emusute1212.makasetechoice.groups.GroupsViewModel
import io.github.emusute1212.makasetechoice.members.MembersViewModel
import javax.inject.Inject

class ChoiceDialogFragment : DaggerDialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val membersViewModel: MembersViewModel by activityViewModels {
        viewModelFactory
    }

    private val groupsViewModel: GroupsViewModel by activityViewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DialogChoiceGroupBinding.inflate(inflater, container, false).also {
            it.membersViewModel = membersViewModel
            it.groupsViewModel = groupsViewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = dialog ?: Dialog(requireContext())
        return dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
//            setContentView(R.layout.dialog_add_member)
            window?.setGravity(Gravity.CENTER)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setCancelable(true)
        }
    }
}