package ru.nikitaartamonov.andersenintensive.pages.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.andersenintensive.R
import ru.nikitaartamonov.andersenintensive.databinding.ActivityMainBinding
import ru.nikitaartamonov.andersenintensive.pages.second_homework.SecondHomeworkActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.secondHomeworkMenuTextView.setOnClickListener {
            startActivity(Intent(this, SecondHomeworkActivity::class.java))
        }
    }
}