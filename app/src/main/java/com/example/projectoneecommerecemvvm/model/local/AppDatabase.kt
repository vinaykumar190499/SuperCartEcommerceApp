package com.example.projectoneecommerecemvvm.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projectoneecommerecemvvm.model.data.Cart

@Database(entities = [Cart::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "myecommerce"
                    ).allowMainThreadQueries().addMigrations(MIGRATION_1_2).build()
                }
            }
            return INSTANCE
        }
    }
}
val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE cart ADD COLUMN productname TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE cart ADD COLUMN description TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE cart ADD COLUMN price REAL NOT NULL DEFAULT 0.0")
        database.execSQL("ALTER TABLE cart ADD COLUMN imageurl TEXT NOT NULL DEFAULT ''")
    }

}