package com.taufikhidayat.ceritakan.ui.auth.register

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.taufikhidayat.ceritakan.R
import com.taufikhidayat.ceritakan.databinding.FragmentRegisterBinding
import com.taufikhidayat.ceritakan.ui.common.utils.asColor
import com.taufikhidayat.ceritakan.ui.common.utils.asDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private var onButtonRegisterEnabled by Delegates.observable(false) { _, _, newValue ->
        binding.btnRegister.apply {
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showCbPassword()
        validateInput()
    }

    private fun showCbPassword() = binding.apply {
        cbShowPassword.setOnCheckedChangeListener { _, isChecked ->
            val start = etPassword.selectionStart
            val end = etPassword.selectionEnd
            val currentTypeface = etPassword.typeface

            etPassword.inputType = (if (isChecked)
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            etPassword.typeface = currentTypeface
            etPassword.setSelection(start, end)
        }
    }

    private fun validateInput() = binding.apply {
        etName.addTextChangedListener(onTextChanged = { _, _, _, _ ->
            validateButton()
        })
        etEmail.addTextChangedListener(onTextChanged = { _, _, _, _ ->
            validateButton()
        })
        etPassword.addTextChangedListener(onTextChanged = { _, _, _, _ ->
            validateButton()
        })
    }

    private fun validateButton() = binding.apply {
        onButtonRegisterEnabled = etName.isValidName()
                && etEmail.isValidEmail()
                && etPassword.isValidPassword()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}