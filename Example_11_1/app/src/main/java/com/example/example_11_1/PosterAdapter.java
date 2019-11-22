package com.example.example_11_1;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PosterAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PosterModel> list = new ArrayList<PosterModel>();
    //화면xml의 아이디 가져오기
    private int layout;
    private LayoutInflater layoutInflater;

    public PosterAdapter(Context context, ArrayList<PosterModel> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
//인플레이트를 사용할수 있게 만들어 준다
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//이미지뷰가져오기
        ImageView gridImageView = convertView.findViewById(R.id.gridImageView);
        TextView textView = convertView.findViewById(R.id.textView);

        PosterModel model = list.get(position);
        gridImageView.setImageResource(model.getImageID());
        textView.setText(model.getMovieName());



        gridImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다이얼 로그 뷰를 만듬
                View dialog = View.inflate(context,R.layout.dialog,null);
                ImageView ivPoster = dialog.findViewById(R.id.ivPoster);

                PosterModel model1 = list.get(position);
                ivPoster.setImageResource(model1.getImageID());

                AlertDialog.Builder alerdialog = new AlertDialog.Builder(context);
                alerdialog.setTitle(model1.getMovieName());
                alerdialog.setIcon(R.mipmap.movie_icon);
                alerdialog.setPositiveButton("닫기",null);
                alerdialog.setView(dialog);
                alerdialog.show();
            }
        });

        return convertView;
    }
}
