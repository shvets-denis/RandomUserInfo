package com.valerityoss.randomuserinfo.ui.ui.screens.main

import androidx.lifecycle.ViewModel
import com.valerityoss.randomuserinfo.ui.utility.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector()
    internal abstract fun mainActivity(): MainActivity

}