package com.taufikhidayat.ceritakan.ui.common.customview

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.taufikhidayat.ceritakan.R

class MaterialEditTextPassword(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    init {
        addTextChangedListener(afterTextChanged = { s ->
            validatePassword(s)
        })
    }

    private fun validatePassword(s: Editable?) {
        when {
            s.isNullOrEmpty() -> error = context.getString(R.string.validation_password_empty)
            s.length < 8 -> error = context.getString(R.string.validation_password)
        }
    }

    fun isValidPassword(): Boolean {
        val text = text.toString()
        return text.isNotEmpty() && text.length >= 8
    }
}