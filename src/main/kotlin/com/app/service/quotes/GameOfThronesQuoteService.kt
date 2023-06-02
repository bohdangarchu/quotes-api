package com.app.service.quotes

import com.app.domain.Quote
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import jakarta.inject.Singleton
import java.net.URL

@Singleton
class GameOfThronesQuoteService : QuoteService {
    private val client = HttpClient.create(URL("https://api.gameofthronesquotes.xyz"))

    override fun getQuotes(number: Int): List<Quote> {
        val request = HttpRequest.GET<Any>("/v1/random/$number")
        val responseList = client.toBlocking().retrieve(request, List::class.java)
        return responseList.filterIsInstance<Map<*, *>>().mapNotNull { map ->
            val quote = map["sentence"] as? String
            val characterData = map["character"] as? Map<*, *>
            val author = characterData?.get("name") as? String
            if (quote != null && author != null) {
                Quote(quote, author)
            } else {
                null
            }
        }
    }
}