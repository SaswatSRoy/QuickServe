package quickserve.androidudemyclass.quickserve.ui.LoginAndSignUp

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.androidudemyclass.quickserve.R
import kotlinx.coroutines.launch
import quickserve.androidudemyclass.quickserve.screens.ScreensForTheApp

@Composable
fun Verify(
    onNavigateTo: () -> Unit,
    navHostController: NavHostController = rememberNavController(),
    currentPage: Int
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val proportionalPadding = screenWidth * 0.04f
    var otp by remember { mutableStateOf("") }
    val isFormValid = otp.length == 6

    // Snackbar state
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope= rememberCoroutineScope()

    // Function to show Snackbar
    fun onVerifyClicked() {
        if (isFormValid) {
            // Show success message when OTP is verified
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Email Verified Successfully!")
            }
        }
    }

    // Use Scaffold to show Snackbar at the bottom
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                    Spacer(modifier = Modifier.width(screenWidth * 0.185f))
                    Text(
                        text = "Email Verification",
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
                Spacer(modifier = Modifier.height(screenHeight * 0.03f))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.screenshot_2024_12_05_193112),
                        contentDescription = "Verify",
                        modifier = Modifier.size(200.dp)
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.04f))
                    Text(
                        text = "Check your email for OTP",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.7f)
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                    Text(
                        text = "We have sent you a 6 digit verification code on your registered email. Check your Spam folder also. Enter the code and verify.",
                        fontSize = 17.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )
                }
                Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                OtpEntry(
                    otp = otp,
                    onOtpChange = { newOtp ->
                        otp = newOtp
                    }
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.06f))

                // Send Code Button
                Button(
                    onClick = { onVerifyClicked() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFormValid) Color(0xFFFF9800) else Color(0xFFEEEEEE)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(40.dp)
                ) {
                    Text(text = "Verify", color = if (isFormValid) Color.White else Color.Gray, fontSize = 17.sp)
                }

                // Sign up option
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Didn’t receive the otp?",
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    TextButton(
                        onClick = { navHostController.navigate(route = ScreensForTheApp.LoginScreen.route) },
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = "Resend",
                            fontSize = 15.sp,
                            color = Color(0xFFFF9800),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(screenHeight * 0.025f))
            }
        }
    }
}

@Composable
fun OtpEntry(
    otp: String,
    onOtpChange: (String) -> Unit
) {
    val focusRequesters = List(6) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(6) { index ->
            Box(
                modifier = Modifier
                    .size(60.dp) // Circle size
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(50)
                    )
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(50)
                    )
                , // Add elevation to make the circle appear raised
                contentAlignment = Alignment.Center // Center the content inside the circle
            ) {
                BasicTextField(
                    value = otp.getOrNull(index)?.toString() ?: "",
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            // Ensure OTP is updated correctly without disrupting the state
                            val updatedOtp = otp.toCharArray().toMutableList().apply {
                                if (index < size) {
                                    this[index] = newValue.firstOrNull() ?: ' '
                                } else {
                                    add(newValue.firstOrNull() ?: ' ')
                                }
                            }
                            onOtpChange(updatedOtp.joinToString("").trim())
                            // Move focus to the next field automatically
                            if (newValue.isNotEmpty() && index < 5) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 40.sp, // Adjust the font size to fit the circle
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    visualTransformation = VisualTransformation.None, // Make cursor invisible
                    cursorBrush = SolidColor(Color.Transparent), // Hide the cursor
                    modifier = Modifier
                        .fillMaxSize()
                        .focusRequester(focusRequesters[index]) // Assign each field a FocusRequester
                        .padding(0.dp) // Remove any padding so the number fits exactly in the center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationPreview() {
    Verify(onNavigateTo = {}, currentPage = 1)
}
