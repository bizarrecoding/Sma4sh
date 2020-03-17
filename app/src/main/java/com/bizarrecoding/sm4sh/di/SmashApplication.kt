package com.bizarrecoding.sm4sh.di

import android.app.Application
import com.bizarrecoding.sm4sh.database.ProductRepository
import com.bizarrecoding.sm4sh.database.ProductRepositoryImpl
import com.bizarrecoding.sm4sh.database.SmashDao
import com.bizarrecoding.sm4sh.database.SmashDatabase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class SmashApplication: Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@SmashApplication))
        bind<SmashDatabase>() with singleton { SmashDatabase.getInstance(applicationContext) }
        bind<SmashDao>() with singleton { instance<SmashDatabase>().smashDao }
        bind<ProductRepository>() with singleton {
            ProductRepositoryImpl(
                instance()
            )
        }
        bind() from provider {
            ViewModelFactory(
                instance()
            )
        }
    }
}