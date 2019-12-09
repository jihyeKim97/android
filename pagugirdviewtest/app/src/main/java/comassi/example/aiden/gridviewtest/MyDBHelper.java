package comassi.example.aiden.gridviewtest;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final  String DB_NAME = "calendarTBL";
    private static final  int VERSION = 1;


    //데이터베이스 생성
    public MyDBHelper(Context context) {
        super(context,DB_NAME,null,VERSION);
    }



    //테이블 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE calendarTBL (year INTEGER, month INTEGER, day INTEGER, schedule CHAR(100));";
        db.execSQL(str);


    }

    //테이블을 삭제하고 다시 생성함
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS calendarTBL;");
        onCreate(db);
    }
}