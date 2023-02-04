package com.example.clonecoding.models

import android.media.Image

data class UserDTO(
    var id: Long? = null,
    var profile: Image? = null,
    var name: String? = null,
)
