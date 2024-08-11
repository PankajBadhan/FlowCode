package com.randomuser.app.utils

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AlertDialog
import com.randomuser.app.R

@Suppress("DEPRECATION")
fun isInternetAvailable(context: Context): Boolean {
    var result = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }
        }
    }
    return result
}

fun showAlertDialog(
    context: Context,
    title: String?,
    message: String?,
    positiveButtonText: String?,
    positiveClick: DialogInterface.OnClickListener?,
    negativeButtonText: String?,
    negativeClick: DialogInterface.OnClickListener?
) {
    val alertBuilder = AlertDialog.Builder(context)
    alertBuilder.setTitle(title)
    alertBuilder.setMessage(message)

    var positiveText: String = context.getString(R.string.ok)
    positiveButtonText?.let {
        positiveText = it
    }

    var positiveListener: DialogInterface.OnClickListener =
        DialogInterface.OnClickListener { dialogInterface, _ ->
            dialogInterface.cancel()
        }
    positiveClick?.let {
        positiveListener = it
    }

    alertBuilder.setPositiveButton(positiveText, positiveListener)

    var negativeListener: DialogInterface.OnClickListener =
        DialogInterface.OnClickListener { dialogInterface, _ ->
            dialogInterface.cancel()
        }
    negativeClick?.let {
        negativeListener = it
    }
    alertBuilder.setNegativeButton(negativeButtonText, negativeListener)

    val alertDialog = alertBuilder.create()
    alertDialog.show()
}

fun showInfoAlertDialog(context: Context, title: String, message: String) {
    showAlertDialog(context, title, message, null, null, null, null)
}