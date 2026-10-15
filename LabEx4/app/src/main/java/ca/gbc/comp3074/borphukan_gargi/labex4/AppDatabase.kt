package ca.gbc.comp3074.borphukan_gargi.labex4

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Customer::class],
    version = 1,
exportSchema = false
)
abstract class AppDatabase: RoomDatabase() { //have to extend to a room dtb
    abstract fun customerdao(): CustomerDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) { //dtb will create a instance-if not there it will create it
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
