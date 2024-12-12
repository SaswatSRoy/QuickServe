package quickserve.androidudemyclass.quickserve.ui.LoginAndSignUp

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LocationSelectionScreen(
    onNavigateTo: () -> Unit,
    navHostController: NavHostController,
    currentPage: Int
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val proportionalPadding = screenWidth * 0.04f
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        // Black top section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.25f)
                .background(Color.Black)
                .padding(horizontal = proportionalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = screenHeight * 0.1f),
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
                Spacer(modifier = Modifier.width(screenWidth * 0.27f))
                Text(
                    text = "Location",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color.White,
                    )
                )
            }
        }

        // White section for content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = screenHeight * 0.20f)
                .background(Color.White)
                .padding(horizontal = proportionalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            // Search bar

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = proportionalPadding)
                    .offset(y = screenHeight * -0.03f)
                    .height(53.dp)
                    .padding(horizontal = 0.2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Select Address", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search Icon",
                            tint = Color(0xFFFF8914)
                        )
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(30.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done // Add Done action to hide keyboard
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide() // Hide the keyboard when "Done" is pressed
                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.Black
                    )
                )
            }



            Spacer(modifier = Modifier.height(16.dp))

            // List of addresses
            val locations = listOf(
                "KMS hall, NIT Rourkela",
                "SD hall, NIT Rourkela",
                "BF hall, NIT Rourkela",
                "CVR hall, NIT Rourkela",
                "VS hall, NIT Rourkela"
            )

            val filteredLocations = locations.filter { it.contains(searchText, ignoreCase = true) }

            filteredLocations.forEach { location ->
                val interactionSource = remember { MutableInteractionSource() }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ){
                        searchText = location
                    }
                    .animateContentSize()

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn, // Updated to location icon
                        contentDescription = "Location Icon",
                        tint = Color(0XFF232323)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = location, fontSize = 16.sp, color = Color.Black)
                }
            }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 2.dp).alpha(0.2f), // Makes it half-visible
                    thickness = 1.dp,
                    color = Color.Gray
                )
            }




            Spacer(modifier = Modifier.height(50.dp))
            // Done button
            Button(
                onClick = { /* Handle done action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8914)), // Orange color
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

@Preview
@Composable
fun LocationSelectionScreenPreview() {
    LocationSelectionScreen(navHostController = rememberNavController(), currentPage = 0, onNavigateTo = {})
}