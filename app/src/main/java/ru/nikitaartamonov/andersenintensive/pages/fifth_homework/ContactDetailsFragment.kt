package ru.nikitaartamonov.andersenintensive.pages.fifth_homework

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.andersenintensive.R
import ru.nikitaartamonov.andersenintensive.databinding.FragmentContactDetailsBinding

private const val CONTACT_KEY = "CONTACT_KEY"

class ContactDetailsFragment : Fragment(R.layout.fragment_contact_details) {

    private val binding by viewBinding(FragmentContactDetailsBinding::bind)
    private val currentContact: Contact by lazy {
        arguments?.getParcelable<Contact>(CONTACT_KEY)
            ?: throw IllegalStateException("Contact should be provided")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contactNameEditText.setText(currentContact.name)
        binding.contactSurnameEditText.setText(currentContact.surname)
        binding.contactPhoneNumberEditText.setText(currentContact.number)
    }

    companion object {
        fun newInstance(contact: Contact): ContactDetailsFragment {
            val args = Bundle()
            args.putParcelable(CONTACT_KEY, contact)
            val fragment = ContactDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}