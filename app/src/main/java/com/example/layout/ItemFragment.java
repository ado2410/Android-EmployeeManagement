package com.example.layout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemFragment extends Fragment {
    MainActivity mainActivity;
    int id;
    TextView txtName;
    ImageView ivAvatar;
    TextView txtDob;
    TextView txtGender;
    TextView txtPosition;
    TextView txtSalary;
    FloatingActionButton btnMore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        txtName = (TextView) view.findViewById(R.id.fragment_item_txt_name);
        ivAvatar = (ImageView) view.findViewById(R.id.fragment_item_ic_avatar);
        TextView txtDob = (TextView) view.findViewById(R.id.fragment_item_txt_dob);
        TextView txtGender = (TextView) view.findViewById(R.id.fragment_item_txt_gender);
        TextView txtPosition = (TextView) view.findViewById(R.id.fragment_item_txt_position);
        TextView txtSalary = (TextView) view.findViewById(R.id.fragment_item_txt_salary);
        btnMore = (FloatingActionButton) view.findViewById(R.id.fragment_item_btn_more);

        //Click vào icon 3 chấm dọc
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.openMorePopup(id);
            }
        });

        //Đưa dữ liệu vào trong các view
        Bundle bundle = getArguments();
        if(bundle != null) {
            id = bundle.getInt("id");
            int imageId = getResources().getIdentifier("item_" + id, "drawable", getActivity().getPackageName());
            if (imageId != 0)
                ivAvatar.setImageResource(imageId);
            else
                ivAvatar.setImageResource(R.drawable.item_0);
            txtName.setText(bundle.getString("name"));
            txtDob.setText(bundle.getString("dob"));
            txtGender.setText(bundle.getInt("gender") == 1 ? "Nữ" : "Nam");
            txtPosition.setText(bundle.getString("position"));
            txtSalary.setText( Float.toString(bundle.getFloat("salary")) + "$");
        }

        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
