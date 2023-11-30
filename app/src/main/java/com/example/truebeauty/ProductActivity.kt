package com.example.truebeauty

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.truebeauty.databinding.ActivityProductBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity(), ProductsAdapter.OnItemClicked {

    // Declaración de variables y objetos
    lateinit var binding: ActivityProductBinding
    lateinit var adapter: ProductsAdapter
    var products = arrayListOf<Products>()
    var product = Products(-1,"","","","","","","", "")
    var isEditando =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración del RecyclerView
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        setupRecyclerView()
        obtenerProducts()

        // Manejo del evento clic en el botón
        binding.btnAddUpdate.setOnClickListener {
            var isValido = validarCampos()
            if (isValido){
                if(!isEditando){
                    agregarProducto()
                }
                else{
                    actualizarProducto()
                }
            }
        }

    }
    fun validarCampos(): Boolean{
        return (binding.etNombre.text.isNullOrEmpty() || binding.etPrecio.text.isNullOrEmpty())

    }


    fun obtenerProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiGetContent = RetrofitClient.webService.getProduct()


            apiGetContent.enqueue(object : Callback<List<Products>> {
                override fun onResponse(
                    call: Call<List<Products>>,
                    response: Response<List<Products>>
                ) {
                    if (response.isSuccessful) {
                        val contentResponseList = response.body()
                        contentResponseList?.let {
                            // Log the product IDs
                            for (product in it) {
                                Log.d("ProductID", "${product.id}")
                                Log.d("ProductName", "${product.name}")
                            }

                            // Actualizar la UI con los productos obtenidos.
                            runOnUiThread {
                                setupRecyclerView(it)
                            }
                        }
                    } else {
                        // Handle unsuccessful response here
                        Log.e("Error obtenerProducts", "Code: ${response.code()}, Message: ${response.message()}")
                        runOnUiThread {
                            Toast.makeText(this@ProductActivity, "Error al obtener productos", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                    Log.e("Error obtenerProducts", t.toString())
                    runOnUiThread {
                        Toast.makeText(this@ProductActivity, "Error al obtener productos", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    // Assuming you have a method to update your RecyclerView with the obtained products
    fun setupRecyclerView(products: List<Products>) {
        adapter = ProductsAdapter(this, products)
        adapter.setOnClick(this@ProductActivity)
        binding.rvProducts.adapter = adapter
    }



    fun agregarProducto(){
        this.product.sku = binding.etsku.text.toString()
        this.product.name = binding.etNombre.text.toString()
        this.product.description = binding.etdescription.text.toString()
        this.product.price = binding.etPrecio.text.toString()
        this.product.quantity = binding.etquantity.text.toString()
        this.product.status = binding.etstatus.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.agregarProducts(product)
            runOnUiThread {
                if (call.isSuccessful){
                    Toast.makeText(this@ProductActivity,call.body().toString(), Toast.LENGTH_SHORT).show()
                    obtenerProducts()
                    limpiarCampos()
                    limpiarObjeto()
                }
                else{
                    Toast.makeText(this@ProductActivity,call.body().toString(), Toast.LENGTH_SHORT).show()

                }
            }
        }

    }

    fun actualizarProducto() {
        val productIdToUpdate = product.id.toString()
        val updatedProductRequest = TraerProduct(
            binding.etsku.text.toString(),
            binding.etNombre.text.toString(),
            binding.etdescription.text.toString(),
            binding.etPrecio.text.toString(),
            binding.etquantity.text.toString(),
            binding.etstatus.text.toString()
        )

        val call = RetrofitClient.webService.updateProduct(updatedProductRequest, productIdToUpdate)
        call.enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) {
                    // Manejar la actualización exitosa aquí
                    val updatedProduct = response.body()
                    Toast.makeText(
                        this@ProductActivity,
                        "Producto actualizado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    obtenerProducts()
                    limpiarCampos() // Limpiar campos después de una actualización exitosa
                    limpiarObjeto()

                    binding.btnAddUpdate.setText("Agregar Producto")
                    binding.btnAddUpdate.backgroundTintList =
                        resources.getColorStateList(R.color.color11)
                    isEditando = false

                    // Agregar registros de éxito
                    Log.d("ActualizarProducto", "Actualización exitosa: $updatedProduct")
                } else {
                    // Manejar la respuesta fallida aquí
                    val errorMessage = "Error al actualizar el producto: ${response.code()}"
                    Toast.makeText(this@ProductActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("Error al actualizar", errorMessage)
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                // Manejar la falla de la solicitud aquí
                val errorMessage = "Error al actualizar el producto: ${t.message}"
                Toast.makeText(this@ProductActivity, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("Error content", errorMessage)
            }
        })
    }








    fun limpiarCampos(){
        binding.etNombre.setText("")
        binding.etPrecio.setText("")

    }
    fun limpiarObjeto(){
        this.product.id = -1
        this.product.sku = ""
        this.product.name = ""
        this.product.description = ""
        this.product.price = ""
        this.product.quantity = ""
        this.product.status = ""

    }

    fun setupRecyclerView() {
        adapter = ProductsAdapter(this, products)
        adapter.setOnClick(this@ProductActivity)
        binding.rvProducts.adapter = adapter

    }



    override fun editarProduct(products: Products) {
        binding.etsku.setText(products.sku)
        binding.etNombre.setText(products.name)
        binding.etdescription.setText(products.description)
        binding.etPrecio.setText(products.price)
        binding.etquantity.setText(products.quantity)
        binding.etstatus.setText(products.status)
        binding.btnAddUpdate.setText("Actualizar Producto")
        binding.btnAddUpdate.backgroundTintList = resources.getColorStateList(R.color.purple_500)

        // Asignar el producto seleccionado al objeto product
        this.product = products
        isEditando = true
    }
    override fun borrarProduct(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.borrarProducts(id)
            runOnUiThread {
                if (call.isSuccessful){
                    Toast.makeText(this@ProductActivity,call.body().toString(), Toast.LENGTH_SHORT).show()
                    obtenerProducts()

                }
            }
        }
    }
}