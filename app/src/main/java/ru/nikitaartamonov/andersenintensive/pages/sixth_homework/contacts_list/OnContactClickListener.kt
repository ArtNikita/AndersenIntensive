package ru.nikitaartamonov.andersenintensive.pages.sixth_homework.contacts_list

import android.view.View
import ru.nikitaartamonov.andersenintensive.pages.fifth_homework.Contact

interface OnContactClickListener {
    fun onClick(contact: Contact)
    fun onLongClick(contact: Contact, anchorView: View)
}