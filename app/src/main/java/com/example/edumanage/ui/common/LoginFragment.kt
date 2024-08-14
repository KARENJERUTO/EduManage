package com.example.edumanage.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.edumanage.R
import com.example.edumanage.databinding.FragmentLoginBinding
import com.example.edumanage.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginFragment : Fragment() {

    private lateinit var bind: FragmentLoginBinding
    private lateinit var auth: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentLoginBinding.inflate(inflater, container, false)
        auth = FirebaseDatabase.getInstance()
        reference = auth.reference.child("students")

        bind.buttonLogin.setOnClickListener {
            val email = bind.email.text?.trim().toString()
            val password = bind.password.text?.trim().toString()
            val navCont = it.findNavController()

            // Validate input
            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check user credentials
            reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var loginSuccessful = false
                    if (snapshot.exists()) {
                        for (data in snapshot.children) {
                            val user = data.getValue(User::class.java)
                            if (user != null && user.email == email && user.password == password) {
                                Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
                                loginSuccessful = true
                                // Navigate to the main fragment
                                navCont.navigate(R.id.action_loginFragment_to_mainFragment)
                                break
                            }
                        }
                    }

                    if (!loginSuccessful) {
                        Toast.makeText(requireContext(), "Incorrect credentials!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        // Set up onClickListener for Register button
        bind.buttonRegister.setOnClickListener {
            val navCont = it.findNavController()
            navCont.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return bind.root
    }
}
