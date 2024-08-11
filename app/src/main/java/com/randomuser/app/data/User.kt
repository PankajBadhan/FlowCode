package com.randomuser.app.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: DOB,
    val phone: String,
    val cell: String,
    val picture: Picture,
): Parcelable

@Parcelize
data class Name(
    val title: String,
    val first: String,
    val last: String,
): Parcelable

@Parcelize
data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
): Parcelable

@Parcelize
data class Street(
    val number: Int,
    val name: String,
): Parcelable

@Parcelize
data class Login(
    val username: String,
): Parcelable

@Parcelize
data class DOB(
    val date: String,
    val age: Int,
): Parcelable

@Parcelize
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String,
): Parcelable