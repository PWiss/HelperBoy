package com.bot.factory.helperboy.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import com.bot.factory.helperboy.R
import com.bot.factory.helperboy.databinding.FragmentSignUpBinding
import kotlinx.android.synthetic.main.fragment_sign_up.*

import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SignUpFragment : Fragment(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    private lateinit var viewModel: AuthViewModel
    private lateinit var myView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSignUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        myView = binding.root
        binding.viewmodel = viewModel

        viewModel.authListener = this
        return myView
    }

    override fun onStarted() {

    }

    override fun onSuccess() {
        myView.findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
    }

    override fun onFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_register.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
}
