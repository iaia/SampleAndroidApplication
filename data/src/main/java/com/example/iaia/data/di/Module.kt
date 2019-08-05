package com.example.iaia.data.di

import com.example.iaia.data.db.DummyDatabase
import com.example.iaia.data.remote.example.api.DummyApiClient
import com.example.iaia.data.remote.example.api.ExampleApiClient
import com.example.iaia.data.repository.EmployeeRepository
import com.example.iaia.data.repository.EmployeeRepositoryImpl
import com.example.iaia.sampleandroidapplication.data.repository.UserRepository
import com.example.iaia.sampleandroidapplication.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val apiModule = module {
    single { DummyApiClient.build() }
}

val mockApiModule = module {
    single { ExampleApiClient.buildMock(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<EmployeeRepository> { EmployeeRepositoryImpl(get(), get()) }
}

val databaseModule = module {
    single { DummyDatabase.getDatabase(get()) }
    single { get<DummyDatabase>().employeeDao() }
}