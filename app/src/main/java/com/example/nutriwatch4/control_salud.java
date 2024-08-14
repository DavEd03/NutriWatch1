package com.example.nutriwatch4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class control_salud extends AppCompatActivity {
    FirebaseAuth mAuth;
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
    }
    public void MenuP (View view){
        Intent i = new Intent(this, Menu_Principal.class);
        startActivity(i);
    }

}