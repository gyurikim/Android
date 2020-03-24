package com.gura.step17sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

    /*
       DB생성 및 업그레이드를 도와주는 도우미 클래스 만들기
       - SQLiteOpenHelper 추상클래스를 상속받아서 만든다
    */
public class DBHelper extends SQLiteOpenHelper {
    //생성자
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);//팩토리는 미리 정의된 내용을 앱을 설치하자마자 값을 가지고있게 할때 사용...?

    }

    //DB가 새로 초기화 될때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db) {
        //todo라는 이름의 테이블 만들기
        String sql="create table todo" +
                   "(num integer primary key autoincrement," +  //시퀀스를 만들지않아도 autoincrement 알아서 순차적으로 숫자를 넣어준다
                   "content text,regdate text)";//SQLite에는 Date타입이 존재하지 않는다 그냥 text타입으로 넣어주면 된다
        db.execSQL(sql);

    }

    //DB 테이블의 구조를 바꾸거나 전체 혹을 부분 엡데이트를 할때 호출되는 메소드
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //todo 테이블이 존재(if exists)한다면 삭제하고 다시 만들어질수 있도록
        db.execSQL("drop table if exists todo");
        onCreate(db);
    }
}
