package ru.nikitaartamonov.andersenintensive.pages.fifth_homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.andersenintensive.R
import ru.nikitaartamonov.andersenintensive.databinding.ContactEntityBinding
import ru.nikitaartamonov.andersenintensive.databinding.FragmentContactsListBinding

private const val CONTACTS_KEY = "CONTACTS_KEY"

class ContactsListFragment : Fragment(R.layout.fragment_contacts_list) {

    private val binding by viewBinding(FragmentContactsListBinding::bind)
    private val contacts: List<Contact> by lazy {
        arguments?.getParcelableArrayList(CONTACTS_KEY)
            ?: throw IllegalStateException("Contacts list should be provided")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateContacts()
    }

    private fun inflateContacts() {
        for (contact in contacts) {
            val currentContact = LayoutInflater.from(requireContext())
                .inflate(R.layout.contact_entity, binding.contactsLinearLayout, false)
                .apply {
                    val binding: ContactEntityBinding = ContactEntityBinding.bind(this)
                    val fullName = "${contact.name} ${contact.surname}"
                    binding.contactFullName.text = fullName
                    binding.contactPhoneNumber.text = contact.number
                }
            binding.contactsLinearLayout.addView(currentContact)
        }
    }

    companion object {
        fun newInstance(contacts: List<Contact>): ContactsListFragment {
            val args = Bundle()
            args.putParcelableArrayList(CONTACTS_KEY, ArrayList(contacts))
            val fragment = ContactsListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}