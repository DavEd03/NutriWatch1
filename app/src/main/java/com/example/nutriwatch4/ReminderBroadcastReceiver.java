package com.example.nutriwatch4;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReminderBroadcastReceiver extends BroadcastReceiver{
    FirebaseAuth mAuth;
    private String uid;
    @Override
    public void onReceive(Context context, Intent intent) {
        // Crear un canal de notificación para Android O y superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "meal_channel",
                    "Meal Notifications",
                    NotificationManager.IMPORTANCE_HIGH  // Alta importancia para heads-up notifications
            );
            channel.setDescription("Notificaciones para recordatorios de comidas");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{0, 500, 1000, 500});

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        // Obtener los datos del Intent
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");
        int requestCode = intent.getIntExtra("requestCode", 0);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] vibrationPattern = {0, 500, 1000, 500};

        // Crear la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "meal_channel")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setVibrate(vibrationPattern)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Alta prioridad para heads-up notifications
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC); // Mostrar en pantalla de bloqueo

        // Crear un Intent para abrir la aplicación
        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, activityIntent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);

        // Forzar heads-up con FullScreenIntent
        builder.setFullScreenIntent(pendingIntent, true);

        // Mostrar la notificación
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(requestCode, builder.build());
        saveNotificationToFirebase(title, message);
    }
    private void saveNotificationToFirebase(String title, String message) {
        // Formatear la fecha actual
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault());
        String formattedDate = dateFormat.format(new Date());

        // Guardar en Firebase
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid= user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference notificationsRef = database.getReference("notificaciones").child(uid); // Reemplaza con el UID del usuario

        String notificationId = notificationsRef.push().getKey();
        Map<String, Object> notificationData = new HashMap<>();
        notificationData.put("titulo", title);
        notificationData.put("mensaje", message);
        notificationData.put("fecha", formattedDate);

        notificationsRef.child(notificationId).setValue(notificationData)
                .addOnSuccessListener(aVoid -> {
                    // Notificación guardada exitosamente
                })
                .addOnFailureListener(e -> {
                    // Manejo de errores al guardar
                });
    }

}
