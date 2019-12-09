package com.example.householderproject.fragment;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.householderproject.R;
import com.example.householderproject.adapter.CalendarListAdapter;
import com.example.householderproject.calendar.MonthAdapter;
import com.example.householderproject.model.CalendarListData;
import com.example.householderproject.model.MonthItem;
import com.example.householderproject.util.DBHelper;

import java.util.ArrayList;

import static com.example.householderproject.MainActivity.myContext;

public class Fragment1 extends Fragment implements View.OnClickListener {
    private TextView btPrevious, btNext;
    private GridView gridViewCalendar;
    private TextView textViewYear;
    private ListView listView;
    private String add;
    private View view;
    private String str;
    private String currentDate;

    private CalendarListAdapter mylist_adapter;
    private ArrayList<CalendarListData> list = new ArrayList<>();
    private MonthAdapter monthAdapter;

    public static SQLiteDatabase sqlDB;
    public static DBHelper myDBHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //BottomNavigationBar 에서는 fragment 로 사용되어 MainActivity 처럼 써야 한다
        view = inflater.inflate(R.layout.fragment1, container, false);

        btPrevious = view.findViewById(R.id.btnPrevious);
        btNext = view.findViewById(R.id.btnNext);
        listView = view.findViewById(R.id.listView);

        gridViewCalendar = view.findViewById(R.id.gvCalender);
        textViewYear = view.findViewById(R.id.tvYearMonth);

        //달력 어뎁터 생성
        monthAdapter = new MonthAdapter(myContext);
        gridViewCalendar.setAdapter(monthAdapter);
        setYearMonth();
        //리스트 어뎁터 생성
        mylist_adapter = new CalendarListAdapter(list, R.layout.calendar_list_view_holder, myContext);
        listView.setAdapter(mylist_adapter);

        gridViewCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MonthItem curItem = (MonthItem) monthAdapter.getItem(position);

