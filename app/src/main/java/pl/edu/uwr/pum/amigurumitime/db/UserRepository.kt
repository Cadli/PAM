package pl.edu.uwr.pum.amigurumitime.db

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class UserRepository(private val db: FirebaseFirestore) {

    suspend fun getUsers(): List<User> {

        val snapshot: QuerySnapshot = db.collection("users").get().await()
        return snapshot.documents.map { document ->
            User(
                username = document.getString("username") ?: "",
                email = document.getString("email") ?: "",
                name = document.getString("name") ?: "",
                lastName = document.getString("last_name") ?: "",
                avatar = document.getString("avatar") ?: "",

            )
        }
    }
}



