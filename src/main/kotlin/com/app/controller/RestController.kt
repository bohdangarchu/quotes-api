package com.app.controller

import com.app.domain.Quote
import com.app.service.math.NumberPartitionService
import com.app.service.quotes.BreakingBadQuoteService
import com.app.service.quotes.GameOfThronesQuoteService
import com.app.service.quotes.LuciferQuoteService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject

@Controller("/quotes")
class RestController {
    @Inject
    private lateinit var breakingBadQuoteService : BreakingBadQuoteService
    @Inject
    private lateinit var gotQuoteService : GameOfThronesQuoteService
    @Inject
    private lateinit var luciferQuoteService : LuciferQuoteService
    @Inject
    private lateinit var numberPartitionService: NumberPartitionService

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun getQuotes(@QueryValue(defaultValue = "5") n: Int): List<Quote> {
        var numbers = numberPartitionService.partitionNumber(n)
        return breakingBadQuoteService.getQuotes(numbers.first) +
                gotQuoteService.getQuotes(numbers.second) +
                luciferQuoteService.getQuotes(numbers.third)
    }

    @Get("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    fun getHello(): String {
        return "hello"
    }


}