
package com.example.mynotificationapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotificationapp.adapter.ChatLogAdapter
import com.example.mynotificationapp.model.ChatLog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ChatDetailActivity : AppCompatActivity() {

    private lateinit var chatHeader: TextView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var messageAdapter: ChatLogAdapter
    private val messageList = mutableListOf<ChatLog>()

    private lateinit var userId: String
    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)

        userId = intent.getStringExtra("userId") ?: ""
        userName = intent.getStringExtra("userName") ?: "Unknown"

        if (userId.isBlank()) {
            Toast.makeText(this, "Invalid user ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        chatHeader = findViewById(R.id.chatHeader)
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)
        messageRecyclerView = findViewById(R.id.messageRecyclerView)

        chatHeader.text = "$userName"

        messageAdapter = ChatLogAdapter(messageList) { /* optional click */ }
        messageRecyclerView.layoutManager = LinearLayoutManager(this)
        messageRecyclerView.adapter = messageAdapter

        sendButton.setOnClickListener {
            val msg = messageInput.text.toString().trim()
            if (msg.isNotEmpty()) {
                val ref = FirebaseDatabase.getInstance().getReference("messages/$userId")
                val msgId = ref.push().key ?: return@setOnClickListener
                val newMsg = ChatLog(userId, userName, msg, System.currentTimeMillis())
                ref.child(msgId).setValue(newMsg)
                messageInput.setText("")
            }
        }

        // Optional message loading logic (if storing messages)
    }
}



