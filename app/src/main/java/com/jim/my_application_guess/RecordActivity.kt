package com.jim.my_application_guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rocord.*


class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rocord)
        val count:Int=intent.getIntExtra("COUNTER",-1)//沒有讀到任何值就顯示-1
        counter.setText(count.toString())
//======================================================
        //onClickListener
        save.setOnClickListener { view ->
            val nick : String=nickname.text.toString()
            Toast.makeText(this,"saved",Toast.LENGTH_LONG).show() //暫時訊息
            getSharedPreferences("guess", Context.MODE_PRIVATE)//定義檔案名稱,COUNTER=只有這個APP可以讀取它
                .edit()//得到編輯器(有編輯器材可以寫入資料)
                .putInt("REC_COUNTER",count)//儲存名稱,寫入資料(數字)
                .putString("REC_NICKNAME",nick)//儲存名稱,寫入資料(文字)
                .apply()//apply不會很確定在這一行寫進去，apply運用一些技術等有空才寫入；如果在一行就要讀取它就要用commit
//======================================================
            val intent = Intent()
            intent.putExtra("NICK",nick)
            setResult(Activity.RESULT_OK,intent)
            finish()//按下SAVE會回到前一頁
        }
    }
}
