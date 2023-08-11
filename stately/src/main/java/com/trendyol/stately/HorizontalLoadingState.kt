package com.trendyol.stately

import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * Displays a horizontal progress bar.
 * @param modifier The modifier to be applied to the layout.
 * @param progressState The state of the progress bar, either [HorizontalProgressState.INFINITIVE] or [HorizontalProgressState.PROGRESSIVE].
 * @param progressValue The initial progress value of the progress bar, between 0.0F and 1.0F.
 */
@Composable
fun HorizontalLoadingState(
  modifier: Modifier = Modifier,
  progressState: HorizontalProgressState = HorizontalProgressState.INFINITIVE,
  @FloatRange(0.0, 1.0) progressValue: Float = 0.0F
) {
  val progress by remember { mutableStateOf(progressValue) }
  val progressModifier: Modifier = modifier.fillMaxWidth()

  val animatedProgress by animateFloatAsState(
    targetValue = progress,
    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    label = "LoadingHorizontalViewAnimatedProgress"
  )

  if (progressState == HorizontalProgressState.INFINITIVE) {
    LinearProgressIndicator(modifier = progressModifier)
  } else {
    LinearProgressIndicator(modifier = progressModifier, progress = animatedProgress)
  }
}

enum class HorizontalProgressState {
  INFINITIVE, PROGRESSIVE
}
