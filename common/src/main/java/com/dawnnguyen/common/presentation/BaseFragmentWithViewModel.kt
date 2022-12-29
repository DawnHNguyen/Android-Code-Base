package com.dawnnguyen.common.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseFragmentWithViewModel<DB: ViewDataBinding, VM: ViewModel>: BaseFragment<DB>() {
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.setVariable(getViewModelBindingVariable(), viewModel)

        return binding.root
    }

    abstract fun getViewModelBindingVariable(): Int
}