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

    private val _uploadImage = MutableLiveData<Result<String>>()
    val uploadImage: LiveData<Result<String>> get() = _uploadImage

    fun uploadImage(uri: Uri) {
        _uploadImage.value = Result.Loading
        val storageRef = FirebaseStorage.getInstance()
        storageRef.getReference(IMAGES).child(System.currentTimeMillis().toString())
            .putFile(uri)
            .addOnSuccessListener { task ->
                val imageUrl = task.metadata!!.reference!!.downloadUrl
                _uploadImage.value = Result.Success(imageUrl.toString())
            }.addOnFailureListener {
                _uploadImage.value = Result.Failure(it.message.toString())
            }
    }

}