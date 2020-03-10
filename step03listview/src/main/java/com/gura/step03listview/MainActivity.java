package com.gura.step03listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ListView의 참조값 얻어오기
        ListView listView=findViewById(R.id.listView);
        //어댑터에 연결할 모델(Data)
        names=new ArrayList<>();
        //모델에 샘플 데이터 저장     모델에 있는 값은 다양한 type이 존재하므로 바로 셀에 출력할수 없다
        for(int i=0;i<100;i++){
            names.add("김구라"+i);
        }

        //ListView에 연결할 어댑터 객체 생성하기
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);
        //ListView에 어댑터 연결하기
        listView.setAdapter(adapter);
        //ListView에 아이템 클릭 리스너 등록하기
        listView.setOnItemClickListener(this);
    }

    //ListView의 셀(item)을 클릭하면 호출되는 메소드
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int j, long id) {

        //인자로 전달되는 i에는 클릭한 셀의 인덱스값이 들어있다
        new AlertDialog.Builder(this)
                .setTitle("내가 몇번을 클릭했냐면은~")
                .setMessage(names.get(j))
                .setNeutralButton("확인",null)
                .create()
                .show();
    }

    //다음예제 버튼을 눌렀을때 호출되는 메소드
    public void moveNext(View v){
        //Main2Activity로 이동할 의도 객체 생성
        Intent i=new Intent(this,Main2Activity.class);
        //startActivity()메소드 호출하면서 의도 객체를 전달하면 이동된다
        startActivity(i);
    }
}
