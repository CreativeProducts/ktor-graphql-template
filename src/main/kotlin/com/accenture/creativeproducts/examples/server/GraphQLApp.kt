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
package com.accenture.creativeproducts.examples.server

import com.accenture.creativeproducts.examples.server.di.KtorGraphQlModule
import com.expediagroup.graphql.generator.extensions.print
import graphql.GraphQL
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.koin.logger.SLF4JLogger

@Suppress("unused")
fun Application.graphQLApp() {
    install(Routing)
    install(CallLogging)
    install(Koin) {
        SLF4JLogger()
        modules(
            KtorGraphQLSchemaModule,
            KtorGraphQlModule,
        )
    }

    val graphQl by inject<GraphQL>()
    val graphQlCallHandler by inject<KtorGraphQLApplicationCallHandler>()

    routing {
        post("graphql") {
            graphQlCallHandler.handle(this.call)
        }

        get("sdl") {
            call.respondText(graphQl.graphQLSchema.print())
        }

        get("playground") {
            this.call.respondText(buildPlaygroundHtml(), ContentType.Text.Html)
        }
    }
}

private fun buildPlaygroundHtml(graphQLEndpoint: String = "graphql", subscriptionsEndpoint: String = "subscriptions") =
    Application::class.java.classLoader.getResource("graphql-playground.html")?.readText()
        ?.replace("\${graphQLEndpoint}", graphQLEndpoint)?.replace("\${subscriptionsEndpoint}", subscriptionsEndpoint)
        ?: throw IllegalStateException("graphql-playground.html cannot be found in the classpath")
