package com.example.nutriwatch4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class control_salud extends AppCompatActivity {
    FirebaseAuth mAuth;
    String userId;
    private DatabaseReference databaseReference;
    private EditText edtidM, edtcomidasC, edtEnferm, edtCalorias, edtMedic,edtTel, edtNutrio, edtnumNutri1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_control_salud);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seguimient_al), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtidM=findViewById(R.id.edtNMedico);
        edtcomidasC=findViewById(R.id.edtcomidas);
        edtEnferm=findViewById(R.id.edtEnfer);
        edtCalorias=findViewById(R.id.edtTotalC);
        edtMedic=findViewById(R.id.edtmedicina);
        edtTel=findViewById(R.id.edtNumber);
        edtNutrio=findViewById(R.id.edtnutri);
        edtnumNutri1=findViewById(R.id.edtnumNutri);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId= currentUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios").child(userId).child("Datos_medicos");
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
                    String calorias = dataSnapshot.child("caloriasC").getValue(String.class);
                    String comidas = dataSnapshot.child("comidasC").getValue(String.class);
                    String enfermedades= dataSnapshot.child("enfermedades").getValue(String.class);
                    String id= dataSnapshot.child("idM").getValue(String.class);
                    String medicamento = dataSnapshot.child("medicamentos").getValue(String.class);
                    String contactoN= dataSnapshot.child("nNutriologo").getValue(String.class);
                    String nutrio = dataSnapshot.child("nutriologo").getValue(String.class);
                    String contacto=dataSnapshot.child("telefono").getValue(String.class);

                    edtidM.setText(id);
                    edtcomidasC.setText(comidas);
                    edtEnferm.setText(enfermedades);
                    edtCalorias.setText(calorias);
                    edtMedic.setText(medicamento);
                    edtTel.setText(contacto);
                    edtNutrio.setText(nutrio);
                    edtnumNutri1.setText(contactoN);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(control_salud.this, "Error al obtener la información del usuario, intente más tarde",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void MenuP (View view){
        Intent i = new Intent(this, Menu_Principal.class);
        startActivity(i);
    }
    public void actualizar(View view){
        try{
            String id=edtidM.getText().toString().trim();
            String comidas=edtcomidasC.getText().toString().trim();
            String enfermedades=edtEnferm.getText().toString().trim();
            String calorias=edtCalorias.getText().toString().trim();
            String medicamento=edtMedic.getText().toString().trim();
            String contacto=edtTel.getText().toString().trim();
            String nutriologo=edtNutrio.getText().toString().trim();
            String numNutriologo=edtnumNutri1.getText().toString().trim();

            fichaM datos= new fichaM(id,comidas,enfermedades, calorias,medicamento,contacto,nutriologo,numNutriologo);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Usuarios/" + userId + "/Datos_medicos/");
            myRef.setValue(datos).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // Datos actualizados con éxito
                    actualizardatos();
                    Toast.makeText(getApplicationContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Error al actualizar los datos
                    Toast.makeText(getApplicationContext(), "Error al actualizar los datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){

        }
    }
    private void actualizardatos(){
        if (databaseReference != null) {
            // Escuchar los datos de Firebase
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String calorias = dataSnapshot.child("caloriasC").getValue(String.class);
                    String comidas = dataSnapshot.child("comidasC").getValue(String.class);
                    String enfermedades= dataSnapshot.child("enfermedades").getValue(String.class);
                    String id= dataSnapshot.child("idM").getValue(String.class);
                    String medicamento = dataSnapshot.child("medicamentos").getValue(String.class);
                    String contactoN= dataSnapshot.child("nNutriologo").getValue(String.class);
                    String nutrio = dataSnapshot.child("nutriologo").getValue(String.class);
                    String contacto=dataSnapshot.child("telefono").getValue(String.class);

                    edtidM.setText(id);
                    edtcomidasC.setText(comidas);
                    edtEnferm.setText(enfermedades);
                    edtCalorias.setText(calorias);
                    edtMedic.setText(medicamento);
                    edtTel.setText(contacto);
                    edtNutrio.setText(nutrio);
                    edtnumNutri1.setText(contactoN);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(control_salud.this, "Error al obtener la información del usuario, intente más tarde",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}