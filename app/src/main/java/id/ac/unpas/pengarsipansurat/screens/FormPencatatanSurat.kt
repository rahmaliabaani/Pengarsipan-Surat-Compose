package id.ac.unpas.pengarsipansurat.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.pengarsipansurat.model.SetoranSurat
import id.ac.unpas.pengarsipansurat.persistences.SetoranSuratDao
import id.ac.unpas.pengarsipansurat.ui.theme.Purple700
import id.ac.unpas.pengarsipansurat.ui.theme.Teal200
import kotlinx.coroutines.launch


@Composable
fun FormPencatatanSurat(setoranSuratDao: SetoranSuratDao) {
    val scope = rememberCoroutineScope()
    val nosurat = remember { mutableStateOf(TextFieldValue("")) }
    val tglterimasurat = remember { mutableStateOf(TextFieldValue("")) }
    val perihal = remember { mutableStateOf(TextFieldValue("")) }
    val tertuju = remember { mutableStateOf(TextFieldValue("")) }
    val tglpelaksanaan = remember { mutableStateOf(TextFieldValue("")) }
    val pengirim = remember { mutableStateOf(TextFieldValue("")) }


    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "No Surat") },
            value = nosurat.value,
            onValueChange = {
                nosurat.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "001/Kegiatan/HMTIF-UNPAS/II/2023") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Terima Surat") },
            value = tglterimasurat.value,
            onValueChange = {
                tglterimasurat.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Perihal") },
            value = perihal.value,
            onValueChange = {
                perihal.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Undangan Pembukaan") }
        )
        OutlinedTextField(
            label = { Text(text = "Tertuju") },
            value = tertuju.value,
            onValueChange = {
                tertuju.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Ketua Umum HMTIF-UNPAS") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Pelaksanaan") },
            value = tglpelaksanaan.value,
            onValueChange = {
                tglpelaksanaan.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Pengirim") },
            value = pengirim.value,
            onValueChange = {
                pengirim.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Fakultas Teknik") }
        )

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = SetoranSurat(id, nosurat.value.text, tglterimasurat.value.text,
                    perihal.value.text, tertuju.value.text, tglpelaksanaan.value.text, pengirim.value.text)
                scope.launch { setoranSuratDao.insertAll(item) }
                nosurat.value = TextFieldValue("")
                tglterimasurat.value = TextFieldValue("")
                perihal.value = TextFieldValue("")
                tertuju.value = TextFieldValue("")
                tglpelaksanaan.value = TextFieldValue("")
                pengirim.value = TextFieldValue("")

            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                nosurat.value = TextFieldValue("")
                tglterimasurat.value = TextFieldValue("")
                perihal.value = TextFieldValue("")
                tertuju.value = TextFieldValue("")
                tglpelaksanaan.value = TextFieldValue("")
                pengirim.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}