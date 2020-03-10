package com.gura.step06fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class MyFragment extends Fragment implements View.OnTouchListener{
    //터치 횟수를 관리할 필드
    private int touchCount=0;
    //textView의 참조값을 필드에 저장
    private TextView textView;
    //액티비티의 참조값을 MyFragmentListener type으로 사용하기
    private MyFragmentListener activity;

    //MyFragment를 사용할 액티비티가 구현할 리스너 인터페이스
    public interface MyFragmentListener{
        public void showMessage(int count);
    }
    /*
       [Fragment 만드는 방법]
       1. Fragment 클래스를 상속받는다.
       2. onCreateView()메소드를 오버라이드 한다
    */
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //인자로 전달되는 레이아웃 전개자 객체를 이용해서 View 객체를 만들어서
            View view=inflater.inflate(R.layout.fragment_my,container);
            //textView 의 참조값 얻어오기
            textView=view.findViewById(R.id.textView);
            //textView에 터치 리스너 등록하기
            textView.setOnTouchListener(this);
            //해당 프레그먼트를 관리하는 액티비디의 참조값
            FragmentActivity a=getActivity();
            //해당 액티비티가 MyFragmentListener type 이 맞으면
            if(a instanceof MyFragmentListener){
                //MyFragmentListener type으로 캐스팅한다.
                activity=(MyFragmentListener)a;
            }
            //리턴해 주어야 한다.
            return view;
       }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //1. 터치카운트를 1 증가시킨다
        touchCount++;
        //2. textView 에 출력하기
        textView.setText(Integer.toString(touchCount));
        //3. 액티비티의 메소드 호출하면서 카운트 전달하기
        if(touchCount%10 == 0 && activity != null){
            activity.showMessage(touchCount);
        }
        return false;
    }
}
