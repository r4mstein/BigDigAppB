package ua.r4mste1n.digitals.big.bigdigappb.main.service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import hugo.weaving.DebugLog;
import io.reactivex.Observable;
import ua.r4mste1n.digitals.big.bigdigappb.R;
import ua.r4mste1n.digitals.big.bigdigappb.main.Constants.ServiceKeys;
import ua.r4mste1n.digitals.big.bigdigappb.main.home_fragment.models.PictureData;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.DBManager;
import ua.r4mste1n.digitals.big.bigdigappb.root.db_manager.IDBManager;

/**
 * Created by Alex Shtain on 04.11.2018.
 */
public final class DataService extends IntentService {

    private static final String CHANNEL_ID = "download_data_channel";

    private PictureData mData;
    private IDBManager mDBManager;

    public DataService() {
        super("DataService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDBManager = new DBManager(this);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    @Override
    protected void onHandleIntent(@Nullable final Intent _intent) {
        if (_intent != null && _intent.hasExtra(ServiceKeys.DATA_KEY)) {
            mData = (PictureData) _intent.getSerializableExtra(ServiceKeys.DATA_KEY);
        }

        if (mData != null) {
            Observable.timer(15, TimeUnit.SECONDS)
                    .subscribe(time -> {
                        if (mDBManager.delete(mData) > 0)
                            createNotification("Link was deleted from database");
                    });
        }
    }

    @SuppressWarnings("SameParameterValue")
    @DebugLog
    private void createNotification(final String message) {
        final NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // The user-visible name of the channel.
            final CharSequence name = "Download data";
            final int importance = NotificationManager.IMPORTANCE_DEFAULT;
            final NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            // Configure the notification channel.
            mChannel.setShowBadge(false);
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            if (mNotificationManager != null) mNotificationManager.createNotificationChannel(mChannel);
        }

        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationBuilder
                .setContentTitle(getText(R.string.app_name))
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND);

        if (mNotificationManager != null) mNotificationManager.notify(createNotificationId(), notificationBuilder.build());
    }

    @DebugLog
    private int createNotificationId(){
        return Integer.parseInt(new SimpleDateFormat("ddHHmmss",  Locale.US).format(new Date()));
    }
}
