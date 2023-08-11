package com.trendyol.stately

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import kotlin.properties.Delegates

internal class LayoutStateImpl(
  initialState: State = State.None
) : LayoutState {
  private var _content by mutableStateOf(initialState)

  private var hasUpdatedValue by Delegates.vetoable(_content) { _, oldValue, newValue ->
    if (oldValue != newValue) {
      _content = newValue
    }
    oldValue != newValue
  }

  override var content: State
    get() = _content
    set(value) {
      hasUpdatedValue = value
    }

  companion object {
    /**
     * A saver object that saves and restores instances of [LayoutStateImpl].
     */
    internal val Saver = listSaver(
      save = { listOf(it.content) },
      restore = {
        LayoutStateImpl(initialState = it[0] as State)
      }
    )
  }
}
