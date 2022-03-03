package com.accenture.creativeproducts.examples.server.schema.repository

import com.accenture.creativeproducts.examples.server.schema.models.Todo

class TodoRepository {
    private val todos = listOf(
        Todo(id = 1, title = "Campbell Biology", content = "Lorem ipsum"),
        Todo(id = 2, title = "The Cell", content = "Lorem ipsum"),
        Todo(id = 3, title = "Data Structures in C++", content = "Lorem ipsum"),
        Todo(id = 4, title = "The Algorithm Design Manual", content = "Lorem ipsum")
    )

    fun find(ids: List<Int>): List<Todo> = todos.filter { ids.contains(it.id) }
}
