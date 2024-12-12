package quickserve.androidudemyclass.quickserve.ui.mainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun NewAddressScreen(
    onNavigateTo: () -> Unit,
    navHostController: NavHostController
){
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val proportionalPadding = screenWidth * 0.04f

    var title by remember { mutableStateOf("") }
    var hall by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var block by remember { mutableStateOf("") }


    val hallOptions = listOf(
        "KMS hall, NIT Rourkela",
        "SD hall, NIT Rourkela",
        "BF hall, NIT Rourkela",
        "CVR hall, NIT Rourkela",
        "VS hall, NIT Rourkela"
    )

    val isTitleValid = title.isNotBlank()
    val isHallValid = hall.isNotBlank()
    val isPhoneNumberValid = phoneNumber.length == 10 && phoneNumber.all { it.isDigit() }
    val isBlockValid = block.isNotBlank()
    val isFormValid = isTitleValid && isHallValid && isPhoneNumberValid && isBlockValid

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Black top section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.2f)
                .background(Color.Black)
                .padding(horizontal = proportionalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { navHostController.navigateUp() },
                    modifier = Modifier.size(screenWidth * 0.09f)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(screenWidth * 0.236f))
                Text(
                    text = "New Address",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color.White
                    )
                )
            }
        }

        // White section for content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = screenHeight * 0.17f)
                .background(Color.White)
                .padding(horizontal = proportionalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            listOf(
                Triple("Title", title) { value: String -> title = value },
                Triple("Block", block) { value: String -> block = value },
                Triple("Phone Number", phoneNumber) { value: String -> phoneNumber = value }
            ).forEach { (label, value, onValueChange) ->
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    label = { Text(text = label, color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {}),
                    trailingIcon = {
                        if (value.isNotEmpty()) {
                            val isValid = when (label) {
                                "Title" -> isTitleValid
                                "Block" -> isBlockValid
                                "Phone Number" -> isPhoneNumberValid
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




            HallDropdownMenuForNewAddress(
                hall = hall,
                onHallChange = { hall = it },
                hallOptions = hallOptions,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )


            Spacer(modifier = Modifier.height(screenHeight * 0.23f))

            // Done button
            Button(
                onClick = { /* Handle done action */ },
                enabled = isFormValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFFFF8914) else Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(vertical = 6.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = "Done",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HallDropdownMenuForNewAddress(
    hall: String,
    onHallChange: (String) -> Unit,
    hallOptions: List<String>,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Adjust box size dynamically (e.g., 80% of screen width)
    val dropdownWidth = screenWidth * 0.8f

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = hall,
            onValueChange = {},
            label = { Text(text = "Hall", color = Color.Gray) },
            singleLine = true,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Dropdown Icon",
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(), // Dynamically set width
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray, // Orange border
                unfocusedBorderColor = Color.Gray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(dropdownWidth) // Dynamically set dropdown width
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
        ) {
            hallOptions.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            color = Color.Black,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    },
                    onClick = {
                        onHallChange(option)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )

                // Add a divider between items (except the last one)
                if (index != hallOptions.lastIndex) {
                    Divider(color = Color.Gray, thickness = 0.5.dp)
                }
            }
        }
    }
}

@Preview
@Composable
fun NewAddressScreenPreview(){
    NewAddressScreen(onNavigateTo = {}, navHostController = NavHostController(LocalContext.current))
}
