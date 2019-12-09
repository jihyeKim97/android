package com.example.androidproject_ver;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.daimajia.swipe.SwipeLayout;
import com.example.androidproject_ver.Calender.Calender_GridView.MonthItem;
import com.example.androidproject_ver.Calender.Calender_GridView.Month_Adapter;
import com.example.androidproject_ver.Calender.Calender_LIstView.MyDBHelper;
import com.example.androidproject_ver.Calender.Calender_LIstView.MyList_Adapter;
import com.example.androidproject_ver.Calender.Calender_LIstView.MyList_Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.androidproject_ver.MainActivity.myContext;

public class Fragment1 extends Fragment implements View.OnClickListener {
    private TextView btnPrevious, btnNext;
    private GridView gvCalender;
    private TextView tvYearMonth;
    private ListView listView;
    private String add;
    private View view;
    private String str;
    private String curDate;
    private ImageView listImgView;

    private TextView listTextViewRdo, listTextViewCombo, listTextViewEdt;

    public static MyList_Data selectlist;
    private MyList_Adapter mylist_adapter;
    private ArrayList<MyList_Data> list = new ArrayList<>();
    private Month_Adapter monthAdapter;

    public static SQLiteDatabase sqlDB;
    public static MyDBHelper myDBHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);

        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnNext = view.findViewById(R.id.btnNext);
        listView = view.findViewById(R.id.listView);

        gvCalender = view.findViewById(R.id.gvCalender);
        tvYearMonth = view.findViewById(R.id.tvYearMonth);

        //1.어뎁터를 생성
        monthAdapter = new Month_Adapter(myContext);
        gvCalender.setAdapter(monthAdapter);
        setYearMonth();

        mylist_adapter = new MyList_Adapter(list, R.layout.income, myContext);
        listView.setAdapter(mylist_adapter);

        gvCalender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonthItem curItem = (MonthItem) monthAdapter.getItem(position);

                curDate = monthAdapter.curYear + "/" + (monthAdapter.curMonth + 1);
                if (curItem.getDayValue() != 0) {
                    myDBHelper = new MyDBHelper(myContext);
                    sqlDB = myDBHelper.getReadableDatabase();
                    Cursor cursor;
                    cursor = sqlDB.rawQuery("SELECT * FROM calenderTBL WHERE date like '" + monthAdapter.curYear + "/"
                            + (monthAdapter.curMonth + 1) + "/" + monthAdapter.items[position].getDayValue() + "/%';", null);

                    list.removeAll(list);
                    while (cursor.moveToNext()) {
                        String databaseCoinrdo = cursor.getString(1);
                        String databaseCoinedttxt = cursor.getString(2);
                        String databaseCoinfilter = cursor.getString(3);
                        list.add(new MyList_Data(databaseCoinrdo, databaseCoinfilter, databaseCoinedttxt));
                    }
                    mylist_adapter.notifyDataSetChanged();
                    cursor.close();
                    sqlDB.close();
                }
            }
        });

        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);

        gvCalender.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                MonthItem curItem1= (MonthItem) monthAdapter.getItem(position);

                curDate = monthAdapter.curYear + "/" + (monthAdapter.curMonth + 1);
                if (curItem1.getDayValue() != 0) {
                    myDBHelper = new MyDBHelper(myContext);
                    sqlDB = myDBHelper.getReadableDatabase();
                    Cursor cursor;
                    cursor = sqlDB.rawQuery("SELECT * FROM calenderTBL WHERE date like '" + monthAdapter.curYear + "/"
                            + (monthAdapter.curMonth + 1) + "/" + monthAdapter.items[position].getDayValue() + "/%';", null);

                    list.removeAll(list);
                    while (cursor.moveToNext()) {
                        String databaseCoinrdo = cursor.getString(1);
                        String databaseCoinedttxt = cursor.getString(2);
                        String databaseCoinfilter = cursor.getString(3);
                        list.add(new MyList_Data(databaseCoinrdo, databaseCoinfilter, databaseCoinedttxt));
                    }
                    mylist_adapter.notifyDataSetChanged();
                    cursor.close();
                    sqlDB.close();
                }

                View dialogView = View.inflate(view.getContext(), R.layout.dialog, null);
                final EditText edtLittle = (EditText) dialogView.findViewById(R.id.edtLittle);
                final Spinner spinnerFilter = (Spinner) dialogView.findViewById(R.id.spinnerFilter);
                final RadioButton rdoPlus = (RadioButton) dialogView.findViewById(R.id.rdoPlus);
                final RadioButton rdoMinus = (RadioButton) dialogView.findViewById(R.id.rdoMinus);
                final MonthItem curItem = (MonthItem) monthAdapter.getItem(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());

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
                dialog.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //현재 시분초를 가져온다
                        long now = System.currentTimeMillis();
                        Date date = new Date(now);

                        SimpleDateFormat currnetHour = new SimpleDateFormat("HH");
                        SimpleDateFormat cuurentMinuite = new SimpleDateFormat("mm");
                        SimpleDateFormat currentSecond = new SimpleDateFormat("ss");

                        String strCurrnetHour = currnetHour.format(date);
                        String strCurrentMinuite = cuurentMinuite.format(date);
                        String strCurrentsecond = currentSecond.format(date);
                        String dateTime = strCurrnetHour + "/" + strCurrentMinuite + "/" + strCurrentsecond;

                        curDate = monthAdapter.curYear + "/" + (monthAdapter.curMonth + 1) + "/" +
                                curItem.getDayValue() + "/" + dateTime;
                        Toast.makeText(getContext(), curDate, Toast.LENGTH_LONG).show();

                        if (rdoPlus.isChecked() && !(spinnerFilter.getSelectedItem().toString().equals("필터를 설정해 주세요")) &&
                                !(edtLittle.getText().toString().equals(""))) {
                            myDBHelper = new MyDBHelper(view.getContext());
                            sqlDB = myDBHelper.getWritableDatabase();
                            sqlDB.execSQL("INSERT INTO calenderTBL VALUES('" + curDate + "','" + rdoPlus.getText().toString() + "','"
                                    + edtLittle.getText().toString() + "','" + spinnerFilter.getSelectedItem().toString() + "');");
                            sqlDB.close();
                            list.add(new MyList_Data(rdoPlus.getText().toString(), spinnerFilter.getSelectedItem().toString(), edtLittle.getText().toString()));

                            myDBHelper = new MyDBHelper(myContext);
                            sqlDB = myDBHelper.getReadableDatabase();
                            Cursor cursor;
                            cursor = sqlDB.rawQuery("SELECT * FROM calenderTBL", null);
                            while (cursor.moveToNext()) {
                                String exDate = cursor.getString(0);
                                Toast.makeText(view.getContext(), exDate, Toast.LENGTH_LONG).show();
                            }
                            cursor.close();
                            monthAdapter.notifyDataSetChanged();
                            //Toast.makeText(view.getContext(), str + "(으)로 " + edtLittle.getText().toString() + "원의 수입이 발생하였습니다", Toast.LENGTH_LONG).show();

                        } else if (rdoMinus.isChecked() && !(spinnerFilter.getSelectedItem().toString().equals("필터를 설정해 주세요")) &&
                                !(edtLittle.getText().toString().equals(""))) {
                            myDBHelper = new MyDBHelper(view.getContext());
                            sqlDB = myDBHelper.getWritableDatabase();
                            sqlDB.execSQL("INSERT INTO calenderTBL VALUES('" + curDate + "','" + rdoMinus.getText().toString() + "','"
                                    + edtLittle.getText().toString() + "','" + spinnerFilter.getSelectedItem().toString() + "');");
                            sqlDB.close();
                            list.add(new MyList_Data(rdoMinus.getText().toString(), spinnerFilter.getSelectedItem().toString(), edtLittle.getText().toString()));
                            monthAdapter.notifyDataSetChanged();
                            Toast.makeText(view.getContext(), str + "(으)로 " + edtLittle.getText().toString() + "원의 지출이 발생하였습니다", Toast.LENGTH_LONG).show();
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

    public void insertListData() {
        myDBHelper = new MyDBHelper(myContext);
        sqlDB = myDBHelper.getReadableDatabase();
        Cursor cursor;//ResultSet과 같은 기능

        //쿼리문을 실행한 데이터들을 전부 가져와서 저장
        cursor = sqlDB.rawQuery("SELECT * FROM calenderTBL", null);
        //리스트를 한번 초기화 시켜준다 = 초기화를 시켜주지 않으면 값이 계속 쌓인다
        list.removeAll(list);
        //반복문으로 값이 있으면 디비에있는 컬럼 0번째 1번째를 가져와서 각 객체를 만들어서 리스트로 저장
        while (cursor.moveToNext()) {
            String databaseDate = cursor.getColumnName(0);
            String databaseCoinrdo = cursor.getColumnName(1);
            String databaseCoinedttxt = cursor.getColumnName(2);
            String databaseCoinfilter = cursor.getColumnName(3);
            list.add(new MyList_Data(databaseDate, databaseCoinrdo, databaseCoinedttxt, databaseCoinfilter));
        }
        //자원을 닫아준다
        cursor.close();
        sqlDB.close();
    }

    // 월 표시 텍스트 설정
    private void setYearMonth() {
        String yearMonth = monthAdapter.curYear + "년   " + (monthAdapter.curMonth + 1) + " 월 ";
        tvYearMonth.setText(yearMonth);
    }
}
