package com.example.devicelistproject.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.model.DeviceInfoBox
import com.example.devicelistproject.R
import com.example.devicelistproject.databinding.ListFragmentBinding
import com.example.devicelistproject.ui.AddDeviceInfoDialogFragment.Companion.REQUEST_KEY
import com.example.devicelistproject.widget.DeviceInfoItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private var _binding : ListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        setFragmentResultListener(AddDeviceInfoDialogFragment.REQUEST_KEY){ s: String, bundle: Bundle ->
            viewModel.addDeviceInfo(
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_NAME,""),
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_MAKER,""),
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_OS,""),
                bundle.getFloat(AddDeviceInfoDialogFragment.BUNDLE_KEY_SIZE,0F),
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_DATEMODIFIED,""),
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_DATEADDED,"")
            )
        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.popup_menu,menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.itemModified -> {
                Toast.makeText(requireContext(), "編集ボタンが押されました", Toast.LENGTH_SHORT).show()
                return false
            }
            R.id.itemDelete -> {
                Toast.makeText(requireContext(), "削除ボタンが押されました", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =GroupieAdapter()
        binding.rvDeviceList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)


        viewModel.deviceInfoList.observe(viewLifecycleOwner) {list ->
            adapter.update(
                list.map { deviceInfo ->
                    DeviceInfoItem(deviceInfo,{toDetail(it)},{ toDelete(it)},{ toModified(it)})//(deviceInfo,{toDetail(it)}//onClickのやつ,{toDetail(it)}//onDeleteのやつ)↓のprivate valでtoDetailをオブジェクト化すると、(deviceInfo,navigatoDetail,navigatoDetail)にできる
                }
            )
        }

        binding.buttonAdd.setOnClickListener{
            val type = arrayOf("true")
            val addDirections = ListFragmentDirections.actionListFragmentToAddDeviceInfoDialogFragment(type)
            findNavController().navigate(addDirections)
            setFragmentResultListener(AddDeviceInfoDialogFragment.REQUEST_KEY){ s: String, bundle: Bundle ->
                viewModel.addDeviceInfo(
                    bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_NAME,""),
                    bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_MAKER,""),
                    bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_OS,""),
                    bundle.getFloat(AddDeviceInfoDialogFragment.BUNDLE_KEY_SIZE,0F),
                    bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_DATEMODIFIED,""),
                    bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_DATEADDED,"")
                )
            }
        }


        binding.rvDeviceList.adapter = adapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun toDetail(id: Int){
        val directions = ListFragmentDirections.actionListFragmentToDetailFragment(id)
        findNavController().navigate(directions)
    }



    private fun toDelete(deviceInfoBox: DeviceInfoBox){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("削除")
            .setMessage("デバイスの情報を削除しますか")
            .setNegativeButton("いいえ"){ _, _ ->

            }
            .setPositiveButton("はい"){ _, _ ->
                viewModel.deleteDeviceInfo(deviceInfoBox)
            }
            .show()


    }

    private fun toModified(id: Int){
        val type = arrayOf("false","","","","","")
        viewModel.getDeviceInfo(id).observe(viewLifecycleOwner){ deviceInfo ->
            deviceInfo?.let {
                type[1] = it.name
                type[2] = it.maker
                type[3] = it.os!!
                type[4] = it.displaySize.toString()
                type[5] = it.dateAdded
                }
            }

        val modifiedDirections = ListFragmentDirections.actionListFragmentToAddDeviceInfoDialogFragment(type)
            findNavController().navigate(modifiedDirections)

        setFragmentResultListener(AddDeviceInfoDialogFragment.REQUEST_KEY){ s: String, bundle: Bundle ->
            viewModel.modifiedDeviceInfo(
                id,
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_NAME,""),
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_MAKER,""),
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_OS,""),
                bundle.getFloat(AddDeviceInfoDialogFragment.BUNDLE_KEY_SIZE,0F),
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_DATEMODIFIED,""),
                bundle.getString(AddDeviceInfoDialogFragment.BUNDLE_KEY_DATEADDED,"")
            )
        }

    }


}