package com.example.listeplanetes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    PlaneteAdapter adapter;
    Data data;
    Button btnVerifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listView);
        this.data = new Data();
        adapter = new PlaneteAdapter(this, data);
        listview.setAdapter(adapter);

        btnVerifier = (Button) findViewById(R.id.btnVerifier);
        btnVerifier.setEnabled(false);
        btnVerifier.setOnClickListener(btnVerifierOnClickListener);
    }

    //----------------------------------------------------------------------------------------------
    View.OnClickListener btnVerifierOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int compteur = 0;
            for (int i = 0; i < adapter.getCount(); i++) {
                View itemview = (View) listview.getChildAt(i);
                Spinner spin = itemview.findViewById(R.id.spinner);
                if (data.getTaillePlanetes()[i].equals(spin.getSelectedItem().toString())) {
                    compteur++;
                }
            }
            popUp(compteur+" bonnes réponses");
        }
    };

    public void popUp(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}