package com.acsoft.movietime.feature_movies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var ratedMoviesAdapter: MovieAdapter
    private lateinit var recommendationsAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularMoviesAdapter = MovieAdapter()
        ratedMoviesAdapter = MovieAdapter()
        recommendationsAdapter = MovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillPopularMovies()
        fillRatedMovies()
        fillRecommendationsMovies()
    }

    private fun fillPopularMovies() {
        moviesViewModel.getPopularMovies()
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularMovies.adapter = popularMoviesAdapter

        moviesViewModel.popularMoviesList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                    //TODO
                }
                is Result.Success -> {
                    val popularMoviesList = result.data.results
                    popularMoviesList?.let {
                        popularMoviesAdapter.setMovieList(it)
                        moviesViewModel.insertPopularMoviesDb(it)
                    }
                }
                is Result.Failure -> {
                    //TODO
                }
            }
        }
    }

    private fun fillRatedMovies() {
        moviesViewModel.getRatedMovies()
        binding.rvRatedMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRatedMovies.adapter = ratedMoviesAdapter

        moviesViewModel.ratedMoviesList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                    //TODO
                }
                is Result.Success -> {
                    val ratedMoviesList = result.data.results
                    ratedMoviesList?.let {
                        ratedMoviesAdapter.setMovieList(it)
                        moviesViewModel.insertRatedMoviesDb(it)
                    }
                }
                is Result.Failure -> {
                    //TODO
                }
            }
        }
    }

    private fun fillRecommendationsMovies() {
        moviesViewModel.getRecommendationsMovies()
        binding.rvRecommendationsMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendationsMovies.adapter = recommendationsAdapter

        moviesViewModel.recommendationsMoviesList.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                    //TODO
                }
                is Result.Success -> {
                    val recommendationsMoviesList = result.data.results
                    recommendationsMoviesList?.let {
                        recommendationsAdapter.setMovieList(it)
                        moviesViewModel.insertRecommendationsMoviesDb(it)
                    }
                }
                is Result.Failure -> {
                    //TODO
                }
            }
        }
    }
}