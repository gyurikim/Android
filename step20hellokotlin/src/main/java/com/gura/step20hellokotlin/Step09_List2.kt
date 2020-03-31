package com.gura.step20hellokotlin

fun main() {
    //수정 가능한 정수배열
    var nums:MutableList<Int> = mutableListOf(10,20,30)
    //수정 가능한 문자배열
    var names:MutableList<String> = mutableListOf("kim","lee","park")

    //item추가
    nums.add(40)
    nums.add(50)
    names.add("jo")
    names.add("an")

    //item 인덱스에 의한 삭제
    nums.removeAt(2)
    names.removeAt(2)
    //item 값에 의한 삭제
    nums.remove(50)
    names.remove("kim") //boolean값으로 리턴된다


    //존재하지 않는 아이템 삭제(false 리턴)
    var result1=names.remove("aaa")
    //존재하지 않는 인덱스 삭제(이셉션 발생)
//    var result2=nums.removeAt(1000)

    //수정가능한 빈 배열 객체 생성
    var members:MutableList<Member> = mutableListOf()

    //member객체 생성해서 배열에 저장
    members.add(Member(1,"김구라"))
    members.add(Member(2,"해골"))
    members.add(Member(3,"원숭이"))

    //배열에 있는 아이템을 순서대로 참조해서 작업하기
    for(item in members){
        println("번호 : ${item.num} , 이름 : ${item.name}")
    }
}