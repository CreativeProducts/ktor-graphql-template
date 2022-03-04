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

package com.accenture.creativeproducts.examples.server.di

import com.accenture.creativeproducts.examples.server.data.login.LoginMutationService
import com.accenture.creativeproducts.examples.server.data.todo.TodoDataLoader
import com.accenture.creativeproducts.examples.server.data.todo.TodoQueryService
import com.accenture.creativeproducts.examples.server.data.user.UserDataLoader
import com.accenture.creativeproducts.examples.server.data.user.UserQueryService
import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import graphql.GraphQL
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Custom logic for how this Ktor server loads all the queries and configuration to create the [GraphQL] object
 * needed to handle incoming requests. In a more enterprise solution you may want to load more things from
 * configuration files instead of hardcoding them.
 */
private val config = SchemaGeneratorConfig(supportedPackages = listOf("com.accenture.creativeproducts.examples.server"))

private fun <T : Any> List<T>.toTopLevelList() = map { TopLevelObject(it) }

private object Qualifiers {
    val queries = named("queries")
    val mutations = named("mutations")
}

val KtorGraphQLSchemaModule = module {
    single {
        listOf(
            TodoDataLoader(get()),
            UserDataLoader(get()),
        )
    }
    single(qualifier = Qualifiers.queries) {
        listOf(
            TodoQueryService(get()),
            UserQueryService(get()),
        )
    }
    single(qualifier = Qualifiers.mutations) {
        listOf<Mutation>(
            LoginMutationService(get())
        )
    }
    single {
        val queries = get<List<Query>>(Qualifiers.queries).toTopLevelList()
        val mutations = get<List<Mutation>>(Qualifiers.mutations).toTopLevelList()
        toSchema(config, queries, mutations)
    }
    single {
        GraphQL.newGraphQL(get()).build()
    }
}
