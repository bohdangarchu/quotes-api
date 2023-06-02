package com.app
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.net.URL


@MicronautTest
class QuotesApiTest {


    @Inject
    lateinit var application: EmbeddedApplication<*>

    private var client = HttpClient.create(URL("http://localhost:8080"))


    @Test
    fun testItWorks() {
        Assertions.assertTrue(application.isRunning)
    }

    @Test
    fun testHello() {
        val request: HttpRequest<String> = HttpRequest.GET("/quotes/hello")
        val body: String = client.toBlocking().retrieve(request)
        Assertions.assertNotNull(body)
        Assertions.assertEquals("hello", body)
    }

}
