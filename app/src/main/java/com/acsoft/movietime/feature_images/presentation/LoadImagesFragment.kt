package com.acsoft.movietime.feature_images.presentation

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.acsoft.movietime.R
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.databinding.FragmentLoadImagesBinding
import com.acsoft.movietime.feature_images.utils.Constants.IMAGES_GALLERY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadImagesFragment : Fragment() {

    private var _binding: FragmentLoadImagesBinding? = null
    private val binding get() = _binding!!

    private lateinit var uri: Uri

    private val loadImagesViewModel: LoadImagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSelectImage.setOnClickListener {
            changeImage.launch(IMAGES_GALLERY)
        }

        binding.btnUploadImage.setOnClickListener {
            loadImagesViewModel.uploadImage(uri)
        }

        loadImagesViewModel.uploadImage.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.isIndeterminate
                    setIsButtonEnable(false)
                }
                is Result.Success -> {
                    clearViewElements()
                    Toast.makeText(context, R.string.image_upload_successfully,Toast.LENGTH_LONG).show()
                }
                is Result.Failure -> {
                   clearViewElements()
                    Toast.makeText(context, R.string.image_upload_failed,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {
            it?.let {
                binding.ivGallery.setImageURI(it)
                uri = it
            }
        }

    private fun clearViewElements() {
        binding.progressBar.visibility = View.GONE
        setIsButtonEnable(true)
    }

    private fun setIsButtonEnable(isEnabled: Boolean = false) {
        binding.btnSelectImage.isEnabled = isEnabled
        binding.btnUploadImage.isEnabled = isEnabled
    }
}