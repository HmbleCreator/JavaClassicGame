package javax.microedition.midlet;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.Window;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.sensor.AccelerometerConnection;
import javax.microedition.sensor.DataListener;

/* loaded from: classes.dex */
public abstract class MIDlet extends Activity {
    private static Window a;
    private static MIDlet b;
    private static Vector<Integer> c = new Vector<>();
    private static boolean f = false;
    private AccelerometerConnection d = null;
    private DataListener e = null;

    public MIDlet() {
        b = this;
    }

    public static int getHeight() {
        return a.getWindowManager().getDefaultDisplay().getHeight();
    }

    public static final MIDlet getMIDletInstance() {
        return b;
    }

    public static int getWidth() {
        return a.getWindowManager().getDefaultDisplay().getWidth();
    }

    protected abstract void destroyApp(boolean z) throws MIDletStateChangeException;

    public final String getAppProperty(String str) {
        return null;
    }

    public final void notifyDestroyed() {
        c.removeAllElements();
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        Log.i("LCM", "MIDlet.onConfigurationChanged");
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c.add(Integer.valueOf(Process.myPid()));
        if (f) {
            if (c.size() > 1) {
                Process.killProcess(c.get(0).intValue());
            }
            startActivity(new Intent(b, b.getClass()));
            System.exit(0);
        }
        f = true;
        Window window = b.getWindow();
        a = window;
        window.requestFeature(1);
        a.setFlags(1024, 1024);
        setVolumeControlStream(3);
        Log.i("LCM", "MIDlet.onCreate");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        Log.i("LCM", "MIDlet.onDestroy");
        super.onDestroy();
        try {
            if (this.d != null) {
                try {
                    this.d.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            destroyApp(true);
        } catch (MIDletStateChangeException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        System.out.println("MIDlet.onPause");
        Log.i("LCM", "MIDlet.onPause");
        if (this.d != null) {
            this.e = this.d.getDataListener();
            this.d.removeDataListener();
        }
        pauseApp();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i("LCM", "MIDlet.onResume");
        if (this.d != null) {
            this.d.setDataListener(this.e, 1);
        }
        try {
            startApp();
        } catch (MIDletStateChangeException e) {
        }
    }

    protected abstract void pauseApp();

    public final boolean platformRequest(String str) throws ConnectionNotFoundException {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return false;
        } catch (ActivityNotFoundException e) {
            throw new ConnectionNotFoundException();
        }
    }

    public void setAccelerometerConnection(AccelerometerConnection accelerometerConnection) {
        this.d = accelerometerConnection;
    }

    protected abstract void startApp() throws MIDletStateChangeException;
}
