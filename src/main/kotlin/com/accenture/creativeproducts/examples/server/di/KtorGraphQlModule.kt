package com.accenture.creativeproducts.examples.server.di

import com.accenture.creativeproducts.examples.server.*
import com.expediagroup.graphql.server.execution.DataLoaderRegistryFactory
import com.expediagroup.graphql.server.execution.GraphQLRequestHandler
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.koin.dsl.module

val KtorGraphQlModule = module {
    single { jacksonObjectMapper() }
    single { KtorGraphQLContextFactory(get()) }
    single { KtorDataLoaderRegistryFactory(get()) }
    single { KtorGraphQLRequestParser(get()) }
    single { GraphQLRequestHandler(get(), get()) }
    single { KtorGraphQLServer(get(), get(), get()) }
    single { KtorGraphQLApplicationCallHandler(get()) }
    single<DataLoaderRegistryFactory> { KtorDataLoaderRegistryFactory(get()) }
}
