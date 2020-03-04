package com.example.helpcare.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        boolean check=sharedPreferences.getBoolean("check",false);
        boolean open=sharedPreferences.getBoolean("open",false);
        Log.d("nav",check+" ");
        if(open)
            openFragmet();

        task=view.findViewById(R.id.btnZadatak);

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFragmet();

                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                editor.putBoolean("open",true);
                editor.putBoolean("check",true);
                editor.commit();
            }
        });
        return view;
    }

    private void openFragmet()
    {

        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
                        .replace(R.id.taskFrame,new TaskFragment())
                        .addToBackStack(null)
                        .commit();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 300);
    }

}
