@file:OptIn(ExperimentalMaterial3Api::class)

package quickserve.androidudemyclass.quickserve.ui.LoginAndSignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Black top section reduced to 24% height
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.24f)  // Reduced height for the black section
                .background(Color.Black)
                .padding(horizontal = proportionalPadding)
        ) {
            // Back button and title
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
                        color = Color(0xFF232323),
                    )
                )
            }
        }

        // Search bar section placed exactly in the middle of the black and white sections
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search address", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { /* Handle search action */ }
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth(0.8f)  // Make the search bar a bit smaller
                .height(56.dp)  // Set a fixed height for the search bar
                .background(Color.White, shape = RoundedCornerShape(20.dp))  // Rounded corners and white background
                .align(Alignment.Center)  // Center the search bar horizontally
                .padding(top = screenHeight * 0.12f)  // Adjust this value to center it between the black and white sections
        )

        // White bottom section starts after the black section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = screenHeight * 0.24f)  // Starts after the black section
                .background(Color.White)
                .padding(horizontal = proportionalPadding)
        ) {
            // Content of the white section
            Text(
                text = "Additional content here",
                style = TextStyle(fontSize = 18.sp, color = Color.Black),
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun LocationSelectionScreenPreview() {
    LocationSelectionScreen(navHostController = rememberNavController(), currentPage = 0, onNavigateTo = {})
}

