package com.taufikhidayat.ceritakan.ui.common.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.taufikhidayat.ceritakan.R

class MaterialTextInputEditTextPassword(context: Context, attrs: AttributeSet) : TextInputEditText(context, attrs) {

    private var isPasswordVisible: Boolean = false
    private var toggleDrawable: Drawable? = null
    private var startDrawable: Drawable? = null
    private var startDrawablePadding: Int = 0

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MaterialTextInputEditTextPassword)
        startDrawable = attributes.getDrawable(R.styleable.MaterialTextInputEditTextPassword_drawableStartIcon)
        startDrawablePadding = attributes.getDimensionPixelSize(
            R.styleable.MaterialTextInputEditTextPassword_drawableStartPadding,
            0
        )

        attributes.recycle()
        setup()
        addTextChangedListener(afterTextChanged = { s ->
            validatePassword(s)
        })
    }

    private fun setup() {
        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        toggleDrawable = ContextCompat.getDrawable(context, R.drawable.ic_visibility_off)
        setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, toggleDrawable, null)
        compoundDrawablePadding = resources.getDimensionPixelSize(R.dimen.value_2dp)

        setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP && event.rawX >= (right - toggleDrawable?.bounds?.width()!!)) {
                v.performClick()
                true
            } else {
                false
            }
        }
    }

    private fun validatePassword(s: Editable?) {
        val parent = parent.parent as? TextInputLayout
        when {
            s.isNullOrEmpty() -> parent?.error =
                context.getString(R.string.validation_password_empty)

            s.length < 8 -> parent?.error = context.getString(R.string.validation_password)
            else -> parent?.error = null
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, toggleDrawable, null)
    }

    fun isValidPassword(): Boolean {
        val text = text.toString()
        return text.isNotEmpty() && text.length >= 8
    }

    override fun performClick(): Boolean {
        togglePasswordVisibility()
        return super.performClick()
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        if (isPasswordVisible) {
            transformationMethod = null
            toggleDrawable = ContextCompat.getDrawable(context, R.drawable.ic_visibility_on)
        } else {
            transformationMethod = PasswordTransformationMethod.getInstance()
            toggleDrawable = ContextCompat.getDrawable(context, R.drawable.ic_visibility_off)
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, toggleDrawable, null)
        setSelection(text?.length ?: 0)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, toggleDrawable, null)
        compoundDrawablePadding = startDrawablePadding
    }
}