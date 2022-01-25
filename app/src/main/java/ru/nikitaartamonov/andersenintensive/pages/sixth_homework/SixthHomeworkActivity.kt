package ru.nikitaartamonov.andersenintensive.pages.sixth_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nikitaartamonov.andersenintensive.R
import ru.nikitaartamonov.andersenintensive.pages.sixth_homework.contacts_list.ContactsListFragment

class SixthHomeworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth_homework)

        if (savedInstanceState == null) {
            startContactsListFragment()
        }
    }

    private fun startContactsListFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.contacts_list_fragment_container,
                ContactsListFragment()
            )
            .commit()
    }
}