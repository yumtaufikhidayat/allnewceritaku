package com.taufikhidayat.ceritakan.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.taufikhidayat.ceritakan.R
import com.taufikhidayat.ceritakan.databinding.FragmentLoginBinding
import com.taufikhidayat.ceritakan.ui.common.utils.asColor
import com.taufikhidayat.ceritakan.ui.common.utils.asDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var onButtonLoginEnabled by Delegates.observable(false) { _, _, newValue ->
        binding.btnLogin.apply {
            if (context == null) return@observable
            isEnabled = newValue
            background = if (isEnabled)
                R.drawable.bg_button_purple.asDrawable(requireContext())
            else
                R.drawable.bg_button_disable.asDrawable(requireContext())

            setTextColor(R.color.white.asColor(requireContext()))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateInput()
    }

    private fun validateInput() = binding.apply {
        etEmail.addTextChangedListener(onTextChanged = { _, _, _, _ ->
            validateButton()
        })
        etPassword.addTextChangedListener(onTextChanged = { _, _, _, _ ->
            validateButton()
        })
    }


    private fun validateButton() = binding.apply {
        onButtonLoginEnabled = etEmail.isValidEmail() && etPassword.isValidPassword()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}