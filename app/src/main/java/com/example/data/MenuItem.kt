package com.example.data

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    val nama: String,
    val harga: String,
    val hargaInt: Int, // for calculating totals inside cart
    val deskripsi: String,
    val gambar: Any, // Can be URL string or Drawable Resource ID (Int)
    val fallbackIcon: ImageVector, // Material Icon fallback
    val rating: Double = 4.8,
    val reviewsCount: Int = 120,
    val estimasiMenit: String = "15-20 Menit",
    val kategori: String = "Nasi",
    val isTerpopuler: Boolean = false,
    val tingkatKepedasanSupported: List<String> = listOf("Tidak Pedas", "Sedang", "Pedas", "Sangat Pedas"),
    val toppingSupported: List<String> = listOf("Telur", "Sosis", "Keju", "Bakso Tambahan")
)
