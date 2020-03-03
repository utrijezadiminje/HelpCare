package com.example.helpcare.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.helpcare.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private Button task;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.home_fragment, container, false);


        task=view.findViewById(R.id.btnZadatak);

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Runnable progressRunnable = new Runnable() {
                    @Override
                    public void run() {
                          getActivity().getSupportFragmentManager()
                          .beginTransaction()
                          .setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
                          .replace(R.id.taskFrame,new TaskFragment())
                                  .commit();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 600);

            }
        });
        return view;
    }

}
