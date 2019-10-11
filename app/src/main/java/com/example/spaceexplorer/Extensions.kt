package com.example.spaceexplorer

import android.content.Context
import android.content.res.Resources
import android.widget.Toast

fun Context.toast(charSequence: CharSequence) = Toast.makeText(this, charSequence, Toast.LENGTH_LONG).show()

fun Resources.getPluralZeroString(resId: Int, resZeroId: Int, quantity: Int) : String {

    return when (quantity == 0) {
        true -> this.getString(resZeroId)
        false -> this.getQuantityString(resId, quantity, quantity)
    }
}
