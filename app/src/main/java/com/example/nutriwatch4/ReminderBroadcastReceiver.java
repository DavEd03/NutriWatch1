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

public class ReminderBroadcastReceiver extends BroadcastReceiver{
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
    }
}
