package com.accenture.creativeproducts.examples.server

import com.expediagroup.graphql.server.execution.DataLoaderRegistryFactory
import com.expediagroup.graphql.server.execution.KotlinDataLoader
import org.dataloader.DataLoaderRegistry

/**
 * Example of how to register the various DataLoaders using [DataLoaderRegistryFactory]
 */
class KtorDataLoaderRegistryFactory(
    private val injectedDataLoaders: List<KotlinDataLoader<*, *>>,
) : DataLoaderRegistryFactory {

    override fun generate(): DataLoaderRegistry = DataLoaderRegistry().apply {
        injectedDataLoaders.forEach { register(it.dataLoaderName, it.getDataLoader()) }
    }
}
