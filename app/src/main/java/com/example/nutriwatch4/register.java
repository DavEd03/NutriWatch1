package com.example.nutriwatch4;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText nombre;
    EditText apellido;
    EditText email;
    EditText fecnac;
    EditText passw;
    Button regis;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        nombre=(EditText)findViewById(R.id.name);
        apellido=(EditText)findViewById(R.id.apellido);
        email=(EditText)findViewById(R.id.correo);
        fecnac=(EditText)findViewById(R.id.fec_nac);
        passw=(EditText)findViewById(R.id.password);
        regis=(Button) findViewById(R.id.regis);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Register (View view){
        try {
            mAuth=FirebaseAuth.getInstance();
            String Nombres = (String) nombre.getText().toString();
            String Apellidos = (String) apellido.getText().toString();
            String Correo = (String) email.getText().toString();
            String Fecha_Nacimiento = (String) fecnac.getText().toString();
            String contras = (String) passw.getText().toString();
            if(Nombres.isEmpty()||Apellidos.isEmpty()||Correo.isEmpty()||Fecha_Nacimiento.isEmpty()||contras.isEmpty()){
                Toast.makeText(register.this, "Ingresa los datos correctos",Toast.LENGTH_LONG).show();

            }else{
                mAuth.createUserWithEmailAndPassword(Correo, contras).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete()){
                            Toast.makeText(register.this,"Usuario registrado, por favor inicie sesión",Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(register.this,"Error "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
                //Método para cargar datos
                variables upload= new variables(Nombres,Apellidos,Correo,Fecha_Nacimiento,contras);
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myRef= database.getReference("Usuarios/");
                // EXECUTE THE QUERY
                myRef.setValue(upload);
                Bundle bnd1= new Bundle();
                bnd1.putString("estado","Aceptado");
                Intent i= new Intent(register.this, Confirm_Register.class);
                i.putExtras(bnd1);
                startActivity(i);
            }
        }catch (Exception e){
            Toast.makeText(register.this, "Error"+e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(this, Confirm_Register.class);
        startActivity(i);
    }
}