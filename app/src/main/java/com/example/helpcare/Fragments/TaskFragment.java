package com.example.helpcare.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helpcare.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TaskFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.task_fragment, container, false);

        return view;
    }
}
