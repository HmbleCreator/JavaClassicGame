package com.digitalchocolate.androidrollergapp;

import com.mascotcapsule.micro3d.v3.Graphics3D;
import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.io.Connector;
import javax.microedition.sensor.ChannelInfo;
import javax.microedition.sensor.Data;
import javax.microedition.sensor.DataListener;
import javax.microedition.sensor.MeasurementRange;
import javax.microedition.sensor.SensorConnection;
import javax.microedition.sensor.SensorInfo;
import javax.microedition.sensor.SensorManager;

/* loaded from: classes.dex */
public class AccelerometerDataListener implements DataListener {
    private static final int ACCELEROMETER_BUFFER_SIZE = 1;
    private static final int BUFFER_SIZE = 10;
    private static final int MAX_ANGLE = 90;
    private static final int MAX_SCALED_VALUE = 1000;
    private static final String RECORD_STORE_NAME = "acc";
    private static final boolean USE_RAW_DATA = false;
    private static int maxXChannelValue;
    private static int maxYChannelValue;
    private static int maxZChannelValue;
    private static int minXChannelValue;
    private static int minYChannelValue;
    private static int minZChannelValue;
    private static SensorConnection sensor;
    private static int smBufferPos;
    private static int smRawMaxXChannelValue;
    private static int smRawMaxYChannelValue;
    private static int smRawMaxZChannelValue;
    private static int smRawMinXChannelValue;
    private static int smRawMinYChannelValue;
    private static int smRawMinZChannelValue;
    private static boolean sm_supported;
    private boolean m_enabled;
    private static int MAX_X_CHANNEL_VALUE = Statics.ACCELEROMETER_DEVICE_MAX_ANGLE_VALUE;
    private static int MAX_Y_CHANNEL_VALUE = Statics.ACCELEROMETER_DEVICE_MAX_ANGLE_VALUE;
    private static int MAX_Z_CHANNEL_VALUE = Statics.ACCELEROMETER_DEVICE_MAX_ANGLE_VALUE;
    private static int IDLE_Y_ANGLE = 0;
    private static int ANGLE_FOR_MAX_ANALOG_VALUE = 45;
    private static int[][] smValueBuffer = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 3, 10);

    public AccelerometerDataListener() {
        if (SensorManager.findSensors("acceleration", null) != null) {
            sm_supported = true;
        } else {
            sm_supported = false;
        }
        if (sm_supported) {
            byte[] record = Toolkit.readRecord(RECORD_STORE_NAME);
            if (record != null) {
                this.m_enabled = record[0] == 1;
            } else {
                this.m_enabled = true;
                Toolkit.writeRecord(RECORD_STORE_NAME, new byte[]{1}, 1);
            }
            initSensor();
            if (this.m_enabled && sm_supported) {
                sensor.setDataListener(this, 1);
            }
        }
    }

    private static void addValueToBuffer(int i, int i2, int i3, int i4) {
        smValueBuffer[i][smBufferPos] = i2 > i3 ? i3 : i2 < i4 ? i4 : i2;
    }

    private static void initSensor() {
        SensorInfo sensorInfo = null;
        SensorInfo[] sensorInfoArrFindSensors = SensorManager.findSensors("acceleration", null);
        String url = "";
        for (int i = 0; i < sensorInfoArrFindSensors.length; i++) {
            sensorInfo = sensorInfoArrFindSensors[i];
            url = sensorInfo.getUrl();
        }
        if (sensorInfo == null) {
            sm_supported = false;
            return;
        }
        ChannelInfo channelInfo = sensorInfo.getChannelInfos()[0];
        ChannelInfo channelInfo2 = sensorInfo.getChannelInfos()[1];
        ChannelInfo channelInfo3 = sensorInfo.getChannelInfos()[2];
        MeasurementRange measurementRange = channelInfo.getMeasurementRanges()[0];
        smRawMaxXChannelValue = (int) measurementRange.getLargestValue();
        smRawMinXChannelValue = (int) measurementRange.getSmallestValue();
        if (MAX_X_CHANNEL_VALUE > 0) {
            maxXChannelValue = MAX_X_CHANNEL_VALUE;
            minXChannelValue = -MAX_X_CHANNEL_VALUE;
        } else {
            maxXChannelValue = smRawMaxXChannelValue;
            minXChannelValue = smRawMinXChannelValue;
        }
        MeasurementRange measurementRange2 = channelInfo2.getMeasurementRanges()[0];
        smRawMaxYChannelValue = (int) measurementRange2.getLargestValue();
        smRawMinYChannelValue = (int) measurementRange2.getSmallestValue();
        if (MAX_Y_CHANNEL_VALUE > 0) {
            maxYChannelValue = MAX_Y_CHANNEL_VALUE;
            minYChannelValue = -MAX_Y_CHANNEL_VALUE;
        } else {
            maxYChannelValue = smRawMaxYChannelValue;
            minYChannelValue = smRawMinYChannelValue;
        }
        MeasurementRange measurementRange3 = channelInfo3.getMeasurementRanges()[0];
        smRawMaxZChannelValue = (int) measurementRange3.getLargestValue();
        smRawMinZChannelValue = (int) measurementRange3.getSmallestValue();
        if (MAX_Z_CHANNEL_VALUE > 0) {
            maxZChannelValue = MAX_Z_CHANNEL_VALUE;
            minZChannelValue = -MAX_Z_CHANNEL_VALUE;
        } else {
            maxZChannelValue = smRawMaxZChannelValue;
            minZChannelValue = smRawMinZChannelValue;
        }
        try {
            sensor = (SensorConnection) Connector.open(url);
        } catch (IOException e) {
            sm_supported = false;
            Debugger.exceptionCaught(e, "AccelerometerController::initSensor");
        }
    }

    private static void resetBuffer(int i) {
        int[] iArr = smValueBuffer[i];
        int i2 = 10;
        while (true) {
            i2--;
            if (i2 < 0) {
                return;
            } else {
                iArr[i2] = 0;
            }
        }
    }

    private static boolean sweepEventOccurred(int i, int i2, int i3) {
        int[] iArr = smValueBuffer[i];
        int i4 = Graphics3D.COMMAND_END;
        int i5 = Integer.MAX_VALUE;
        int i6 = 10;
        while (true) {
            i6--;
            if (i6 < 0) {
                break;
            }
            int i7 = iArr[i6];
            if (i7 > i4) {
                i4 = i7;
            }
            if (i7 < i5) {
                i5 = i7;
            }
        }
        return i4 - i5 >= ((i2 - i3) * 7) / 10;
    }

    public boolean accelerationSupported() {
        return sm_supported;
    }

    @Override // javax.microedition.sensor.DataListener
    public void dataReceived(SensorConnection sensorConnection, Data[] dataArr, boolean z) {
        int i;
        int i2;
        int i3;
        int i4 = -dataArr[0].getIntValues()[0];
        int i5 = -dataArr[1].getIntValues()[0];
        int i6 = -dataArr[2].getIntValues()[0];
        int i7 = IDLE_Y_ANGLE != 0 ? ((maxYChannelValue * IDLE_Y_ANGLE) / 90) + i5 : i5;
        if (ANGLE_FOR_MAX_ANALOG_VALUE > 0) {
            i2 = (i4 * 90) / ANGLE_FOR_MAX_ANALOG_VALUE;
            i = (i7 * 90) / ANGLE_FOR_MAX_ANALOG_VALUE;
            i3 = (i6 * 90) / ANGLE_FOR_MAX_ANALOG_VALUE;
        } else {
            i = i7;
            i2 = i4;
            i3 = i6;
        }
        if (i2 < minXChannelValue) {
            i2 = minXChannelValue;
        } else if (i2 > maxXChannelValue) {
            i2 = maxXChannelValue;
        }
        if (i < minYChannelValue) {
            i = minYChannelValue;
        } else if (i > maxYChannelValue) {
            i = maxYChannelValue;
        }
        int i8 = i3 < minZChannelValue ? minZChannelValue : i3 > maxZChannelValue ? maxZChannelValue : i3;
        int i9 = (i2 * 1000) / maxXChannelValue;
        int i10 = (i * 1000) / maxYChannelValue;
        int i11 = (i8 * 1000) / maxZChannelValue;
        addValueToBuffer(0, i4, smRawMaxXChannelValue, smRawMinXChannelValue);
        addValueToBuffer(1, i5, smRawMaxYChannelValue, smRawMinYChannelValue);
        addValueToBuffer(2, i6, smRawMaxZChannelValue, smRawMinZChannelValue);
        smBufferPos++;
        smBufferPos %= 10;
        DChocMIDlet dChocMIDlet = DChocMIDlet.getInstance();
        dChocMIDlet.controllerEventOccurred(1, 4, i9, i10, i11);
        if (sweepEventOccurred(0, smRawMaxXChannelValue, smRawMinXChannelValue)) {
            resetBuffer(0);
            dChocMIDlet.controllerEventOccurred(1, 2000, i9, i10, i11);
        }
        if (sweepEventOccurred(1, smRawMaxYChannelValue, smRawMinYChannelValue)) {
            resetBuffer(1);
            dChocMIDlet.controllerEventOccurred(1, IController.ACCELEROMETER_EVENT_SWEEP_Y, i9, i10, i11);
        }
        if (sweepEventOccurred(2, smRawMaxZChannelValue, smRawMinZChannelValue)) {
            resetBuffer(2);
            dChocMIDlet.controllerEventOccurred(1, IController.ACCELEROMETER_EVENT_SWEEP_Z, i9, i10, i11);
        }
    }

    public boolean isEnabled() {
        return this.m_enabled;
    }

    public void setEnabled(boolean z) {
        if (this.m_enabled != z) {
            this.m_enabled = z;
            byte[] bArr = new byte[1];
            bArr[0] = (byte) (this.m_enabled ? 1 : 0);
            Toolkit.writeRecord(RECORD_STORE_NAME, bArr, 1);
        }
        if (z) {
            sensor.setDataListener(this, 1);
        } else {
            sensor.removeDataListener();
        }
    }
}
