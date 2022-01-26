package ru.nikitaartamonov.andersenintensive.pages.sixth_homework

import ru.nikitaartamonov.andersenintensive.pages.fifth_homework.Contact

interface ContactDetailsContract {
    fun saveContact(oldContact: Contact, newContact: Contact)
}