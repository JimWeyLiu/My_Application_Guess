package com.jim.my_application_guess

import java.util.*

class SecretNumber {
    val secret=Random().nextInt(10)+1//val常數
    var count=0//var變數
    fun validate(number:Int):Int{
        count++
        return number-secret
    }
}

fun main(){
    val secretNumber=SecretNumber()
    println(secretNumber.secret)
    //println(secretNumber.validate(2))
    println("${secretNumber.validate(2)},count:${secretNumber.count}")
}