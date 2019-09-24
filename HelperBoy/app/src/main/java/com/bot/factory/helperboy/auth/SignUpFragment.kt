package com.bot.factory.helperboy.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.bot.factory.helperboy.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.signup_fragment.*
import kotlinx.android.synthetic.main.signup_fragment.progressbar
import kotlinx.android.synthetic.main.signup_fragment.text_view_register
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SignUpFragment : Fragment(), AuthListener, KodeinAware {

    companion object {
        fun newInstance() = SignUpFragment()
    }

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }
    override fun onStarted() {
        /*//progressbar.visibility = View.VISIBLE
        Intent(context, HomeFragment::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }*/
    }

    override fun onSuccess() {
        //progressbar.visibility = View.GONE
        myView.findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
    }

    override fun onFailure(message: String) {
        //progressbar.visibility = View.GONE
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_register.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment)
        }


    }
}
