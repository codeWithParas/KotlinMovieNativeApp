package com.xyz.iitpatna

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xyz.iitpatna.ui.theme.IitpatnaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Main UI Thread
        enableEdgeToEdge()
        setContent {
            IitpatnaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login_page") {
                    composable("login_page") {
                        LoginScreen(navController, modifier = Modifier)
                    }
                    composable("home_page") {
                        HomePage()
                    }
                }


            }
        }
    }
}

@Composable
fun LoginScreen(navcontroller: NavHostController, modifier: Modifier) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login Screen")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = username, onValueChange = { x ->
            username = x
        }, label = { Text(text = "Username") })
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = password, onValueChange = { p ->
            password = p
        }, label = { Text(text = "Password") })
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(
            onClick = {
            // UI Thread

            Toast.makeText(
                navcontroller.context,
                "Login Successfull",
                Toast.LENGTH_LONG
            ).show()

            // API HIT
            fetchMoviesData(navcontroller)

        }) {
            Text(text = "Login Button")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    IitpatnaTheme {
        //LoginScreen(context = null, modifier = Modifier)
    }
}