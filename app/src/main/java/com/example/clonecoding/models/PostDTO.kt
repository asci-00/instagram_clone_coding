package com.example.clonecoding.models

import android.media.Image
import java.sql.Timestamp

data class PostDTO(
    var title: String? = null,
    var profile: String? = null,
    var timestamp: Timestamp? = null,
    var location: String? = null,
) {
    companion object {
        const val COMMON = 1
        const val VIDEO = 2
    }
}

data class CommonPostDTO (
    var id: Long? = null,
    var viewType: Int? = null,
    var title: String? = null,
    var profile: String? = null,
    var userName: String? = null,
    var timestamp: Timestamp? = null,
    var location: String? = null,
    var mainImage: Image? = null,
    var favoriteCount: Number? = null,
    var commentCount: Number? = null,
    var bookmarked: Boolean? = null,
    var favorited: Boolean? = null,
    var textContent: String? = null,
)
