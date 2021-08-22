package com.jim.my_application_guess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    val secretNumber=SecretNumber()
    val TAG : String = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")//設抬頭文字
                .setMessage("Ary you sure?")//設提示訊息
                .setPositiveButton(getString(R.string.ok), { dialog,which -> //開始執行
                    secretNumber.reset()//呼叫SecretNumber.reset方法
                    counter.setText(secretNumber.count.toString())//歸零
                    number.setText("")//消除文字方塊內的文字
                })
                .setNeutralButton("Cancel",null)
                .show()
        }
        counter.setText(secretNumber.count.toString())
        Log.d(TAG, "onCreate: ："+secretNumber.secret)
        val count :Int=getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getInt("REC_COUNTER",-1)//讀"guess"檔案裡的REC_COUNTER，如果讀不到就顯示-1
        val nick :String?=getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getString("REC_NICKNAME",null)//如果沒有就null值
        Log.d(TAG, "data: "+ count +"/" + nick)//顯示除錯的資訊
    }
    fun check(view: View){
        val n:Int=number.text.toString().toInt()
        println("number：$n")
        Log.d(TAG,"number:"+n)
        val diff : Int = secretNumber.validate(n)
        var message = getString(R.string.Yes_you_got_it)
        if (diff < 0) {
            message = getString(R.string.bigger)
        } else if (diff > 0){
            message = getString(R.string.smaller)
        }
        counter.setText(secretNumber.count.toString())
        AlertDialog.Builder(this) //對話方塊
            .setTitle(getString(R.string.dialot_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), { dialog,which ->
                if (diff == 0){
                    val intent = Intent(this, RecordActivity ::class.java)
                    intent.putExtra("COUNTER",secretNumber.count)//"COUNTER"代表標籤，裡頭要放count的資料
                    startActivity(intent)//調跳轉至RecordActivity
                }
            })
            .show()
    }
}