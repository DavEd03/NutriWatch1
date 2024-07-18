package com.example.nutriwatch4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class register extends AppCompatActivity {
    EditText nombre;
    EditText apellido;
    EditText email;
    EditText fecnac;
    EditText ciudad;
    Button regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        nombre=(EditText)findViewById(R.id.name);
        apellido=(EditText)findViewById(R.id.apellido);
        email=(EditText)findViewById(R.id.correo);
        fecnac=(EditText)findViewById(R.id.fec_nac);
        ciudad=(EditText)findViewById(R.id.city);
        regis=(Button) findViewById(R.id.regis);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seguimient_al), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Register (View view){
        String Nombres = (String) nombre.getText().toString();
        String Apellidos = (String) apellido.getText().toString();
        String Correo = (String) email.getText().toString();
        String Fecha_Nacimiento = (String) fecnac.getText().toString();
        String Ciudad = (String) ciudad.getText().toString();
        Intent i = new Intent(this, Confirm_Register.class);
        startActivity(i);
    }
}