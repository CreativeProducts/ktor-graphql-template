package com.accenture.creativeproducts.examples.server.di

import com.accenture.creativeproducts.examples.server.schema.repository.TodoRepository
import com.accenture.creativeproducts.examples.server.schema.repository.UserRepository
import org.koin.dsl.module

val RepositoriesModule = module {
    single { TodoRepository() }
    single { UserRepository() }
}