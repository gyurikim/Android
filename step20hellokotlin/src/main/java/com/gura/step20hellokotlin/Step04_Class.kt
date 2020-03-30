package com.gura.step20hellokotlin

//클래스 정의하기
class MyClass

//클래스 정의하기
class YourClass{
    //멤버함수
    fun printInfo(){
        println("YourClass의 메소드(함수) 호출됨")
    }
}

//대표(primary) 생성자는 클래스명 우측에 선언한고 생략 가능하다
class OurClass1 constructor(){

}
class OurClass2(){//constructor 예약어 생략가능

}
class OurClass3{//인자로 전달받을게 없으면 () 생략가능

}

//생성자의 인자로 문자열을 전달 받는 생성자
class Car(name:String){
    //String type을 담을 수 있는 필드 선언
    var name:String
    //초기화 블럭(객체가 생성되는 시점에 무언가 작업할 수 있는 블럭)
    init {
        println("Car 클래스 init")
        //생성자의 인자로 전달받은 문자열을 필드에 저장
        this.name=name//코틀린에서는 변수를 초기화 시켜주지않으면 오류가 발생한다
    }

    //함수
    fun drive(){
        println("$name 이(가) 달려요~")
    }
}

class YourCar(var name:String){//생성자의 인자를 선언할때 var을 붙여 선언하면 필드와 init안에서 하는 작업을 알아서해준다
    //멤버함수 만들기
    fun drive(){
    println("$name 이(가) 달려요~")
    }
}

class OurCar(){//primary  생성자
    //property 정의
    var name:String?=null
    //두번쩨 생성자 정의하기
    constructor(name:String):this(){//primary 생성자를 호출해야 한다.
        //property에 값 대입하기
        this.name=name
    }
    fun drive(){
        println("$name 이(가) 달려요")
    }
}

fun main(){
    //MyClass로 객체 생성해서 참조값을 my라는 변수에 담기
    var my=MyClass()    //new라는 예약어를 사용하지 않는다

    //YourClass로 객체생성해서 참조값을  your라는 변수에 담기
    var your=YourClass()
    //printInfo()함수 호출하기
    your.printInfo()

    var c1=Car("소나타")
    var info1=c1.name;// 필드값(property) 참조
    c1.drive()//함수호출


    var c2=YourCar("아반떼")
    var info2=c1.name//getter() 역할
    c2.name="프라이드"//settet() 역할 변수를 val로 선언하면 사용할수가 없다
    println("Info : $info2")
    c2.drive()

    var c3=OurCar()
    var c4=OurCar("그랜져")
    c3.drive()
    c4.drive()

}