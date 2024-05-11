package top.softmind.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import top.softmind.presentation.overview.ArtCollectionSource
import top.softmind.presentation.details.DetailsViewModel
import top.softmind.presentation.overview.OverviewViewModel

val presentationModule = module {
    single{
        ArtCollectionSource(get())
    }
    viewModelOf(::OverviewViewModel)
    viewModelOf(::DetailsViewModel)
}