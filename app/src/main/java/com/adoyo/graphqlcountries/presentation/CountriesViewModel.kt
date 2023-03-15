package com.adoyo.graphqlcountries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adoyo.graphqlcountries.domain.DetailedCountry
import com.adoyo.graphqlcountries.domain.GetCountriesUseCase
import com.adoyo.graphqlcountries.domain.GetCountryUseCase
import com.adoyo.graphqlcountries.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CountriesViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
): ViewModel() {
    private val _state = MutableStateFlow(CountryState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { it.copy(countries = getCountriesUseCase.execute(),isLoading = false) }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update { it.copy(
                selectedCountry = getCountryUseCase.execute(code)
            ) }
        }
    }

    fun dismissDialog() {
        _state.update { it.copy(selectedCountry = null) }
    }

    data class CountryState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )
}