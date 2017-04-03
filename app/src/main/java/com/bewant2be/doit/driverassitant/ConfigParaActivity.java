package com.bewant2be.doit.driverassitant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bewant2be.doit.utilslib.userexperience.*;

public class ConfigParaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_para);

        Button btnSet = (Button)findViewById(R.id.btnSet);
        final EditText editTextProvince = (EditText)findViewById(R.id.editTextProvince);
        final EditText editTextLicencePlate = (EditText)findViewById(R.id.editTextLicencePlate);
        final EditText editTextEngineId = (EditText)findViewById(R.id.editTextEngineId);

        editTextLicencePlate.setTransformationMethod(new InputLowerToUpper());
        editTextEngineId.setTransformationMethod(new InputLowerToUpper());

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("config", MODE_PRIVATE).edit();
                editor.putString("province", editTextProvince.getText().toString() );
                editor.putString("licencePlate", editTextLicencePlate.getText().toString() );
                editor.putString("enginID", editTextEngineId.getText().toString() );
                editor.commit();

                finish();
            }
        });

    }

}
