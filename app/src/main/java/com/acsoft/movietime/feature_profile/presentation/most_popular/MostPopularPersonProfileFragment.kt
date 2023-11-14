package com.acsoft.movietime.feature_profile.presentation.most_popular

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.acsoft.movietime.R
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

    private lateinit var knownForAdapter: KnownForAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        knownForAdapter = KnownForAdapter()
    }

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
                    Log.d(TAG,getString(R.string.loading))
                }
                is Result.Success -> {
                    fillProfile(result.data)
                    mostPopularPersonViewModel.insertMostPopularPerson(result.data)
                }
                is Result.Failure -> {
                    Log.d(TAG,getString(R.string.unexpected_error_occurred))
                }
            }
        }
    }

    private fun fillProfile(popularPersonProfile: PopularPersonProfile) {

        binding.rvKnownFor.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvKnownFor.adapter = knownForAdapter

        popularPersonProfile.apply {
            Glide.with(this@MostPopularPersonProfileFragment)
                .load(AppConstants.IMAGE_URL.plus(this.profilePath))
                .centerCrop()
                .into(binding.ivProfile)
            binding.tvName.text = this.name
            binding.tvPopularity.text = getString(R.string.popularity_points,this.popularity.toString())
            binding.tvKnownForDepartment.text = this.knownForDepartment

            val knownForList = popularPersonProfile.knownFor
            knownForList?.let {
                knownForAdapter.setKnownForList(it)
            }
        }
    }

    companion object {
        const val TAG = "PROFILE_FRAGMENT"
    }
}