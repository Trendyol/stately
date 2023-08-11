package com.trendyol.stately

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

/**
 * A sealed class that represents the different states of a layout.
 */
@Stable
@Parcelize
sealed class State : Parcelable {
  /**
   * The loading state of a layout.
   *
   * @param message The message to be displayed in the loading view.
   */
  data class Loading(val message: String? = null) : State()

  /**
   * The content state of a layout.
   */
  object Content : State()

  /**
   * The info state of a layout.
   *
   * @param model The [StatelyInfo] to be displayed in the info view.
   */
  data class Info(val model: StatelyInfo) : State()

  /**
   * The loading with content state of a layout.
   *
   * @param type The [LoadingType] of the loading view to be displayed.
   */
  data class LoadingWithContent(val type: LoadingType) : State()

  /**
   * The error state of a layout.
   */
  object Error : State()

  /**
   * The none state of a layout.
   */
  object None : State()
}
