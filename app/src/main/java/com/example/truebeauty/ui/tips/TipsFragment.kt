package com.example.truebeauty.ui.tips

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.truebeauty.ApiService.ApiService
import com.example.truebeauty.databinding.FragmentTipsBinding
import com.example.truebeauty.enviar.Tip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TipsFragment : Fragment() {

    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTipsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
