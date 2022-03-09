/*
 * Copyright 2021 Expedia, Inc
 * Copyright 2022 Creative Products and contributors *
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

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

/**
 * The Ktor specific code to handle incoming [ApplicationCall]s, send them to GraphQL,
 * and then format and send a correct response back.
 */
class KtorGraphQLApplicationCallHandler(private val ktorGraphQLServer: KtorGraphQLServer) {

    private val mapper = jacksonObjectMapper()

    /**
     * Handle incoming Ktor Http requests and send them back to the response methods.
     */
    suspend fun handle(applicationCall: ApplicationCall) {
        // Execute the query against the schema
        val result = ktorGraphQLServer.execute(applicationCall.request)

        if (result != null) {
            // write response as json
            val json = mapper.writeValueAsString(result)
            applicationCall.response.call.respond(json)
        } else {
            applicationCall.response.call.respond(HttpStatusCode.BadRequest, "Invalid request")
        }
    }
}
