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

public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button Access;
    Button newacount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        email=(EditText)findViewById(R.id.correo);
        password=(EditText)findViewById(R.id.contrasena);
        Access=(Button) findViewById(R.id.acceder);
        newacount=(Button) findViewById(R.id.newcuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void MenuP (View view){
        String User = (String) email.getText().toString();
        String Password = (String) password.getText().toString();
        Intent i = new Intent(this, Menu_Principal.class);
        i.putExtra("x",User);
        i.putExtra("y",Password);
        startActivity(i);
    }
    public void Registro (View view){
        String User = (String) email.getText().toString();
        String Password = (String) password.getText().toString();
        Intent i = new Intent(this, register.class);
        i.putExtra("x",User);
        i.putExtra("y",Password);
        startActivity(i);
    }
}