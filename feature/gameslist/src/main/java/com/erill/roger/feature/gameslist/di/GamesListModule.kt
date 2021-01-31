package com.erill.roger.feature.gameslist.di

import com.erill.roger.feature.gameslist.data.datasource.GamesDataSource
import com.erill.roger.feature.gameslist.data.remote.RestApi
import com.erill.roger.feature.gameslist.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class GamesListModule {

    @Binds
    internal abstract fun dataSourceProvider(repository: GamesDataSource): Repository

    companion object {

        @Provides
        internal fun accountManagerApiRestProvider(retrofit: Retrofit): RestApi {
            return retrofit.create(RestApi::class.java)
        }
    }
}