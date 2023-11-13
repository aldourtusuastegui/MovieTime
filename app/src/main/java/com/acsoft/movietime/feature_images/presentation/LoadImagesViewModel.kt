package com.acsoft.movietime.feature_images.presentation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_images.utils.Constants.IMAGES
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoadImagesViewModel @Inject constructor(): ViewModel() {

    private val _uploadImage = MutableLiveData<Result<Int>>()
    val uploadImage: LiveData<Result<Int>> get() = _uploadImage

    fun uploadImage(selectedImages: List<Uri>) {
        _uploadImage.value = Result.Loading
        val storageRef = FirebaseStorage.getInstance()
        selectedImages.forEachIndexed { index, uri ->
            val fireReference = storageRef.getReference(IMAGES).child(System.currentTimeMillis().toString())
            fireReference.putFile(uri)
                .addOnSuccessListener { _ ->
                    _uploadImage.value = Result.Success(index+1)
                }.addOnFailureListener {
                    _uploadImage.value = Result.Failure(it.message.toString())
                }
        }
    }

}