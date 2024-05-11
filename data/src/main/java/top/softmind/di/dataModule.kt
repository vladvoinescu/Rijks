package top.softmind.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import top.softmind.data.api.ArtCollectionRemoteApi
import top.softmind.data.mapper.ArtCollectionDetailsDomainMapper
import top.softmind.data.mapper.ArtCollectionDomainMapper
import top.softmind.data.repository.RemoteArtCollectionRepository
import top.softmind.domain.repository.ArtCollectionRepository

val dataModule = module {

    single {
        ArtCollectionDomainMapper()
    }

    single {
        ArtCollectionDetailsDomainMapper()
    }

    single<ArtCollectionRepository> {
        RemoteArtCollectionRepository(get(), get(), get())
    }

    single<ArtCollectionRemoteApi> {
        get<Retrofit>().create()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://www.rijksmuseum.nl/api/nl/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get<HttpLoggingInterceptor>())
        }.build()
    }

    single {
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }
}