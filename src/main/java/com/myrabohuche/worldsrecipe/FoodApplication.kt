package com.myrabohuche.worldsrecipe

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class FoodApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@FoodApplication))



        bind() from singleton { ApiService() }
        bind() from singleton { FoodDatabase((instance())) }
        bind() from singleton { instance<FoodDatabase>().foodDao() }
        bind() from singleton { FoodRepository(instance(), instance()) }
        bind() from provider { FoodViewmodelFactory(instance()) }



    }
}

//
//bind() from singleton { instance<FoodDatabase>().foodDao() }
//        bind() from singleton { ApiService() }
//        bind() from singleton { FoodRepository(instance(), instance()) }
//        bind() from provider { FoodViewmodelFactory(instance()) }
//    }
//}
////bind<FoodDatabase>() with singleton { FoodDatabase.getInstance(instance()) }
