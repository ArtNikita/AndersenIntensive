package ru.nikitaartamonov.andersenintensive.pages.third_homework

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.nikitaartamonov.andersenintensive.databinding.ActivityThirdHomeworkBinding

class ThirdHomeworkActivity : AppCompatActivity() {

    private val binding by lazy { ActivityThirdHomeworkBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.loadImageButton.setOnClickListener {
            hideKeyboard(this)
            binding.imageUrlEditText.clearFocus()
            loadImage()
        }
    }

    private fun loadImage() {
        Glide
            .with(this)
            .load(binding.imageUrlEditText.text.toString())
            .into(binding.loadedImageImageView)
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0);
    }
}