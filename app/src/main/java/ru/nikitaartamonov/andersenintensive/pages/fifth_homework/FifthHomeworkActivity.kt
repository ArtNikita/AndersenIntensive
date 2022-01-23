package ru.nikitaartamonov.andersenintensive.pages.fifth_homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import ru.nikitaartamonov.andersenintensive.R

private const val CONTACTS_KEY = "CONTACTS_KEY"

class FifthHomeworkActivity : AppCompatActivity(), OnContactClickListener {

    private lateinit var contacts: List<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth_homework)
        initContactsList(savedInstanceState)

        if (savedInstanceState == null) {
            startContactsListFragment()
        }
    }

    private fun startContactsListFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.contacts_list_fragment_container,
                ContactsListFragment.newInstance(contacts)
            )
            .commit()
    }

    private fun initContactsList(savedInstanceState: Bundle?) {
        val defaultContactsList = listOf(
            Contact("+7-911-911-91-91", "Name 1", "Surname 1"),
            Contact("+7-922-922-92-92", "Name 2", "Surname 2"),
            Contact("+7-933-933-93-93", "Name 3", "Surname 3"),
            Contact("+7-944-944-94-94", "Name 4", "Surname 4")
        )
        contacts =
            savedInstanceState?.getParcelableArrayList<Contact>(CONTACTS_KEY)?.toMutableList()
                ?: defaultContactsList
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(CONTACTS_KEY, ArrayList(contacts))
    }

    override fun onClick(contact: Contact) {
        supportFragmentManager
            .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.contact_details_fragment_container,
                ContactDetailsFragment.newInstance(contact)
            )
            .addToBackStack(null)
            .commit()
    }
}