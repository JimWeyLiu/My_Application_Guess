package com.jim.my_application_guess

import java.util.*

class SecretNumber {
    var secret=Random().nextInt(10)+1//val常數
    var count=0//var變數
    fun validate(number:Int):Int{
        count++
        return number-secret
    }

    fun reset() {
        secret=Random().nextInt(10)+1
        count=0
    }
}

fun main(){
    val secretNumber=SecretNumber()
    println(secretNumber.secret)
    //println(secretNumber.validate(2))
    println("${secretNumber.validate(2)},count:${secretNumber.count}")
}