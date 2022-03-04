package com.accenture.creativeproducts.examples.server.data.login

import com.accenture.creativeproducts.examples.server.data.user.UserRepository
import com.expediagroup.graphql.server.operations.Mutation


class LoginMutationService(private val userRepository: UserRepository) : Mutation {
    fun login(email: String, password: String): AuthPayload = userRepository.login(email, password)
}
