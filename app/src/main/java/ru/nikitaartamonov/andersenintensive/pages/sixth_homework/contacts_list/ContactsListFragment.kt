package ru.nikitaartamonov.andersenintensive.pages.sixth_homework.contacts_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.andersenintensive.R
import ru.nikitaartamonov.andersenintensive.databinding.FragmentContactsRecyclerListBinding

class ContactsListFragment : Fragment(R.layout.fragment_contacts_recycler_list) {

    private val binding by viewBinding(FragmentContactsRecyclerListBinding::bind)
    private val viewModel by viewModels<ContactsListViewModel>()
    private val adapter by lazy { ContactsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.contactsListRecyclerView.adapter = adapter
        binding.contactsListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}