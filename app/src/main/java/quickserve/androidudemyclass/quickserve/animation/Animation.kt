package quickserve.androidudemyclass.quickserve.animation
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
@Composable
fun AnimatedDotIndicatorWithEffects(
    totalDots: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    val dotSpacing = 16.dp
    val dotSize = 8.dp

    val transition = updateTransition(targetState = currentPage, label = "dot transition")

    val offsetAnimation by transition.animateFloat(
        label = "dot offset",
        transitionSpec = {
            tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        }
    ) { page ->
        page * (dotSize + dotSpacing).value
    }

    val scaleAnimation by transition.animateFloat(
        label = "dot scale",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        }
    ) { _ ->
        1.2f
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Background dots
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(totalDots) { index ->
                val dotAlpha by animateFloatAsState(
                    targetValue = if (index == currentPage) 1f else 0.5f,
                    animationSpec = tween(300),
                    label = "dot alpha"
                )

                Box(
                    modifier = Modifier
                        .size(dotSize)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = Color.Black.copy(alpha = dotAlpha),
                            shape = CircleShape
                        )
                        .background(
                            if (index == currentPage)
                                Color(0xFFFF9800)
                            else
                                Color.LightGray.copy(alpha = dotAlpha)
                        )
                        .graphicsLayer {
                            translationX = offsetAnimation
                        }
                )
                if (index < totalDots - 1) {
                    Spacer(modifier = Modifier.width(dotSpacing))
                }
            }
        }
    }
}