package com.bot.factory.helperboy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bot.factory.helperboy.auth.AuthListener
import com.bot.factory.helperboy.auth.AuthViewModel
import com.bot.factory.helperboy.auth.AuthViewModelFactory
import com.bot.factory.helperboy.databinding.SignupFragmentBinding
import com.bot.factory.helperboy.home.HomeFragment
import kotlinx.android.synthetic.main.signup_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BlankFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment(), AuthListener, KodeinAware {


    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    private lateinit var viewModel: AuthViewModel
    private lateinit var myView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SignupFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)
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
        progressbar.visibility = View.VISIBLE
        Intent(context, HomeFragment::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        // Navigation.findNavController(myView).navigate(R.id.action_signupFragment_to_homeFragment)
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}