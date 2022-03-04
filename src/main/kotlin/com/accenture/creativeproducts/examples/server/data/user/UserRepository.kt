package com.accenture.creativeproducts.examples.server.data.user

import com.accenture.creativeproducts.examples.server.data.login.AuthPayload

class UserRepository {
    private val users = listOf(
        User(
            email = "test@test.com",
            firstName = "Test",
            lastName = "Testlast",
        ),
        User(
            email = "test2@test.com",
            firstName = "Test 2",
            lastName = "Testlast 2",
        ),
    )

    private val tokensMap = mapOf(
        "fake-token" to users.first { it.email == "test@test.com" }
    )

    fun findByEmails(emails: List<String>) = users.filter { it.email in emails }

    fun findByToken(token: String) =
        tokensMap[token]

    fun login(email: String, password: String): AuthPayload {
        val user = users.find { it.email == email }
        if (user != null && password == "test") {
            val token = "fake-token"
            return AuthPayload(token, user)
        } else throw LoginException()
    }

    fun findByEmail(email: String) = users.find { it.email == email }
}

class LoginException : Exception("Login credentials incorrect")