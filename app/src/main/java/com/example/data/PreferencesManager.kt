package com.example.data

import android.content.Context

class PreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences("restoran_prefs", Context.MODE_PRIVATE)

    companion object {
        const val KEY_NAMA = "nama_restoran"
        const val KEY_ALAMAT = "alamat"
        const val KEY_DESKRIPSI = "deskripsi"
        const val KEY_JAM_BUKA = "jam_buka"
        const val KEY_THEME_DARK = "theme_dark"
        const val PREFIX_RATING = "rating_"
    }

    init {
        if (!prefs.contains(KEY_NAMA)) {
            prefs.edit()
                .putString(KEY_NAMA, "Restoran Kita")
                .putString(KEY_ALAMAT, "Jl. Contoh No. 123, Jakarta")
                .putString(KEY_DESKRIPSI, "Restoran keluarga dengan cita rasa nusantara, menyajikan hidangan lezat dengan suasana nyaman.")
                .putString(KEY_JAM_BUKA, "08:00 – 21:00, Setiap Hari")
                .apply()
        }
    }

    fun saveProfil(nama: String, alamat: String, deskripsi: String, jamBuka: String) {
        prefs.edit()
            .putString(KEY_NAMA, nama)
            .putString(KEY_ALAMAT, alamat)
            .putString(KEY_DESKRIPSI, deskripsi)
            .putString(KEY_JAM_BUKA, jamBuka)
            .apply()
    }

    fun loadProfil(): Map<String, String> = mapOf(
        KEY_NAMA to (prefs.getString(KEY_NAMA, "Restoran Kita") ?: "Restoran Kita"),
        KEY_ALAMAT to (prefs.getString(KEY_ALAMAT, "Jl. Contoh No. 123, Jakarta") ?: "-"),
        KEY_DESKRIPSI to (prefs.getString(KEY_DESKRIPSI, "Restoran keluarga dengan cita rasa nusantara, menyajikan hidangan lezat dengan suasana nyaman.") ?: "-"),
        KEY_JAM_BUKA to (prefs.getString(KEY_JAM_BUKA, "08:00 – 21:00, Setiap Hari") ?: "-")
    )

    fun saveThemeDark(isDark: Boolean) {
        prefs.edit().putBoolean(KEY_THEME_DARK, isDark).apply()
    }

    fun isThemeDark(): Boolean = prefs.getBoolean(KEY_THEME_DARK, false)

    fun saveRating(itemId: String, rating: Float) {
        prefs.edit().putFloat(PREFIX_RATING + itemId, rating).apply()
    }

    fun loadRatings(): Map<String, Double> {
        val ratings = mutableMapOf<String, Double>()
        prefs.all.forEach { (key, value) ->
            if (key.startsWith(PREFIX_RATING) && value is Float) {
                val itemId = key.substring(PREFIX_RATING.length)
                ratings[itemId] = value.toDouble()
            }
        }
        return ratings
    }
}
