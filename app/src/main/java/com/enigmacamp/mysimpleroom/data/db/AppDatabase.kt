package com.enigmacamp.mysimpleroom.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enigmacamp.mysimpleroom.data.entity.Customer
import com.enigmacamp.mysimpleroom.data.entity.Pocket
import com.enigmacamp.mysimpleroom.data.entity.PocketList

/*
    1. Ada perubahan, tapi tidak menaikan versi => Crash
    2. Ada perubahan, cuma menaikan versi => Crash
    3. Ada perubahan, naik versi, tambah config fallBackToDestructive => Jalan dengan hilang data
    4. Ada perubahan, naik versi, buat object migration => Jalan dengan data tetap ada
 */
@Database(entities = [Customer::class, Pocket::class, PocketList::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun pocketDao(): PocketDao
    abstract fun pocketListDao(): PocketListDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE m_customer ADD COLUMN gender TEXT NOT NULL DEFAULT ''")
//            }
//        }
//        private val MIGRATION_2_3 = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE m_customer ADD COLUMN mobile_phone_1 TEXT NOT NULL DEFAULT ''")
//            }
//        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "enigma_database"
                )
//                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}