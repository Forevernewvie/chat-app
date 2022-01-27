package com.example.ktrotest.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.PROPERTY_GETTER,AnnotationTarget.PROPERTY_SETTER,AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value: KClass<out ViewModel>)