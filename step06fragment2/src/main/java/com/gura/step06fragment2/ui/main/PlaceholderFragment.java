package com.gura.step06fragment2.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gura.step06fragment2.CountryDto;
import com.gura.step06fragment2.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment{
    //인덱스를 전달받아서 Fragment객체를 생성해서 리턴해주는 static메소드 > 뷰페이저에서 사용함
    //팩토리스타일
    public static PlaceholderFragment newInstance(CountryDto dto) {
        PlaceholderFragment fr=new PlaceholderFragment();
        //Fragment에 전달할 Bundle 객체
        Bundle bundle=new Bundle();
        bundle.putSerializable("dto",dto);
        //Fragment에 인자 전달하기
        fr.setArguments(bundle);
        return fr;
    }

    //이미지 리소스아이디를 담을 필드 선언
    private CountryDto dto;

    //1. (객체가 하나하나 생성될때)프레그먼트가 최초!! 사용될때 호출되는 메소드
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dto=(CountryDto) getArguments().getSerializable("dto");//setArguments()한거는 직접 getArguments()해서 읽어외야한다
    }

    //2. 프레그먼트가 활성화 될때마다!! 호출되는 메소드 > 생성해놨다가 다음에 다시 필요할때 액티비티가 종료되기 전까지 재 사용함, 셀 생성하는것과는 매우 다름!
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // res/layout/fragment_main.xml 문서를 전개해서 View 객체를 만든다
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        //이미지뷰의 참조값을 얻어와서
        ImageView imageView=view.findViewById(R.id.imageView);
        TextView textView=view.findViewById(R.id.textView);
        //이미지 출력하기
        imageView.setImageResource(dto.getResId());
        textView.setText(dto.getContent());
        //View객체 리턴해주기
        return view;
    }
}