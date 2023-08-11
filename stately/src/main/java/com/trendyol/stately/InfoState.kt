package com.trendyol.stately

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Composable function that displays an info state with an image, title, description, and button.
 *
 * @param modifier Modifier to be applied to the composable.
 * @param model is the [StatelyInfo] model to be shown.
 * @param retryButtonClicked Callback function to be executed when the retry button is clicked.
 * */
@Composable
fun InfoState(
  modifier: Modifier = Modifier,
  model: StatelyInfo = StatelyInfo(),
  retryButtonClicked: (() -> Unit)? = null
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colors.background)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if (model.image != null) {
      Image(painter = painterResource(id = model.image), contentDescription = null)
    }
    Spacer(Modifier.height(8.dp))
    Text(
      modifier = Modifier.padding(bottom = 4.dp),
      text = model.title.orEmpty(),
      style = MaterialTheme.typography.h5
    )
    Text(
      modifier = Modifier.padding(bottom = 16.dp),
      text = model.description.orEmpty(),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.body1
    )
    if (model.buttonText != null) {
      Button(
        modifier = Modifier
          .fillMaxWidth()
          .defaultMinSize(minHeight = 43.dp)
          .padding(horizontal = 16.dp),
        onClick = retryButtonClicked ?: {},
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        shape = RoundedCornerShape(8.dp)
      ) {
        Text(
          modifier = Modifier,
          color = MaterialTheme.colors.onPrimary,
          fontWeight = FontWeight.W600,
          textAlign = TextAlign.Center,
          text = model.buttonText
        )
      }
    }
  }
}
