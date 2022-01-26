package ru.nikitaartamonov.andersenintensive.pages.sixth_homework.contacts_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import io.github.serpro69.kfaker.faker
import ru.nikitaartamonov.andersenintensive.pages.fifth_homework.Contact
import ru.nikitaartamonov.andersenintensive.pages.sixth_homework.ContactsDiffUtil
import ru.nikitaartamonov.andersenintensive.pages.sixth_homework.Event
import java.util.*
import kotlin.collections.ArrayList

private const val MOCK_CONTACTS_LIST_SIZE = 10

class ContactsListViewModel : ViewModel() {

    private val faker = faker { }

    private val contactsList = generateContactsList()

    val setContactsLiveData: LiveData<List<Contact>> = MutableLiveData()
    val openContactDetailLiveData: LiveData<Event<Contact>> = MutableLiveData()
    val dispatchDiffUtilLiveData: LiveData<Event<DiffUtil.DiffResult>> = MutableLiveData()

    fun onViewIsReady() {
        setContactsLiveData.postValue(contactsList)
    }

    private fun generateContactsList(): ArrayList<Contact> {
        val contacts = ArrayList<Contact>(MOCK_CONTACTS_LIST_SIZE)
        for (i in 0 until MOCK_CONTACTS_LIST_SIZE) {
            val id = UUID.randomUUID().toString()
            contacts += Contact(
                faker.phoneNumber.phoneNumber(),
                faker.name.firstName(),
                faker.name.lastName(),
                "https://picsum.photos/200/200/?temp=$id",
                id
            )
        }
        return contacts
    }

    fun onContactClick(contact: Contact) {
        openContactDetailLiveData.postValue(Event(contact))
    }

    fun onContactLongClick(contact: Contact) {
        val oldContacts = ArrayList(contactsList)
        contactsList -= contact
        updateListWithDiffUtil(oldContacts, contactsList)
    }

    private fun updateListWithDiffUtil(
        oldContacts: List<Contact>,
        newContacts: List<Contact>
    ) {
        val diffUtil = ContactsDiffUtil(oldContacts, newContacts)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        dispatchDiffUtilLiveData.postValue(Event(diffResult))
    }
}

private fun <T> LiveData<T>.postValue(value: T) {
    (this as MutableLiveData<T>).postValue(value)
}