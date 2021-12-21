package com.example.devicelistproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.devicelistproject.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {


    private val viewModel: DetailViewModel by viewModels()
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDeviceInfo(args.deviceId).observe(viewLifecycleOwner){ deviceInfo ->
            deviceInfo?.let {
                binding.name.text = it.name
                binding.maker.text = it.maker
                binding.deviceOs.text = it.os
                binding.deviceSize.text = it.displaySize.toString()
                binding.dateModified.text = it.dateModified
                binding.dateAdded.text = it.dateAdded
            }
        }





    }

    override fun onDestroy() { // お作法
        super.onDestroy()
        _binding = null
    }

}