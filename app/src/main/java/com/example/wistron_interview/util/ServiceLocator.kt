package com.example.wistron_interview.util

import androidx.annotation.VisibleForTesting
import com.example.wistron_interview.TaipeiTravelApplication
import com.example.wistron_interview.data.DataSource.DefaultTaipeiTravelRepository
import com.example.wistron_interview.data.DataSource.TaipeiTravelRepository
import com.example.wistron_interview.data.remote.TaipeiTravelRemoteDataSource

object ServiceLocator {

    @Volatile
    var taipeiTravelRepository: TaipeiTravelRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(taipeiTravelApplication: TaipeiTravelApplication): TaipeiTravelRepository {
        synchronized(this) {
            return taipeiTravelRepository
                ?: taipeiTravelRepository
                ?: createRepository()
        }
    }

    private fun createRepository(): TaipeiTravelRepository {
        return DefaultTaipeiTravelRepository(
            TaipeiTravelRemoteDataSource
        )
    }

}