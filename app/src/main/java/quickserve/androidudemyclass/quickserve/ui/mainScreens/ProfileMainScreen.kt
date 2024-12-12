package quickserve.androidudemyclass.quickserve.ui.mainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun EditProfile(
    navController: NavHostController,
    onNavigateTo: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val proportionalPadding = screenWidth * 0.04f
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isNameValid = name.isNotEmpty()
    val isContactNumberValid = phoneNumber.length == 10 && phoneNumber.all { it.isDigit() }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Black Top Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Black)
                .padding(horizontal = proportionalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = screenHeight * 0.05f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier.size(screenWidth * 0.09f)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(screenWidth * 0.24f))
                Text(
                    text = "Edit Profile",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(400)
                    )
                )
            }

        }



        // Profile Picture and Form
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = screenHeight * 0.15f)
                .background(Color.White)
                .padding(horizontal = proportionalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            // Profile Picture with Edit Icon
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .padding(bottom = 32.dp),

            )

            Box(
                modifier = Modifier,
                contentAlignment = Alignment.TopCenter

            ){

                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                )
            }



            Spacer(modifier = Modifier.height(20.dp))

            // Input Fields
            listOf(
                Triple("Name", name) { value: String -> name = value },
                Triple("Email", email) { value: String -> email = value },
                Triple("Phone Number", phoneNumber) { value: String -> phoneNumber = value }
            ).forEach { (label, value, onValueChange) ->
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    label = { Text(text = label, color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {  }),
                    trailingIcon = {
                        if (value.isNotEmpty()) {
                            val isValid = when (label) {
                                "Name" -> isNameValid
                                "Email" -> isEmailValid
                                "Phone Number" -> isContactNumberValid
                                else -> true
                            }
                            val iconColor = if (isValid) Color(0xFFFF9800) else Color.Gray
                            Icon(
                                imageVector = if (isValid) Icons.Filled.Check else Icons.Default.Warning,
                                contentDescription = if (isValid) "Valid $label" else "Invalid $label",
                                tint = iconColor
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Save Changes Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFFFFA500))
                    .clickable {
                        // Handle Save Changes
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Save Changes",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview
@Composable
fun EditProfilePreview() {
    EditProfile(navController = NavHostController(LocalContext.current), onNavigateTo = {})
}
