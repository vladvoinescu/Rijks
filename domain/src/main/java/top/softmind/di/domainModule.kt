package top.softmind.di

import org.koin.dsl.module
import top.softmind.domain.usecase.GetArtCollectionDetailsUseCase
import top.softmind.domain.usecase.GetArtCollectionsUseCase

val domainModule = module {
    single {
        GetArtCollectionsUseCase(get())
    }
    single {
        GetArtCollectionDetailsUseCase(get())
    }
}