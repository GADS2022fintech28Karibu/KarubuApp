package com.pauljuma.karibuapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pauljuma.karibuapp.data.CartItem
import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.data.FeaturedPartnersItem

@Database(
    entities = [FavoriteMealsItem::class, FeaturedPartnersItem::class, CartItem::class],
    version = 7,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFeaturedPartnerDao(): FeaturedPartnersDao
    abstract fun getFavoriteMealDao(): FavoriteMealsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "karibu.db"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries().build()

    }

}