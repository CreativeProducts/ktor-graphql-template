package com.accenture.creativeproducts.examples.server.data.login

import com.accenture.creativeproducts.examples.server.data.user.User
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

@GraphQLDescription("The result for a successful login attempt")
data class AuthPayload(val token: String, val user: User)
