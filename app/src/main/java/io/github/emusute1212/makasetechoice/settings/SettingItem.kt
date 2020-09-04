package io.github.emusute1212.makasetechoice.settings

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.databinding.SettingItemViewBinding

data class SettingItem(
    private val item: SettingMenuItems,
    private val viewModel: SettingsViewModel
) : BindableItem<SettingItemViewBinding>(
    item.hashCode().toLong()
) {
    override fun getLayout(): Int = R.layout.setting_item_view

    override fun bind(viewBinding: SettingItemViewBinding, position: Int) {
        viewBinding.item = item
        viewBinding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): SettingItemViewBinding {
        return SettingItemViewBinding.bind(view)
    }
}