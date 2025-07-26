package com.example.mynotificationapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotificationapp.adapter.ChatLogAdapter
import com.example.mynotificationapp.model.ChatLog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Chat1Fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var chatAdapter: ChatLogAdapter
    private val chatList = mutableListOf<ChatLog>()
    private lateinit var chatHeader: TextView  // Add this

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat1, container, false)

        chatHeader = view.findViewById(R.id.chatHeader) // Reference the header
        recyclerView = view.findViewById(R.id.chatRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        chatAdapter = ChatLogAdapter(chatList) { chat ->
            if (!chat.userId.isNullOrBlank()) {
                val intent = Intent(requireContext(), ChatDetailActivity::class.java)
                intent.putExtra("userId", chat.userId)
                intent.putExtra("userName", chat.userName)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "User info missing", Toast.LENGTH_SHORT).show()
            }
        }



        recyclerView.adapter = chatAdapter

        fetchChatLogs()
        return view
    }

    private fun fetchChatLogs() {
        val dbRef = FirebaseDatabase.getInstance().getReference("chat_logs")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                chatList.clear()
                for (child in snapshot.children) {
                    val chat = child.getValue(ChatLog::class.java)
                    if (chat != null) {
                        chatList.add(chat)
                    }
                }

                if (chatList.isNotEmpty()) {
                    chatHeader.text = "Available Users (${chatList.size})"
                } else {
                    chatHeader.text = "No users found"
                }

                chatAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to load chat logs", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
