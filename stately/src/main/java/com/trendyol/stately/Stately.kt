package com.trendyol.stately

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays different states of a content based on the given [LayoutState].
 *
 * @param modifier The modifier to be applied to the layout.
 * @param layoutState The state of the layout to be displayed.
 * @param loadingLayout The layout to be displayed when the content is in the loading state.
 * @param contentLayout The layout to be displayed when the content is available.
 * @param infoLayout The layout to be displayed when the content is in the info state.
 */
@Composable
fun Stately(
  modifier: Modifier = Modifier,
  layoutState: LayoutState,
  loadingLayout: @Composable (message: String) -> Unit = { LoadingState(text = it) },
  infoLayout: @Composable (model: StatelyInfo) -> Unit = { InfoState(model = it) },
  contentLayout: @Composable () -> Unit = {}
) {
  Box(modifier = modifier) {
    when (layoutState.content) {
      is State.Loading -> {
        loadingLayout((layoutState.content as State.Loading).message.orEmpty())
      }

      is State.Info, is State.Error -> {
        infoLayout((layoutState.content as State.Info).model)
      }

      is State.Content -> {
        contentLayout()
      }

      is State.LoadingWithContent -> {
        LoadingWithContentState(
          loadingWithContent = layoutState.content as State.LoadingWithContent,
          contentLayout = contentLayout
        )
      }

      is State.None -> {}
    }
  }
}
