package com.trendyol.stately

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

/**
 * Model for the info state of a layout.
 *
 * @param title The title of the info state.
 * @param description The description of the info state.
 * @param buttonText The text to be displayed on the button of the info state.
 * @param image The drawable resource ID of the image to be displayed in the info state.
 */
@Stable
@Parcelize
data class StatelyInfo(
  val title: String? = null,
  val description: String? = null,
  val buttonText: String? = null,
  @DrawableRes val image: Int? = null
) : Parcelable
