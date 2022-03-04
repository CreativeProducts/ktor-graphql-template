package com.accenture.creativeproducts.examples.server.di

import com.accenture.creativeproducts.examples.server.*
import com.expediagroup.graphql.server.execution.DataLoaderRegistryFactory
import com.expediagroup.graphql.server.execution.GraphQLRequestHandler
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val KtorGraphQlModule = module {
    single { jacksonObjectMapper() }
    singleOf(::KtorGraphQLContextFactory)
    singleOf(::KtorGraphQLRequestParser)
    singleOf(::GraphQLRequestHandler)
    singleOf(::KtorGraphQLServer)
    singleOf(::KtorGraphQLApplicationCallHandler)
    singleOf(::KtorDataLoaderRegistryFactory) {
        bind<DataLoaderRegistryFactory>()
    }
}
