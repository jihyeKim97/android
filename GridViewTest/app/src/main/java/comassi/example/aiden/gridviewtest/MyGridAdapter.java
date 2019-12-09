package comassi.example.aiden.gridviewtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MyGridAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<MyData> list;
    LayoutInflater layoutInflater;
    private static final String TAG = "MusicService";

    int day;


    public MyGridAdapter(Context context, int layout, ArrayList<MyData> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public MyGridAdapter(Context context, Attributes attribites) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(layout, null);
        }
        final MyData myData = list.get(position);

        TextView tvDay = convertView.findViewById(R.id.tvDay);
        ImageView ivPic = convertView.findViewById(R.id.ivpic);
        final TextView tvContent = convertView.findViewById(R.id.tvContent);

        day = myData.getCalDay();
        if (day != 0) {
            tvDay.setText("" + day);
            ivPic.setImageResource(R.drawable.ic_launcher);
            tvContent.setText("");
        } else {
            tvDay.setText("");
            ivPic.setImageResource(0);
            tvContent.setText("");

        }


        int columIndex = position % 7;
        if (columIndex == 0) {
            //itemView.setTextColor(Color.RED);
            tvDay.setTextColor(Color.RED);
        } else if (columIndex == 6) {
            //itemView.setTextColor(Color.BLUE);
            tvDay.setTextColor(Color.BLUE);
        }

        MainActivity.sqlDB = MainActivity.myHelper.getWritableDatabase();

        MainActivity.cursor = MainActivity.sqlDB.rawQuery("SELECT schedule FROM calendarTBL WHERE year = "
                + MainActivity.curYear + " AND month = " + MainActivity.curMonth + " AND day = " + day + ";", null);

        if(MainActivity.cursor.getCount() !=0){
            MainActivity.cursor.moveToLast();
            String text = MainActivity.cursor.getString(MainActivity.cursor.getColumnIndex("schedule"));
            tvContent.setText(text);
            MainActivity.cursor.close();
        }



        ivPic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                View viewDialog = View.inflate(v.getContext(), R.layout.dialog, null);

                final EditText editText = viewDialog.findViewById(R.id.editText);


                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle(String.valueOf(MainActivity.curYear)+"."+String.valueOf(MainActivity.curMonth+1)+"."+
                        String.valueOf(position-MainActivity.firstDay+1));
                dialog.setIcon(R.drawable.ic_launcher);
                dialog.setView(viewDialog);
                dialog.setNegativeButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.sqlDB = MainActivity.myHelper.getWritableDatabase();
                        MainActivity.sqlDB.execSQL("INSERT INTO calendarTBL VALUES (" + MainActivity.curYear + "," +
                                "" + (MainActivity.curMonth) + "," + (position-MainActivity.firstDay+1) + ",'" +
                                editText.getText().toString() + "');");
                        MainActivity.sqlDB.close();

                        Log.d(TAG,String.valueOf(MainActivity.curYear)+"."+String.valueOf(MainActivity.curMonth+1)+"."+
                        String.valueOf(position-MainActivity.firstDay+1));
                        notifyDataSetChanged();
                        //myData.setCalContext(editText.getText().toString());

                    }
                });
                dialog.setPositiveButton("닫기", null);
                dialog.show();


                return false;
            }
        });


        switch (MainActivity.curMonth+1){
            case 3: case 4: case 5:
                MainActivity.conLay.setBackgroundDrawable(ContextCompat.getDrawable(convertView.getContext(),R.drawable.spr));
                MainActivity.conLay.getBackground().setAlpha(100);
                break;
            case 6: case 7: case 8:
                MainActivity.conLay.setBackgroundDrawable(ContextCompat.getDrawable(convertView.getContext(),R.drawable.sum));
                MainActivity.conLay.getBackground().setAlpha(100);
                break;
            case 9: case 10: case 11:
                MainActivity.conLay.setBackgroundDrawable(ContextCompat.getDrawable(convertView.getContext(),R.drawable.fal));
                MainActivity.conLay.getBackground().setAlpha(100);
                break;
            case 12: case 1: case 2:
                MainActivity.conLay.setBackgroundDrawable(ContextCompat.getDrawable(convertView.getContext(),R.drawable.win));
                MainActivity.conLay.getBackground().setAlpha(100);
                break;

        }

        return convertView;
    }
}
