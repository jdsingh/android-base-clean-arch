package me.jagdeep.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "queries")
data class CachedQuery(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    @ColumnInfo(index = true)
    var text: String,

    var lastCachedTime: Long,

    var pageIds: String
)
