package com.bot.factory.helperboy.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.bot.factory.helperboy.R
import com.bot.factory.helperboy.databinding.HomeFragmentBinding
import kotlinx.android.synthetic.main.home_fragment.*

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

import org.kodein.di.android.x.kodein

class HomeFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory : HomeViewModelFactory by instance()

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: HomeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        val myView : View = binding.root
        binding.viewmodel = viewModel

        return myView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logout.setOnClickListener {
            viewModel.logout()
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        }


    }

}
