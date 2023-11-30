package com.example.truebeauty.ui.productos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.truebeauty.ProductActivity
import com.example.truebeauty.R
import com.example.truebeauty.databinding.FragmentProductsBinding

// Fragmento para mostrar una lista de productos
class ProductsFragment : Fragment() {

    // Declaraciones de variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout del fragmento utilizando el databinding
        val homeViewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProduct
        // Observar el cambio en el texto del ViewModel y actualizar la vista
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root // Devolver la vista raíz del fragmento
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuración del RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val homeViewModel = ViewModelProvider(this)[ProductsViewModel::class.java]

        // Observar el cambio en los datos del ViewModel y actualizar el adaptador del RecyclerView
        homeViewModel.contentData.observe(viewLifecycleOwner) { newData ->
            adapter = ProductAdapter(newData)
            recyclerView.adapter = adapter
        }

        // Manejar clic en el botón Subir para dirigirse a ProductActivity
        binding.Subir.setOnClickListener {
            val intent = Intent(activity, ProductActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Liberar la referencia al binding al destruir la vista del fragmento
    }
}
