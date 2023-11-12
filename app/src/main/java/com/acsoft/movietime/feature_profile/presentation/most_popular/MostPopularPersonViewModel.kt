package com.acsoft.movietime.feature_profile.presentation.most_popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_profile.domain.entities.PopularPersonProfile
import com.acsoft.movietime.feature_profile.domain.usecase.GetMostPopularPersonUseCase
import com.acsoft.movietime.feature_profile.domain.usecase.InsertMostPopularPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPopularPersonViewModel @Inject constructor(
    private val getMostPopularPersonUseCase: GetMostPopularPersonUseCase,
    private val insertMostPopularPersonUseCase: InsertMostPopularPersonUseCase
) : ViewModel() {
    private val _mostPopularPerson = MutableLiveData<Result<PopularPersonProfile>>()
    val mostPopularPerson: LiveData<Result<PopularPersonProfile>> get() = _mostPopularPerson

    fun getMostPopularPerson() {
        viewModelScope.launch {
            try {
                _mostPopularPerson.value = Result.Loading
                _mostPopularPerson.value = getMostPopularPersonUseCase.invoke()
            } catch (e: Exception) {
                _mostPopularPerson.value = Result.Failure("An unexpected error occurred: ${e.message}")
            }
        }
    }

    fun insertMostPopularPerson(popularPersonProfile: PopularPersonProfile) {
        CoroutineScope(Dispatchers.IO).launch {
            insertMostPopularPersonUseCase.invoke(popularPersonProfile)
        }
    }
}