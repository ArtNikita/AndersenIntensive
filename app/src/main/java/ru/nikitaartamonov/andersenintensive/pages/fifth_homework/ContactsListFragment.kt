package ru.nikitaartamonov.andersenintensive.pages.fifth_homework

import android.content.Context
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
    private lateinit var listener: OnContactClickListener

    fun updateContact(modifiedContact: Contact, index: Int){
        contacts[index].name = modifiedContact.name
        contacts[index].surname = modifiedContact.surname
        contacts[index].number = modifiedContact.number
        binding.contactsLinearLayout.removeAllViews()
        inflateContacts()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val launchActivity = requireActivity()
        if (launchActivity is OnContactClickListener) {
            listener = launchActivity
        } else {
            throw IllegalStateException("Launch activity should implement OnContactClickListener")
        }
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
                    binding.contactEntityCardView.setOnClickListener { listener.onClick(contact) }
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