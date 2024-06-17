package pl.edu.uwr.pum.amigurumitime.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.edu.uwr.pum.amigurumitime.db.Post
import pl.edu.uwr.pum.amigurumitime.db.PostRepository


class PostListViewModel : ViewModel() {

    private val repository = PostRepository(FirebaseFirestore.getInstance())
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val postsList = repository.getPosts()
                _posts.value = postsList
            } catch (e: Exception) { }
        }
    }
}