package com.trendyol.stately

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Displays a loading state with a circular progress indicator and optional text.
 * @param modifier The modifier to be applied to the layout.
 * @param indicatorColor The color of the circular progress indicator.
 * @param indicatorStrokeWidth The width of the circular progress indicator stroke.
 * @param text The optional text to display below the circular progress indicator.
 * @param textStyle The style of the optional text.
 */
@Composable
fun LoadingState(
  modifier: Modifier = Modifier,
  indicatorColor: Color = MaterialTheme.colors.primary,
  indicatorStrokeWidth: Dp = 4.dp,
  text: String = "",
  textStyle: TextStyle = MaterialTheme.typography.body1
) {
  Column(
    modifier = modifier.background(Color.Transparent).fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if (text.isNotEmpty()) {
      Text(
        text = text,
        style = textStyle,
        modifier = Modifier
          .wrapContentSize()
          .padding(bottom = 16.dp)
      )
    }
    CircularProgressIndicator(
      color = indicatorColor,
      modifier = Modifier.wrapContentSize(),
      strokeWidth = indicatorStrokeWidth
    )
  }
}
