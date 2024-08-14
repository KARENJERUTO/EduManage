package com.example.edumanage

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.edumanage.databinding.ActivityMainBinding
import com.example.edumanage.model.Admin
import com.example.edumanage.model.Student
import com.example.edumanage.model.Teacher
import com.example.edumanage.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.jvm.internal.CallableReference

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var auth: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val admin = User().apply {
            id = "1"
            email = "karenjeruto6@gmail.com"
            password = "123456"
            usertype = "Admin"
        }
        val teacher = User().apply {
            id = "2"
            email = ""
            password = ""
            usertype = ""
        }
        val student = User().apply {
            id = "3"
            email = ""
            password = ""
            usertype = ""
        }
        auth = FirebaseDatabase.getInstance()
        reference = auth.reference.child("students")

        student.id?.let {
            reference.child(it).setValue(student).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "Failed to add student: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        admin.id?.let {
            reference.child(it).setValue(admin).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Admin added successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "Failed to add Admin: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        teacher.id?.let {
            reference.child(it).setValue(teacher).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Teacher added successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "Failed to add Teacher: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }


}