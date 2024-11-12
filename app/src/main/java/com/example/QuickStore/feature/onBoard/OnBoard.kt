package com.example.QuickStore.feature.onBoard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.QuickStore.R
import com.example.QuickStore.feature.main.route.Screen
import com.example.QuickStore.ui.theme.Purple10
import com.example.QuickStore.ui.theme.Purple2
import kotlinx.coroutines.launch

data class OnboardingPage(val image: Int, val title: String, val description: String)

val onboardingPages = listOf(
    OnboardingPage(
        image = R.drawable.onboard2,
        title = "Selamat Datang di Ruang Amanmu",
        description = "Di sini, kamu bisa dapatkan dukungan mental yang aman dan nyaman."
    ),
    OnboardingPage(
        image = R.drawable.onboard2,
        title = "Dukungan yang Pas Untukmu",
        description = "Mindfulme memberikan rekomendasi psikolog dan konten yang cocok buat kondisi mentalmu."
    ),
    OnboardingPage(
        image = R.drawable.onboard2,
        title = "Cerita dan Tumbuh Bareng-Bareng",
        description = "Yuk, sharing cerita, temukan teman baru, dan tumbuh bareng komunitas kita!"
    )
)

@Composable
fun OnBoard(navController: NavController) {
    val listState = rememberLazyListState()
    val currentPage by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val totalPages = onboardingPages.size
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(top = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RoundedProgressIndicator(
                    progress = (currentPage + 1) / totalPages.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(10.dp)
                )
                TextButton(onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(totalPages - 1)
                    }
                }) {
                    Text(
                        text = "Skip",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Purple10
                    )
                }
            }
            LazyRow(
                state = listState,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f)
                    .fillMaxSize(),
                userScrollEnabled = false
            ) {
                itemsIndexed(onboardingPages) { index, page ->
                    Box(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .fillParentMaxHeight()

                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = page.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                style = MaterialTheme.typography.displayMedium,
                                color = Purple10,
                                textAlign = TextAlign.Left
                            )
                            Text(
                                text = page.description,

                                style = MaterialTheme.typography.bodyLarge,
                                color = Purple10,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(bottom = 24.dp, top = 8.dp)
                            )
                            Image(
                                painter = painterResource(id = page.image),
                                contentDescription = page.title,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxSize()
                            )
                        }

                    }
                }
            }
        }
        if (currentPage == totalPages - 1) {
            Button(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),colors = ButtonDefaults.buttonColors(
                containerColor = Purple10
            ) ,onClick = {
                navController.navigate(Screen.SignIn.route) {
                    popUpTo(Screen.OnBoard.route) { inclusive = true }
                }
            }) {
                Text(text = "Get Started")
            }
        } else {
            Button(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp), colors = ButtonDefaults.buttonColors(
                containerColor = Purple10
            ) ,onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(currentPage + 1)
                }
            }) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun RoundedProgressIndicator(progress: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Purple2)
    ) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp)),
            color = Purple10
        )
    }
}