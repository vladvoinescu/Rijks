package top.softmind.di

import org.koin.dsl.module

val appModule = module {
    includes(
        domainModule,
        dataModule,
        presentationModule
    )
}