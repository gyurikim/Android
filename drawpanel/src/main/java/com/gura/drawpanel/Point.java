package com.gura.drawpanel;

import java.io.Serializable;

public class Point implements Serializable {//객체를 파일에 저장, 읽어들이기 하기 위해서 > Serializable이 아니라면 저장,읽어들이기를 절대할수없다
    public  int x;
    public  int y;
    public boolean isStartPoint;//선의 시작점인지의 여부확인
}
