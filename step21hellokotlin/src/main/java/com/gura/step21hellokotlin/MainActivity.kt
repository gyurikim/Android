package com.gura.step21hellokotlin

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener,AdapterView.OnItemLongClickListener { // AppCompatActivity, OnClickListener을 extends(상속) 받았다
    //필드선언
//    var inputMsg:EditText?=null //null로 property초기화하기 > 필드를 선언할때 값을 넣어주지않으면 오류가 발생한다
    lateinit var inputMsg:EditText// 키워드 : lateinit > 지금은 초기화하지 않을껀데 나중에 초기화 할꺼야
    lateinit var adapter: ArrayAdapter<String>
    lateinit var msgList: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {//savedInstanceState은 번들타입이고 null일 가능성이있다
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EditText 객체의 참조값
       inputMsg = findViewById<EditText>(R.id.inputMsg)//참조값을 얻어올때 타입을 적어주면 변수의 타입을 추론할수있다
        //Button 객체의 참조값
//        val addBtn: Button =findViewById(R.id.addBtn)//변수의 타입을 정해주면 참조값을 얻어올때 작성해주지 않아도 된다

        //리스너 등록
        addBtn.setOnClickListener(this)
        clearBtn.setOnClickListener(this)

        // mutableList 객체의 참조값을 얻어내서 property에 저장하기
        msgList= mutableListOf()
        //ArrayAdapter 객체의 참조값을 얻어내서 property에 저장하기
        adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,msgList)
        //ListView에 어댑터 연결하기
//        findViewById<ListView>(R.id.myListView).adapter=adapter   //setAdapter(adapter)를 작성한 것과 같다
        //id를 이용햇 View의 참조값 바로 사용가능
        myListView.adapter=adapter//코틀린에서는 알아서 참조해주기때문에 그냥 참조해서 사용하면 된다
        //롱 클릭 리스너 등록하기
        myListView.setOnItemLongClickListener(this)

    }

    //추가, 모두 삭제 버튼을 누르면 호출되는 함수
    override fun onClick(v: View?) {
        // v 는 눌러진 버튼의 참조값이 전달된다.
        when(v?.id){
            R.id.addBtn -> {
                //입력한 문자열 읽어오기
                val msg=inputMsg.text.toString()
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
                //입력한 문자열을 모델에 추가하고
                msgList.add(msg)
                inputMsg.setText("")
                //ListView 가 업데이트 되도록 아답타에 알린다.
                adapter.notifyDataSetChanged()
            }
            R.id.clearBtn -> {
                msgList.clear()
                adapter.notifyDataSetChanged()
            }
        }

    }

    override fun onItemLongClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long): Boolean {
        var activity=this

        //클릭한 곳의 item
        val item:String = msgList.get(position)

        AlertDialog.Builder(this)
                .setMessage("자세히 보기로 이동 하겠습니까?")
                .setPositiveButton("네", object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        //액티비티 이동
                        //val intent=Intent(activity, DetailActivity::class.java)
                        val intent=Intent(this@MainActivity, DetailActivity::class.java)
                        //Intent 객체에 item 이라는 키값으로 String type 담기
                        intent.putExtra("item", item)
                        startActivity(intent)
                    }
                })
                .setNegativeButton("아니요", null)
                .create()
                .show();

        return false;
    }
}
