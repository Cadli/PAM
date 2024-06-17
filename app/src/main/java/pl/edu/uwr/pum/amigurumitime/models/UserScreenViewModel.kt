package pl.edu.uwr.pum.amigurumitime.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.edu.uwr.pum.amigurumitime.db.Post
import pl.edu.uwr.pum.amigurumitime.db.User

import pl.edu.uwr.pum.amigurumitime.db.UserRepository

class UserScreenViewModel : ViewModel() {

    private val repository = UserRepository(FirebaseFirestore.getInstance())
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val usersList = repository.getUsers()
                _users.value = usersList
            } catch (e: Exception) { }
        }
    }
}




