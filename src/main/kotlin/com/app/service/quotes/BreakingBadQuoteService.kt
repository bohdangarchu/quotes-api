package com.app.service.quotes

import com.app.domain.Quote
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import jakarta.inject.Singleton
import java.net.URI
import java.net.URL

@Singleton
class BreakingBadQuoteService : QuoteService {

    private val url = URI.create("https://api.breakingbadquotes.xyz").toURL()

    // @Inject @Client("https://api.breakingbadquotes.xyz") private lateinit var client: HttpClient
    private val client = HttpClient.create(url)

    override fun getQuotes(number: Int): List<Quote> {
        val request = HttpRequest.GET<Any>("/v1/quotes/$number")
        val responseList = client.toBlocking().retrieve(request, List::class.java)
        return responseList.filterIsInstance<Map<*, *>>().mapNotNull { map ->
            val quote = map["quote"] as? String
            val author = map["author"] as? String
            if (quote != null && author != null) {
                Quote(quote, author)
            } else {
                null
            }
        }
    }

}