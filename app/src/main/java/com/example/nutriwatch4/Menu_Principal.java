package com.example.nutriwatch4;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu_Principal extends AppCompatActivity {
    FirebaseAuth mAuth;
    String userId;
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
        Bundle datos= getIntent().getExtras();
        if(datos != null) {
            userId= datos.getString("nUsuario");
            if (userId == null) {
                // "nUsuario" no existe en el Bundle
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    userId = user.getUid();
                } else {
                    // No se puede obtener el UID, manejar el error
                    showErrorAndRedirect();
                }
            }
        }else{
            FirebaseUser user = mAuth.getCurrentUser();
            if (user!=null) {
                userId = user.getUid();
            }else{
                showErrorAndRedirect();
            }
        }
       // Toast.makeText(this,userId,Toast.LENGTH_SHORT).show();
    }
    public void Notificacion (View view){
        Intent i = new Intent(this, Notification.class);
        i.putExtra("nUsuario",userId);
        startActivity(i);
    }
    public void User (View view){
        Intent i = new Intent(this, Usuario.class);
        i.putExtra("nUsuario",userId);
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
        i.putExtra("nUsuario",userId);
        startActivity(i);
    }
    public void cerrar(View v){
        mAuth.signOut();
        Intent ini= new Intent(this,MainActivity.class);
        startActivity(ini);
        finish();
    }
    private void showErrorAndRedirect() {
        // Mostrar un mensaje de error al usuario
        Toast.makeText(this, "Error al obtener la información del usuario. Por favor, intenta iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
        // Redirigir al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Cerrar la actividad actual
    }

}