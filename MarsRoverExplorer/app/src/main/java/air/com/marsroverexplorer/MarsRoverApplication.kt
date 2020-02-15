package air.com.marsroverexplorer

import air.com.marsroverexplorer.data.network.MarsAPI
import air.com.marsroverexplorer.data.network.NetWorkConnectionInterceptor
import air.com.marsroverexplorer.data.repository.RoverRepository
import air.com.marsroverexplorer.ui.roverlist.RoverListViewModelFactory
import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MarsRoverApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MarsRoverApplication))

        bind() from singleton { NetWorkConnectionInterceptor(instance())}
        bind() from singleton { MarsAPI(instance()) }
        bind() from singleton { RoverRepository(instance()) }
        bind() from provider { RoverListViewModelFactory(instance()) }
    }
}