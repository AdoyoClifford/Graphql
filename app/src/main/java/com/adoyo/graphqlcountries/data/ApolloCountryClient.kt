package com.adoyo.graphqlcountries.data

import com.adoyo.CountriesQuery
import com.adoyo.CountryQuery
import com.adoyo.graphqlcountries.domain.CountryClient
import com.adoyo.graphqlcountries.domain.DetailedCountry
import com.adoyo.graphqlcountries.domain.SimpleCountry
import com.apollographql.apollo3.ApolloClient

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient.query(CountriesQuery())
            .execute().data?.countries?.map { it.toSimpleCountry() } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient.query(CountryQuery(code)).execute().data?.country?.toDetailedCountry()
    }
}