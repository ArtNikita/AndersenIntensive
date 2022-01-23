package ru.nikitaartamonov.andersenintensive.pages.fifth_homework

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Contact(
    var number: String,
    var name: String,
    var surname: String,
    val id: String = UUID.randomUUID().toString()
) : Parcelable