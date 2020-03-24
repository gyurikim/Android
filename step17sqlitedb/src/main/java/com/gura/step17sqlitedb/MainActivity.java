package com.gura.step17sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gura.step17sqlitedb.DBHelper;
import com.gura.step17sqlitedb.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    //필드
    private DBHelper helper;
    private EditText inputText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    //ListView에 출력하기 위해 어댑터에 연결할 문자열 목록
    private List<String> stringList;
    //DB에 있는 실제 data를 가지고 있는 todoDto목록
    private List<TodoDto> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // version 을 바꾸게 되면 DBHelper 객체의 onUpgrade() 메소드가 호출된다.
        //https://sqliteonline.com/ 온라인에서 문서를 확인할수있다.
        helper=new DBHelper(this, "MyDB.sqlite",    //xxx.sqlite는 문서일뿐이지만 SQLite app을 이용해서 열어주면 DB처럼 사용할수있다
                null, 1);//새로 업그레이드 하거나 다른 내용으로 바꾸고 싶으면 버젼을 증가시키면 된다
        //UI  의 참조값 얻어오기
        inputText=findViewById(R.id.inputText);
        listView=findViewById(R.id.listView);
        Button saveBtn=findViewById(R.id.saveBtn);
        Button detailBtn=findViewById(R.id.detailBtn);
        Button deleteBtn=findViewById(R.id.deleteBtn);
        //버튼 리스너 등록
        saveBtn.setOnClickListener(this);
        detailBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);


        //모델 객체 생성
        stringList=new ArrayList<>();
        todoList=new ArrayList<>();
        //아답타 객체 생성
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,stringList);
        //ListView 에 아답타 연결하기
        listView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        //목록 출력하기
        showList();
    }
    //버튼을 눌렀을때 호출되는 메소드
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveBtn: //저장 버튼을 눌렀을때
                //1. 입력한 문자열을 읽어와서
                String inputMsg=inputText.getText().toString();
                //2. todo 테이블에 저장한다.
                SQLiteDatabase db=helper.getWritableDatabase();
                Object[] args={inputMsg};
                String sql="INSERT INTO todo (content, regdate)" +
                        " VALUES(?, datetime('now','localtime'))";
                db.execSQL(sql, args);
                db.close();

                showList();
                inputText.setText("");
            break;
            case R.id.detailBtn:
                int index2=listView.getCheckedItemPosition();
                if(index2==-1){//선택된 값이 없을때
                    Toast.makeText(this,"셀을 선택하세요",Toast.LENGTH_LONG).show();
                    return;//메소드 종료하기
                }
                //액티비티를 이동하기 위한 Intent(의도) 객체를 생성한다.
                Intent intent=new Intent(this,DetailActivity.class);
                //클릭한 셀에 해당되는 dto 객체 얻어오기
                TodoDto dto=todoList.get(index2);
                //intent객체에 담고
                intent.putExtra("dto",dto); //dto를 Serializable type으로 만들어주지 않으면 intent객체에 담을수 없다.
                //startActivity()메소드를 호출하면서 액티비티 이동 > Intent객체를 전달한다.
                startActivity(intent);//레이아웃만 바뀌는게 아니기떄문에 새로 메소드를 작성해주어야한다
            break;
            case R.id.deleteBtn: //삭제 버튼을 눌렀을때
                //리스트뷰에서 선택된아이템의 인덱스값읽어오기
                int index=listView.getCheckedItemPosition();
                if(index==-1){//선택된 값이 없을때
                    Toast.makeText(this,"삭제힐 셀을 선택하세요",Toast.LENGTH_LONG).show();
                    return;//메소드 종료하기
                }
                SQLiteDatabase db2=helper.getWritableDatabase();
                String sql2="delete from todo" +
                            " where num=?";
                //삭제할 셀의 primary key
                int num=todoList.get(index).getNum();
                Object[] args2={num};
                db2.execSQL(sql2,args2);
                db2.close();
                //선택된 cell 목록 취소하기
                listView.clearChoices();
                //목록 다시 출력하기
                showList();
            break;
        }
    }
    //ListView 에 할일 목록을 출력하는 메소드
    public void showList(){
        //모델 초기화
        stringList.clear();
        todoList.clear();
        SQLiteDatabase db=helper.getReadableDatabase();
        //실행할 SELECT 문
        /*
            strftime("날짜형식",날짜,'localtime')
            년 : %Y
            월 : %m
            일 : %d
            시 : %H
            분 : %M
            초 : %S
         */
        String sql="SELECT num,content,regdate " +
                " FROM todo" +
                " ORDER BY num DESC";
        //SELECT 문 수행하고 결과를 Cursor type 으로 받아오기 > java에서 ResultSet의 역할
        Cursor result=db.rawQuery(sql, null);
        //반목문 돌면서 Cursor 객체에서 정보 읽어오기
        while(result.moveToNext()){
            // 0,1,2 번째 칼럼의 문자열 읽어오기
            int num=result.getInt(0);
            String content=result.getString(1);
            String regdate=result.getString(2);
            // 읽어온 문자열을 모델에 추가하기    >리스트뷰에 보여주기
            stringList.add(content);
            //dto 객체를 생성해서 번호 내용 날짜를 넣어주고   > 실제데이터값을 관리
            TodoDto dto=new TodoDto(num,content,regdate);
            //
            todoList.add(dto);
        }
        //모델의 data 가 바뀌었다고 아답타에 알려서 ListView 업데이트 하기
        adapter.notifyDataSetChanged();
    }
}







