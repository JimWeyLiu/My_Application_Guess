package com.jim.my_application_guess


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)//標示這一支程式要用什麼工具來執行，類別定義
class  MaterialActivityTest {
    @Rule //標示符號
    @JvmField //它代表的是這個類別，未來在Java裡面的屬性
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)//存取到MaterialActivity，鑽石<>符號是限定說我以後要針對什麼事情做一個Rule

    @Test // 標示為測試方法
    fun guessWrong() {
        val resources = activityTestRule.activity.resources //經常存取簡化用，在getString使用
        val secret = activityTestRule.activity.secretNumber.secret
//        val n = 5 //定義變數<-打字動作
        for(n in 1..10) {//測試1~10
            if (n != secret) {
//                perform進行動作使用ViewActions類別.動作
//                使用Espresso類別庫
                onView(withId(R.id.number))//元件id(需先定義元件id)
                    .perform(clearText())//清除字
                onView(withId(R.id.number))//元件id(需先定義元件id)
                    .perform(typeText(n.toString()))//打字動作
                onView(withId(R.id.button))//元件id(需先定義元件id)
                    .perform(click())//按一下
//===============================================================================
//                測試對話框
                val message =
                    if (n < secret) resources.getString(R.string.bigger)//測試文字
                    else resources.getString(R.string.smaller)//測試文字

                onView(withText(message))//訊息對話框
                    .check(matches(isDisplayed()))//驗證用，檢查有沒有出現在畫面上

                onView(withText(resources
                    .getString(R.string.ok))).perform(click())
//===============================================================================
//                撰寫方法：由單一測試到多重測試
            }
        }
    }
}