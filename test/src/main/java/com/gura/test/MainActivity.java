package com.gura.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,DialogInterface.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(this);
        Button button2=findViewById(R.id.button);
        button2.setOnClickListener(this);
        Button button3=findViewById(R.id.button);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(button){
            Toast.makeText(this, "버튼을 눌렀군요!", Toast.LENGTH_SHORT).show();
        }else if(findViewById(R.id.button2)){
            //인자로 전달되는 i에는 클릭한 셀의 인덱스값이 들어있다
            new AlertDialog.Builder(this)
                    .setMessage("버튼2를 눌럿군요!")
                    .setNeutralButton("확인",null)
                    .create()
                    .show();
        }

    }

    public void move(View v){
        //액티비티를 이동하기 위한 Intent(의도) 객체를 생성한다.
        Intent intent=new Intent(this,exam.class);
        //startActivity()메소드를 호출하면서 Intent객체를 전달한다.
        startActivity(intent);//레이아웃만 바뀌는게 아니기떄문에 새로 메소드를 작성해주어야한
    }


}
