package com.erill.roger.feature.gamedetail.di

import com.erill.roger.feature.gamedetail.data.datasource.GameDataSource
import com.erill.roger.feature.gamedetail.data.remote.GameRunRestApi
import com.erill.roger.feature.gamedetail.domain.GameRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class GameRunModule {

    @Binds
    internal abstract fun dataSourceProvider(repository: GameDataSource): GameRepository

    companion object {

        @Provides
        internal fun gameRunRestProvider(retrofit: Retrofit): GameRunRestApi {
            return retrofit.create(GameRunRestApi::class.java)
        }
    }
}