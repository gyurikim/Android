package com.gura.step20hellokotlin

//추상클래스 > default값은 open이기 때문에 예약어를 붙여주지 않아도 된다
abstract class Weapone{
    fun move(){
        println("이동합니다.")
    }
    //추상함수(모양만 정의된함수)
    abstract fun attack()
}

//추상클래스를 상속받은 클래스
class MyWeapon:Weapone(){
    //추상함수 오버라이드
    override fun attack() {
        println("지상 공격을 해요")
    }

}

fun main(){
    var w1=MyWeapon()
    w1.move()
    w1.attack()
    //annonymous inner type
    var w2=object :Weapone(){
        override fun attack() {
            println("공중공격을 해요")
        }
    }
    w2.move()
    w2.attack()
}