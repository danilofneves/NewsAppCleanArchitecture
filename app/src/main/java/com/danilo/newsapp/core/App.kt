package com.danilo.newsapp.core

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.danilo.newsapp.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initDagger()
    }

    fun initDagger() {
        DaggerAppComponent.builder().build().inject(this)
    }

    companion object {
        lateinit var context: Context
    }
}
