package com.example.edumanage.repository

import com.example.edumanage.model.Assignment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AssignmentRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getAssignments(): List<Assignment> {
        return try {
            val snapshot = db.collection("assignments").get().await()
            snapshot.documents.map { document ->
                document.toObject(Assignment::class.java)!!
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
