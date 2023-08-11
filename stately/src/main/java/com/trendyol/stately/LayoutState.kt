package com.trendyol.stately

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.rememberSaveable

/**
 * A state that stores the current state of a layout.
 */
@Stable
interface LayoutState {
  var content: State
}

/**
 * Creates a [LayoutState] that is remembered across compositions.
 * @param initialState The initial state of the [LayoutState].
 */
@Composable
fun rememberContentState(
  initialState: State = State.None
): LayoutState {
  return rememberSaveable(key = initialState.toString(), saver = LayoutStateImpl.Saver) {
    LayoutStateImpl(initialState)
  }
}
