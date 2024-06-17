package pl.edu.uwr.pum.amigurumitime.models

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                when (e) {
                    is FirebaseAuthInvalidUserException -> {
                        onError("Nieprawidłowy adres email")
                    }
                    is FirebaseAuthInvalidCredentialsException -> {
                        onError("Niepoprawne dane")
                    }
                    else -> {
                        onError("Bład logowania: ${e.message}")
                    }
                }
            }
    }
}