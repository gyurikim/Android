package test.kotlin01

fun main() {
    //key : int type, value : String type
    var map1= mutableMapOf<Int,String>()
    for(num in 0..255){
        //num에 해당하는 이진수의 문자
        var bin =Integer.toBinaryString(num)
        //map에 저장하기
        map1.put(num,bin)
    }
    println(map1.get(100))

    for( (key, value) in map1){
        println("정수 ${key} 는 이진수 ${value} 입니다.")
    }
}