package com.example.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.example.task.R
import com.example.task.model.TransactionData

class TransactionAdapter(private val transactions: List<TransactionData>) :
    RecyclerView.Adapter<TransactionAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userImg: ImageView = view.findViewById(R.id.userImg)
        val userName: TextView = view.findViewById(R.id.userName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.userName.text = transaction.login

        Glide.with(holder.itemView.context)
            .load(transaction.avatar_url)
            .apply(circleCropTransform())
            .into(holder.userImg)
    }

    override fun getItemCount(): Int = transactions.size
}