package com.trendyol.stately

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trendyol.stately.ui.theme.StatelyTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      StatelyTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          val contentState = rememberContentState()

          LaunchedEffect(Unit) {
            contentState.content = State.Loading()
            delay(2000)
            contentState.content = State.Content
            delay(2000)
            contentState.content = State.LoadingWithContent(LoadingType.Circle)
            delay(2000)
            contentState.content = State.LoadingWithContent(LoadingType.Progressive)
            delay(2000)
            contentState.content = State.Info(
              model = StatelyInfo(
                title = "Title",
                description = "Description",
                buttonText = "Button Text"
              )
            )
          }

          val tabs = remember {
            listOf(
              "Main",
              "LoadingState",
              "LoadingWithContentState Circle",
              "LoadingWithContentState Progressive",
              "InfoState"
            )
          }
          var selectedTabIndex by remember { mutableStateOf(0) }

          Scaffold(
            topBar = {
              TopAppBar(
                title = { Text(text = "Stately") },
                colors = TopAppBarDefaults.topAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primary,
                  titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
              )
            }
          ) { contentPadding ->
            Column(
              modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
            ) {
              ScrollableTabRow(
                divider = {
                  Divider(color = MaterialTheme.colorScheme.onTertiary)
                },
                modifier = Modifier.fillMaxWidth(),
                edgePadding = 0.dp,
                containerColor = MaterialTheme.colorScheme.primary,
                selectedTabIndex = selectedTabIndex
              ) {
                tabs.forEachIndexed { index, item ->
                  Tab(
                    selected = index == selectedTabIndex,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { selectedTabIndex = tabs.indexOf(item) },
                    text = {
                      Text(
                        text = item,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = if (selectedTabIndex == index) {
                          FontWeight.SemiBold
                        } else {
                          FontWeight.Normal
                        }
                      )
                    }
                  )
                }
              }

              when (selectedTabIndex) {
                0 -> MainScreen(
                  contentPadding = contentPadding,
                  contentState = contentState
                )

                1 -> LoadingStateScreen()
                2 -> LoadingWithContentStateCircleScreen()
                3 -> LoadingWithContentStateProgressiveScreen()
                4 -> InfoStateScreen()
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun MainScreen(contentPadding: PaddingValues, contentState: LayoutState) {
  Column(
    modifier = Modifier.padding(contentPadding),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Stately(
      modifier = Modifier.weight(1f),
      layoutState = contentState,

      contentLayout = {
        Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          SampleContent(modifier = Modifier.padding(8.dp))
        }
      }
    )
    val text = buildAnnotatedString {
      append("Current state is : ")
      withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
        append(contentState.content.javaClass.simpleName)
      }
    }
    Text(modifier = Modifier.padding(16.dp), text = text)
  }
}

@Composable
private fun LoadingStateScreen() {
  LoadingState(
    modifier = Modifier.fillMaxSize()
  )
}

@Composable
private fun LoadingWithContentStateCircleScreen() {
  LoadingWithContentState(
    loadingWithContent = State.LoadingWithContent(type = LoadingType.Circle)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent),
      verticalArrangement = Arrangement.Center
    ) {
      SampleContent()
    }
  }
}

@Composable
private fun LoadingWithContentStateProgressiveScreen() {
  LoadingWithContentState(
    loadingWithContent = State.LoadingWithContent(type = LoadingType.Progressive)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent),
      verticalArrangement = Arrangement.Center
    ) {
      SampleContent()
    }
  }
}

@Composable
private fun InfoStateScreen() {
  InfoState(
    modifier = Modifier.fillMaxSize(),
    model = StatelyInfo(
      title = "An error occurred!",
      description = "",
      buttonText = "Retry!",
      image = R.drawable.ic_error
    )
  )
}

@Composable
private fun SampleContent(modifier: Modifier = Modifier) {
  Card(modifier = modifier) {
    Row(modifier = Modifier.padding(8.dp)) {
      Text(
        text = "Lorem Ipsum is simply dummy text of the printing and typesetting " +
          "industry. Lorem Ipsum has been the industry's standard dummy text ever" +
          " since the 1500s,"
      )
    }
  }
}
