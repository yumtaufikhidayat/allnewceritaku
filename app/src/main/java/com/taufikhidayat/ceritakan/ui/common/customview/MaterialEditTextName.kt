package com.taufikhidayat.ceritakan.ui.common.customview

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.taufikhidayat.ceritakan.R

class MaterialEditTextName(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    init {
        addTextChangedListener(afterTextChanged = { s ->
            validateName(s)
        })
    }

    private fun validateName(s: Editable?) {
        if (s.isNullOrEmpty()) error = context.getString(R.string.validation_name_empty)
    }

    fun isValidName(): Boolean = text?.isNotEmpty() ?: false
}