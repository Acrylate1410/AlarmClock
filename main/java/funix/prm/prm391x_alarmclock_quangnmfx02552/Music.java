package funix.prm.prm391x_alarmclock_quangnmfx02552;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class Music extends Service {
    private MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent)  {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // phát audio
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // dừng audio
        super.onDestroy();
        mediaPlayer.stop();
    }
}
