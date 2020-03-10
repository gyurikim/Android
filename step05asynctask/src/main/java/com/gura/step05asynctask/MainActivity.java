package com.gura.step05asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //진행시작, 진행과정, 결과를 표시하는 textView
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView의 참조값을 필드에 저장하기
        textView=findViewById(R.id.textView);
    }
    //작업하기 버튼을 눌렀을떄 호출되는 메소드
    public void start(View v){
    /*
        버튼을 누르면 여기에 실행 순서가 들어온다. > 그 스레드는 UI 스레드(main 스레드)이다.
        UI 스레드에서 시간이 오래 걸리거나 언제 끝날지 모르는 불확실한 작업을 하면 안된다. > 그랬을시에는 앱을 다운시켜버린다
        UI의 업데이트는 UI스레드에서만 가능하다.
     */
        //비동기 작업의 시작은 객체를 생성하고
        CounterTask task=new CounterTask();
        //execute() 메소드를 호출하면 된다
        task.execute("김구라","원숭이","해골");
    }
    /*
        extends AsyncTask<전달받은 type,진행중 반환하는 type, 결과 type>
        type이 필요없으면 Void type을 사용하면 된다
        extends AsyncTask<String, Void, Void>
     */
    public class CounterTask extends AsyncTask<String,Integer,String>{//제너릭 타입은 상황에따라 바뀐다. *** 안드로이드에서 자주 사용하는 아주중요한 메소드!!!!!!!!!!!
        //publishProgress() 메소드를 호출하면 아래의 메소드가 호출된다
        @Override
        protected void onProgressUpdate(Integer... values) {//Integer... 은 정수 배열을 의미한다. 인자를 1개, 2개 받아도되고 안받아도 되고
            super.onProgressUpdate(values);
            //여기는 UI스레드이기 때문에 UI업데이트가 가능하다
            //publishProgress() 메소드에 전달된 인자가 이 메소드의 인자로 전달된다
            int count=values[0];//Integer 배열의 0번방애 값이 들어있다
            textView.setText(Integer.toString(count));
        }

        //doInBackground() 메소드가 리턴되면 아래의 메소드가 호출된다
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }

        //doInBackground() 메소드를 실행하기 이전에 호출되는 메소드
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("숫자를 세어볼까요??");
        }

        @Override
        protected String doInBackground(String... strings) {
            String name1=strings[0];//김구라
            String name2=strings[1];//원숭이
            String name3=strings[2];//해골
            //백그라운드(새로운 스래드)에서 작업할 내용은 여기서 하면 된다
            int count=0;
            for(int i=0;i<20;i++){
                try {
                    Thread.sleep(500);
                }catch (Exception e){}
                count++;
                //count  값을 텍스트뷰에 출력해볼까? > doInBackground() 메소드에서는 UI업데이트를 실행시킬수 없다
                //textView.setText(Integer.toString(count));
                publishProgress(count);
            }
            String result="숫자세기 성공";
            return result;
        }
    }
}
