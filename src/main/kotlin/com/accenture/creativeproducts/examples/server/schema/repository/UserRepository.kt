package com.accenture.creativeproducts.examples.server.schema.repository

import com.accenture.creativeproducts.examples.server.schema.models.AuthPayload
import com.accenture.creativeproducts.examples.server.schema.models.User

class UserRepository {
    private val users = listOf(
        User(
            email = "test@test.com",
            firstName = "Test",
            lastName = "Testlast",
        ),
        User(
            email = "test@test.com",
            firstName = "Someone",
            lastName = "You Don't know",
        ),
    )

    fun findByEmails(emails: List<String>) = users.filter { it.email in emails }

    fun login(email: String, password: String): AuthPayload {
        val user = users.find { it.email == email }
        if (user != null && password == "test") {
            val token = "fake-token"
            return AuthPayload(token, user)
        } else throw LoginException()
    }
}

class LoginException : Exception("Login credentials incorrect")