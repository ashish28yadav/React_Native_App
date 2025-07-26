package com.example.mynotificationapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotificationapp.R
import com.example.mynotificationapp.model.ChatLog

class ChatLogAdapter(
    private val chatList: List<ChatLog>,
    private val onItemClick: (ChatLog) -> Unit
) : RecyclerView.Adapter<ChatLogAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameText: TextView = itemView.findViewById(R.id.userNameText)
        val lastMessageText: TextView = itemView.findViewById(R.id.lastMessageText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_log, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.userNameText.text = chat.userName
        holder.lastMessageText.text = chat.lastMessage
        holder.itemView.setOnClickListener { onItemClick(chat) }
    }

    override fun getItemCount(): Int = chatList.size
}
