package com.example.mynotificationapp.model

data class ChatLog(
    val userId: String = "",
    val userName: String = "",
    val lastMessage: String = "",
    val timestamp: Long = 0L
)
