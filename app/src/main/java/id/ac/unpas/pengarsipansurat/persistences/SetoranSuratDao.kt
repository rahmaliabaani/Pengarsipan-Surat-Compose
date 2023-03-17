package id.ac.unpas.pengarsipansurat.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.pengarsipansurat.model.SetoranSurat

@Dao
interface SetoranSuratDao {
    @Query("SELECT * FROM SetoranSurat")
    fun loadAll(): LiveData<List<SetoranSurat>>

    @Query("SELECT * FROM SetoranSurat WHERE nosurat = :nosurat")
    fun find(nosurat: String): SetoranSurat?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: SetoranSurat)

    @Delete
    fun delete(item: SetoranSurat)
}

@Database(entities = [SetoranSurat::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun setoranSuratDao(): SetoranSuratDao
}