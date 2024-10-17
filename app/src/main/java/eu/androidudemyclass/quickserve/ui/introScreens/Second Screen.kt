package eu.androidudemyclass.quickserve.ui.introScreens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.androidudemyclass.quickserve.R


@Composable
fun SecondScreen(
    onNavigateTo: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize().fillMaxWidth()
                .padding(
                    horizontal = screenWidth * 0.06f,
                    vertical = screenHeight * 0.05f
                ).graphicsLayer {

                }, // 6% padding on horizontal, 5% on vertical
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier =Modifier.padding(screenHeight*0.09f))

            Column {

                Image(
                    painter = painterResource(id = R.drawable.dishes), // replace with your image resource
                    contentDescription = "Delivery Illustration",
                    modifier = Modifier
                        .size(screenHeight * 0.35f) // 30% of screen height
                        .padding(top = screenHeight * 0.0005f)
                        .width(screenWidth * 0.01f), // 5% of screen height padding from the top
                    contentScale = ContentScale.Fit,

                )

            }




            Spacer(modifier = Modifier.padding(screenHeight*0.01f))

            // Text section
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Order Food Parcels",
                    fontSize = (screenHeight * 0.035f).value.sp, // Text size scaled to 3.5% of screen height
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6C6C6C)
                )
                Spacer(modifier = Modifier.height(screenHeight * 0.01f)) // Spacer of 1% of screen height
                Text(
                    text = "\"Skip the wait - Grab your food parcel & Go!\"",
                    fontSize = (screenHeight * 0.021f).value.sp, // Text size scaled to 2.5% of screen height
                    color = Color(0xFFA1A1A1),
                    fontWeight = FontWeight(400)

                )
            }

            // Dots indicator and buttons
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(screenHeight*0.01f)
            ) {
                // Dots indicator
                Spacer(modifier = Modifier.padding(screenHeight*0.03f))
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(4) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(if (index == 1) Color(0xFFFF9800) else Color.LightGray)
                                .padding(8.dp),

                            )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }

                Spacer(modifier = Modifier.padding(screenHeight*0.015f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth().fillMaxSize()
                        .padding(horizontal = screenWidth * 0.06f), // 6% padding on horizontal
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    TextButton(onClick = { /* Handle skip click */ }) {
                        Text(
                            text = "Skip",
                            color = Color(0xFFFF9800),
                            fontSize = (screenHeight * 0.025f).value.sp,
                            fontWeight = FontWeight.ExtraBold
                        ) // Text size scaled
                    }
                    Button(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                        shape = RoundedCornerShape(50.dp),
                        contentPadding = PaddingValues(13.dp),
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                            contentDescription ="" ,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SecondScreenPreview(){
    SecondScreen(onNavigateTo = {})
}