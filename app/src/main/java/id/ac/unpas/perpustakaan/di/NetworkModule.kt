package id.ac.unpas.perpustakaan.di

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ac.unpas.agenda.networks.RequestInterceptor
import id.ac.unpas.perpustakaan.networks.BookApi
import id.ac.unpas.perpustakaan.networks.MembershipApi
import id.ac.unpas.perpustakaan.networks.BookRequestApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(RequestInterceptor()).build()
    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://ppm-api.nimbus.biz.id/api/library/") // Pastikan base URL diakhiri dengan /
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://ppm-api.nimbus.biz.id/api/library/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBookApi(retrofit: Retrofit): BookApi {
        return retrofit.create(BookApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMembershipApi(retrofit: Retrofit): MembershipApi {
        return retrofit.create(MembershipApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRequestApi(retrofit: Retrofit): BookRequestApi {
        return retrofit.create(BookRequestApi::class.java)
    }
}