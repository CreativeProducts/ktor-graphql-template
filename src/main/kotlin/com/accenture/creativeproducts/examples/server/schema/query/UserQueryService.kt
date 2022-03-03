package com.accenture.creativeproducts.examples.server.schema.query

import com.accenture.creativeproducts.examples.server.currentUserEmail
import com.accenture.creativeproducts.examples.server.schema.models.User
import com.accenture.creativeproducts.examples.server.schema.repository.UserRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment

class UserQueryService(
    private val userRepository: UserRepository,
) : Query {
    @GraphQLDescription("Return the currently logged in user")
    fun myUser(dataFetchingEnvironment: DataFetchingEnvironment): User? =
        dataFetchingEnvironment.graphQlContext.currentUserEmail?.let {
            userRepository.findByEmail(it)
        }
}
