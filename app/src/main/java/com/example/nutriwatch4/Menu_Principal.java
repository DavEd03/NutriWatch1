package com.example.nutriwatch4;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Menu_Principal extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seguimient_al), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth=FirebaseAuth.getInstance();
    }
    public void Notificacion (View view){
        Intent i = new Intent(this, Notification.class);
        startActivity(i);
    }
    public void User (View view){
        Intent i = new Intent(this, Usuario.class);
        startActivity(i);
    }

    public void control_S(View view){
        Intent cambio= new Intent(this, control_salud.class);
    startActivity(cambio);
    }
    public void recomendaciones_ej(View view){
        Intent i= new Intent(this, recomendaciones_ej.class);
        startActivity(i);
    }
    public void seguimiento_ali(View view){
        Intent i= new Intent(this, seguimiento_al.class);
        startActivity(i);
    }
    public void cerrar(View v){
        mAuth.signOut();
        finish();
        Intent ini= new Intent(this,MainActivity.class);
        startActivity(ini);
    }
}