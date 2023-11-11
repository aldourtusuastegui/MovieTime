package com.acsoft.movietime.feature_profile.presentation.most_popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.databinding.FragmentMostPopularPersonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostPopularPersonFragment : Fragment() {

    private var _binding: FragmentMostPopularPersonBinding? = null

    private val binding get() = _binding!!

    private val mostPopularPersonViewModel: MostPopularPersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMostPopularPersonBinding.inflate(inflater, container, false)

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
                }
                is Result.Failure -> {
                }
            }
        }
    }
}