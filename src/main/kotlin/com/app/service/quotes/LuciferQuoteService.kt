package com.app.service.quotes

import com.app.domain.Quote
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.net.URL

@Singleton
class LuciferQuoteService : QuoteService{

//    @Inject
//    @Client(id = "lucifer")
//    private lateinit var client : HttpClient

    private val client = HttpClient.create(URL("https://lucifer-quotes.vercel.app"))

    override fun getQuotes(number: Int): List<Quote> {
        val request = HttpRequest.GET<Any>("/api/quotes/$number")
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