package com.gura.step16jsonparse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class MemberAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MemberDto> list;
    private int layoutRes;

    //생성자
    public MemberAdapter(Context context, int layoutRes, List<MemberDto> list){
        inflater=LayoutInflater.from(context);
        this.layoutRes=layoutRes;
        this.list=list;
    }

    //모델의 갯수리턴
    @Override
    public int getCount() {
        return list.size();
    }

    //i번째 아이템 리턴
    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        //회원번호가 PK임으로 회뭔의 번호 리턴해주기
        return list.get(i).getNum();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=inflater.inflate(layoutRes,viewGroup,false);
        }
        //TextView의 참조값을 얻어와서
        TextView name=view.findViewById(R.id.name);
        //i번째 회원의 이름을 출력한다
        name.setText(list.get(i).getName());

        return view;
    }
}
