package com.example.nutriwatch4;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class seguimiento_al extends AppCompatActivity {
    FirebaseAuth mAuth;
    String userId;
    EditText edtAlmuerzo, edtCol1, edtCol2, edtComida, edtCena;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seguimiento_al);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seguimient_al), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        edtAlmuerzo=findViewById(R.id.edtAlmuerzo);
        edtCol1=findViewById(R.id.edtCol1);
        edtCol2=findViewById(R.id.edtCol2);
        edtComida=findViewById(R.id.edtComida);
        edtCena=findViewById(R.id.edtCena);
        mAuth= FirebaseAuth.getInstance();
        Bundle datos= getIntent().getExtras();
        userId= datos.getString("nUsuario");
        // En tu actividad o fragmento
        scheduleMealNotifications();

    }
    @Override
    protected void onStart(){
        super.onStart();
        String dayOfWeek = getDayOfWeekName();
        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios").child(userId).child("Comidas").child(dayOfWeek);
        if (databaseReference != null) {
            // Escuchar los datos de Firebase
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String almuerzo = dataSnapshot.child("almuerzo").getValue(String.class);
                    String colacion1 = dataSnapshot.child("colacion1").getValue(String.class);
                    String colacion2 = dataSnapshot.child("colacion2").getValue(String.class);
                    String comida = dataSnapshot.child("comida").getValue(String.class);
                    String cena = dataSnapshot.child("cena").getValue(String.class);
                    //String total = dataSnapshot.child("total").getValue(String.class);

                    edtAlmuerzo.setText(almuerzo);
                    edtCol1.setText(colacion1);
                    edtCol2.setText(colacion2);
                    edtComida.setText(comida);
                    edtCena.setText(cena);
                    //Toast.makeText(seguimiento_al.this, "Para el almuerzo: "+almuerzo,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(seguimiento_al.this, "Error al obtener la información del usuario, intente más tarde",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public String getDayOfWeekName() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        String[] days = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        return days[dayOfWeek - 1]; // Convertir el número en el nombre del día
    }
    private void scheduleMealNotifications() {
        Calendar calendar = Calendar.getInstance();
        long currentTime = System.currentTimeMillis();

        // Desayuno: 8:00 AM
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 57);
        calendar.set(Calendar.SECOND, 0);
        if (calendar.getTimeInMillis() > currentTime) {
            setAlarm(this, calendar.getTimeInMillis(), 1, "Desayuno", "¡Es hora de desayunar!");
        }

        // Almuerzo: 1:00 PM
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 30);
        if (calendar.getTimeInMillis() > currentTime) {
            setAlarm(this, calendar.getTimeInMillis(), 2, "Almuerzo", "¡Es hora de almorzar!");
        }

        // Merienda: 4:00 PM
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 30);
        if (calendar.getTimeInMillis() > currentTime) {
            setAlarm(this, calendar.getTimeInMillis(), 3, "Merienda", "¡Es hora de la merienda!");
        }

        // Cena: 7:00 PM
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        if (calendar.getTimeInMillis() > currentTime) {
            setAlarm(this, calendar.getTimeInMillis(), 4, "Cena", "¡Es hora de cenar!");
        }

        // Snack nocturno: 9:00 PM
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        if (calendar.getTimeInMillis() > currentTime) {
            setAlarm(this, calendar.getTimeInMillis(), 5, "Snack nocturno", "¡Es hora del snack nocturno!");
        }
    }

    private void setAlarm(Context context, long triggerTime, int requestCode, String title, String message) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, ReminderBroadcastReceiver.class);
        intent.putExtra("title", title);
        intent.putExtra("message", message);
        intent.putExtra("requestCode", requestCode);

        PendingIntent pendingIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
        }
    }
    public void control_S(View view){
        Intent cambio= new Intent(this, control_salud.class);
        startActivity(cambio);
    }
    /*public void Agg_comida (View view){
        Intent i= new Intent(this, agregar_comida.class);
        startActivity(i);
    }*/
    public void MenuP (View view){
        Intent i = new Intent(this, Menu_Principal.class);
        startActivity(i);
    }
    public void seguimiento_ali(View view){
        Intent i= new Intent(this, seguimiento_al.class);
        startActivity(i);
    }

    }