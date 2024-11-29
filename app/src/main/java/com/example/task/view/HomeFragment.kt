package com.example.task.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.example.assessment.model.RetrofitClient
import com.example.task.R
import com.example.task.adapter.TransactionAdapter
import com.example.task.databinding.FragmentHomeBinding
import com.example.task.model.TransactionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransactionAdapter
    private var transactions = mutableListOf<TransactionData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        Glide.with(this)
            .load(R.drawable.ic_user)
            .apply(circleCropTransform())
            .into(binding.userImg)

        recyclerView = binding.transactionRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = TransactionAdapter(transactions)
        recyclerView.adapter = adapter

        recentTransactions()

        return binding.root
    }

    private fun recentTransactions() {
        RetrofitClient.instance.getUsers(1, 20).enqueue(object : Callback<List<TransactionData>> {
            override fun onResponse(call: Call<List<TransactionData>>, response: Response<List<TransactionData>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        transactions.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<TransactionData>>, t: Throwable) {
                Toast.makeText(requireContext(), "api error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
