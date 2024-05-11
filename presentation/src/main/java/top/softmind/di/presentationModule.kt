package top.softmind.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import top.softmind.presentation.DetailsViewModel
import top.softmind.presentation.OverviewViewModel

val presentationModule = module {
    viewModelOf(::OverviewViewModel)
    viewModelOf(::DetailsViewModel)
}