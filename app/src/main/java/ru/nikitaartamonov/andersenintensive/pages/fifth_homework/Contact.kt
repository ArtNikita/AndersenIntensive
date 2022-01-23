package ru.nikitaartamonov.andersenintensive.pages.fifth_homework

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    var number: String,
    var name: String,
    var surname: String
) : Parcelable