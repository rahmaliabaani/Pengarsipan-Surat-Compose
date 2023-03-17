package id.ac.unpas.pengarsipansurat.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.pengarsipansurat.model.SetoranSurat
import id.ac.unpas.pengarsipansurat.persistences.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PengelolaanSuratScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pengelolaan-surat"
    ).build()
    val setoranSuratDao = db.setoranSuratDao()
    val list : LiveData<List<SetoranSurat>> = setoranSuratDao.loadAll()
    val items: List<SetoranSurat> by list.observeAsState(initial = listOf())
    Column(modifier = Modifier.fillMaxWidth()) {
        FormPencatatanSurat(setoranSuratDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "No Surat", fontSize = 14.sp)
                        Text(text = item.nosurat, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal Terima Surat", fontSize = 14.sp)
                        Text(text = item.tglterimasurat, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Perihal", fontSize = 14.sp)
                        Text(text = item.perihal, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tertuju", fontSize = 14.sp)
                        Text(text = item.tertuju, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal Pelaksanaan", fontSize = 14.sp)
                        Text(text = item.tglpelaksanaan, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Pengirim", fontSize = 14.sp)
                        Text(text = item.pengirim, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}