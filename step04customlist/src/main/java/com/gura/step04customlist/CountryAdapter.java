package com.gura.step04customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gura.step04customlist.CountryDto;
import com.gura.step04customlist.R;

import java.util.List;

/*
    [ ListView 에 연결할 어댑터 클래스 정의하기 ]

    - BaseAdapter 추상 클래스를 상속 받아서 만든다.
*/
public class CountryAdapter extends BaseAdapter {
    //필요한 필드 정의하기
    private Context context;
    private int layoutRes;
    private List<CountryDto> list;
    private LayoutInflater inflater;    //레이아웃 전개자 객체

    //생성자
    public CountryAdapter(Context context, int layoutRes, List<CountryDto> list){
        this.context=context;       //레이아웃 전개자객체를 얻어내기 위해서
        this.layoutRes=layoutRes;   //레이아웃리소스의 아이디
        this.list=list;
        /*
            [ 레이아웃 전개자 객체 ]
            xml 로 정의한 레이아웃 정보를 실제로 전개해서 View 객체로 만들어 주는 객체
         */
        inflater=LayoutInflater.from(context);  //LayoutInflater 클래스에 static from() 메서드를 이용해서 레이아웃 전개자 객체를 리턴해준다.
    }                                           //생성자에서 inflater의 값을 얻어서 필드에 저장


    //전체 모델의 갯수를 리턴해준다.
    @Override
    public int getCount() {
        return list.size();
    }
    // i 인덱스에 해당하는 아이템(data) 를 리턴해준다.
    @Override
    public Object getItem(int i) {
        return list.get(i); // i번째 해당되는 아이템 리턴.
    }
    // i 인덱스에 해당하는 아이템의 아이디가 있으면 리턴해준다.
    @Override
    public long getItemId(int i) {
        return i;   //아이디 또는 DB에서 값을 가져오는거면 primary key값을(dto.getNum(i)), 지금은 아이디가 없으므로 index를 ID로 리턴해준다.
    }
    // i 인덱스에 해당하는 셀 View 를 리턴해준다.(수없이 호출되는 메소드)
    @Override//스크롤을 내리다가 새로운 셀이 생성되는 순간에  getView()메소드를 호출하는 방식이다.
    public View getView(int i, View view, ViewGroup viewGroup) {    // i 번째 인덱스에 맞는 view를 만들어서 리턴해주어야 한다(여기서는 국가정보 등)
        //처음에는 인자로 선언된 지역변수 view 에 null 이 들어있다.
        if(view == null){//한 페이지에 4개의 정보만 보이게 되므로 최소한의 셀 4개의 view 에만 null이 들어있다
            //레이아웃 전개자 객체를 이용해서 view 객체를 만든다.
            view=inflater.inflate(layoutRes, viewGroup, false);
            /*
                뷰를 수많이 만드는 것이 아니라 한 화면에 보이는 최소한의 셀만 생성한후
                들어있는 내용만 바꿔가면서 레이아웃을 보여주는 것이다!
             */
        }                   //layuotRes => R.layout.listview_cell 즉, 정수값이 들어있다.
        //View 에서 원하는 UI 의 참조값을 얻어낸다.
        ImageView imageView=view.findViewById(R.id.imageView);  // 그냥 findViewById 는 Activity에서 찾는것임. 헷갈리지 마라.
        TextView textView=view.findViewById(R.id.textView);
        //i 번째 인덱스에 해당하는 데이터를 얻어온다.
        CountryDto dto=list.get(i);
        //View 에 데이터를 출력한다.
        imageView.setImageResource(dto.getResId());
        textView.setText(dto.getName());
        //구성된 View 객체를 리턴해준다.
        return view;
    }   //이 getView 라는 메서드는 나중에 List가 호출할 예정임. 우리가 직접하지 않는다.
}