package com.pauljuma.karibuapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pauljuma.karibuapp.R

@Entity("partner_name")
data class FeaturedPartnersItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)