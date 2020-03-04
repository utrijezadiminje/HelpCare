package com.example.helpcare.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.helpcare.Classes.Tasks;
import com.example.helpcare.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TaskFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap gmap;
    private ArrayList<Tasks> tasks=new ArrayList<Tasks>();

    private TextView ime;
    private TextView broj;
    private TextView adresa;
    private TextView dijagnoza;
    private TextView terapija;
    private ImageButton check;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.task_fragment, container, false);

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        ime=view.findViewById(R.id.tvName);
        broj=view.findViewById(R.id.tvNumber);
        adresa=view.findViewById(R.id.tvAdress);
        dijagnoza=view.findViewById(R.id.tvDijagnoza);
        terapija=view.findViewById(R.id.tvterapija);
        check=view.findViewById(R.id.btnCheck);

        Tasks task1=new Tasks("Marko Marković","Somborska 37","0652018097","Dijabetes","Insulin 10mg",new LatLng(43.334247, 21.914090));
        Tasks task2=new Tasks("Nikola Nikolic","Radoja Dikića 32","0602356854","Cerebralna paraliza","Menjanje pelena",new LatLng(43.321677,21.931396));
        Tasks task3=new Tasks("Andrej Milinkovic","Ćirila i Metodija 56","0615258971","Karcinom besike","Menjanje katetera",new LatLng(43.322296,21.903886));
        Tasks task4=new Tasks("Jelena Mitrović","Knjaževačka 128","0652255664","Parkinsonova bolest","Dopamin admeda 50",new LatLng(43.329632,21.928233));

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

                int br=sharedPreferences.getInt("task",0);
                br++;
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("task",br);
                editor.putBoolean("check",false);
                editor.putBoolean("open",false);
                editor.commit();

                getActivity().getSupportFragmentManager().beginTransaction().remove(TaskFragment.this).commit();
            }
        });


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gmap = googleMap;
        showTask();
        onResume();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void display(Tasks task)
    {
        ime.setText(task.getImePrezime());
        adresa.setText(task.getAdresa());
        broj.setText(task.getBroj());
        dijagnoza.setText(task.getDijagnoza());
        terapija.setText(task.getTerapija());

        LatLng ny = task.getLatLng();
        gmap.addMarker(new MarkerOptions().position(ny));
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        gmap.setMinZoomPreference(15);
    }
    private void showTask()
    {
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        int br=sharedPreferences.getInt("task",0);
        if(br>=4)
            br=0;
        display(tasks.get(br));
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("task",br);
        editor.commit();
    }
}
