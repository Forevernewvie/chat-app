package com.jaebin.what.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.ktrotest.di.MainComponent
import com.example.ktrotest.di.MyApp
import com.example.ktrotest.di.ViewModelFactory
import com.jaebin.what.R
import com.jaebin.what.base.BaseActivity
import com.jaebin.what.databinding.ActivityMainBinding
import com.jaebin.what.databinding.DialogBinding
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent = (application as MyApp).appComponent.mainComponent().create()
        mainComponent.inject(this)


    }
    override fun onSupportNavigateUp() = findNavController(com.jaebin.what.R.id.nav_controller).navigateUp()

}