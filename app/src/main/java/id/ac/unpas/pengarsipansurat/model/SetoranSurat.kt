package id.ac.unpas.pengarsipansurat.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SetoranSurat(
    @PrimaryKey val id: String,
    val nosurat: String,
    val tglterimasurat: String,
    val perihal: String,
    val tertuju: String,
    val tglpelaksanaan: String,
    val pengirim: String
)
