package pl.edu.uwr.pum.amigurumitime.db

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class PostRepository(private val db: FirebaseFirestore) {

    suspend fun getPosts(): List<Post> {

        //val postsCollectionRef = db.collection("posts").
        val snapshot: QuerySnapshot = db.collection("posts").get().await()
        return snapshot.documents.map { document ->
            Post(
                id = document.id,
                title = document.getString("title") ?: "",
                author = document.getString("author") ?: "",
                pattern = document.getString("pattern") ?: "",
                img = document.getString("img") ?: "",
                //timestamp = document.getString("timestamp") ?: ""
            )
        }
    }
}