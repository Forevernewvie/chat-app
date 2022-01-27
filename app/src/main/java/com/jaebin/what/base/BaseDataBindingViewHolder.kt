package com.jaebin.what.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDataBindingViewHolder<T: ViewDataBinding, DATA>(
    parent: ViewGroup,
    @LayoutRes id: Int,
    val binding: T =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), id, parent, false)
) : RecyclerView.ViewHolder(binding.root){

    fun bind(data : DATA){
        bindData(data)
        binding.executePendingBindings()
    }

    protected abstract fun bindData(data: DATA)
}