package javax.microedition.rms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class RecordStore {
    private File a;

    private RecordStore(String str, File file) {
        this.a = file;
    }

    public static void deleteRecordStore(String str) throws RecordStoreException {
        File fileStreamPath = MIDlet.getMIDletInstance().getFileStreamPath(str + "_data");
        File file = new File(fileStreamPath, "1");
        if (!file.exists()) {
            throw new RecordStoreNotFoundException("");
        }
        if (!file.delete()) {
            throw new RecordStoreException("unable to delete Resord Store " + str);
        }
        fileStreamPath.delete();
    }

    public static String[] listRecordStores() {
        return null;
    }

    public static RecordStore openRecordStore(String str, boolean z) throws Exception {
        File file;
        boolean zCreateNewFile;
        File fileStreamPath = MIDlet.getMIDletInstance().getFileStreamPath(str + "_data");
        if (!fileStreamPath.exists() && !z) {
            throw new RecordStoreNotFoundException("Record Store " + str + " does not exist");
        }
        if (fileStreamPath.exists() || !z) {
            file = new File(fileStreamPath, "1");
        } else {
            try {
                if (fileStreamPath.mkdirs()) {
                    File file2 = new File(fileStreamPath, "1");
                    zCreateNewFile = file2.createNewFile();
                    file = file2;
                } else {
                    file = null;
                    zCreateNewFile = false;
                }
                if (!zCreateNewFile) {
                    throw new Exception();
                }
            } catch (Exception e) {
                throw new RecordStoreException("RecordStore " + str + " could not be created");
            }
        }
        return new RecordStore(str, file);
    }

    public int addRecord(byte[] bArr, int i, int i2) throws RecordStoreException, IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.a);
            fileOutputStream.write(bArr, i, i2);
            fileOutputStream.close();
            return 1;
        } catch (IOException e) {
            throw new RecordStoreException("");
        }
    }

    public void closeRecordStore() {
    }

    public int getNumRecords() {
        return 0;
    }

    public byte[] getRecord(int i) throws RecordStoreException, IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.a);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (Exception e) {
            throw new RecordStoreException("");
        }
    }

    public void setRecord(int i, byte[] bArr, int i2, int i3) {
    }
}
