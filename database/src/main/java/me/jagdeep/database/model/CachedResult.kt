package me.jagdeep.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "results")
data class CachedResult(
    @PrimaryKey
    var pageId: Int,
    var title: String,
    var description: String?,
    var imageUrl: String?
)
