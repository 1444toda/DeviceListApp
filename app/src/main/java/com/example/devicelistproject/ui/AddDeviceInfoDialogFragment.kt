package com.example.devicelistproject.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.example.devicelistproject.databinding.AddDeviceInfoFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class AddDeviceInfoDialogFragment :DialogFragment() {

    companion object {
        const val REQUEST_KEY = "AddDeviceInfoDialogFragmentKey"
        const val BUNDLE_KEY_NAME = "BundleKeyName"
        const val BUNDLE_KEY_MAKER = "BundleKeyMaker"
        const val BUNDLE_KEY_OS = "BundleKeyOs"
        const val BUNDLE_KEY_SIZE = "BundleKeySize"
        const val BUNDLE_KEY_DATEMODIFIED = "BundleKeyDateModified"
        const val BUNDLE_KEY_DATEADDED = "BundleKeyDateAdded"
    }

    private var _binding: AddDeviceInfoFragmentBinding? = null
    private val binding get() = _binding!!
    private val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss")
    private val args: AddDeviceInfoDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        _binding = AddDeviceInfoFragmentBinding.inflate(requireActivity().layoutInflater)

        if (args.type[0] == "true") {
            // 追加
            val mDialog = MaterialAlertDialogBuilder(requireContext()).setView(binding.root)
                .setTitle("デバイスの追加")
                .setPositiveButton("追加"){ _, _ ->
                    setFragmentResult(REQUEST_KEY,
                        bundleOf(
                            BUNDLE_KEY_NAME to binding.editTextName.text.toString(),
                            BUNDLE_KEY_MAKER to binding.editTextMaker.text.toString(),
                            BUNDLE_KEY_OS to binding.editTextOs.text.toString(),
                            BUNDLE_KEY_SIZE to binding.editTextSize.text.toString(),
                            BUNDLE_KEY_DATEMODIFIED to ZonedDateTime.now().format(formatter),
                            BUNDLE_KEY_DATEADDED to ZonedDateTime.now().format(formatter)
                        ))
                }
                .setNegativeButton("キャンセル"){ _, _ ->
                    dialog?.cancel()
                }
                .create()
            return mDialog
        }
        else {
            binding.editTextName.setText(args.type[1], TextView.BufferType.NORMAL)
            binding.editTextMaker.setText(args.type[2], TextView.BufferType.NORMAL)
            binding.editTextOs.setText(args.type[3], TextView.BufferType.NORMAL)
            binding.editTextSize.setText(args.type[4], TextView.BufferType.NORMAL)
            val mDialog = MaterialAlertDialogBuilder(requireContext()).setView(binding.root)
                .setTitle("デバイス情報の編集")
                .setPositiveButton("保存"){ _, _ ->
                    //ラムダ{}の使わない引数は_
                    setFragmentResult(REQUEST_KEY,
                        bundleOf(
                            BUNDLE_KEY_NAME to binding.editTextName.text.toString(),
                            BUNDLE_KEY_MAKER to binding.editTextMaker.text.toString(),
                            BUNDLE_KEY_OS to binding.editTextOs.text.toString(),
                            BUNDLE_KEY_SIZE to binding.editTextSize.text.toString(),
                            BUNDLE_KEY_DATEMODIFIED to ZonedDateTime.now().format(formatter),
                            BUNDLE_KEY_DATEADDED to args.type[5]
                        ))

                }
                .setNegativeButton("キャンセル"){ _, _ ->
                    dialog?.cancel()
                }
                .create()
            return mDialog
        }


    }



}