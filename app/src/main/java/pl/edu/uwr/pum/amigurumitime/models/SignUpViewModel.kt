package pl.edu.uwr.pum.amigurumitime.models

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun signUp(
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnSuccessListener { result ->
                if (result.signInMethods?.isEmpty() == false) {
                    onError("Podany email już istnieje")
                } else {

                    db.collection("users")
                        .whereEqualTo("username", username)
                        .get()
                        .addOnSuccessListener { querySnapshot ->
                            if (!querySnapshot.isEmpty) {
                                onError("Podana nazwa użytkownika jest już zajęta")
                            } else {
                                auth.createUserWithEmailAndPassword(email, password)
                                    .addOnSuccessListener { authResult ->
                                        val currentUser = authResult.user
                                        currentUser?.let { user ->

                                            val userMap = hashMapOf(
                                                "username" to username,
                                                "email" to email,
                                                "name" to "",
                                                "last_name" to "",
                                                "avatar" to ""
                                            )

                                            db.collection("users")
                                                .document(user.uid)
                                                .set(userMap)
                                                .addOnSuccessListener {
                                                    onSuccess()
                                                }
                                                .addOnFailureListener { e ->
                                                    onError("Nieudana próba rejestracji: ${e.message}")
                                                }
                                        }
                                    }
                                    .addOnFailureListener { e ->
                                        onError("Błąd rejestracji: ${e.message}")
                                    }
                            }
                        }
                        .addOnFailureListener { e ->
                            onError("Błąd podczas sprawdzania nazwy użytkownika: ${e.message}")
                        }
                }
            }
            .addOnFailureListener { e ->
                onError("Błąd podczas sprawdzania adresu email: ${e.message}")
            }
    }
}