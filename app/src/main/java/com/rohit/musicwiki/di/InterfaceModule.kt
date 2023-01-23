package com.rohit.musicwiki.di


import com.rohit.musicwiki.network.IRemoteDataSource
import com.rohit.musicwiki.network.RemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class InterfaceModule {
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: RemoteDataSource):
            IRemoteDataSource

}