package com.bot.factory.helperboy.auth

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bot.factory.helperboy.R
import com.bot.factory.helperboy.databinding.LoginFragmentBinding
import kotlinx.android.synthetic.main.login_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

import org.kodein.di.android.x.kodein
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(), AuthListener, KodeinAware {

    //Kodein
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    //Binding ViewModel
    private lateinit var viewModel: AuthViewModel
    private lateinit var myView : View
    private lateinit var binding: LoginFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        myView = binding.root

        return myView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        // TODO: Use the ViewModel
        viewModel.user?.let {
            myView.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_register.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    override fun onStarted() {
        //progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        //progressbar.visibility = View.GONE
        myView.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    override fun onFailure(message: String) {
        //progressbar.visibility = View.GONE
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
