package com.dawnnguyen.common.presentation.utils

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.dawnnguyen.common.R

fun showHidePass(context: Context, editText: EditText, imageButton: ImageButton) {
    when (editText.transformationMethod) {
        PasswordTransformationMethod.getInstance() -> {
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imageButton.setImageDrawable(
                (ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_invisible
                ))
            )
        }
        HideReturnsTransformationMethod.getInstance() -> {
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            imageButton.setImageDrawable(
                (ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_visible
                ))
            )
        }
    }
}