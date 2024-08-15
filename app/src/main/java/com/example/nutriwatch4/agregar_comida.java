package com.example.nutriwatch4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class agregar_comida extends AppCompatActivity {
    private String dia;
    FirebaseAuth mAuth;
    String userId;
    private DatabaseReference databaseReference;
    private EditText edAlm,edCol1, edCom,edCol2,edCen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_comida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth= FirebaseAuth.getInstance();
        Bundle datos= getIntent().getExtras();
        userId= datos.getString("nUsuario");

        edAlm=findViewById(R.id.edtAlmuerzoA);
        edCol1=findViewById(R.id.edtColacion1A);
        edCom=findViewById(R.id.edtComidaA);
        edCol2=findViewById(R.id.edtColacion2A);
        edCen=findViewById(R.id.edtCenaA);
        mAuth= FirebaseAuth.getInstance();
        // Definir el Spinner
        Spinner spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek);

        // Crear un ArrayAdapter usando el array de días y un layout por defecto para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_of_week_array, android.R.layout.simple_spinner_item);

        // Especificar el layout a usar cuando la lista de opciones aparece
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicar el adaptador al Spinner
        spinnerDaysOfWeek.setAdapter(adapter);

        // Configurar un listener para obtener el día seleccionado
        spinnerDaysOfWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el día seleccionado como un String
                String selectedDay = parent.getItemAtPosition(position).toString();
                dia=selectedDay;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void agregar_dieta(View view){
       try {
           String almuerzo = edAlm.getText().toString().trim();
           String colacion1 = edCol1.getText().toString().trim();
           String comida = edCom.getText().toString().trim();
           String colacion2 = edCol2.getText().toString().trim();
           String cena = edCen.getText().toString().trim();
           if (almuerzo.isEmpty() || colacion1.isEmpty() || comida.isEmpty() || colacion2.isEmpty() || cena.isEmpty()) {
               Toast.makeText(agregar_comida.this, "No puede estar vacío este campo", Toast.LENGTH_SHORT).show();
           } else {
               comidas datos= new comidas(almuerzo,colacion1,comida,colacion2,cena,0);
               FirebaseDatabase database = FirebaseDatabase.getInstance();
               DatabaseReference myRef = database.getReference("Usuarios/" + userId + "/Comidas/"+dia);
               myRef.setValue(datos).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       // Datos actualizados con éxito
                       Toast.makeText(getApplicationContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                        clear1();
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       // Error al actualizar los datos
                       Toast.makeText(getApplicationContext(), "Error al actualizar los datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });
           }
       }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error en: " + e.getMessage(), Toast.LENGTH_SHORT).show();
       }
    }
    public void seguimiento_ali(View view){
        Intent i= new Intent(this, seguimiento_al.class);
        i.putExtra("nUsuario",userId);
        startActivity(i);
    }
    private void clear1(){
        edAlm.setText("");
        edCol1.setText("");
        edCom.setText("");
        edCol2.setText("");
        edCen.setText("");
        Spinner mySpinner = findViewById(R.id.spinnerDaysOfWeek);
        // Restablecer el spinner al primer elemento
        mySpinner.setSelection(0);

    }
    public void MenuP (View view){
        Intent i = new Intent(this, Menu_Principal.class);
        startActivity(i);
    }

}
