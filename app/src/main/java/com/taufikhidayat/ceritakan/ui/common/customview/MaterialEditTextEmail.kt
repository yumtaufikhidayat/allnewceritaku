package com.taufikhidayat.ceritakan.ui.common.customview

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.taufikhidayat.ceritakan.R

class MaterialEditTextEmail(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    init {
        addTextChangedListener(afterTextChanged = { s ->
            validateEmail(s)
        })
    }

    private fun validateEmail(s: Editable?) {
        when {
            s.isNullOrEmpty() -> error = context.getString(R.string.validation_email_empty)
            !Patterns.EMAIL_ADDRESS.matcher(s).matches() -> error = context.getString(R.string.validation_email)
        }
    }

    fun isValidEmail(): Boolean {
        val email = text.toString()
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }
}