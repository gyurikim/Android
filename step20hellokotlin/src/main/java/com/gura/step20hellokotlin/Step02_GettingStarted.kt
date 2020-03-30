package com.gura.step20hellokotlin

//인자로 Int타입을 2개 전달받아서 Int타입을 리턴해주는 함수
fun sum(a:Int,b:Int):Int{
    //return이라는 예약어로 값을 리턴해준다
    return a+b;
}

fun sum2(c:Int,d:Double)=c+d;
fun describe(obj: Any): String =
        when (obj) {
            1          -> "One"
            "Hello"    -> "Greeting"
            is Long    -> "Long"
            !is String -> "Not a string"
            else       -> "Unknown"
        }
fun main() {
    println(sum(10, 20))
    println(sum2(10, 3.45))

    //read only 지역변수를 만들때 사용하는 예약어 val
    val a:Int=10
//    a=20//이미 값이 들어가있으면 다른값을 집어넣지 못한다 >자바에서 final역할

    val b=20;//type이 추론가능한 경우 type생략 가능

    val c:Int
    c=30//값을 나중에 집어 넣고 싶으면 타입을 무조건 지정해주어야한다

    var myNick="김구라"
    var myName="내 이름 is $myNick"
    myNick="개구리"

    var result="${myName.replace("is","was")} but now is $myNick"
    println(result)
    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))

}