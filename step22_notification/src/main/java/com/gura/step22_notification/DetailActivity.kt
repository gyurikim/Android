package com.gura.step22_notification

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Intent 객체에 "msg"라는 키값으로 담긴 문자열 읽어오기
        val msg=intent.getStringExtra("msg")
        //textview 에 출력하기
        textView.setText(msg)

        //intent객체에 "id"라는키값으로 전달괸 값이있는지 읽어와본다
        val id=intent.getIntExtra("id",-1)
        if(id != -1){//알림을 수동으로 취소해야하는경우
            //알림매니저 객체의 참조값을 얻 어와서
            val manager=getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
            //알림의 id 값을 전달해서 취소시킨다
//            manager.cancel(id)
            //모든알람취소
            manager.cancelAll()
        }
    }
}
