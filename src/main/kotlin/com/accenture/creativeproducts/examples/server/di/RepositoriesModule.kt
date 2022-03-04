package com.accenture.creativeproducts.examples.server.di

import com.accenture.creativeproducts.examples.server.data.todo.TodoRepository
import com.accenture.creativeproducts.examples.server.data.user.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val RepositoriesModule = module {
    singleOf(::TodoRepository)
    singleOf(::UserRepository)
}