package com.accenture.creativeproducts.examples.server.data.user

import com.expediagroup.graphql.server.execution.KotlinDataLoader
import kotlinx.coroutines.runBlocking
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import java.util.concurrent.CompletableFuture

class UserDataLoader(
    private val userRepository: UserRepository,
) : KotlinDataLoader<List<String>, List<User>> {
    override val dataLoaderName = "BATCH_TODO_LOADER"
    override fun getDataLoader(): DataLoader<List<String>, List<User>> = DataLoaderFactory.newDataLoader { emails ->
        CompletableFuture.supplyAsync {
            val allTodos = runBlocking { userRepository.findByEmails(emails.flatten()).toMutableList() }

            emails.fold(mutableListOf()) { acc: MutableList<List<User>>, emailsSet ->
                val matchingTodos = allTodos.filter { emailsSet.contains(it.email) }
                acc.add(matchingTodos)
                acc
            }
        }
    }
}
