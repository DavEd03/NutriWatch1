package com.example.nutriwatch4;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Usuario extends AppCompatActivity {
    FirebaseAuth mAuth;
    String userId;
    private EditText edtNombre, edtEdad, edtPeso, edtTalla, edtEst, edtImc,edtEnf;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seguimient_al), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        edtEdad=findViewById(R.id.edtedad);
        edtNombre=findViewById(R.id.edtnombre);
        edtPeso=findViewById(R.id.edtpeso);
        edtTalla=findViewById(R.id.edtTalla);
        edtEst= findViewById(R.id.edtEstatura);
        edtImc = findViewById(R.id.edtImc);
        edtEnf= findViewById(R.id.edtEnf);

        mAuth=FirebaseAuth.getInstance();
        Bundle datos= getIntent().getExtras();
        userId= datos.getString("nUsuario");

        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios").child(userId).child("Datos básicos");

    }
    @Override
    protected void onStart(){
        super.onStart();
        if (databaseReference != null) {
            // Escuchar los datos de Firebase
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Leer los datos básicos
                    String nombre = dataSnapshot.child("nombre").getValue(String.class);
                    String edad = dataSnapshot.child("edad").getValue(String.class);
                    String imc= dataSnapshot.child("imc").getValue(String.class);
                    String talla= dataSnapshot.child("talla").getValue(String.class);
                    String Enf = dataSnapshot.child("Enfermedad").getValue(String.class);
                    String peso= dataSnapshot.child("peso").getValue(String.class);
                    String estatura = dataSnapshot.child("estatura").getValue(String.class);


                    // Rellenar los EditText con los datos obtenidos
                   // editTextNombre.setText(nombre);
                   // editTextCorreo.setText(correo);
                   // editTextTelefono.setText(telefono);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Manejar posibles errores
                }
            });
        }
    }

    public void MenuP (View view){
        Intent i = new Intent(this, Menu_Principal.class);
        startActivity(i);
    }


}