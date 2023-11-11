package com.acsoft.movietime.feature_profile.presentation.most_popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.databinding.FragmentMostPopularPersonProfileBinding
import com.acsoft.movietime.feature_profile.domain.entities.PopularPersonProfile
import com.acsoft.movietime.utils.AppConstants
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostPopularPersonProfileFragment : Fragment() {

    private var _binding: FragmentMostPopularPersonProfileBinding? = null

    private val binding get() = _binding!!

    private val mostPopularPersonViewModel: MostPopularPersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMostPopularPersonProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mostPopularPersonViewModel.getMostPopularPerson()
        mostPopularPersonViewModel.mostPopularPerson.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                }
                is Result.Success -> {
                    fillProfile(result.data)
                }
                is Result.Failure -> {
                }
            }
        }
    }

    private fun fillProfile(popularPersonProfile: PopularPersonProfile) {
        popularPersonProfile.apply {
            Glide.with(this@MostPopularPersonProfileFragment)
                .load(AppConstants.IMAGE_URL.plus(this.profilePath))
                .centerCrop()
                .into(binding.ivProfile)
            binding.tvName.text = this.name
            binding.tvPopularity.text = this.popularity.toString()
        }
    }
}