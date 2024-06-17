package pl.edu.uwr.pum.amigurumitime.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import pl.edu.uwr.pum.amigurumitime.db.Post
import pl.edu.uwr.pum.amigurumitime.models.PostListViewModel
import pl.edu.uwr.pum.amigurumitime.templates.CustomScaffold


@Composable
fun PostListScreen(
    loggedUser: String,
    onPostClick: (Post) -> Unit
) {
    val viewModel: PostListViewModel = viewModel()
    val posts by viewModel.posts.collectAsState()


    CustomScaffold(
        loggedUser = loggedUser,
        title = "Wzory",
        content = {

            LazyColumn {
                items(posts) { post ->
                    PostCard(post = post, onClick = { onPostClick(post) })
                }
            }
        }
    )
}

@Composable
fun PostCard(post: Post, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = post.img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.title, fontWeight = FontWeight.Bold, fontSize = 26.sp)
            Text(text = "Autor: ${post.author}", fontSize = 18.sp)
        }
    }
}
