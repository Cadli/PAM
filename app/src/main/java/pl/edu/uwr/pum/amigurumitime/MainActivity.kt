package pl.edu.uwr.pum.amigurumitime


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.edu.uwr.pum.amigurumitime.models.LoginViewModel
import pl.edu.uwr.pum.amigurumitime.models.SignUpViewModel
import pl.edu.uwr.pum.amigurumitime.screens.LoginScreen
import pl.edu.uwr.pum.amigurumitime.screens.PostListScreen
import pl.edu.uwr.pum.amigurumitime.screens.SignUpScreen
import pl.edu.uwr.pum.amigurumitime.ui.theme.AmigurumiTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmigurumiTimeTheme {

                var isLogged by remember { mutableStateOf(false) }
                var isSingUp by remember { mutableStateOf(false) }

                var errorMessage by remember { mutableStateOf<String?>(null) }
                var loggedUser by remember { mutableStateOf<String?>(null) }

                val loginViewModel: LoginViewModel = viewModel()
                val signUpViewModel: SignUpViewModel = viewModel()


                if (isLogged) {
                    PostListScreen(
                        loggedUser = loggedUser.toString(),
                        onPostClick = { post ->

                    })
                } else if(!isLogged and !isSingUp) {
                    LoginScreen(
                        onLoginClick = { email, password ->
                            errorMessage = null
                            loginViewModel.login(email, password,
                                onSuccess = {
                                    isLogged = true
                                    loggedUser = email },
                                onError = { errorMessage = it }
                            ) },
                        onSignUpClick = { isSingUp = true },
                        errorMessage = errorMessage
                    )
                } else if (isSingUp) {

                    SignUpScreen(
                        onSignUpClick = { username, email, password->
                            signUpViewModel.signUp(username, email, password,
                                onSuccess = {
                                    isLogged = true },
                                onError = { errorMessage = it }
                            )
                        },
                        onLoginClick = { isSingUp = false },
                        errorMessage = errorMessage
                    )
                }


            }
        }
    }
}






