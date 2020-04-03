package com.gura.step22_notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,View.OnClickListener{
    //현재 알림의 아이디
    var curentId=0
    //채널의 아이디를 겹치지않게 유일하게 구성하기
    val CHANNEL_ID  ="com.gura.step22notification.ALERT_CHANNEL"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //버튼에 리스너 등록하기
        notiBtn.setOnClickListener(this)
        /*
        notiBtn2.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
              //오버라이드할 게 하나뿐이라면 간략하게 {}만 작성해주어도 문법이 성립한다
         */
        notiBtn2.setOnClickListener({
            //입력한 문자열 읽어오기
            var msg=inputMsg.text.toString()
            makeManualCancelNoti(msg)
        })
    }

    override fun onClick(v: View?) {//버튼을 클릭하면 View type의 참조값이 v에 저장된다  View?: 널러블뷰타입/View:뷰타입
    var btn:Button= v as Button//Button type으로 캐스팅하기
        when(v?.id){// 널러블타입의 참조값을 사용할때에는 ?를 붙여주어야한다
            R.id.notiBtn -> {
                //입력한 문자열을 읽어와서
                var msg=inputMsg.text.toString();
                //알림에 띄운다
                makeAutoCancelNoti(msg)
            }
        }
    }
    //인자로 전달되는 문자열을 알림에 띄우는 함수
    fun makeManualCancelNoti(msg:String){
        //이 앱의 알림 채널 만들기
        //아이디값 증가시키기
        curentId++
        createNotificationChannel()
        //알림을 클릭했을때 실행할 Activity 정보를 가지고 있는 Intent 객체
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("msg",msg)
            putExtra("id",curentId)
        }
        //Intent 객체를 인텐트 전달자 객체에 담는다
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("오빠 나야~")
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)//자동취소되지 않도록

        curentId++//d알림의 아이디값 증가시키기 > 아이디값이 다르기때문에 알림창에 알림이 쌓인다
        with(NotificationManagerCompat.from(this)) {
            // 수동으로 취소하려면 아래에 전달된 아이디 값을 알아야한다
            notify(curentId, builder.build())//알림의 아이디
        }
    }
    //인자로 전달되는 문자열을 알림에 띄우는 함수
    fun makeAutoCancelNoti(msg:String){
        //이 앱의 알림 채널 만들기
        createNotificationChannel()
        //알림을 클릭했을때 실행할 Activity 정보를 가지고 있는 Intent 객체
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("msg",msg)
        }
        //Intent 객체를 인텐트 전달자 객체에 담는다
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("오빠 나야~")
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        curentId++//알림의 아이디값 증가시키기 > 아이디값이 다르기때문에 알림창에 알림이 쌓인다
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(curentId, builder.build())//알림의 아이디
        }
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "kim"
            val descriptionText = "test!"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
