package com.accenture.creativeproducts.examples.server

import com.accenture.creativeproducts.examples.server.data.user.UserRepository
import com.expediagroup.graphql.server.execution.GraphQLContextFactory
import graphql.GraphQLContext
import io.ktor.request.*

/**
 * Creates a context based on the request
 */
class KtorGraphQLContextFactory(
    private val userRepository: UserRepository,
) : GraphQLContextFactory<Nothing, ApplicationRequest> {

    override suspend fun generateContextMap(request: ApplicationRequest): Map<String, Any> = buildMap {
        val currentUser = request.headers["x-token"]?.let { token ->
            userRepository.findByToken(token)?.email
        }

        if (currentUser != null) {
            put(CURRENT_USER_EMAIL, currentUser)
        }
    }

    override suspend fun generateContext(request: ApplicationRequest): Nothing? = null
}

private const val CURRENT_USER_EMAIL = "currentUserEmail"
val GraphQLContext.currentUserEmail: String get() = get(CURRENT_USER_EMAIL)