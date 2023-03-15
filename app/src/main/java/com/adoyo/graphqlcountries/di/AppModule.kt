package com.adoyo.graphqlcountries.di

import com.adoyo.graphqlcountries.data.ApolloCountryClient
import com.adoyo.graphqlcountries.domain.CountryClient
import com.adoyo.graphqlcountries.domain.GetCountriesUseCase
import com.adoyo.graphqlcountries.domain.GetCountryUseCase
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(
                "https://countries.trevorblades.com/graphql\n" +
                        "\n"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(countryClient: CountryClient): GetCountriesUseCase {
        return GetCountriesUseCase(countryClient)
    }

    @Provides
    @Singleton
    fun provideGetCountryUseCase(countryClient: CountryClient): GetCountryUseCase {
        return GetCountryUseCase(countryClient)
    }

}