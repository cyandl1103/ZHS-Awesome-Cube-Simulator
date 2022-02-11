package com.example.zhsimulator

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import android.widget.Toast


class CubeMain : AppCompatActivity() {
    var skin_Have = IntArray(75)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cube_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        var button_Reset : Button = findViewById<Button>(R.id.button_Reset)
        var button_Pull : Button = findViewById<Button>(R.id.button_Pull)
        var button_Back : Button = findViewById<Button>(R.id.button_Back)

        var text_Time : TextView = findViewById<TextView>(R.id.text_Time)
        var text_Gas : TextView = findViewById<TextView>(R.id.text_Gas)

        var time = Integer.parseInt(text_Time.text as String);
        var gas = Integer.parseInt(text_Gas.text as String);

        var skin = arrayOfNulls<ImageView>(75)

        for(i in 0 .. 74)
            skin_Have[i] = 0

        var skin_Id =
            arrayOf(R.id.c0, R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8, R.id.c9,
                    R.id.c10, R.id.c11, R.id.c12, R.id.c13, R.id.c14, R.id.c15, R.id.c16, R.id.c17, R.id.c18, R.id.c19,
                    R.id.c20, R.id.c21, R.id.c22, R.id.c23, R.id.c24, R.id.c25, R.id.c26, R.id.c27, R.id.c28, R.id.c29,
                    R.id.c30, R.id.c31, R.id.c32, R.id.c33, R.id.c34, R.id.c35, R.id.c36, R.id.c37, R.id.c38, R.id.c39,
                    R.id.c40, R.id.c41, R.id.c42, R.id.c43, R.id.c44, R.id.c45, R.id.c46, R.id.c47, R.id.c48, R.id.c49,
                    R.id.c50, R.id.c51, R.id.c52, R.id.c53, R.id.c54, R.id.c55, R.id.c56, R.id.c57, R.id.c58, R.id.c59,
                    R.id.c60, R.id.c61, R.id.c62, R.id.c63, R.id.c64, R.id.c65, R.id.c66, R.id.c67, R.id.c68, R.id.c69,
                    R.id.c70, R.id.c71, R.id.c72, R.id.c73, R.id.c74)


        for(i in skin_Id.indices){
            skin[i] = findViewById<ImageView>(skin_Id[i])
            skin[i]!!.setColorFilter(Color.parseColor("#ff878787"), PorterDuff.Mode.MULTIPLY)
        }


        button_Reset.setOnClickListener {
            text_Time.setText("0")
            text_Gas.setText("0")
            for(i in 0 .. 74) {
                skin_Have[i] = 0
                skin[i]!!.setColorFilter(Color.parseColor("#ff878787"), PorterDuff.Mode.MULTIPLY)
            }

        }

        button_Back.setOnClickListener {
            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        button_Pull.setOnClickListener {
            var available = false
            for(i in skin_Have.indices){
                if(skin_Have[i] == 0){
                    available = true
                    break
                }
            }

            if(available) {
                var intent = Intent(applicationContext, CubeResult::class.java)
                intent.putExtra("Skin_Have", skin_Have)
                startActivityForResult(intent, 0)
            }

            else{
                val myToast = Toast.makeText(this.applicationContext, "모든 스킨을 뽑았습니다. 초기화해주세요.", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            var text_Time : TextView = findViewById<TextView>(R.id.text_Time)
            var text_Gas : TextView = findViewById<TextView>(R.id.text_Gas)

            var time = Integer.parseInt(text_Time.text as String);
            var gas = Integer.parseInt(text_Gas.text as String);

            time += 1
            gas += 99

            text_Time.setText(Integer.toString(time))
            text_Gas.setText(Integer.toString(gas))

            var select = data!!.getIntExtra("Select",-1)

            var skin = arrayOfNulls<ImageView>(75)
            var skin_Id =
                arrayOf(R.id.c0, R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8, R.id.c9,
                    R.id.c10, R.id.c11, R.id.c12, R.id.c13, R.id.c14, R.id.c15, R.id.c16, R.id.c17, R.id.c18, R.id.c19,
                    R.id.c20, R.id.c21, R.id.c22, R.id.c23, R.id.c24, R.id.c25, R.id.c26, R.id.c27, R.id.c28, R.id.c29,
                    R.id.c30, R.id.c31, R.id.c32, R.id.c33, R.id.c34, R.id.c35, R.id.c36, R.id.c37, R.id.c38, R.id.c39,
                    R.id.c40, R.id.c41, R.id.c42, R.id.c43, R.id.c44, R.id.c45, R.id.c46, R.id.c47, R.id.c48, R.id.c49,
                    R.id.c50, R.id.c51, R.id.c52, R.id.c53, R.id.c54, R.id.c55, R.id.c56, R.id.c57, R.id.c58, R.id.c59,
                    R.id.c60, R.id.c61, R.id.c62, R.id.c63, R.id.c64, R.id.c65, R.id.c66, R.id.c67, R.id.c68, R.id.c69,
                    R.id.c70, R.id.c71, R.id.c72, R.id.c73, R.id.c74)

            for(i in skin_Id.indices){
                skin[i] = findViewById<ImageView>(skin_Id[i])
            }

            skin_Have[select] = 1
            skin[select]!!.setColorFilter(null)

        }

        else{
            return
        }
    }

    override fun onBackPressed() {

    }
}