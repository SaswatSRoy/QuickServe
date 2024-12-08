package quickserve.androidudemyclass.quickserve.welcome


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import eu.androidudemyclass.quickserve.R
import kotlinx.coroutines.delay
import quickserve.androidudemyclass.quickserve.screens.ScreensForTheApp


object Variables {
    val ReflowAAWcagReflowMaxZoomPct: Dp = 300.dp
}
fun Modifier.circularRipple(
    color: Color = Color(0xFFE45E29), // Orange ripple color
    duration: Int = 3000, // 2 seconds animation duration
    targetRadius: Dp = 500.dp // Ripple expands to 400.dp
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val radius by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetRadius.value ,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    drawWithContent {
        drawContent()
        drawCircle(
            color = color,
            radius = radius,
            center = center,
            alpha = (1 - radius / targetRadius.toPx()).coerceAtLeast(0f)
        )
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               navHostController: NavHostController,
               currentPage: Int) {

    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds delay
        navHostController.navigate(ScreensForTheApp.FirstScreen.route) // Navigate to the next screen
    }
    Scaffold(
        topBar = {
            Modifier
                .width(428.07211.dp)
                .height(30.00505.dp)

        },
        containerColor = Color(0xFFFF8914)

    ) {
        contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
                .width(500.dp)
                .height(926.dp)
                .background(color = Color(0xFFFF8914)),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(60.dp))
            Box(
                modifier = Modifier
                    .size(Variables.ReflowAAWcagReflowMaxZoomPct) // Circular size
                    .clip(CircleShape)
                    .background(Color.White) // White background inside the circle
                    .border(
                        width = 0.25.dp,
                        color = Color.Black,
                        shape = CircleShape // Circular border
                    )
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .circularRipple()
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.main_logo),
                        contentDescription = "image description",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxSize() // Use fillMaxSize instead of size
                            .clip(CircleShape)
                    )

                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Initiative by NIT Rourkela Students",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = modifier
                .alpha(0.2f)
                .fillMaxSize()
            ) {
                Spacer(modifier = modifier.absolutePadding(left = 100.dp, right = 100.dp))
                Image(
                    painter = painterResource(id = R.drawable.chef),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.align(Alignment.BottomStart)
                        .clip(CircleShape)
                        .size(300.dp)
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){

}