package com.adoyo.graphqlcountries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.adoyo.graphqlcountries.presentation.CountriesScreen
import com.adoyo.graphqlcountries.presentation.CountriesViewModel
import com.adoyo.graphqlcountries.ui.theme.GraphQlCountriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQlCountriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<CountriesViewModel>()
                    val state by viewModel.state.collectAsState()

                    CountriesScreen(
                        state = state,
                        onSelectedCountry = viewModel::selectCountry,
                        onCountryDismiss = {})
                }
            }
        }
    }
}

