package ru.nikitaartamonov.andersenintensive.pages.fifth_homework

import android.content.Context
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
    private lateinit var contract: ContactDetailContract

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val launchActivity = requireActivity()
        if (launchActivity is ContactDetailContract) {
            contract = launchActivity
        } else {
            throw IllegalStateException("Launch activity should implement ContactDetailContract")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillData()
        initSaveButton()
    }

    private fun initSaveButton() {
        binding.saveContactButton.setOnClickListener {
            val modifiedContact = currentContact.copy(
                number = binding.contactPhoneNumberEditText.text.toString(),
                name = binding.contactNameEditText.text.toString(),
                surname = binding.contactSurnameEditText.text.toString()
            )
            contract.saveContact(
                currentContact,
                modifiedContact
            )
        }
    }

    private fun fillData() {
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