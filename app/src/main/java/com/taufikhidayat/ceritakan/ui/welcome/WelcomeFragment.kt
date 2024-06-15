package com.taufikhidayat.ceritakan.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.taufikhidayat.ceritakan.databinding.FragmentWelcomeBinding
import com.taufikhidayat.ceritakan.ui.common.bottomsheet.BottomSheetCustomFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openBottomSheetDialog()
    }

    private fun openBottomSheetDialog() {
        binding.apply {
            btnLogin.setOnClickListener {
                val bottomSheetCustomFragment = BottomSheetCustomFragment()
                if (!childFragmentManager.isStateSaved ||
                    childFragmentManager.findFragmentByTag("")?.isAdded == true
                ) bottomSheetCustomFragment.show(requireActivity().supportFragmentManager, bottomSheetCustomFragment.tag)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}