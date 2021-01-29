package com.erill.roger.remote

import arrow.core.Either
import io.reactivex.Single
import retrofit2.Response

fun <T, S> Single<Response<T>>.toGenericEither(mapToDomain: (T) -> S): Single<Either<Throwable, S>> {
    return this
        .map { retrofitResponse ->
            try {
                Either.conditionally(
                    retrofitResponse.isSuccessful,
                    { Throwable() },
                    { mapToDomain(retrofitResponse.body()!!) }
                )
            } catch (e: Exception) {
                Either.left(Throwable(e))
            }
        }.onErrorReturn {
            Either.left(it)
        }
}