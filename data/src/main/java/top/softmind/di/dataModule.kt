package top.softmind.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import top.softmind.data.api.ArtCollectionRemoteApi
import top.softmind.data.repository.RemoteArtCollectionRepository
import top.softmind.domain.repository.ArtCollectionRepository

val dataModule = module {

    single<ArtCollectionRepository> {
        RemoteArtCollectionRepository(get())
    }

    single<ArtCollectionRemoteApi> {
        get<Retrofit>().create()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://www.rijksmuseum.nl/api/nl/")
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder().build()
    }
}