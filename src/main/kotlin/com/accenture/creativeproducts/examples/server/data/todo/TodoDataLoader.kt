package com.accenture.creativeproducts.examples.server.data.todo

import com.expediagroup.graphql.server.execution.KotlinDataLoader
import kotlinx.coroutines.runBlocking
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import java.util.concurrent.CompletableFuture

class TodoDataLoader(
    private val todoRepository: TodoRepository,
) : KotlinDataLoader<List<Int>, List<Todo>> {
    override val dataLoaderName = "BATCH_TODO_LOADER"
    override fun getDataLoader(): DataLoader<List<Int>, List<Todo>> = DataLoaderFactory.newDataLoader { ids ->
        CompletableFuture.supplyAsync {
            val allTodos = runBlocking { todoRepository.find(ids.flatten()).toMutableList() }

            ids.fold(mutableListOf()) { acc: MutableList<List<Todo>>, idSet ->
                val matchingTodos = allTodos.filter { idSet.contains(it.id) }
                acc.add(matchingTodos)
                acc
            }
        }
    }
}
