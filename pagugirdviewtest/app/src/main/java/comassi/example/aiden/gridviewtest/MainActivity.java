package comassi.example.aiden.gridviewtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPrevious, btnNext;
    TextView tvYearMonth;
    GridView gvCalendar;
    ArrayList<MyData> list = new ArrayList<MyData>();
    MyGridAdapter myGridAdapter;
    static LinearLayout conLay;


    public Calendar mCalendar;
    static public int firstDay; //예
    public int mStartDay; //현재달력시작위치요일(일요일부터 시작됨) Calender.SUNDAY
    public int startDay; //Time.SUNDAY
    public static int curYear; //현재 달력의 연도
    public static int curMonth;//현재 달력의 월
    public int lastDay; //현재 달의 마지막 날짜가 며칠까지 있냐
    public int selectedPosition = -1; //무슨의미일까?


    static MyDBHelper myHelper;
    static SQLiteDatabase sqlDB;
    static Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("달력");

        myHelper = new MyDBHelper(this);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        gvCalendar = findViewById(R.id.gvCalendar);
        tvYearMonth = findViewById(R.id.tvYearMonth);
        conLay = findViewById(R.id.conLay);

        gvCalendar = findViewById(R.id.gvCalendar);
        myGridAdapter = new MyGridAdapter(this, R.layout.image_view_layout, list);
        gvCalendar.setAdapter(myGridAdapter);

        conLay.getBackground().setAlpha(100);

        //초기설정을 한다(날짜를 계산해서 오늘 날짜의 달력을 뿌려줌)
        init();

        setYearMonth();

        //이벤트를 건다
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        gvCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyData curItem = list.get(position);
                int day = curItem.getCalDay();
                String message = curYear + "년" + (curMonth + 1) + "월" + day + "일" + "고생했어요";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            }
        });

        tvYearMonth.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                sqlDB = myHelper.getWritableDatabase();
                Toast.makeText(getApplicationContext(),"데이터베이스 초기화",Toast.LENGTH_SHORT).show();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();


                myGridAdapter.notifyDataSetChanged();
                return false;
            }
        });








    }//end of oncreate;

    private void setYearMonth() {
        String yearMonth = curYear + "년" + (curMonth + 1) + "월";
        tvYearMonth.setText(yearMonth);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                setNextMonth();
                myGridAdapter.notifyDataSetChanged();
                setYearMonth();
                break;
            case R.id.btnPrevious:
                setPreviousMonth();
                myGridAdapter.notifyDataSetChanged();
                setYearMonth();
                break;
        }
    }


    public void init() {
        //42개가 넘으면 안된다
        //items = new MonthItem[42];
        //현재연도, 달, 날짜, 시간
        mCalendar = Calendar.getInstance();
        //11월달, 1일~30일, 1일 = 금요일, 무슨요일부터 시작하는가?일요일
        //년도, 월, 마지막일(윤년), 1일 > 요일위치, 달력위치 ( 월, 일, 토 선택)
        recalculate();
        //11월달에 뿌려질 객체자료를 배열화시켜서 MonthItem 객체배열에 넣어줘야한다.
        resetDayNumbers();

    }

    public void setPreviousMonth() {
        //현재가 11월달이라면 10월로 감
        mCalendar.add(Calendar.MONTH, -1);
        recalculate();
        resetDayNumbers();
        //무슨의미일까??
        selectedPosition = -1;
    }

    public void setNextMonth() {
        //현재가 11월달이라면 10월로 감
        mCalendar.add(Calendar.MONTH, +1);
        recalculate();
        resetDayNumbers();
        //무슨의미일까??
        selectedPosition = -1;
    }


    private void resetDayNumbers() {
        list.removeAll(list);
        for (int i = 0; i < 42; i++) {
            int dayNumber = (i + 1) - firstDay;
            if (dayNumber < 1 || dayNumber > lastDay) {
                dayNumber = 0;
            }
            list.add(new MyData(dayNumber));

        }
    }

    private void recalculate() {
        //현재 월과 날짜를 기준으로 해서 1일로 세팅한다.
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        //11월 1일이 무슨요일이냐? ( 금요일 > 5)
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        firstDay = getFirstDay(dayOfWeek);

        //현재 시스템에 달력이 첫번째를 무슨요일로 시작하느냐?
        //Time.Sunday
        //컴퓨터가 계산하는 방식
        mStartDay = mCalendar.getFirstDayOfWeek();
        //현재년도
        curYear = mCalendar.get(Calendar.YEAR);
        curMonth = mCalendar.get(Calendar.MONTH);

        //현재달 마지막 날짜
        lastDay = getMonthLastDay(curYear, curMonth);
        //우리가 계산하는 방식
        startDay = getFirstDayofWeek();
        resetDayNumbers();


    }

    private int getFirstDayofWeek() {
        int startDay = Calendar.getInstance().getFirstDayOfWeek();
        switch (startDay) {
            case Calendar.SATURDAY:
                return Time.SATURDAY;
            case Calendar.MONDAY:
                return Time.MONDAY;
            case Calendar.SUNDAY:
                return Time.SUNDAY;

        }
        return 0;
    }

    private int getMonthLastDay(int curYear, int curMonth) {
        switch (curMonth + 1) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                if ((curYear % 4 == 0 && curYear % 100 != 0) || (curYear % 400 == 0)) {
                    return 29;
                } else return 28;


        }

    }

    //각각 나라에 맞게 요일을 숫자로 표현하는 함수
    private int getFirstDay(int dayOfWeek) {
        int result = 0;
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                result = 0;
                break;
            case Calendar.MONDAY:
                result = 1;
                break;
            case Calendar.TUESDAY:
                result = 2;
                break;
            case Calendar.WEDNESDAY:
                result = 3;
                break;
            case Calendar.THURSDAY:
                result = 4;
                break;
            case Calendar.FRIDAY:
                result = 5;
                break;
            case Calendar.SATURDAY:
                result = 6;
                break;
        }
        return result;
    }


}

