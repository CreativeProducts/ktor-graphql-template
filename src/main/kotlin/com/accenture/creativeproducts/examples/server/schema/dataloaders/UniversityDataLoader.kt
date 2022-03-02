/*
 * Copyright 2021 Expedia, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accenture.creativeproducts.examples.server.schema.dataloaders

import com.accenture.creativeproducts.examples.server.schema.models.University
import com.expediagroup.graphql.server.execution.KotlinDataLoader
import kotlinx.coroutines.runBlocking
import org.dataloader.DataLoaderFactory
import java.util.concurrent.CompletableFuture

val UniversityDataLoader = object : KotlinDataLoader<Int, University?> {
    override val dataLoaderName = "UNIVERSITY_LOADER"
    override fun getDataLoader() = DataLoaderFactory.newDataLoader<Int, University?> { ids ->
        CompletableFuture.supplyAsync {
            runBlocking { University.search(ids).toMutableList() }
        }
    }
}
