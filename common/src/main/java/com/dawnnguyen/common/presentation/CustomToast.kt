package com.dawnnguyen.common.presentation

import android.content.Context
import android.widget.Toast

class CustomToast(context: Context) : Toast(context) {

    companion object {
        fun makeText(
            context: Context, title: String, description: String?, duration: Int,
//            type: CustomToastType
        ): Toast {
            val toast = Toast(context)
            toast.duration = duration
            toast.setText(title)
//            TODO: Customize your toast here
//            val binding: CustomToastBinding = DataBindingUtil.inflate(
//                LayoutInflater.from(context),
//                R.layout.custom_toast,
//                null,
//                false
//            )
//
//            binding.progressBarCustomToast.visibility = when (type) {
//                CustomToastType.LOADING -> View.VISIBLE
//                else -> View.GONE
//            }
//
//            if (type == CustomToastType.ERROR) {
//                binding.parentViewCustomToast.minWidth =
//                    context.resources.displayMetrics.widthPixels - (12 * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))
//            }
//
//            binding.imageViewCustomToastError.visibility = when (type) {
//                CustomToastType.ERROR -> View.VISIBLE
//                else -> View.GONE
//            }
//
//            binding.imageViewCustomToastSuccess.visibility = when (type) {
//                CustomToastType.SUCCESS -> View.VISIBLE
//                else -> View.GONE
//            }
//
//            binding.textViewCustomToastDescription.visibility =
//                if (description.isNullOrEmpty()) View.GONE else {
//                    binding.textViewCustomToastDescription.text = Html.fromHtml(description)
//                    View.VISIBLE
//                }
//
//            binding.textViewCustomToastTitle.text = Html.fromHtml(title)

//            toast.view = binding.root
//            toast.setGravity(Gravity.TOP, 0, 0)
            return toast
        }
    }
}