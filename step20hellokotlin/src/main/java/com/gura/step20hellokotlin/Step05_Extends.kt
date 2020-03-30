package com.gura.step20hellokotlin

import android.provider.ContactsContract

//kotlin에서는 defaule로 final클래스 이다 ***
//상속을 받을수 있게 하려면 open이라는 예약어를 붙여주어야한다
open class Phone{
    fun call(){
        println("전화를 걸어요")
    }
}

//Phone 클래스를 상속받기
open class HandPhone : Phone() {// 생성자가 있으면 호출하라는 소괄호 표현을 써주어야한다
    fun mobilCall(){
        println("이동중에 전화를 걸어요")
    }
    //함수도 default로 final이다
    //override를 가능하게 하려면 open이라는 예약어가 필요하다
    open fun takePicture(){
        println("30만화소의 사진을 찍어요")
    }
}

class SmartPhone : HandPhone(){
   fun doInternet(){
       println("인터넷을 해요")
   }
    //open된 함수를 override할 수 있다
    override fun takePicture() {
        //super.takePicture() 부모메소드 호출
        println("1000만 화소의 사진을 찍어요")
    }
}

fun main(){
    var p1=Phone()
    var p2=HandPhone()
    var p3=SmartPhone()

    p1.call()
    println("-------")
    p2.call()
    p2.mobilCall()
    p2.takePicture()
    println("-------")
    p3.call()
    p3.mobilCall()
    p3.doInternet()
    p3.takePicture()
}