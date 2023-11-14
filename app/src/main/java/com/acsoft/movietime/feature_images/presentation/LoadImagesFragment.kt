package com.acsoft.movietime.feature_images.presentation

import android.content.Intent
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

    private var position = 0
    private var numberOfImagesUploaded = 0

    private val loadImagesViewModel: LoadImagesViewModel by viewModels()

    private var selectedImages: MutableList<Uri> = mutableListOf()

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
            selectImages()
        }

        binding.btnUploadImage.setOnClickListener {
            it.isEnabled = false
            loadImagesViewModel.uploadImage(selectedImages)
        }

        binding.ibNext.setOnClickListener {
            showNextImage()
        }

        binding.ibBack.setOnClickListener {
            showPreviousImage()
        }

        loadImagesViewModel.uploadImage.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.isIndeterminate
                    binding.btnSelectImage.isEnabled = false
                }
                is Result.Success -> {
                    restoreDefaultValuesAfterUploadImages()
                    Toast.makeText(context, getString(R.string.image_upload_successfully, result.data.toString()),Toast.LENGTH_LONG).show()
                }
                is Result.Failure -> {
                    binding.btnSelectImage.isEnabled = true
                    binding.btnUploadImage.isEnabled = false
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, R.string.image_upload_failed,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun selectImages() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = IMAGES_GALLERY
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        getContent.launch(IMAGES_GALLERY)
        binding.btnUploadImage.isEnabled = true
    }

    private fun restoreDefaultValuesAfterUploadImages() {
        numberOfImagesUploaded++
        if (numberOfImagesUploaded==selectedImages.size) {
            binding.btnSelectImage.isEnabled = true
            binding.btnUploadImage.isEnabled = false
            binding.progressBar.visibility = View.GONE
            selectedImages.clear()
            position = 0
            numberOfImagesUploaded = 0
            binding.ivGallery.setImageResource(R.drawable.ic_upload_file_24)
        }
    }

    private fun showPreviousImage() {
        if (position > 0) {
            position--
            binding.ivGallery.setImageURI(selectedImages[position])
        }
    }

    private fun showNextImage() {
        if (position < selectedImages.size -1) {
            position++
            binding.ivGallery.setImageURI(selectedImages[position])
        }
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris: List<Uri>? ->
            uris?.let {
                binding.ivGallery.setImageURI(it.first())
                selectedImages.addAll(it)
            }
        }
}