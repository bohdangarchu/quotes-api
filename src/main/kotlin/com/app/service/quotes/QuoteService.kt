package com.app.service.quotes

import com.app.domain.Quote

interface QuoteService {
    fun getQuotes(number: Int): List<Quote>
}