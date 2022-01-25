package ru.nikitaartamonov.andersenintensive.pages.sixth_homework.contacts_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nikitaartamonov.andersenintensive.databinding.ContactEntityBinding
import ru.nikitaartamonov.andersenintensive.pages.fifth_homework.Contact

class ContactViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    ContactEntityBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = ContactEntityBinding.bind(itemView)

    fun bind(contact: Contact) {
        binding.contactPhoneNumber.text = contact.number
        val fullName = "${contact.name} ${contact.surname}"
        binding.contactFullName.text = fullName
        Glide
            .with(itemView.context)
            .load(contact.imageUrl)
            .circleCrop()
            .into(binding.contactAvatarImageView)
    }
}