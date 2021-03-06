package com.example.fregmentviewtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentviewtest_1.R;

public class Fragment1_Activity extends Fragment implements View.OnClickListener {
    Button f1btnName;
    View view;

    public Fragment1_Activity(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment1,container,false);
        f1btnName=view.findViewById(R.id.f1btnName);
        f1btnName.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.f1btnName :toastDisplaY("안녕"); break;
        }
    }

    private void toastDisplaY(String s) {

        Toast.makeText(view.getContext(),s,Toast.LENGTH_LONG).show();
    }
}
