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

public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button Access;
    Button newacount;
    FirebaseAuth mAuth;
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
        try{
            mAuth=FirebaseAuth.getInstance();
            String correo, contra;
            correo=email.getText().toString().trim();
            contra=password.getText().toString().trim();
            if(correo.isEmpty()&&contra.isEmpty()){
                Toast.makeText(MainActivity.this, "Vuelva a ingresar los datos",Toast.LENGTH_LONG).show();
            }else{
                mAuth.signInWithEmailAndPassword(correo,contra).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isComplete()){
                           Intent i =new Intent(MainActivity.this,Menu_Principal.class);
                           startActivity(i);
                       }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Error al iniciar sesión "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"Error al iniciar sesión "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void Registro (View view){
        Intent i = new Intent(this, register.class);
        startActivity(i);
    }
}