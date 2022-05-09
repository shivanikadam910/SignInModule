package Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.login.BoundActivity;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BoundService extends Service {

    ScheduledExecutorService myschedule_executor;
    private IBinder binder = new Binder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //Define a ScheduledExecutorService
        myschedule_executor = Executors.newScheduledThreadPool(1);
        //Schedule at every 1 second rate
        myschedule_executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //Update textview in MainActivity
                BoundActivity.textView.setText("Current Time: " + new Date());
            }
        }, 1, 1, TimeUnit.SECONDS);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        //Stop ScheduledExecutorService
        myschedule_executor.shutdown();
        return super.onUnbind(intent);
    }
}
