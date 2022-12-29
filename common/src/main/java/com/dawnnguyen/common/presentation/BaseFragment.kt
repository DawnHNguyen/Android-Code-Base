package com.dawnnguyen.common.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.dawnnguyen.common.utils.ConnectionLiveData
import com.dawnnguyen.common.utils.checkForInternet

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    private var _binding: DB? = null
    protected lateinit var connectionLiveData: ConnectionLiveData

    protected lateinit var noNetworkErrorToast: Toast


    // This property is only valid between onCreateView and
    // onDestroyView.
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity?.checkForInternet() == false) CustomToast.makeText(
            requireContext(),
            "Mất mạng",
            "Đã mất mạng",
            Toast.LENGTH_SHORT,
        ).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        noNetworkErrorToast = CustomToast.makeText(
            requireContext(),
            "Mất mạng",
            "Đã mất mạng",
            Toast.LENGTH_SHORT,
        )
        connectionLiveData = ConnectionLiveData(requireContext())
        connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                if(!it) noNetworkErrorToast.show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun getLayoutId(): Int
}