package com.example.silasonyango.ewe;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by ${Qortez} on 1/23/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "FROM:" + remoteMessage.getFrom());

        //Check if the message contains data
        if(remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Messagedata:" + remoteMessage.getData());



                Map<String, String> data = remoteMessage.getData();
                String body = data.get("body");
                String title = data.get("title");
            String NewsId = data.get("NewsId");
            String Topic = data.get("Topic");
            String StartIndex = data.get("StartIndex");
            String EndIndex = data.get("EndIndex");
            String Name = data.get("Name");
                String click_action = data.get("click_action");
            Log.d(TAG, "StartIndex:" + StartIndex+EndIndex+Topic);
            sendNotification(body,title,click_action,NewsId,Topic,StartIndex,EndIndex,Name);


        }

        //Check if the message contains notification

        if(remoteMessage.getNotification() != null) {
            Log.d(TAG, "Mesage body:" + remoteMessage.getNotification().getBody());



            sendNotification(remoteMessage.getNotification().getBody());
        }
    }
    /**
     * Dispay the notification
     *
     * @param body
     * @param title
     * @param body
     */
    private void sendNotification(String body, String title, String click_action, String NewsId,String Topic,String StartIndex,String EndIndex,String Name) {

        Intent intent = null;
        if(click_action.equals("BOOKINGS")){

            intent = new Intent(this, MainActivity.class);
            intent.putExtra("Topic",Topic);
            intent.putExtra("StartIndex",StartIndex);
            intent.putExtra("EndIndex",EndIndex);
            intent.putExtra("BookMark","firebase");
            intent.putExtra("Name",Name);



        }else if(click_action.equals("NEWS")){
            intent = new Intent(this, News.class);
            intent.putExtra("NewsId",NewsId);

        }

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0/*Request code*/,
                intent, PendingIntent.FLAG_ONE_SHOT);
        //Set sound of notification
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(body);
        bigText.setBigContentTitle(title);
        bigText.setSummaryText("PPRA News/Event");
        notifiBuilder.setStyle(bigText);
        notifiBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(0 /*ID of notification*/, notifiBuilder.build());
        }
    }
    /**
     * Dispay the notification
     * @param body
     */
    private void sendNotification(String body) {

        Intent intent = new Intent(this, SearchResults.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0/*Request code*/, intent, PendingIntent.FLAG_ONE_SHOT);
        //Set sound of notification
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Firebase Cloud Messaging")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(0 /*ID of notification*/, notifiBuilder.build());
        }
    }
}

