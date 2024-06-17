package pl.edu.uwr.pum.amigurumitime.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.edu.uwr.pum.amigurumitime.db.User
import pl.edu.uwr.pum.amigurumitime.models.UserScreenViewModel
import pl.edu.uwr.pum.amigurumitime.templates.CustomScaffold


@Composable
fun UserScreen( loggedUser: String) {
    val viewModel:UserScreenViewModel = viewModel()
    val users by viewModel.users.collectAsState()


    CustomScaffold(
        loggedUser = loggedUser,
        title = "Wzory",
        content = {

            LazyColumn {
                items(users) { user ->
                    if (user.email == loggedUser) UserCard(user = user)
                }
            }
        }
    )
}

@Composable
fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {  }
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Username: ${user.username}", fontWeight = FontWeight.Bold, fontSize = 26.sp)
            Text(text = "email: ${user.email}", fontSize = 18.sp)

        }
    }
}
