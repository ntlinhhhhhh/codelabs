package com.example.dessert.ui.theme

import androidx.lifecycle.ViewModel
import com.example.dessert.model.Dessert
import com.example.dessert.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val dessertList = Datasource.dessertList

    private val _uiState = MutableStateFlow(
        DessertUiState(
            currentDessertPrice = dessertList.first().price,
            currentDessertImageId = dessertList.first().imageId
        )
    )
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update { currentState ->
            val newDessertsSold = currentState.dessertsSold + 1

            val nextDessert = determineDessertToShow(dessertList, newDessertsSold)

            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = newDessertsSold,
                currentDessertPrice = nextDessert.price,
                currentDessertImageId = nextDessert.imageId
            )
        }
    }

    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }
}