package com.example.zhsimulator

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import java.util.*

class CubeResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cube_result)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        var button_A : Button = findViewById<Button>(R.id.button_A)
        var button_B : Button = findViewById<Button>(R.id.button_B)
        var button_C : Button = findViewById<Button>(R.id.button_C)

        var skin_A : ImageView = findViewById<ImageView>(R.id.skin_A)
        var skin_B : ImageView = findViewById<ImageView>(R.id.skin_B)
        var skin_C : ImageView = findViewById<ImageView>(R.id.skin_C)


        var skin_Id =
            arrayOf(R.drawable.c0, R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9,
                R.drawable.c10, R.drawable.c11, R.drawable.c12, R.drawable.c13, R.drawable.c14, R.drawable.c15, R.drawable.c16, R.drawable.c17, R.drawable.c18, R.drawable.c19,
                R.drawable.c20, R.drawable.c21, R.drawable.c22, R.drawable.c23, R.drawable.c24, R.drawable.c25, R.drawable.c26, R.drawable.c27, R.drawable.c28, R.drawable.c29,
                R.drawable.c30, R.drawable.c31, R.drawable.c32, R.drawable.c33, R.drawable.c34, R.drawable.c35, R.drawable.c36, R.drawable.c37, R.drawable.c38, R.drawable.c39,
                R.drawable.c40, R.drawable.c41, R.drawable.c42, R.drawable.c43, R.drawable.c44, R.drawable.c45, R.drawable.c46, R.drawable.c47, R.drawable.c48, R.drawable.c49,
                R.drawable.c50, R.drawable.c51, R.drawable.c52, R.drawable.c53, R.drawable.c54, R.drawable.c55, R.drawable.c56, R.drawable.c57, R.drawable.c58, R.drawable.c59,
                R.drawable.c60, R.drawable.c61, R.drawable.c62, R.drawable.c63, R.drawable.c64, R.drawable.c65, R.drawable.c66, R.drawable.c67, R.drawable.c68, R.drawable.c69,
                R.drawable.c70, R.drawable.c71, R.drawable.c72, R.drawable.c73, R.drawable.c74)

        var select : Int = -1
        var option = IntArray(3)
        var count_Zero_Normal = 0
        var count_Zero_Rare = 0

        var in_Intent = intent
        var skin_Have = in_Intent.getIntArrayExtra("Skin_Have")

        for(i in skin_Have!!.indices){
            if(skin_Have[i] == 0 && i < 51)
                count_Zero_Rare++
            if(skin_Have[i] == 0 && i >= 51)
                count_Zero_Normal++
        }


        // 67% 일반, 33% 레어/ 큐브 전용 ... 레어도 뽑기
        val random = Random()
        var rare = IntArray(3)

        if(count_Zero_Normal > 0 && count_Zero_Rare > 0) { //일반, 레어 모두 3개 이상 남았을 때
            rare[0] = random.nextInt(100) // 0 ~ 99
            rare[1] = random.nextInt(100)
            rare[2] = random.nextInt(100)
        }

        else if(count_Zero_Rare == 0 && count_Zero_Normal >0){
            rare[0] = random.nextInt(67) + 33
            rare[1] = random.nextInt(67) + 33
            rare[2] = random.nextInt(67) + 33
        }

        else if(count_Zero_Normal == 0 && count_Zero_Rare > 0){
            rare[0] = random.nextInt(33)
            rare[1] = random.nextInt(33)
            rare[2] = random.nextInt(33)
        }

        if (skin_Have != null) {
            if(count_Zero_Normal >=3 && count_Zero_Rare >= 3) { //중복이 필요 없을 때
                for (i in rare.indices) {
                    if (rare[i] < 33) { //레어, 큐브 전용
                        when (i) {
                            0 -> {
                                option[i] = random.nextInt(51)

                                while (skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(51)

                                option[1] = option[0]
                                option[2] = option[0]
                            }
                            1 -> {
                                while (option[1] == option[0] || skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(51)
                            }
                            else -> {
                                while (option[2] == option[0] || option[2] == option[1] || skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(51)
                            }
                        }
                    } else { // 일반
                        when (i) {
                            0 -> {
                                option[i] = random.nextInt(24) + 51

                                while (skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(51)

                                option[1] = option[0]
                                option[2] = option[0]
                            }
                            1 -> {
                                while (option[1] == option[0] || skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(24) + 51
                            }
                            else -> {
                                while (option[2] == option[0] || option[2] == option[1] || skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(24) + 51
                            }
                        }
                    }
                }
            }

            if(count_Zero_Rare < 3 && count_Zero_Normal >= 3 ){
                for (i in rare.indices) {
                    if (rare[i] < 33) { // 레어, 큐브 전용
                        option[i] = random.nextInt(24) + 51
                        while (skin_Have[option[i]] == 1)
                            option[i] = random.nextInt(51)
                    }
                    else { // 일반
                        when (i) {
                            0 -> {
                                option[i] = random.nextInt(24) + 51

                                while (skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(51)

                                option[1] = option[0]
                                option[2] = option[0]
                            }
                            1 -> {
                                while (option[1] == option[0] || skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(24) + 51
                            }
                            else -> {
                                while (option[2] == option[0] || option[2] == option[1] || skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(24) + 51
                            }
                        }
                    }
                }
            }

            if(count_Zero_Normal < 3 && count_Zero_Rare >=3){
                for (i in rare.indices) {
                    if (rare[i] < 33) { // 레어, 큐브 전용
                        when (i) {
                            0 -> {
                                option[i] = random.nextInt(51)

                                while (skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(51)

                                option[1] = option[0]
                                option[2] = option[0]
                            }
                            1 -> {
                                while (option[1] == option[0] || skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(51)
                            }
                            else -> {
                                while (option[2] == option[0] || option[2] == option[1] || skin_Have[option[i]] == 1)
                                    option[i] = random.nextInt(51)
                            }
                        }
                    }
                    else { // 일반
                        option[i] = random.nextInt(24) + 51
                        while (skin_Have[option[i]] == 1)
                            option[i] = random.nextInt(24) + 51
                    }
                }
            }

            if(count_Zero_Normal < 3 && count_Zero_Rare < 3){
                for (i in rare.indices) {
                    if (rare[i] < 33) { // 레어, 큐브 전용
                        while (skin_Have[option[i]] == 1)
                            option[i] = random.nextInt(51)
                    } else { // 일반
                        while (skin_Have[option[i]] == 1)
                            option[i] = random.nextInt(24) + 51
                    }
                }
            }
        }


        skin_A.setImageResource(skin_Id[option[0]])
        skin_B.setImageResource(skin_Id[option[1]])
        skin_C.setImageResource(skin_Id[option[2]])


        button_A.setOnClickListener {
            var intent = intent
            select = option[0]
            intent.putExtra("Select", select)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        button_B.setOnClickListener {
            var intent = intent
            select = option[1]
            intent.putExtra("Select", select)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        button_C.setOnClickListener {
            var intent = intent
            select = option[2]
            intent.putExtra("Select", select)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onBackPressed() {
        val myToast = Toast.makeText(this.applicationContext, "스킨을 선택해주세요", Toast.LENGTH_SHORT)
        myToast.show()
    }
}
