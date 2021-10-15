package funix.prm.prm391x_alarmclock_quangnmfx02552;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static android.content.Context.ACTIVITY_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isRunning = false;
        String string = intent.getExtras().getString("extra");

        // nếu báo thức đang chạy thì isRunning là true
        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (Music.class.getName().equals(service.service.getClassName())) {
                isRunning = true;
            }
        }

        Intent mIntent = new Intent(context, Music.class);
        // chỉ chạy nếu isRunning là false để không chạy nhiều báo thức cùng lúc
        if (string.equals("on") && !isRunning) {
            // chạy báo thức
            context.startService(mIntent);
            // chỉnh activeAlarm để xác định alarm đang chạy
            MainActivity.activeAlarm = intent.getExtras().getString("active");
        } else if (string.equals("off")) {
            // dừng báo thức
            context.stopService(mIntent);
            MainActivity.activeAlarm = "";
        }
    }
}
