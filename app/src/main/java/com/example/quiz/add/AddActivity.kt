package com.example.quiz.add

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quiz.R
import com.example.quiz.data.Quiz
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var addViewModel: AddViewModel

    val EXTRA_STATE_NAME = "extra_state_name_to_be_updated"
    val EXTRA_CAPITAL_NAME = "extra_capital_name_to_be_updated"
    val EXTRA_STATE_ID = "extra_state_id_to_be_updated"
    val UPDATE_STATE_REQUEST_CODE = 1
    val NEW_STATE_REQUEST_CODE = 2

    private lateinit var stateName: String
    private lateinit var capitalName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val stateET = findViewById<EditText>(R.id.state)
        val capitalET = findViewById<EditText>(R.id.capital)
        val button = findViewById<Button>(R.id.button)

        addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        val extras: Bundle? = intent.extras
        if (extras != null) {
            stateName = extras.getString(EXTRA_STATE_NAME, "")
            capitalName = extras.getString(EXTRA_CAPITAL_NAME, "")

            if (stateName.isNotEmpty()) {
                stateET.setText(stateName)
            }
            if (capitalName.isNotEmpty()) {
                capitalET.setText(capitalName)
            }
            button.setText(getString(R.string.update))
        }
        button.setOnClickListener {
            val newStateName = stateET.text.toString()
            val newCapitalName = capitalET.text.toString()


            if (extras != null && extras.containsKey(EXTRA_STATE_ID)) {
                val quizID = extras.getLong(EXTRA_STATE_ID, 0L)
                val quiz = Quiz(newStateName, newCapitalName, quizID)
                addViewModel.updateState(quiz)
                finish()
            } else {
                if (newStateName.isEmpty() or newCapitalName.isEmpty()) {
                    Toast.makeText(this, "Enter a state and a capital", Toast.LENGTH_SHORT).show()
                } else {
                    val quiz = Quiz(newStateName, newCapitalName)
                    addViewModel.insertState(quiz)
                    finish()
                }
            }
        }
    }
}