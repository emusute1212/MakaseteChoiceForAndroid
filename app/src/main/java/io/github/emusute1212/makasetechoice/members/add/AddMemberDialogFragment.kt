package io.github.emusute1212.makasetechoice.members.add

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerDialogFragment
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.databinding.DialogAddMemberBinding
import io.github.emusute1212.makasetechoice.members.MembersViewModel
import kotlinx.android.synthetic.main.dialog_add_member.view.*
import javax.inject.Inject


class AddMemberDialogFragment : DaggerDialogFragment() {
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
        return DialogAddMemberBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root.also {
            it.init()
        }
    }

    override fun onDestroyView() {
        closeKeyboard()
        super.onDestroyView()
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

    private fun View.init() {
        adding_button.setOnClickListener {
            viewModel.onAddButtonClick()
            dismiss()
        }
        cancel_button.setOnClickListener {
            dismiss()
        }
        showKeyboard()
    }

    private fun showKeyboard() {
        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE).also {
            it as InputMethodManager
            it.toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                0
            )
        }
    }

    private fun closeKeyboard() {
        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE).also {
            it as InputMethodManager
            it.toggleSoftInput(
                InputMethodManager.HIDE_IMPLICIT_ONLY,
                0
            )
        }
    }

    companion object {
        val FRAGMENT_TAG = AddMemberDialogFragment::class.java.simpleName
    }
}