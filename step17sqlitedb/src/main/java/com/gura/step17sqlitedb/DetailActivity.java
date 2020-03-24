package com.gura.step17sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    //필드
    private DBHelper helper;
    private EditText editContent;
    private TodoDto dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //원래 type으로 캐스팅해주기!
        dto = (TodoDto) getIntent().getSerializableExtra("dto");
        helper=new DBHelper(this, "MyDB.sqlite",null,1);
        TextView textNum=findViewById(R.id.textNum);
        editContent=findViewById(R.id.editContent);
        TextView textRegdate=findViewById(R.id.textRegdate);
        Button updateBtn=findViewById(R.id.updateBtn);
        Button listBtn=findViewById(R.id.listBtn);
        //할일 출력하기
        textNum.setText("번호 : "+dto.getNum());
        editContent.setText(dto.getContent());
        textRegdate.setText("등록일 : "+dto.getRegdate());
        //버튼에 리스너 등록하기
        updateBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.updateBtn:
            //할일을 읽어와서
            String todo=editContent.getText().toString();
            //DB에 수정반영한다.
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="update todo" +
                    " set content=?" +
                    "where num=?";
            Object[] args={todo,dto.getNum()};
            db.execSQL(sql,args);
            db.close();
            Toast.makeText(this,"수정하였습니다.",Toast.LENGTH_LONG).show();
            break;
        case R.id.listBtn:
            //액티비티를 종료시켜서 목록보기 액티비티로 돌아간다
            finish();
            break;
        }
    }
}
