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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularMoviesAdapter = MovieAdapter()
        ratedMoviesAdapter = MovieAdapter()
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
                    popularMoviesAdapter.setMovieList(result.data.results ?: listOf())
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
                    ratedMoviesAdapter.setMovieList(result.data.results ?: listOf())
                }
                is Result.Failure -> {
                    //TODO
                }
            }
        }
    }
}