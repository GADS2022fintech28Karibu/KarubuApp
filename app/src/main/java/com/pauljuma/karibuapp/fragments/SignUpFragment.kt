package com.pauljuma.karibuapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.pauljuma.karibuapp.HomeActivity
import com.pauljuma.karibuapp.R
import com.pauljuma.karibuapp.databinding.FragmentSignUpBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        auth = FirebaseAuth.getInstance()
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        return binding.root
    }

    private fun registerUser() {
        val email = binding.tfEmail.text.toString()
        val password = binding.tfPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    try {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    requireContext(),
                                    "Registration Successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    "The email already exists",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnCanceledListener {
                                Toast.makeText(
                                    requireContext(),
                                    "An error occurred please try again later",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .await()
                        checkLoginStatus()
                    } catch (e: Exception) {
                        Toast.makeText(
                            requireContext(),
                            "The email already exists",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun checkLoginStatus() {
        if (auth.currentUser == null) {
            Toast.makeText(
                requireContext(),
                "You have entered the wrong details",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            requireContext().startActivity(intent)
        }
    }
}