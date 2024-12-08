package quickserve.androidudemyclass.quickserve.ui.LoginAndSignUp


import android.app.Activity
import android.content.Context
import android.widget.ScrollView
import android.widget.TextView
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.androidudemyclass.quickserve.R
import quickserve.androidudemyclass.quickserve.screens.ScreensForTheApp


@Composable
fun SignUp(
    onNavigateTo: () -> Unit,
    navHostController: NavHostController = rememberNavController(),
    currentPage: Int
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val proportionalPadding = screenWidth * 0.04f
    var email by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(false) }
    isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    var name by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val isPasswordValid = password.length >= 6
    val isNameValid = name.isNotEmpty() // simple check for name (non-empty)
    val isContactNumberValid = contactNumber.length == 10 && contactNumber.all { it.isDigit() }
    val isFormValid = isEmailValid && isNameValid && isContactNumberValid && isPasswordValid
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val termsAccepted = prefs.getBoolean("termsAccepted", false)

    if (!termsAccepted && !showDialog) {
        showDialog = true
    }

    // Terms and Conditions Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Terms and Conditions") },
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
                Spacer(modifier = Modifier.width(screenWidth * 0.2f))
                Text(
                    text = "Create Account",
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF232323),
                    )
                )
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.05f))


            Text(
                text = "Lets Goo!! You are just few steps away Fill the details & continue",
                fontSize = 13.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(1f)
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                shape = RoundedCornerShape(120.dp),
                label = { Text(text = "Enter Name", color = Color.Gray) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp), // Space between text and icon
                trailingIcon = {
                    if (name.isNotEmpty()) {
                        val iconColor = if (isNameValid) Color(0xFFFF9800) else Color.Gray
                        Icon(
                            imageVector = if (isNameValid) Icons.Filled.Check else Icons.Default.Warning,
                            contentDescription = if (isNameValid) "Valid Name" else "Invalid Name",
                            tint = iconColor
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(120.dp),
                label = { Text(text = "Enter email", color = Color.Gray) },
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
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
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
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))
            OutlinedTextField(
                value = contactNumber,
                onValueChange = { contactNumber = it },
                shape = RoundedCornerShape(120.dp),
                label = { Text(text = "Enter Contact Number", color = Color.Gray) },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp), // Space between text and icon
                trailingIcon = {
                    if (contactNumber.isNotEmpty()) {
                        val iconColor = if (isContactNumberValid) Color(0xFFFF9800) else Color.Gray
                        Icon(
                            imageVector = if (isContactNumberValid) Icons.Filled.Check else Icons.Default.Warning,
                            contentDescription = if (isContactNumberValid) "Valid Number" else "Invalid Number",
                            tint = iconColor
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.06f))

            // Send Code Button
            Button(
                onClick = onNavigateTo,
                colors = ButtonDefaults.buttonColors( containerColor = if (isFormValid) Color(0xFFFF9800) else Color(0xFFEEEEEE)),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(40.dp)
            ) {
                Text(text = "Continue", color = if(isFormValid) Color.White else Color.Gray, fontSize = 15.sp)
            }


            // Sign up option
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account?",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(
                    onClick = { navHostController.navigate(route = ScreensForTheApp.LoginScreen.route) },
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "Login",
                        fontSize = 14.sp,
                        color = Color(0xFFFF9800),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            // Continue with Google button
            OutlinedButton(
                onClick = { /* TODO: Handle Google Sign-In */ },
                border = BorderStroke(width = 0.5.dp, color = Color.LightGray),
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
                fontSize = 18.sp,
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
                        fontSize = 18.sp,
                        color = Color(0xFFFF9800)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUp(onNavigateTo = {}, currentPage = 1)
}
