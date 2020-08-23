package io.github.emusute1212.makasetechoice.members.add

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerDialogFragment
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.members.MemberMessenger
import io.github.emusute1212.makasetechoice.members.MembersViewModel
import javax.inject.Inject

class AddMemberDialogFragment : DaggerDialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MembersViewModel by activityViewModels {
        viewModelFactory
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = dialog ?: Dialog(requireContext())
        return dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_add_member)
            window?.setGravity(Gravity.BOTTOM)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setCancelable(true)
        }
    }

    private fun initMessenger() {
        viewModel.messenger.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MemberMessenger.OnDoAddMember -> {
                    dismiss()
                }
                is MemberMessenger.OnCancelAdd -> {
                    dismiss()
                }
                else -> Unit
            }.let {}
        })
    }

    companion object {
        val FRAGMENT_TAG = AddMemberDialogFragment::class.java.simpleName
    }
}