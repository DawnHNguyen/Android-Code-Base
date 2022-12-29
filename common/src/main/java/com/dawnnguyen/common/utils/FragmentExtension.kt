package com.dawnnguyen.common.utils

import androidx.fragment.app.Fragment
import com.dawnnguyen.common.utils.hideKeyboard

fun Fragment.hideKeyboard(){
    activity?.hideKeyboard()
}