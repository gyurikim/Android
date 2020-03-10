package com.gura.step07customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/*
    Custom View 클래스 만드는 방법
    1. View 클래스를 상속받는다
    2. Context 객체를 생성자의 인자로 전달받아서 부모 생성자에 전달하도록 한다
    3. onDraw() 메소드를 오버라이딩해서 View 화면을 구성한다.
 */
public class MyView extends View {// 1.
    //터치 이벤트가 일어난 X,Y좌표를 저장할 필드
    private int x,y;

    //2. 생성자 >> 화면을 하나로 꽉 채우고자 한다면 위에 생성자 하나만 생성해도 된다. / 잘 모르겟다면 걍 생성자를 두개 만들어라^^
    public MyView(Context context) {
        super(context);
    }
    //2-1. layout xml문서에서 해당 View 를 사용하게 하려면 필요한 생성자
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //3.onDraw() > 차지하고 있는 공간에 뭘 표시할건지 표시하는 메소드
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
        Paint textPaint=new Paint();//페인트객체를 통해 전달하깅
        textPaint.setColor(Color.YELLOW);
        textPaint.setTextSize(100);
        canvas.drawText("x: "+x+" y: "+y,10,100,textPaint);
    }

    //터치이벤트 처리
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //이벤트가 일어난 곳의 좌표를 필드에 저장
        x=(int)event.getX();//getX()는 float로 리턴되는데 우린 int가 필요하니깐 캐스팅
        y=(int)event.getY();//

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://=0

                break;
            case MotionEvent.ACTION_MOVE://=2 > 터치의 움직임이있을때마다 무수히 호출됨

                break;
            case MotionEvent.ACTION_UP://=1

                break;
        }
        //View 갱신하기(결과적으로 onDraw()메소드가 다시 호출된다.)
        invalidate();//현재 화면에 뿌려진걸 무효화하고 다시 그리겟다!!!
/* 필드에 값을 저장해놓고 invalidate()를 호출하면 지금 저장된 내용을 무효화하고 다시 onDraw() 메소드를 호출함 */
        return true;
    }
}
