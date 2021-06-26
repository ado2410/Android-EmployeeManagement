package com.example.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MoreFragment extends BottomSheetDialogFragment {
    MainActivity mainActivity;
    Button editButton;
    Button removeButton;
    Bundle bundle = getArguments();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container);

        editButton = (Button) view.findViewById(R.id.fragment_more_edit);
        removeButton = (Button) view.findViewById(R.id.fragment_more_remove);

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        //Bấm nút chỉnh sửa trong khung
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.openEditActivity(id);
                dismiss();
            }
        });

        //Bấm nút xóa trong khung
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.removeItem(id);
                dismiss();
            }
        });

        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
