package com.bot.factory.helperboy.createProject

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bot.factory.helperboy.R
import com.bot.factory.helperboy.auth.AuthViewModel
import com.bot.factory.helperboy.auth.AuthViewModelFactory
import com.bot.factory.helperboy.databinding.FragmentCreateProjectBinding
import com.bot.factory.helperboy.databinding.FragmentSignUpBinding
import com.bot.factory.helperboy.repository.ProjectListener
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class CreateProjectFragment : Fragment(), ProjectListener, KodeinAware {

    override val kodein by kodein()
    private val factory : CreateProjectViewModelFactory by instance()

    private lateinit var viewModel: CreateProjectViewModel
    private lateinit var myView : View

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCreateProjectBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_project, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(CreateProjectViewModel::class.java)
        myView = binding.root
        binding.viewmodel = viewModel

        viewModel.projectListener = this
        return myView
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()    }
}