                currentDate = String.valueOf(monthAdapter.curYear)  + (monthAdapter.curMonth + 1);
                    //원하는 날짜를 클릭했을때 그안에 값이 들어있다면
                if (curItem.getDayValue() != 0) {
                    myDBHelper = new DBHelper(myContext);
                    sqlDB = myDBHelper.getReadableDatabase();
                    Cursor cursor;
                    cursor = sqlDB.rawQuery("SELECT * FROM calenderTBL WHERE date = '" + monthAdapter.curYear + ""
                            + (monthAdapter.curMonth + 1) + "" + monthAdapter.items[position].getDayValue() + "';", null);
                    //리스트를 한번 비워준다
                    list.removeAll(list);
                    //해당 날짜의 저장 되어잇는 값을 가져오기 위해 반복문을 통해 검사
                    while (cursor.moveToNext()) {

                        String getCredit = cursor.getString(2);
                        String getDetail = cursor.getString(3);
                        String getCategory = cursor.getString(4);

                        list.add(new CalendarListData(getCredit, getDetail, getCategory));

                    }
                    //변환한 값을 적용
                    mylist_adapter.notifyDataSetChanged();
                    cursor.close();
                    sqlDB.close();
                }
            }
        });
        //달력의 이전달,다음달 버튼 이벤트 처리
        btNext.setOnClickListener(this);
        btPrevious.setOnClickListener(this);

        gridViewCalendar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                MonthItem curItem1= (MonthItem) monthAdapter.getItem(position);

                currentDate = monthAdapter.curYear + "/" + (monthAdapter.curMonth + 1);
                //원하는 날짜를 클릭했을때 그안에 값이 들어있다면
                if (curItem1.getDayValue() != 0) {

                    myDBHelper = new DBHelper(myContext);
                    sqlDB = myDBHelper.getReadableDatabase();
                    Cursor cursor;
                    cursor = sqlDB.rawQuery("SELECT * FROM calenderTBL WHERE date = '" + monthAdapter.curYear + ""
                            + (monthAdapter.curMonth + 1) + "" + monthAdapter.items[position].getDayValue() + "';", null);
                    //리스트를 한번 비워준다
                    list.removeAll(list);
                    //해당 날짜의 저장 되어잇는 값을 가져오기 위해 반복문을 통해 검사
                    while (cursor.moveToNext()) {

                        String getCredit = cursor.getString(2);
                        String getDetail = cursor.getString(3);
                        String getCategory = cursor.getString(4);

                        list.add(new CalendarListData(getCredit, getDetail, getCategory));

                    }
                    //변환한 값을 적용
                    mylist_adapter.notifyDataSetChanged();
                    cursor.close();
                    sqlDB.close();
                }
                //내가 만든 xml로 dialog로 표현
                View dialogView = View.inflate(view.getContext(), R.layout.calendar_input_data_dialog, null);

                final EditText editTextCredit = dialogView.findViewById(R.id.edtLittle);
                final Spinner spinnerFilter = dialogView.findViewById(R.id.spinnerFilter);
                final RadioButton radioButtonPlus = dialogView.findViewById(R.id.rdoPlus);
                final RadioButton radioButtonMinus = dialogView.findViewById(R.id.rdoMinus);
                final MonthItem currentItem = (MonthItem) monthAdapter.getItem(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                //스피너를 클릭했을때 이벤트 처리
                spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        str = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                dialog.setView(dialogView);
                //저장을 눌렀을때 추가한 리스트가 리스트 뷰에 뿌려진다
                dialog.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        currentDate = String.valueOf(monthAdapter.curYear) + (monthAdapter.curMonth + 1) + currentItem.getDayValue();
                        Toast.makeText(getContext(), currentDate, Toast.LENGTH_LONG).show();
                            //RdoButton이 수입일 경우
                        if (radioButtonPlus.isChecked() && !(spinnerFilter.getSelectedItem().toString().equals("필터를 설정해 주세요")) &&
                                !(editTextCredit.getText().toString().equals(""))) {
                            myDBHelper = new DBHelper(view.getContext());
                            sqlDB = myDBHelper.getWritableDatabase();
                            sqlDB.execSQL("INSERT INTO calenderTBL VALUES( null, '" + currentDate + "','" + editTextCredit.getText().toString() + "','"
                                    + radioButtonPlus.getText().toString() + "','" + spinnerFilter.getSelectedItem().toString() + "', null);");
                            sqlDB.close();
                            list.add(new CalendarListData( editTextCredit.getText().toString(),radioButtonPlus.getText().toString(), spinnerFilter.getSelectedItem().toString()));

                            myDBHelper = new DBHelper(myContext);
                            sqlDB = myDBHelper.getReadableDatabase();
                            Cursor cursor;
                            cursor = sqlDB.rawQuery("SELECT * FROM calenderTBL", null);
                            while (cursor.moveToNext()) {
                                String exDate = cursor.getString(0);
                                Toast.makeText(view.getContext(), exDate, Toast.LENGTH_LONG).show();
                            }
                            cursor.close();
                            //변환값을 다시 적용시킨다
                            monthAdapter.notifyDataSetChanged();
                            Toast.makeText(view.getContext(), str + "(으)로 " + editTextCredit.getText().toString() + "원의 수입이 발생하였습니다", Toast.LENGTH_LONG).show();
                            //RdoButton이 지출일 경우
                        } else if (radioButtonMinus.isChecked() && !(spinnerFilter.getSelectedItem().toString().equals("필터를 설정해 주세요")) &&
                                !(editTextCredit.getText().toString().equals(""))) {
                            myDBHelper = new DBHelper(view.getContext());
                            sqlDB = myDBHelper.getWritableDatabase();
                            sqlDB.execSQL("INSERT INTO calenderTBL VALUES(null, '" + currentDate + "','" + editTextCredit.getText().toString() + "','"
                                    + radioButtonMinus.getText().toString() + "','" + spinnerFilter.getSelectedItem().toString() + "', null);");
                            sqlDB.close();
                            list.add(new CalendarListData(editTextCredit.getText().toString(),radioButtonMinus.getText().toString(), spinnerFilter.getSelectedItem().toString() ));
                            monthAdapter.notifyDataSetChanged();
                            Toast.makeText(view.getContext(), str + "(으)로 " + editTextCredit.getText().toString() + "원의 지출이 발생하였습니다", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "선택하지 않은 항목이 있습니다", Toast.LENGTH_LONG).show();
                        }
                        //변한 값을 알려주고 적용시킨다
                        mylist_adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("취소", null);
                dialog.show();
                return true;
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                monthAdapter.setNextMonth();
                monthAdapter.notifyDataSetChanged();

                setYearMonth();
                break;
            case R.id.btnPrevious:
                monthAdapter.setPreviousMonth();
                monthAdapter.notifyDataSetChanged();
                setYearMonth();
                break;
        }
    }

    // 월 표시 텍스트 설정
    private void setYearMonth() {
        String yearMonth = monthAdapter.curYear + "년   " + (monthAdapter.curMonth + 1) + " 월 ";
        textViewYear.setText(yearMonth);
    }
}