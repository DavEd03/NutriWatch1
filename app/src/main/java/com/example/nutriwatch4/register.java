package com.example.nutriwatch4;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class register extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText nombre;
    EditText contra;
    EditText email;
    EditText edad;
    EditText ciudad;
    Button regis;
    private String uid;
    private String state;
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
        regis=(Button) findViewById(R.id.REGRESAR);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seguimient_al), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                state ="YES";
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(register.this, "Ha ocurrido un error de conexión, intente más tarde", Toast.LENGTH_SHORT).show();
                state="NO";
            }
        });
    }
    public void MenuP (View view){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("Estado",state);
        startActivity(i);
    }
    public void Register (View view){
        try {
        mAuth= FirebaseAuth.getInstance();
        String Correo = (String) email.getText().toString().trim();
        String Contra = (String)contra.getText().toString().trim();
        if(Correo.isEmpty()&& Contra.isEmpty()){
            Toast.makeText(register.this, "Ingresa los datos correctos", Toast.LENGTH_LONG).show();
        }else{
            mAuth.createUserWithEmailAndPassword(Correo,Contra).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isComplete()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                           uid= user.getUid();
                            Toast.makeText(register.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                            cargarDatos();
                        } else {
                            Toast.makeText(register.this, "EL usuario no existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(register.this,"Error "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
        }catch (Exception e1){
            Toast.makeText(register.this,"Error "+e1.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

    }
    private void cargarDatos()
    {
        try{
            String defau ="No establecido";
            String Nombre= nombre.getText().toString().trim();
            String Edad= edad.getText().toString().trim();
            String Ciudad= ciudad.getText().toString().trim();
            String Correo= email.getText().toString().trim();
            String Peso= defau;
            String Imc= defau;
            String estatura=defau;
            String enfermedades=defau;
            String talla= defau;
            if(Nombre.isEmpty()){
                nombre.setText("Introduzca un valor válido");
            }else if(Edad.isEmpty()){
                edad.setText("Introduzca un valor válido");
            }else if(Ciudad.isEmpty()){
                ciudad.setText("Introduzca un valor válido");
            }else if(Correo.isEmpty()){
                email.setText("Introduzca un valor válida");
            }else{
            //Upload datos
            variables upload= new variables(Nombre, Correo, Edad, Ciudad, Peso, Imc, estatura, enfermedades, talla);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Usuarios/"+ uid + "/Datos básicos/");
            myRef.setValue(upload);
            Toast.makeText(register.this,"Usuario registrado correctamente",Toast.LENGTH_LONG).show();
            crearficha();
            mAuth.signOut();
            nombre.setText("");
            edad.setText("");
            ciudad.setText("");
            email.setText("");
            contra.setText("");
            }
        }catch (Exception e2){
            Toast.makeText(register.this,"Error en: "+e2.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void crearficha(){
// Generar un ID médico único
        String medicalId = UUID.randomUUID().toString();
        String Comidas="No establecido";
        String enfer="Sin datos";
        String calorias="0";
        String medicamento="Sin especificar";
        String telefono="6751153832";
        String nutriologo= "Dra. Pamela Perez ";
        String numNutri="sin especificar";

        // Crear una referencia a la base de datos
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        fichaM datos= new fichaM(medicalId,Comidas,enfer,calorias,medicamento,telefono,nutriologo,numNutri);
        // Almacenar los datos médicos bajo el UID del usuario
        databaseReference.child("Usuarios").child(uid).child("Datos_medicos").child(medicalId).setValue(datos)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Datos médicos almacenados con éxito

                    } else {
                        // Manejar el error

                    }
                });
    }
}