package com.example.zhsimulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        var button_Cube : Button = findViewById<Button>(R.id.button_Cube)
        var button_Exit : Button = findViewById<Button>(R.id.button_Exit)

        button_Cube.setOnClickListener {
            var intent = Intent(applicationContext, CubeMain::class.java)
            startActivity(intent)
        }

        button_Exit.setOnClickListener {
            moveTaskToBack(true)    // 태스크를 백그라운드로 이동
            finishAndRemoveTask()			// 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)    // 태스크를 백그라운드로 이동
        finishAndRemoveTask()			// 액티비티 종료 + 태스크 리스트에서 지우기
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}