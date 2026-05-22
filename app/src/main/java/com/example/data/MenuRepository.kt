package com.example.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SoupKitchen
import com.example.R

object MenuRepository {
    val menuList = listOf(
        MenuItem(
            id = "1",
            nama = "Nasi Goreng Spesial",
            harga = "Rp 35.000",
            hargaInt = 35000,
            deskripsi = "Nasi goreng dengan bumbu spesial yang kaya akan rempah pilihan, disajikan dengan telur mata sapi yang lembut, kerupuk udang renyah, ayam suwir berbumbu, dan acar segar.",
            gambar = R.drawable.nasi_goreng,
            fallbackIcon = Icons.Filled.Restaurant,
            rating = 4.8,
            reviewsCount = 120,
            estimasiMenit = "15-20 Menit",
            kategori = "Nasi",
            isTerpopuler = true
        ),
        MenuItem(
            id = "2",
            nama = "Mie Ayam Bakso",
            harga = "Rp 30.000",
            hargaInt = 30000,
            deskripsi = "Mie kenyal buatan sendiri disajikan dengan tumisan daging ayam cincang gurih, daun bawang segar, kuah kaldu hangat yang bening, dan bakso sapi urat.",
            gambar = R.drawable.mie_ayam_bakso,
            fallbackIcon = Icons.Filled.DinnerDining,
            rating = 4.7,
            reviewsCount = 240,
            estimasiMenit = "10-15 Menit",
            kategori = "Mie",
            isTerpopuler = true
        ),
        MenuItem(
            id = "3",
            nama = "Ayam Bakar Madu",
            harga = "Rp 35.000",
            hargaInt = 35000,
            deskripsi = "Ayam muda segar yang dimasak dengan racikan bumbu bakar madu manis-gurih meresap sempurna, dibakar di atas arang kelapa.",
            gambar = R.drawable.ayam_bakar_madu,
            fallbackIcon = Icons.Filled.Fastfood,
            rating = 4.8,
            reviewsCount = 120,
            estimasiMenit = "20-25 Menit",
            kategori = "Populer",
            isTerpopuler = true
        ),
        MenuItem(
            id = "4",
            nama = "Sate Ayam Premium",
            harga = "Rp 35.000",
            hargaInt = 35000,
            deskripsi = "Sate daging dada ayam fillet tebal pilihan tanpa lemak, dipanggang merata dengan olesan kecap manis, disajikan dengan bumbu kacang kental.",
            gambar = R.drawable.sate_ayam,
            fallbackIcon = Icons.Filled.Restaurant,
            rating = 4.8,
            reviewsCount = 100,
            estimasiMenit = "15-20 Menit",
            kategori = "Sate",
            isTerpopuler = false
        ),
        MenuItem(
            id = "6",
            nama = "Soto Ayam Kuning",
            harga = "Rp 25.000",
            hargaInt = 25000,
            deskripsi = "Soto ayam gurih berkuah kuning rempah kunyit alami, berisi irisan kol segar, tauge, bihun, ayam suwir melimpah, dan telur rebus.",
            gambar = R.drawable.soto_ayam,
            fallbackIcon = Icons.Filled.SoupKitchen,
            rating = 4.7,
            reviewsCount = 95,
            estimasiMenit = "12 Menit",
            kategori = "Nasi",
            isTerpopuler = false
        ),
        MenuItem(
            id = "7",
            nama = "Kopi Tubruk Pilihan",
            harga = "Rp 15.000",
            hargaInt = 15000,
            deskripsi = "Biji kopi robusta Lampung premium digiling segar langsung saat dipesan, diseduh tubruk tradisional menyisakan rasa cokelat gelap.",
            gambar = R.drawable.kopi_tubruk,
            fallbackIcon = Icons.Filled.Coffee,
            rating = 4.8,
            reviewsCount = 150,
            estimasiMenit = "5 Menit",
            kategori = "Minuman",
            isTerpopuler = false
        ),
        MenuItem(
            id = "8",
            nama = "Es Teh Segar",
            harga = "Rp 5.000",
            hargaInt = 5000,
            deskripsi = "Teh seduh pilihan yang disajikan dingin dengan es batu kristal, sangat menyegarkan.",
            gambar = R.drawable.es_teh,
            fallbackIcon = Icons.Filled.Coffee,
            rating = 4.9,
            reviewsCount = 300,
            estimasiMenit = "2 Menit",
            kategori = "Minuman",
            isTerpopuler = false
        )
    )

    fun getMenuById(id: String): MenuItem? {
        return menuList.find { it.id == id }
    }
}
