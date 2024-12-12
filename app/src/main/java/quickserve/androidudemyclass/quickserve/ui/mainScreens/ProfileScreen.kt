package quickserve.androidudemyclass.quickserve.ui.mainScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import quickserve.androidudemyclass.quickserve.screens.ScreensForTheApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileMainScreen(
    onNavigateTo: () -> Unit,
    navHostController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    // Dynamic measurements based on screen size
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val topSectionHeight = screenHeight * 0.15f // 15% of screen height
    val horizontalPadding = screenWidth * 0.04f // 4% of screen width
    val cardOffset = topSectionHeight * 0.1f // 20% of top section height
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val alpha=0.5f

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Black top section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(topSectionHeight)
                .background(Color.Black)
        )

        // White bottom section
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topSectionHeight)
                .background(Color.White)
        )

        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding, vertical = 20.dp)
        ) {
            Text(
                text = "My Profile",
                color = Color.White,
                fontSize = (screenWidth * 0.055f).value.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    top = screenHeight * 0.02f,
                    bottom = screenHeight * 0.03f
                )
            )

            // Profile Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = -cardOffset),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.apply {
                    alpha.sp
                })
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding, vertical = screenHeight * 0.02f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Profile Image
                    Box(
                        modifier = Modifier
                            .size(screenWidth * 0.12f)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )

                    Spacer(modifier = Modifier.width(horizontalPadding))

                    // User Info
                    Column {
                        Text(
                            text = "Natasha Gupta",
                            fontSize = (screenWidth * 0.04f).value.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "natasha381@gmail.com",
                            fontSize = (screenWidth * 0.035f).value.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "955337760",
                            fontSize = (screenWidth * 0.035f).value.sp,
                            color = Color.Gray
                        )
                    }
                    IconButton(onClick = {
                        navHostController.navigate(ScreensForTheApp.ProfileEdit)
                    },
                        modifier = Modifier.absolutePadding(left = 100.dp).size(screenWidth * 0.1f)
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
                        
                    }

                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            // Menu Items
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = screenHeight * 0.01f)
            ) {
                MenuTextField(
                    text = "Order History",
                    icon = android.R.drawable.ic_menu_recent_history,
                    screenWidth = screenWidth
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                MenuTextField(
                    text = "Address",
                    icon = android.R.drawable.ic_dialog_map,
                    screenWidth = screenWidth
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                MenuTextField(
                    text = "Help & Support",
                    icon = android.R.drawable.ic_menu_help,
                    screenWidth = screenWidth
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                MenuTextField(
                    text = "Sign Out",
                    icon = android.R.drawable.ic_lock_power_off,
                    screenWidth = screenWidth
                )
            }
        }

        // Bottom Navigation
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(screenHeight * 0.08f)
                .background(Color(0xFFF8F8F8))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NavBarItem(
                    icon = android.R.drawable.ic_menu_gallery,
                    text = "Home",
                    isSelected = currentRoute == ScreensForTheApp.WelcomeScreen.route,
                    screenWidth = screenWidth,
                    onClick = {
                        navHostController.navigate(ScreensForTheApp.WelcomeScreen.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavBarItem(
                    icon = android.R.drawable.ic_menu_recent_history,
                    text = "Restaurants",
                    isSelected = false, // Add appropriate route check when available
                    screenWidth = screenWidth,
                    onClick = {
                        // Add navigation when restaurant screen is available
                    }
                )
                NavBarItem(
                    icon = android.R.drawable.star_big_off,
                    text = "Favourites",
                    isSelected = false, // Add appropriate route check when available
                    screenWidth = screenWidth,
                    onClick = {
                        // Add navigation when favorites screen is available
                    }
                )
                NavBarItem(
                    icon = android.R.drawable.ic_menu_my_calendar,
                    text = "Profile",
                    isSelected = currentRoute == ScreensForTheApp.MainProfile.route,
                    screenWidth = screenWidth,
                    onClick = {
                        navHostController.navigate(ScreensForTheApp.MainProfile.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuTextField(
    text: String,
    icon: Int,
    screenWidth: androidx.compose.ui.unit.Dp
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text, fontSize = (screenWidth * 0.040f).value.sp) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color(0xFFFF6F00),
                modifier = Modifier.size(screenWidth * 0.06f)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(screenWidth * 0.05f)
            )
        },
        shape = RoundedCornerShape(30.dp)

    )
}

@Composable
private fun NavBarItem(
    icon: Int,
    text: String,
    isSelected: Boolean,
    screenWidth: androidx.compose.ui.unit.Dp,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(screenWidth * 0.25f)
            .clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = if (isSelected) Color(0xFFFF6F00) else Color.Gray,
            modifier = Modifier.size(screenWidth * 0.06f)
        )
        Text(
            text = text,
            fontSize = (screenWidth * 0.03f).value.sp,
            color = if (isSelected) Color(0xFFFF6F00) else Color.Gray
        )
    }
}


@Preview
@Composable
fun ProfileMainScreenPreview() {
    ProfileMainScreen(
        onNavigateTo = { },
        navHostController = NavHostController(LocalContext.current)
    )
}