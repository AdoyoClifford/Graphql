package com.adoyo.graphqlcountries.data

import com.adoyo.CountriesQuery
import com.adoyo.CountryQuery
import com.adoyo.graphqlcountries.domain.DetailedCountry
import com.adoyo.graphqlcountries.domain.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
    )
}