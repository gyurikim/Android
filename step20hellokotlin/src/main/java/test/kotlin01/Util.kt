package test.kotlin01

class Util {
    //companion object (동반 객체)
    companion object{
        val version="1.0"
        @JvmField
        val author="김구라"
        fun download(){
            println("다운로드해요!")
        }
        @JvmStatic
        fun upload(){
            println("업로드해요!")
        }
    }
}