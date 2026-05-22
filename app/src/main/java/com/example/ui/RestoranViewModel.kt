package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.data.MenuItem
import com.example.data.MenuRepository
import com.example.data.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int,
    val spiciness: String = "Sedang",
    val toppings: List<String> = emptyList()
)

data class RestoranUiState(
    val nama: String = "Restoran Kita",
    val alamat: String = "Jl. Contoh No. 123, Jakarta",
    val deskripsi: String = "Restoran keluarga dengan cita rasa nusantara.",
    val jamBuka: String = "08:00 – 21:00, Setiap Hari",
    val isDarkTheme: Boolean = false,
    val cartList: List<CartItem> = emptyList(),
    val itemRatings: Map<String, Double> = emptyMap(), // menuItemId to userRating
    val placedOrders: List<PlacedOrder> = emptyList()
)

data class PlacedOrder(
    val id: String,
    val timestamp: String,
    val items: List<CartItem>,
    val totalAmount: Int,
    val status: String = "Diproses"
)

class RestoranViewModel(application: Application) : AndroidViewModel(application) {
    private val prefsManager = PreferencesManager(application)
    
    private val _uiState = MutableStateFlow(RestoranUiState())
    val uiState: StateFlow<RestoranUiState> = _uiState.asStateFlow()

    init {
        loadDataFromPrefs()
    }

    fun loadDataFromPrefs() {
        val profil = prefsManager.loadProfil()
        val isDark = prefsManager.isThemeDark()
        val ratings = prefsManager.loadRatings()
        _uiState.update {
            it.copy(
                nama = profil[PreferencesManager.KEY_NAMA] ?: "Restoran Kita",
                alamat = profil[PreferencesManager.KEY_ALAMAT] ?: "-",
                deskripsi = profil[PreferencesManager.KEY_DESKRIPSI] ?: "-",
                jamBuka = profil[PreferencesManager.KEY_JAM_BUKA] ?: "-",
                isDarkTheme = isDark,
                itemRatings = ratings
            )
        }
    }

    fun updateProfil(nama: String, alamat: String, deskripsi: String, jamBuka: String) {
        prefsManager.saveProfil(nama, alamat, deskripsi, jamBuka)
        _uiState.update {
            it.copy(
                nama = nama,
                alamat = alamat,
                deskripsi = deskripsi,
                jamBuka = jamBuka
            )
        }
    }

    fun toggleTheme(isDark: Boolean) {
        prefsManager.saveThemeDark(isDark)
        _uiState.update {
            it.copy(isDarkTheme = isDark)
        }
    }

    fun addToCart(menuItem: MenuItem, qty: Int, spiciness: String = "Sedang", toppings: List<String> = emptyList()) {
        _uiState.update { current ->
            val existing = current.cartList.find { 
                it.menuItem.id == menuItem.id && 
                it.spiciness == spiciness && 
                it.toppings == toppings 
            }
            val newList = if (existing != null) {
                current.cartList.map {
                    if (it == existing) it.copy(quantity = it.quantity + qty) else it
                }
            } else {
                current.cartList + CartItem(menuItem, qty, spiciness, toppings)
            }
            current.copy(cartList = newList)
        }
    }

    fun updateCartQuantity(item: CartItem, newQty: Int) {
        _uiState.update { current ->
            if (newQty <= 0) {
                current.copy(cartList = current.cartList.filter { it != item })
            } else {
                current.copy(cartList = current.cartList.map {
                    if (it == item) it.copy(quantity = newQty) else it
                })
            }
        }
    }

    fun checkoutCart() {
        val cart = _uiState.value.cartList
        if (cart.isEmpty()) return

        val total = cart.sumOf { it.menuItem.hargaInt * it.quantity }
        val order = PlacedOrder(
            id = "ORD-${System.currentTimeMillis() % 100000}",
            timestamp = "Hari Ini, ${java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault()).format(java.util.Date())}",
            items = cart,
            totalAmount = total
        )

        _uiState.update {
            it.copy(
                placedOrders = listOf(order) + it.placedOrders,
                cartList = emptyList() // clear cart
            )
        }
    }

    fun submitRating(itemId: String, rating: Double) {
        prefsManager.saveRating(itemId, rating.toFloat())
        _uiState.update { current ->
            val ratings = current.itemRatings.toMutableMap()
            ratings[itemId] = rating
            current.copy(itemRatings = ratings)
        }
    }
}
