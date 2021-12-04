package com.amirmohammed.androidultrassat.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.amirmohammed.androidultrassat.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFCMService extends FirebaseMessagingService {
    private static final String TAG = "MyFCM";
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void showNotification(String title, String body) {
        final String CHANNEL_ID = "NOTIFICATIONS";

        // Notification => (3) Manager, Channel, Builder
        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        Intent intent = new Intent(this, FirebaseSplashActivity.class);
//        intent.putExtra("offerId", "1");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.firebase_logo)
                .setColor(0x169AB9) //for color: #169AB9
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "NOTIFICATIONS", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(new Random().nextInt(), builder.build());

    }


}
