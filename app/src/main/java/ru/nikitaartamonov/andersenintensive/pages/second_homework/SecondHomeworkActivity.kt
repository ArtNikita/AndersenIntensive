package ru.nikitaartamonov.andersenintensive.pages.second_homework

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.nikitaartamonov.andersenintensive.R
import ru.nikitaartamonov.andersenintensive.databinding.ActivitySecondHomeworkBinding

private const val TAG = "SecondHomeworkActivity@@@"
private const val COUNTER_KEY = "COUNTER_KEY"
private const val COUNTER_INIT_VALUE = "0"

class SecondHomeworkActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondHomeworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called")
        super.onCreate(savedInstanceState)
        binding = ActivitySecondHomeworkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews(savedInstanceState)
    }

    private fun initViews(savedInstanceState: Bundle?) {
        initCounterTextView(savedInstanceState)
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        binding.plusButton.setOnClickListener{
            binding.counterTextView.apply {
                text = (text.toString().toInt() + 1).toString()
                changeZeroButtonColorIfNeeded()
            }
        }
        binding.minusButton.setOnClickListener{
            binding.counterTextView.apply {
                text = (text.toString().toInt() - 1).toString()
                changeZeroButtonColorIfNeeded()
            }
        }
        binding.zeroButton.setOnClickListener{
            binding.counterTextView.text = COUNTER_INIT_VALUE
            changeZeroButtonColorIfNeeded()
        }
        binding.toastButton.setOnClickListener {
            Toast.makeText(this, binding.counterTextView.text, Toast.LENGTH_SHORT).show()
        }
    }

    private fun changeZeroButtonColorIfNeeded() {
        if (binding.counterTextView.text != COUNTER_INIT_VALUE){
            binding.zeroButton.setBackgroundColor(Color.GREEN)
        } else {
            binding.zeroButton.setBackgroundColor(Color.GRAY)
        }
    }

    private fun initCounterTextView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) binding.counterTextView.text = COUNTER_INIT_VALUE
        savedInstanceState?.let { bundle ->
            binding.counterTextView.text = bundle.getString(COUNTER_KEY, COUNTER_INIT_VALUE)
        }
        changeZeroButtonColorIfNeeded()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(COUNTER_KEY, binding.counterTextView.text.toString())
    }

    override fun onStart() {
        Log.d(TAG, "onStart() called")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume() called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause() called")
        super.onPause()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart() called")
        super.onRestart()
    }

    override fun onStop() {
        Log.d(TAG, "onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy() called")
        super.onDestroy()
    }
}