package com.hubwallet.commons.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.media.Image
import android.net.Uri
import android.os.Build
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.util.TypedValue
import android.view.*
import android.view.animation.RotateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.hubwallet.commons.constants.ConstantValues
import com.hubwallet.utillss.ErrorResponse
import com.hubwallet.utillss.ResultWrapper
import retrofit2.Response
import retrofit2.Retrofit
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class Utils @Inject constructor(val retrofit: Retrofit)  {

    companion object {
        fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit)) {
            if (value1 != null && value2 != null) {
                bothNotNull(value1, value2)
            }
        }
        fun <T : Any> parseError(response: Response<T>): ResultWrapper<Nothing> {
            val retrofit = Retrofit.Builder() .build()
            val converter = retrofit.responseBodyConverter<ErrorResponse>(
                ErrorResponse::class.java,
                arrayOfNulls(0)
            )

            val error: ErrorResponse?

            error = try {
                converter.convert(response.errorBody()!!)
            } catch (e: IOException) {
                return ResultWrapper.NetworkError
            }

            return ResultWrapper.GenericError(error)
        }
    }


}