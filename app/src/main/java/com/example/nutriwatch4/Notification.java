package com.example.nutriwatch4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Notification extends AppCompatActivity {
    private ArrayList<String> arraList;
    private ListView notis;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seguimient_al), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        arraList=new ArrayList<>();
        notis=findViewById(R.id.Notis);
        Bundle datos= getIntent().getExtras();
        userId= datos.getString("nUsuario");
    }
    @Override
    protected void onStart(){
        super.onStart();
        obtenerNotis();
    }
    private void obtenerNotis(){
        try{
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ruta= database.getReference("notificaciones").child(userId);
        ruta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arraList.clear();
                for(DataSnapshot variables:snapshot.getChildren()){
                    HashMap dlibros=(HashMap) variables.getValue();
                    String Titulo= String.valueOf(dlibros.get("titulo").toString());
                    String Mensaje= String.valueOf(dlibros.get("mensaje").toString());
                    String Fecha= String.valueOf(dlibros.get("fecha").toString());

                    arraList.add("Titulo: "+Titulo+" ,Mensaje: "+Mensaje+" ,Fecha: "+Fecha);

                }
                ArrayAdapter<String> adaptador= new ArrayAdapter<>(getBaseContext(),
                        android.R.layout.simple_expandable_list_item_1,arraList);
                notis.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Notification.this,"Error al recuperar los datos",Toast.LENGTH_LONG).show();
            }
        });

    }catch (Exception e){
        Toast.makeText(Notification.this,"Error en: "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
    }
}

    public void MenuP (View view){
        Intent i = new Intent(this, Menu_Principal.class);
        startActivity(i);
    }
    public void User (View view){
        Intent i = new Intent(this, Usuario.class);
        i.putExtra("nUsuario",userId);
        startActivity(i);
    }
}