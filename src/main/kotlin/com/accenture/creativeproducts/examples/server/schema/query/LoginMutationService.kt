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

package com.accenture.creativeproducts.examples.server.schema.query

import com.accenture.creativeproducts.examples.server.schema.models.AuthPayload
import com.accenture.creativeproducts.examples.server.schema.repository.UserRepository
import com.expediagroup.graphql.server.operations.Mutation


class LoginMutationService(private val userRepository: UserRepository) : Mutation {
    fun login(email: String, password: String): AuthPayload = userRepository.login(email, password)
}
