package com.example.unisportinverse.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.unisportinverse.R
import com.example.unisportinverse.presentation.fragments.SplashScreenFragment
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        MapKitFactory.setApiKey("a527d12d-2fd5-4eaf-9b11-4a301efad482")
        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_main)

        val splashScreenFragment = SplashScreenFragment()
        val fragment : Fragment? =

            supportFragmentManager.findFragmentByTag(SplashScreenFragment::class.java.simpleName)

        if (fragment !is SplashScreenFragment){
            supportFragmentManager.beginTransaction()
                .add(R.id.layout_fragment, splashScreenFragment, SplashScreenFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

}