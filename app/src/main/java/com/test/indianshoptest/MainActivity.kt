package com.test.indianshoptest

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.android_utils.navigation.NavControllerHolder
import com.test.feature_home.LoginFragment
import com.test.feature_home.SingInFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

     @Inject
    lateinit var navControllerHolders: Array<NavControllerHolder>

    private val navController: NavController by lazy {
        navHostFragment.findNavController()
    }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
    }


    private val bottomBar by lazy { findViewById<BottomNavigationView>(R.id.bottom_bar) }

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View = window.decorView
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
        window.statusBarColor = this.resources.getColor(R.color.white);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavControllerToNavigators()
        initNavControllerListener()
    }

    private fun setNavControllerToNavigators() {
        navControllerHolders.forEach { it.navController = navController }
    }

    private fun initBottomBar(){

    }

    private fun initNavControllerListener(){
        navHostFragment.childFragmentManager.addFragmentOnAttachListener { fragmentManager, fragment ->
            if(fragment is LoginFragment || fragment is SingInFragment){
                bottomBar.visibility = View.GONE
            }else{
                bottomBar.visibility = View.VISIBLE
            }
        }
    }


}