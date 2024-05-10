package top.softmind.rijks

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin
import top.softmind.di.appModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}