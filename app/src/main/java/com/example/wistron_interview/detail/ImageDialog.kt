//package com.example.wistron_interview.detail
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.appcompat.app.AppCompatDialogFragment
//import androidx.fragment.app.DialogFragment
//import com.example.wistron_interview.R
//import com.example.wistron_interview.databinding.DialogImageBinding
//
//class ImageDialog : AppCompatDialogFragment() {
//
//    var imageInfo: String? = null
//    private val imageInfoArg by lazy {
//        ImageDialogArgs.fromBundle(requireArguments()).imageInfo
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MessageDialog)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//
//        val binding = DialogImageBinding.inflate(inflater, container, false)
//        binding.lifecycleOwner = viewLifecycleOwner
//        binding.dialog = this
//
//        imageInfo = imageInfoArg
//
//        return binding.root
//    }
//
//    interface DialogImage {
//        var imageInfo: String
//    }
//
//    class ImageInfo : DialogImage {
//        private var _imageInfo = ""
//        override var imageInfo: String
//            get() = _imageInfo
//            set(value) {
//                _imageInfo = value
//            }
//    }
//
//}