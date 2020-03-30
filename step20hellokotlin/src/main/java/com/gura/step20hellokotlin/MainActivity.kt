package com.gura.step20hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    /*
        자바랑 비슷하지만 약간의 차이가 있다
        코틀린이 복잡하긴 하지만 코딩이 짧아진다는 점에서 많이들 사용한다
        *** 변수를 작성할떄 # 변수명 : 데이터 타입 # ***
        데이터다입은 ㄷ참조데이터 타입을 사용한다. 자바에서 int > 코틀린 Int
     */
    override fun onCreate(savedInstanceState: Bundle?) {//Bundle? : ?를 붙여주면 null값도 허용된다는 의미이다(널포인트 이셉션 대비!)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
