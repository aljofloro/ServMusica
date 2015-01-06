package com.exgperu.servmusica;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Beto on 29/12/2014.
 */
public class ServicioMusica extends Service {

    MediaPlayer reproductor;

    private static final int FM_NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado",Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio);



    }


    @Override
    /*public void onStart(Intent intent, int startId){
        Toast.makeText(this, "Servicio arrancado " + startId,Toast.LENGTH_SHORT).show();
        reproductor.start();
    }*/
    public int onStartCommand(Intent intenc, int flags, int idArranque) {
        Toast.makeText(this,"Servicio arrancado "+ idArranque,Toast.LENGTH_SHORT).show();
        reproductor.start();

        long[] vibrate = {1000, 1000, 1000, 1000, 1000};

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setVibrate(vibrate);
        builder.setLights(Color.RED, 3000, 3000);
        builder.setContentTitle("Notificación de Música");
        builder.setContentText("Servicio de Música");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(FM_NOTIFICATION_ID, builder.build());



        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Servicio detenido",Toast.LENGTH_SHORT).show();

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(FM_NOTIFICATION_ID);


        reproductor.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
