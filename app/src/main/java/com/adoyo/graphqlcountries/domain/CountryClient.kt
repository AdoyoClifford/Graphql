package com.adoyo.graphqlcountries.domain

import com.adoyo.graphqlcountries.domain.DetailedCountry
import com.adoyo.graphqlcountries.domain.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}