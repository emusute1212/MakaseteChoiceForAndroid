package io.github.emusute1212.makasetechoice.settings.aboutapp

import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.emusute1212.makasetechoice.R
import kotlinx.android.synthetic.main.fragment_about_app.view.*


class AboutAppFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_about_app, container, false).also {
            it.init()
        }
    }

    private fun View.init() {
        app_version.text = requireContext()
            .packageManager
            .getPackageInfo(requireContext().packageName, 0)
            .versionName

        Linkify.addLinks(
            app_author,
            PATTERN,
            MY_PAGE_URL,
            null,
            Linkify.TransformFilter { _, _ ->
                MY_PAGE_URL
            })
    }

    companion object {
        private const val MY_PAGE_URL = "https://www.emusute.com/"
        private val PATTERN = "Yosuke Miyanishi".toPattern()
    }
}