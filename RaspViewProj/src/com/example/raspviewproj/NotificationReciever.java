package com.example.raspviewproj;

import java.util.ArrayList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class NotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.parovoz_small, "Спешите!!!", System.currentTimeMillis());
        Intent intentTL = new Intent(context, MainActivity.class);
        Uri uri= intent.getData();
        notification.setLatestEventInfo(context, "Расписание", "Не опопоздайте на паровоз",
                PendingIntent.getActivity(context, 0, intentTL,
                        PendingIntent.FLAG_CANCEL_CURRENT)
        );
        notification.defaults = Notification.DEFAULT_ALL;
        nm.notify(1, notification);
        ArrayList<String> list = new ArrayList<String>(AlarmSave.get(context).get_key());
        AlarmSave.get(context).delete(list.indexOf(uri.toString()));
        

    }

}
