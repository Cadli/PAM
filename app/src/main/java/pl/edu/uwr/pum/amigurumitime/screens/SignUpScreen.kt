package pl.edu.uwr.pum.amigurumitime.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SignUpScreen(
    onSignUpClick: (String, String, String) -> Unit,
    onLoginClick: () -> Unit,
    errorMessage: String? = null
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var password1 by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Rejestracja:", fontWeight = FontWeight.Bold, fontSize = 35.sp,)

        errorMessage?.let { message ->
            Spacer(modifier = Modifier.height(17.dp))
            Text(text = message, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nazwa użytkownika") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = password1,
            onValueChange = { password1 = it },
            label = { Text("Podaj hasło") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = password2,
            onValueChange = { password2 = it },
            label = { Text("Powtórz hasło") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        if(password2 == password1) password = password1
        else{
            Text(text = "Hasła się różnią!", fontSize = 20.sp, color = Color.Red)
            password.isBlank()
        }


        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                keyboardController?.hide()
                if(email.isNotBlank() and password.isNotBlank() and username.isNotBlank()) {
                    onSignUpClick(username, email, password)
                } },
            modifier = Modifier.fillMaxWidth(),
            enabled = email.isNotBlank() and password.isNotBlank() and username.isNotBlank()
        ) {
            Text(text = "Zarejestruj się", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        }
        Spacer(modifier = Modifier.height(25.dp))

        Row() {
            Text(text = "Masz już konto? ", fontSize = 20.sp)
            Text(
                text = "Zaloguj się",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .clickable { onLoginClick() }
            )
        }

    }
}


