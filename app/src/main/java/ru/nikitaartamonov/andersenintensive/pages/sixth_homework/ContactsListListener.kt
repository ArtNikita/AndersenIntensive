package ru.nikitaartamonov.andersenintensive.pages.sixth_homework

import ru.nikitaartamonov.andersenintensive.pages.fifth_homework.Contact

interface ContactsListListener {
    fun openContactDetails(contact: Contact)
}