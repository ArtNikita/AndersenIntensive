package ru.nikitaartamonov.andersenintensive.pages.sixth_homework.contact_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.nikitaartamonov.andersenintensive.R
import ru.nikitaartamonov.andersenintensive.databinding.FragmentContactDetailsBinding
import ru.nikitaartamonov.andersenintensive.pages.fifth_homework.Contact

private const val CONTACT_KEY = "CONTACT_KEY"

class ContactDetailsFragment : Fragment(R.layout.fragment_contact_details) {

    private val binding by viewBinding(FragmentContactDetailsBinding::bind)
    private val currentContact: Contact by lazy {
        arguments?.getParcelable<Contact>(CONTACT_KEY)
            ?: throw IllegalStateException("Contact should be provided")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillData()
    }

    private fun fillData() {
        binding.contactNameEditText.setText(currentContact.name)
        binding.contactSurnameEditText.setText(currentContact.surname)
        binding.contactPhoneNumberEditText.setText(currentContact.number)
        Glide
            .with(requireContext())
            .load(currentContact.imageUrl)
            .circleCrop()
            .into(binding.avatarImageView)
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