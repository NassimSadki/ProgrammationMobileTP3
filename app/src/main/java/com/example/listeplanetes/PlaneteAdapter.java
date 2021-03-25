package com.example.listeplanetes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

class PlaneteAdapter extends BaseAdapter {

    private MainActivity MainActivity;
    private Data data;
    private int compteur = 0;


    public PlaneteAdapter(MainActivity mainActivity, Data data) {
        this.MainActivity = mainActivity;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.getPlanetes().size();
    }

    @Override
    public Object getItem(int arg0) {
        return data.getPlanetes().get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    MainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.listitem, null);
        }

        TextView nomPlanete = (TextView) itemView.findViewById(R.id.textView);
        final CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        final Spinner spinner = (Spinner) itemView.findViewById(R.id.spinner);

        nomPlanete.setText(data.getPlanetes().get(position));

        //  installer l'adaptateur pour la liste déroulante (spinner)
        String[] taillePlanetes = {"4900", "12000", "12800", "6800", "144000", "120000", "52000", "50000", "2300"};
        final ArrayAdapter<String> spinadapter =
                new ArrayAdapter<String>(MainActivity, android.R.layout.simple_spinner_item, taillePlanetes);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Button btnVerifier = MainActivity.findViewById(R.id.btnVerifier);

                // on vérifie que tous les choix ont été validés avant de permettre d'appuyer sur le bouton vérifier
                if (checkBox.isChecked()){
                    compteur++;
                }
                else {
                    compteur--;
                }
                if (compteur >= data.getPlanetes().size()){
                    btnVerifier.setEnabled(true);
                }
                else {
                    if (btnVerifier.isEnabled()){
                        btnVerifier.setEnabled(false);
                    }
                }
                spinner.setEnabled(!checkBox.isChecked());
                spinadapter.notifyDataSetChanged();
            }
        });

        return itemView;
    }
}
