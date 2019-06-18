package com.example.iaia.data.remote.example.api

import android.content.Context
import com.example.iaia.data.model.User
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate

class MockExampleService(
    private val delegate: BehaviorDelegate<ExampleService>,
    private val context: Context
) : ExampleService {
    override suspend fun meAsync(): Response<User> {
        return Response.success(User(123, "iaia"))
        // val response = getJsonData("mock/json/user/me.json")
        // val response = Response.success(User(0, "iaia"))
        // return delegate.returningResponse(response).meAsync()
    }

    private fun getJsonData(jsonFilePath: String): String =
        context.assets.open(jsonFilePath).bufferedReader().use { it.readText() }
}
