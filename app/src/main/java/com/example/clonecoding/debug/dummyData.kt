package com.example.clonecoding.debug

import com.example.clonecoding.models.CommonPostDTO
import com.example.clonecoding.models.UserDTO
import java.sql.Timestamp

class DummyData {
    companion object {
        val postList = mutableListOf(
            CommonPostDTO(
                0,
                1,
                "test title",
                null,
                null,
                Timestamp(System.currentTimeMillis())),
            CommonPostDTO(
                1,
                0,
                "test title",
                null,
                null,
                Timestamp(System.currentTimeMillis())),
            CommonPostDTO(
                2,
                0,
                "test title",
                null,
                null,
                Timestamp(System.currentTimeMillis())),
            CommonPostDTO(
                3,
                1,
                "test title",
                null,
                null,
                Timestamp(System.currentTimeMillis())))
        val userList = mutableListOf(
            UserDTO(0, null, "Jone"),
            UserDTO(1, null, "Jarry"),
            UserDTO(1, null, "Henry"),
            UserDTO(1, null, "Tom"),
            UserDTO(1, null, "Brad Garrett"),
            UserDTO(1, null, "John Gallagher, Jr."),
            UserDTO(1, null, "Bob Gunton"),
            UserDTO(1, null, "Amédée Théodore Gaston Lerond")
        )

    }
}