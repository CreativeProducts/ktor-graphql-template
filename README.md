# GraphQL Kotlin Ktor starter project

[Ktor](http://ktor.io/) is an asynchronous framework for creating microservices, web applications, and more.

### Running locally

Build the application by running the following from examples root directory:

```bash
# build example
./gradlew ktor-server:build
```

> NOTE: in order to ensure you use the right version of Gradle we highly recommend that you use the provided wrapper scripts

Start the server by running `Application.kt` directly from your IDE. (Make sure that your Kotlin Compiler jvm target is
1.8 or greater.)
Alternatively, you can start the server using Gradle.

```bash
cd /path/to/ktor-graphql-template
./gradlew ktor-server:run
```

Once the app has started you can:

- send GraphQL requests by opening the Playground endpoint at http://localhost:8080/playground
- send GraphQL requests directly to the endpoint at http://localhost:8080/graphql
- explore and interact with the example schema by opening the Playground IDE endpoint
  at http://localhost:8080/playground

## Credits

Based on the Ktor example code at https://github.com/ExpediaGroup/graphql-kotlin. The implementation of the schema and
internals have been heavily modified or totally substituted to provide a more complete template to start a project.

The copyright of the unmodified files and the original work is to be attributed to Expedia, Inc. Full original license 
can be found in the `vendor/LICENSE` file.
