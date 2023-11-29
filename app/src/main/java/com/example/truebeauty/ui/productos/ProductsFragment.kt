package com.example.truebeauty.ui.productos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.truebeauty.R
import com.example.truebeauty.databinding.FragmentProductsBinding

class ProductsFragment: Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private var _binding: FragmentProductsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this)[ProductsViewModel::class.java]

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProduct
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val homeViewModel = ViewModelProvider(this)[ProductsViewModel::class.java]

        homeViewModel.contentData.observe(viewLifecycleOwner) { newData ->
            adapter = ProductAdapter(newData)
            recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}