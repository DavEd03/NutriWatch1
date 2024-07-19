package com.example.nutriwatch4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText nombre;
    EditText contra;
    EditText email;
    EditText edad;
    EditText ciudad;
    Button regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        nombre=(EditText)findViewById(R.id.name);
        contra=(EditText)findViewById(R.id.usr_contra);
        email=(EditText)findViewById(R.id.correo);
        edad=(EditText)findViewById(R.id.fec_nac);
        ciudad=(EditText)findViewById(R.id.city);
        regis=(Button) findViewById(R.id.regis);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seguimient_al), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Register (View view){
        try {
        mAuth= FirebaseAuth.getInstance();
        String Nombres = (String) nombre.getText().toString().trim();
        String Apellidos = (String)contra.getText().toString().trim();
        if(Nombres.isEmpty()&& Apellidos.isEmpty()){
            Toast.makeText(register.this, "Ingresa los datos correctos", Toast.LENGTH_LONG).show();
        }else{
            mAuth.createUserWithEmailAndPassword(Nombres,Apellidos).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isComplete()){
                        Toast.makeText(register.this,"Usuario registrado exitosamente",Toast.LENGTH_LONG).show();
                    }
                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(register.this,"Error "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
        }catch (Exception e){
            Toast.makeText(register.this,"Error "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }



    }
}