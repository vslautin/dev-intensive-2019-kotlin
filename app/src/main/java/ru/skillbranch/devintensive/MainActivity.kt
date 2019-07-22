package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var girlImage: ImageView
    lateinit var messageEdit: EditText
    lateinit var textView: TextView
    lateinit var sendButton: ImageView
    lateinit var benderObject: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity", "On create")
        girlImage = girl_image
        messageEdit = et_message
        textView = tv_text
        sendButton = send_button

        benderObject = Bender()
        textView.text = benderObject.askQuestion()
        sendButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.send_button) {
            val answer = messageEdit.text.toString().toLowerCase()
            val result = benderObject.listenAnswer(answer)
            Log.d("M_MainActivity","On click. answer=\"$answer\" result=\"$result\"")
            messageEdit.setText(result)
            val (r, g, b) = benderObject.checkStatus()
            girlImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            textView.text = benderObject.askQuestion()
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "On restart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "On start")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "On resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "On pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "On stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "On destroy")
    }


}
