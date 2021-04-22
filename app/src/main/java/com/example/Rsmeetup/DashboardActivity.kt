package com.example.Rsmeetup
import android.R
import android.accessibilityservice.GestureDescription
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.MalformedURLException
import java.net.URL


class DashboardActivity : AppCompatActivity() {
    var secretCodeBox: EditText? = null
    var joinBtn: Button? = null
    var shareBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        secretCodeBox = findViewById(R.id.codeBox)
        joinBtn = findViewById(R.id.joinBtn)
        shareBtn = findViewById(R.id.shareBtn)
        val serverURL: URL
        try {
            serverURL = URL("https://meet.jit.si")
            val defaultOptions: JitsiMeetConferenceOptions = GestureDescription.Builder()
                    .setServerURL(serverURL)
                    .setWelcomePageEnabled(false)
                    .build()
            JitsiMeet.setDefaultConferenceOptions(defaultOptions)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        joinBtn.setOnClickListener(View.OnClickListener {
            val options: JitsiMeetConferenceOptions = GestureDescription.Builder()
                    .setRoom(secretCodeBox.getText().toString())
                    .setWelcomePageEnabled(false)
                    .build()
            JitsiMeetActivity.launch(this@DashboardActivity, options)
        })
    }
}