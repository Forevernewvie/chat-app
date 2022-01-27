package com.jaebin.what.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentWithBinding<V : ViewBinding> : Fragment() {

    var binding: V? = null
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createBinding(inflater, container, savedInstanceState)
            .also { binding = it }
            .root
    }

    protected abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): V

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(requireBinding(), savedInstanceState)
    }

    abstract fun onViewCreated(binding: V, savedInstanceState: Bundle?)

    protected fun requireBinding(): V = requireNotNull(binding)

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}