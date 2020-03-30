package com.gura.step20hellokotlin
// MemberDto 클래스 정의하기
//val로 선언하면 setter()메소드를 사용할수없다
data class MemberDto(var num:Int,var name:String,var addr:String)

fun foo(num:Int=0,name:String="이름"){    //전달된 값이없으면 디폴트 값을 넣어주기로 지정해놓기
    println("num : $num name : $name ")
}

fun main() {
    var mem1=MemberDto(1,"김구라","노량진")
    //내부적으로 getter()메소드가 사용됨
    var num=mem1.num
    var name=mem1.name
    var addr=mem1.addr
    println(mem1)

   //내부적으로 setter()메소드가 사용됨
    mem1.num=2
    mem1.name="이정호"
    mem1.addr="독산동"
    println(mem1)

    foo(1,"김구라")
    foo()
    foo(2)
    foo(name="해골")
    foo(name="원숭이",num=3)

    //숫자배열
    var nums= listOf(-20,-10,0,10,20)
    //item 중에서 0보다 큰값을 추출
    var result = nums.filter { it >= 0 }
    println("result : $result")

    //MemberDto객체를 Any type변수에 담기
    var whoami:Any=mem1
    when(whoami){
        is String -> println("String type")
        is MemberDto -> println("MemberDto type")
        else -> println("뭔지 모르는 type")
    }
    //변경불가(immutable)한 Map > 사용이유 : 반응속도가 빠르다
    var info= mapOf("num" to 1,"name" to "김구라","isMan" to true)
    //
    var myNum:Int=info["num"] as Int//as연산자를 이용해서 캐스팅
    var myName:String=info["name"] as String
    var myIsMan:Boolean=info["isMan"] as Boolean
}