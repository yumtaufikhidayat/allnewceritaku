package com.taufikhidayat.ceritakan.ui.common.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.taufikhidayat.ceritakan.R
import com.taufikhidayat.ceritakan.databinding.FragmentBottomSheetCustomBinding
import com.taufikhidayat.ceritakan.ui.auth.login.LoginFragment
import com.taufikhidayat.ceritakan.ui.auth.register.RegisterFragment
import com.taufikhidayat.ceritakan.ui.common.adapter.TabPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetCustomFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetCustomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetCustomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabLayout()
        setupDialogView()
    }

    private fun setupDialogView() {
        // init style
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogThemeInput)

        val bottomSheet =
            dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            it.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded_dialog)
        }

        val behavior = bottomSheet?.let { BottomSheetBehavior.from(it) }
        dialog?.setOnShowListener {
            behavior?.state = BottomSheetBehavior.STATE_EXPANDED
            setCancelable(true)
        }

        behavior?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN ||
                    newState == BottomSheetBehavior.STATE_COLLAPSED
                ) dismiss()
            }
        })
    }

    private fun setTabLayout() {
        val listOfFragments = listOf(
            RegisterFragment(),
            LoginFragment()
        )

        binding.apply {
            val tabPagerAdapter = TabPagerAdapter(listOfFragments, this@BottomSheetCustomFragment)
            viewPagerMovie.adapter = tabPagerAdapter
            TabLayoutMediator(tabLayoutWelcome, viewPagerMovie) { tabs, position ->
                tabs.text = getString(tabsTitle[position])
            }.attach()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @StringRes
        private val tabsTitle = intArrayOf(
            R.string.action_register,
            R.string.action_login
        )
    }
}