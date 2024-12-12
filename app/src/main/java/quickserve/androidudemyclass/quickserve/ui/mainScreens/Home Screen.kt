package quickserve.androidudemyclass.quickserve.ui.mainScreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import eu.androidudemyclass.quickserve.R
import kotlinx.coroutines.delay

@Composable
fun HomeScreenApp(
    navHostController: NavHostController,
    onNavigateTo: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    // Dynamic measurements based on screen size
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val topSectionHeight = screenHeight * 0.23f // 15% of screen height
    val horizontalPadding = screenWidth * 0.04f // 4% of screen width
    val cardOffset = topSectionHeight * 0.1f // 20% of top section height
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    var searchQuery by remember { mutableStateOf("") }
    var isSearchFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header Section
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(topSectionHeight),
                    colors = CardDefaults.cardColors(Color(0xFF232323)),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                ) {
                    // Previous header content remains the same
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 30.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Address Section
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Column {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    ) {
                                        Text(
                                            text = "Address",
                                            color = Color.White,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Icon(
                                            imageVector = Icons.Default.Place,
                                            contentDescription = "Address Icon",
                                            tint = Color.White,
                                            modifier = Modifier
                                                .padding(start = 4.dp)
                                                .size(16.dp)
                                        )
                                    }
                                    Text(
                                        text = "CVR Hall A-103",
                                        color = Color.White.copy(alpha = 0.8f),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "Notifications",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.main_logo),
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        // Search Bar
                        SearchBar(searchQuery, isSearchFocused) { query ->
                            searchQuery = query
                        }
                    }
                }
            }

            // Trending Section
            item {
                Text(
                    text = "Trending in NIT Rourkela",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(trendingItems) { item ->
                        TrendingItemCard(item)
                    }
                }
            }

            // Exclusive Deals Section
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Unlock !! Exclusive deals",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "from your favorite Restaurants",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    AutoSlidingPromotions()
                }
            }

            // Let's Eat Section
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Let's Eat",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(foodItems) { item ->
                        FoodItemCard(item)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    searchQuery: String,
    isSearchFocused: Boolean,
    onQueryChange: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .size(50.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {},
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Box {
                        if (searchQuery.isEmpty() && !isSearchFocused) {
                            Text(
                                text = "Search for 'SD Juice Center'",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
    }
}

@Composable
fun AutoSlidingPromotions() {
    val promotionImages = listOf(
        R.drawable.main_logo,
        R.drawable.main_logo
    )

    var currentPage by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while(true) {
            delay(3000)
            currentPage = (currentPage + 1) % promotionImages.size
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        AnimatedVisibility(
            visible = true,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Image(
                painter = painterResource(id = promotionImages[currentPage]),
                contentDescription = "Promotion",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .scale(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun TrendingItemCard(item: TrendingItem) {
    Card(
        modifier = Modifier.width(140.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = item.restaurant,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun FoodItemCard(item: FoodItem) {
    Card(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentScale = ContentScale.Crop
        )
    }
}

data class TrendingItem(
    val imageRes: Int,
    val name: String,
    val restaurant: String
)

data class FoodItem(
    val imageRes: Int,
    val name: String
)

val trendingItems = listOf(
    TrendingItem(R.drawable.main_logo, "Chicken Biryani", "LT6"),
    TrendingItem(R.drawable.main_logo, "Oreo Shake", "SD Juice"),
    TrendingItem(R.drawable.main_logo, "Cake", "Dosa Plaza")
)

val foodItems = listOf(
    FoodItem(R.drawable.main_logo, "Rolls"),
    FoodItem(R.drawable.main_logo, "Burger"),
    FoodItem(R.drawable.main_logo, "Biryani")
)

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreenApp(navHostController= NavHostController(LocalContext.current),onNavigateTo = {})
}

//@Composable
//fun UnlockCard() {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(20.dp)
//        ) {
//            Text(
//                text = "Unlock !! Exclusive deals",
//                style = MaterialTheme.typography.titleMedium.copy(
//                    fontWeight = FontWeight.Bold
//                )
//            )
//            Text(
//                text = "from your favorite Restaurants",
//                style = MaterialTheme.typography.titleMedium.copy(
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color.Gray
//                )
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Button(
//                onClick = { },
//                modifier = Modifier.wrapContentSize(),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
//                border = BorderStroke(1.dp, Color(0xFFFF9800)),
//                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
//            ) {
//                Text(
//                    "Explore Now",
//                    color = Color(0xFFFF9800)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.restaurant1),
//                    contentDescription = "Restaurant 1",
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.restaurant2),
//                    contentDescription = "Restaurant 2",
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun LetsEatSection() {
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text(
//            text = "Let's Eat",
//            style = MaterialTheme.typography.titleLarge.copy(
//                fontWeight = FontWeight.Bold
//            ),
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            horizontalArrangement = Arrangement.spacedBy(12.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(foodItems) { item ->
//                FoodItemCard(item)
//            }
//        }
//    }
//}
//
//@Composable
//fun FoodItemCard(item: FoodItem) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Card(
//            modifier = Modifier
//                .size(100.dp)
//                .clip(RoundedCornerShape(12.dp)),
//            elevation = CardDefaults.cardElevation(4.dp)
//        ) {
//            Image(
//                painter = painterResource(id = item.imageRes),
//                contentDescription = item.name,
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
//        }
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(
//            text = item.name,
//            style = MaterialTheme.typography.bodyMedium,
//            textAlign = TextAlign.Center
//        )
//    }
//}
//
//@Composable
//fun FoodGallery() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Food Gallery",
//            style = MaterialTheme.typography.titleLarge.copy(
//                fontWeight = FontWeight.Bold
//            ),
//            modifier = Modifier.padding(vertical = 16.dp)
//        )
//
//        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//        val itemSize = screenWidth / 5 // 5 items per row
//
//        FlowRow(
//            modifier = Modifier.fillMaxWidth(),
//            mainAxisSpacing = 8.dp,
//            crossAxisSpacing = 8.dp
//        ) {
//            galleryItems.forEach { item ->
//                Image(
//                    painter = painterResource(id = item.imageRes),
//                    contentDescription = item.name,
//                    modifier = Modifier
//                        .size(if (item.isLarge) itemSize * 1.5f else itemSize)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//            }
//        }
//
//        Text(
//            text = "Let your eyes select, What to eat Today!",
//            style = MaterialTheme.typography.bodyMedium.copy(
//                color = Color.Gray
//            ),
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp)
//        )
//    }
//}
//
//data class FoodItem(
//    val imageRes: Int,
//    val name: String
//)
//
//data class GalleryItem(
//    val imageRes: Int,
//    val name: String,
//    val isLarge: Boolean = false
//)
//
//val foodItems = listOf(
//    FoodItem(R.drawable.paneer_roll, "Paneer Roll"),
//    FoodItem(R.drawable.hotdog, "Hotdog"),
//    FoodItem(R.drawable.mutton_biryani, "Mutton Biryani"),
//    FoodItem(R.drawable.burger, "Burger"),
//    FoodItem(R.drawable.mix_veg, "Mix veg"),
//    FoodItem(R.drawable.soup, "Soup")
//)
//
//val galleryItems = listOf(
//    GalleryItem(R.drawable.gallery1, "Item 1"),
//    GalleryItem(R.drawable.gallery2, "Item 2"),
//    GalleryItem(R.drawable.gallery3, "Item 3", true),
//    GalleryItem(R.drawable.gallery4, "Item 4"),
//    GalleryItem(R.drawable.gallery5, "Item 5"),
//    GalleryItem(R.drawable.gallery6, "Item 6"),
//    GalleryItem(R.drawable.gallery7, "Item 7", true),
//    GalleryItem(R.drawable.gallery8, "Item 8"),
//    GalleryItem(R.drawable.gallery9, "Item 9"),
//    GalleryItem(R.drawable.gallery10, "Item 10")
//)
//
//// Update the main HomeScreenApp to include these sections
//@Composable
//fun HomeScreenApp(
//    navHostController: NavHostController,
//    onNavigateTo: () -> Unit
//) {
//    // Previous header code remains the same...
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//        // Header item (existing code)
//        item {
//            // Previous header implementation
//        }
//
//        // Unlock Card
//        item {
//            UnlockCard()
//        }
//
//        // Let's Eat Section
//        item {
//            LetsEatSection()
//        }
//
//        // Food Gallery
//        item {
//            FoodGallery()
//        }
//    }
//}