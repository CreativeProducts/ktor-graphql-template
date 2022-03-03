package com.accenture.creativeproducts.examples.server

import com.accenture.creativeproducts.examples.server.schema.dataloaders.BookDataLoader
import com.accenture.creativeproducts.examples.server.schema.dataloaders.CourseDataLoader
import com.accenture.creativeproducts.examples.server.schema.dataloaders.UniversityDataLoader
import com.expediagroup.graphql.server.execution.DataLoaderRegistryFactory
import org.dataloader.DataLoaderRegistry

/**
 * Example of how to register the various DataLoaders using [DataLoaderRegistryFactory]
 */
class KtorDataLoaderRegistryFactory :
    DataLoaderRegistryFactory {

    override fun generate(): DataLoaderRegistry = DataLoaderRegistry().apply {
        register(UniversityDataLoader.dataLoaderName, UniversityDataLoader.getDataLoader())
        register(CourseDataLoader.dataLoaderName, CourseDataLoader.getDataLoader())
        register(BookDataLoader.dataLoaderName, BookDataLoader.getDataLoader())
    }
}
