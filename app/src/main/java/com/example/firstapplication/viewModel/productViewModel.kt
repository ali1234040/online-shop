package com.example.firstapplication.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.firstapplication.data.db.ProductData
import com.example.firstapplication.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor() : ViewModel(){
    private val _products = MutableStateFlow<List<ProductModel>>(emptyList())
    val products: StateFlow<List<ProductModel>> = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = ProductData.productList
    }

    //برای افزودن محصولات به لیست خرید
    private val _selectedProducts = mutableStateListOf<ProductModel>()
    val selectedProducts: List<ProductModel> get() = _selectedProducts

    fun toggleProductSelection(product: ProductModel) {
        _selectedProducts.add(product)
    }
}

