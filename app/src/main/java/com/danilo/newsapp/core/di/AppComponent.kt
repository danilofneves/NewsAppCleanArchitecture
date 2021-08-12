package com.danilo.newsapp.core.di


import com.danilo.newsapp.core.App
import com.danilo.newsapp.data.di.DataModule
import com.danilo.newsapp.domain.di.DomainModule
import dagger.Component
import dagger.android.AndroidInjectionModule


import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModuleBuilder::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }

    fun inject(app: App)
}