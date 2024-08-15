package com.example.nutriwatch4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class recomendaciones_ej extends AppCompatActivity {
    FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    TextView TVConsejo1, TVConsejo2, TVConsejo3, TVde1, TVde2, TVde3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recomendaciones_ej);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth=FirebaseAuth.getInstance();
         TVConsejo1=findViewById(R.id.TVConsejo1);
         TVConsejo2=findViewById(R.id.TVConsejo2);
         TVConsejo3=findViewById(R.id.TVConsejo3);
         TVde1=findViewById(R.id.TVdesc1);
         TVde2=findViewById(R.id.TVdesc2);
         TVde3=findViewById(R.id.TVdesc3);

    }
    @Override
    protected void onStart(){
        super.onStart();
        databaseReference = FirebaseDatabase.getInstance().getReference("Consejos");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<consejos> todosLosConsejos = new ArrayList<>();

                // Obtener todos los consejos de Firebase
                for (DataSnapshot consejoSnapshot : dataSnapshot.getChildren()) {
                    consejos consejo = consejoSnapshot.getValue(consejos.class);
                    todosLosConsejos.add(consejo);
                }

                // Seleccionar 3 consejos aleatorios
                List<consejos> consejosAleatorios = obtenerConsejosAleatorios(todosLosConsejos, 3);

                // Mostrar los consejos en la UI
                mostrarConsejosEnPantalla(consejosAleatorios);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    // Método para seleccionar n elementos aleatorios de la lista
    private List<consejos> obtenerConsejosAleatorios(List<consejos> todosLosConsejos, int cantidad) {
        Collections.shuffle(todosLosConsejos); // Mezcla la lista para obtener elementos aleatorios
        return todosLosConsejos.subList(0, Math.min(cantidad, todosLosConsejos.size())); // Devuelve los primeros n elementos
    }

    // Método para mostrar los consejos en la UI
    private void mostrarConsejosEnPantalla(List<consejos> consejosAleatorios) {
        // Asigna el texto de cada consejo a los TextViews correspondientes
        if (consejosAleatorios.size() > 0) {
            TVConsejo1.setText(consejosAleatorios.get(0).titulo);
            TVde1.setText(consejosAleatorios.get(0).descripcion);
        }
        if (consejosAleatorios.size() > 1) {
            TVConsejo2.setText(consejosAleatorios.get(1).titulo);
            TVde2.setText(consejosAleatorios.get(1).descripcion);
        }
        if (consejosAleatorios.size() > 2) {
            TVConsejo3.setText(consejosAleatorios.get(2).titulo);
            TVde3.setText(consejosAleatorios.get(2).descripcion);
        }
    }
    public void MenuP (View view){
        Intent i = new Intent(this, Menu_Principal.class);
        startActivity(i);
    }

}