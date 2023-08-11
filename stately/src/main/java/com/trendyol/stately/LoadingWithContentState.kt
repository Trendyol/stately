package com.trendyol.stately

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays a loading view with content.
 *
 * @param loadingWithContent The state of the layout to be displayed.
 * @param contentLayout The layout to be displayed when the content is available.
 */
@Composable
fun LoadingWithContentState(
  modifier: Modifier = Modifier,
  loadingWithContent: State.LoadingWithContent,
  contentLayout: @Composable () -> Unit
) {
  Box(modifier = modifier.fillMaxSize()) {
    contentLayout()

    if (loadingWithContent.type == LoadingType.Circle) {
      LoadingState()
    } else {
      HorizontalLoadingState()
    }
  }
}

/**
 * An enum class that represents the different types of loading views.
 */
enum class LoadingType {
  Circle, Progressive
}
