package com.erill.roger.remote

import retrofit2.Retrofit

interface RemoteComponent {
    fun retrofit(): Retrofit
}