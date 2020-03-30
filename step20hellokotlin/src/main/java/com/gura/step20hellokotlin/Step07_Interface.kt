package com.gura.step20hellokotlin

//인터페이스
interface Remocon{
    fun up()
    fun down()
}

//인터페이스를 구현한 클래스
class TvRemocon : Remocon{
    override fun up() {
        println("채널을 올려요")
    }

    override fun down() {
        println("채널을 내려려요")
    }
}

class MultiClass : Weapone(),Remocon{
    override fun attack() {
        println("아무거나 공격해요")
    }

    override fun up() {
        println("파워를 올려요")
    }

    override fun down() {
        println("파워를 내려요")
    }

}

fun main() {
    var r1 = TvRemocon()
    r1.up()
    r1.down()
    println("-------")
    var r2 = object : Remocon {
        override fun up() {
            println("볼륨을 올려요")
        }

        override fun down() {
            println("볼륨을 내려요")
        }
    }

    r2.up()
    r2.down()
    println("-------")
    //MultiClass 객체의 참조값을 다양한 type변수에 담기
    var m1:MultiClass= MultiClass()
    var m2:Weapone= MultiClass()
    var m3:Remocon=MultiClass()

    m1.attack()
    m1.up()
    m1.down()
    println("-------")
    m2.move()
    m2.attack()
    println("-------")
    m3.up()
    m3.down()
}