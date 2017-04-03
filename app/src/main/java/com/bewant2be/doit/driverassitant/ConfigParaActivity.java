package com.bewant2be.doit.driverassitant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfigParaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_para);

        Button btnSet = (Button)findViewById(R.id.btnSet);
        final EditText editTextLicencePlate = (EditText)findViewById(R.id.editTextLicencePlate);
        final EditText editTextEngineId = (EditText)findViewById(R.id.editTextEngineId);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("config", MODE_PRIVATE).edit();
                editor.putString("licencePlate", editTextLicencePlate.getText().toString() );
                editor.putString("enginID", editTextEngineId.getText().toString() );
                editor.commit();

                finish();
            }
        });

    }

}
