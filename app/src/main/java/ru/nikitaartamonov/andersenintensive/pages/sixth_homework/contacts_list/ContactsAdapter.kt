package ru.nikitaartamonov.andersenintensive.pages.sixth_homework.contacts_list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.andersenintensive.pages.fifth_homework.Contact

class ContactsAdapter : RecyclerView.Adapter<ContactViewHolder>() {

    var contactsList = emptyList<Contact>()
    lateinit var listener: OnContactClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactsList[position])
    }

    override fun getItemCount() = contactsList.size
}