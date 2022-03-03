package com.accenture.creativeproducts.examples.server.schema.query

import com.accenture.creativeproducts.examples.server.schema.repository.TodoRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query

class TodoQueryService(
    private val todoRepository: TodoRepository,
) : Query {
    @GraphQLDescription("Return list of todos based on the given parameters")
    @Suppress("unused")
    fun searchTodos(params: TodoSearchParameters) = todoRepository.find(params.ids)
}

data class TodoSearchParameters(val ids: List<Int>)
