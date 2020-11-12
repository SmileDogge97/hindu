package com.anushka.retrofitdemo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//6) se hace esta clase
class RetrofitInstance {
    companion object {
        //la url de la api
        val BASE_URL: String = "https://jsonplaceholder.typicode.com"
        //10) hago la variable para el interceptor
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        //11) asigno el tiempo que se va a tardar en actualizar
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(25,TimeUnit.SECONDS)
        }.build()

        //la función que interactua con la api
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                    //la url del api
                .baseUrl(BASE_URL)
                .client(client)
                    //convertimos lo que manda la api a un formato legible facilmente
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                //crea la acción
                .build()
        }

    }
}