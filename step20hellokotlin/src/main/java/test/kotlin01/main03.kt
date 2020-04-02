package test.kotlin01

class Friend{
    //property
    var num:Int=0
    var name:String?=null
        set(value) {    //name의 setter역할
            field=value+"님"
        }
    var phone:String?=null
        get(){
            return field?:"전화번호 없음"//   ?:    엘비스 연산자는 널값을 허용하지 않는 변수에 널 값이 들어 갔을때 널 값을 변환할 수 있는 함수
        }

    fun showInfo(){
        println("num:${num} name:${name} phone:${phone}")
    }
}

fun main() {
    val f1=Friend()
    f1.name="김구라"
    f1.showInfo()
}