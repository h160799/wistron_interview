package com.example.wistron_interview

import android.app.Application
import com.example.wistron_interview.data.DataSource.TaipeiTravelRepository
import com.example.wistron_interview.util.ServiceLocator
import kotlin.properties.Delegates

class TaipeiTravelApplication : Application() {

    val taipeiTravelRepository: TaipeiTravelRepository
        get() = ServiceLocator.provideTasksRepository(this)


    companion object {
        var instance: Application by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}