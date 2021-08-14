package com.jim.my_application_guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val secretNumber=SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","secret:"+secretNumber.secret)
        //setContentView(R.layout.linear_main)//執行linearlayout
        /** 練習
        R.id.hello_main     //呼叫元件
        R.drawable.eggplant //呼叫png
        R.string.ok         //呼叫字串
        R.color.messageColor//呼叫顏色
        **/
    }
    fun check(view:View){
        val n:Int=number.text.toString().toInt()
        println("number：$n")
        Log.d("MainActivity","number:"+n)
        val diff : Int = secretNumber.validate(n)
        var message = "Yes,you got it"
        if (diff < 0) {
            message = "Bigger"
        } else if (diff > 0){
            message = "Smaller"
        }
        Toast.makeText(this,message,Toast.LENGTH_LONG).show() //暫時訊息
//        AlertDialog.Builder(this) //對話方塊
//            .setTitle("Message")
//            .setMessage(message)
//            .setPositiveButton("OK",null)
//            .show()
    }
}