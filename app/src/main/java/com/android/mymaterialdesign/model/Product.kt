package com.android.mymaterialdesign.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val imageUrl:String,
    val title:String,
    val description:String
) : Parcelable