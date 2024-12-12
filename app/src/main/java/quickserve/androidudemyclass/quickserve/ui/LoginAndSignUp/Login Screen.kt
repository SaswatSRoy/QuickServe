package quickserve.androidudemyclass.quickserve.ui.LoginAndSignUp

import android.app.Activity
import android.content.Context
import android.widget.ScrollView
import android.widget.Space
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.shape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.androidudemyclass.quickserve.R
import quickserve.androidudemyclass.quickserve.Api.LoginRequest
import quickserve.androidudemyclass.quickserve.Api.Result
import quickserve.androidudemyclass.quickserve.Api.RetrofitClient
import quickserve.androidudemyclass.quickserve.Api.UserRepository
import quickserve.androidudemyclass.quickserve.Api.UserViewModel
import quickserve.androidudemyclass.quickserve.Api.UserViewModelFactory


@Composable
fun LoginScreen(
    onNavigateTo: (Boolean) -> Unit,
    navHostController: NavHostController = rememberNavController(),
    currentPage: Int,
    onAccount:()->Unit
) {
    val userRepository = UserRepository(RetrofitClient.instance)  // Create an instance of the repository

    // Initialize the UserViewModel using the ViewModelProvider.Factory
    val viewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(userRepository)
    )
    val success=true
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val proportionalPadding = screenWidth * 0.04f
    var email by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(false) }
    isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val isPasswordValid = password.length >= 6

    // Get SharedPreferences context
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val termsAccepted = prefs.getBoolean("termsAccepted", false)
    val isFormValid=isEmailValid && isPasswordValid


    // Show dialog if terms have not been accepted
    if (!termsAccepted && !showDialog) {
        showDialog = true
    }

    // Terms and Conditions Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Terms and Conditions", fontFamily = MaterialTheme.typography.bodyLarge.fontFamily) },
            text = {
                // ScrollView for terms text
                val scrollView = ScrollView(context)
                val termsTextView = TextView(context).apply {
                    text = "Ok"
                    setPadding(20, 20, 20, 20)
                }
                scrollView.addView(termsTextView)

                // This creates a scrollable terms text block.
                AndroidView({ scrollView })
            },
            confirmButton = {
                TextButton(onClick = {
                    // Save user acceptance in SharedPreferences
                    prefs.edit().putBoolean("termsAccepted", true).apply()
                    showDialog = false
                }) {
                    Text(text = "Accept", color = Color(0xFFFF9800))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    // Handle user declining the terms (e.g., exit app or navigate away)
                    (context as? Activity)?.finish()  // This will close the app or the current activity
                }) {
                    Text(text = "Decline", color = Color.Red)
                }
            }
        )
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = proportionalPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            // Back button and title row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = screenHeight * 0.03f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { navHostController.navigateUp() },
                    modifier = Modifier.size(screenWidth * 0.1f)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(screenWidth * 0.15f))
                Text(
                    text = "LogIn to Quickserve",
                    fontSize = 20.sp,
                    color = Color(0xFF232323),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF232323),
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                    )
                )
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.07f))

            // Email input
            Text(
                text = "Enter Your valid Email ID",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(0.94f),
                fontFamily = MaterialTheme.typography.labelLarge.fontFamily
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(120.dp),
                label = { Text(text = "Enter Email", color = Color.Gray) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp), // Space between text and icon
                trailingIcon = {
                    if (email.isNotEmpty()) {
                        val iconColor = if (isEmailValid) Color(0xFFFF9800) else Color.Gray
                        Icon(
                            imageVector = if (isEmailValid) Icons.Filled.Check else Icons.Default.Warning,
                            contentDescription = if (isEmailValid) "Valid Email" else "Invalid Email",
                            tint = iconColor
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(120.dp),
                label = { Text(text = "Enter Password", color = Color.Gray) },
                singleLine = true,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Filled.Person else Icons.Filled.Lock,
                            contentDescription = "Toggle Password Visibility",
                            tint = Color.Gray
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            // Send Code Button
            Button(
                onClick = {

                    val loginRequest=LoginRequest(email,password)
                    viewModel.login(loginRequest){result ->
                        when(result){
                            is Result.Success -> {

                                Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                                onNavigateTo(success) // Navigate to verification screen
                            }
                            is Result.Error -> {

                                Toast.makeText(context, "Login failed: ${result.exception.message}", Toast.LENGTH_SHORT).show()
                            }
                            Result.Loading -> TODO()

                        }
                    }



                },
                colors = if(isFormValid) ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)) else ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(40.dp)
                    .alpha(if (isFormValid) 1f else 0.6f)
            ) {
                Text(text = "Send Code", color = if(isFormValid) Color.White else Color.Gray, fontSize = 15.sp)
            }


            // Sign up option
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    fontSize = 15.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(3.dp))
                TextButton(
                    onClick =  onAccount,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "Create an Account",
                        fontSize = 15.sp,
                        color = Color(0xFFFF9800),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.1f))

            // Continue with Google button
            OutlinedButton(
                onClick = { /* TODO: Handle Google Sign-In */ },
                border = BorderStroke(width = 0.3.dp, color = Color.LightGray),
                shape = RoundedCornerShape(120.dp),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(40.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_logo_search_new_svgrepo_com),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(24.dp)

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Continue with Google...",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.025f))

            // Terms and Conditions
            Text(
                text = "By continuing, you agree to our",
                fontSize = 15.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,

            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = { showDialog = true },

                ) {
                    Text(
                        text = "Terms of Use & Privacy Policy",
                        fontSize = 15.sp,
                        color = Color(0xFFFF9800)
                    )
                }
            }
        }
    }
}


