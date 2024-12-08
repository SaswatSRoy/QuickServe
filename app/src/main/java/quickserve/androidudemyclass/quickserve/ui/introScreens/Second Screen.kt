package quickserve.androidudemyclass.quickserve.ui.introScreens





import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import eu.androidudemyclass.quickserve.R
import quickserve.androidudemyclass.quickserve.animation.AnimatedDotIndicatorWithEffects
import quickserve.androidudemyclass.quickserve.screens.ScreensForTheApp


@Composable
fun SecondScreen(
    onNavigateTo: () -> Unit,
    navHostController: NavHostController= rememberNavController(),
    currentPage:Int
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val proportionalPadding = screenWidth * 0.04f
    val backStackEntry by navHostController.currentBackStackEntryAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Back button at the top left
        IconButton(
            onClick = {navHostController.navigateUp() },
            modifier = Modifier
                .padding(proportionalPadding)
                .align(Alignment.TopStart)
                .size(screenWidth * 0.12f)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(screenWidth * 0.06f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = proportionalPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(screenHeight * 0.15f))

            // Cake icon section with increased size
            Box(
                modifier = Modifier
                    .size(screenWidth * 0.7f) // Increased from 0.5f to 0.7f
                    .padding(bottom = screenHeight * 0.02f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dishes),
                    contentDescription = "Order Food Parcels",
                    modifier = Modifier
                        .fillMaxSize(0.9f), // Increased from 0.8f to 0.9f
                    colorFilter = ColorFilter.tint(Color(0xFFFF9800))
                )
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.08f))

            // Text section with larger font sizes
            Text(
                text = "Order Food Parcels",
                fontSize = (screenWidth * 0.08f).value.sp, // Increased from 0.06f to 0.08f
                fontWeight = FontWeight.Medium,
                color = Color(0xFF6C6C6C),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            Text(
                text = "Skip the wait -\nGrab your food parcel & Go!",
                fontSize = (screenWidth * 0.055f).value.sp, // Increased from 0.04f to 0.045f
                color = Color(0xFFA1A1A1),
                textAlign = TextAlign.Center,
                lineHeight = (screenWidth * 0.06f).value.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.1f))

            // Dots indicator with larger dots
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedDotIndicatorWithEffects(totalDots =4, currentPage =currentPage )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom navigation with larger buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = proportionalPadding * 1.5f,
                        end = proportionalPadding * 1.5f,
                        bottom = screenHeight * 0.05f
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {navHostController.navigate(route = ScreensForTheApp.LoginScreen.route) },
                    modifier = Modifier.padding(start = screenWidth * 0.02f)
                ) {
                    Text(
                        text = "Skip",
                        color = Color(0xFFFF9800),
                        fontSize = (screenWidth * 0.05f).value.sp, // Increased from 0.045f to 0.05f
                        fontWeight = FontWeight.Medium
                    )
                }

                Button(
                    onClick = onNavigateTo,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                    shape = RoundedCornerShape(50.dp),
                    contentPadding = PaddingValues(13.dp),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = "Next",
                        tint = Color.White,
                        modifier = Modifier.size(screenWidth * 0.07f) // Increased from 0.06f to 0.07f
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SecondScreenPreview(){
    SecondScreen(onNavigateTo = {}, currentPage = 1)
}