package javax.microedition.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
public class HttpConnectionImpl implements HttpConnection {
    private HttpURLConnection a;
    private URL b;
    protected boolean isConnected = false;

    public HttpConnectionImpl(String str) throws IOException {
        this.b = new URL(str);
        this.a = (HttpURLConnection) this.b.openConnection();
        this.a.setDoOutput(true);
    }

    private int a(int i) {
        return (this.a.getHeaderFieldKey(0) != null || this.a.getHeaderField(0) == null) ? i : i + 1;
    }

    @Override // javax.microedition.io.Connection
    public void close() throws IOException {
        if (this.a == null) {
            return;
        }
        this.a.disconnect();
        this.a = null;
    }

    @Override // javax.microedition.io.HttpConnection
    public long getDate() throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getDate();
    }

    @Override // javax.microedition.io.ContentConnection
    public String getEncoding() {
        try {
            return getHeaderField("content-encoding");
        } catch (IOException e) {
            return null;
        }
    }

    @Override // javax.microedition.io.HttpConnection
    public long getExpiration() throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getExpiration();
    }

    @Override // javax.microedition.io.HttpConnection
    public String getFile() {
        if (this.a == null) {
            return null;
        }
        return this.a.getURL().getFile();
    }

    @Override // javax.microedition.io.HttpConnection
    public String getHeaderField(int i) throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getHeaderField(a(i));
    }

    @Override // javax.microedition.io.HttpConnection
    public String getHeaderField(String str) throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getHeaderField(str);
    }

    @Override // javax.microedition.io.HttpConnection
    public long getHeaderFieldDate(String str, long j) throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getHeaderFieldDate(str, j);
    }

    @Override // javax.microedition.io.HttpConnection
    public int getHeaderFieldInt(String str, int i) throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getHeaderFieldInt(str, i);
    }

    @Override // javax.microedition.io.HttpConnection
    public String getHeaderFieldKey(int i) throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getHeaderFieldKey(a(i));
    }

    @Override // javax.microedition.io.HttpConnection
    public String getHost() {
        if (this.a == null) {
            return null;
        }
        return this.a.getURL().getHost();
    }

    @Override // javax.microedition.io.HttpConnection
    public long getLastModified() throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getLastModified();
    }

    @Override // javax.microedition.io.ContentConnection
    public long getLength() {
        try {
            return getHeaderFieldInt("content-length", -1);
        } catch (IOException e) {
            return -1L;
        }
    }

    @Override // javax.microedition.io.HttpConnection
    public int getPort() {
        if (this.a == null) {
            return -1;
        }
        int port = this.a.getURL().getPort();
        if (port == -1) {
            return 80;
        }
        return port;
    }

    @Override // javax.microedition.io.HttpConnection
    public String getProtocol() {
        return "http";
    }

    @Override // javax.microedition.io.HttpConnection
    public String getQuery() {
        return null;
    }

    @Override // javax.microedition.io.HttpConnection
    public String getRef() {
        if (this.a == null) {
            return null;
        }
        return this.a.getURL().getRef();
    }

    @Override // javax.microedition.io.HttpConnection
    public String getRequestMethod() {
        if (this.a == null) {
            return null;
        }
        return this.a.getRequestMethod();
    }

    @Override // javax.microedition.io.HttpConnection
    public String getRequestProperty(String str) {
        if (this.a == null) {
            return null;
        }
        return this.a.getRequestProperty(str);
    }

    @Override // javax.microedition.io.HttpConnection
    public int getResponseCode() throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getResponseCode();
    }

    @Override // javax.microedition.io.HttpConnection
    public String getResponseMessage() throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (!this.isConnected) {
            this.a.connect();
            this.isConnected = true;
        }
        return this.a.getResponseMessage();
    }

    @Override // javax.microedition.io.ContentConnection
    public String getType() {
        try {
            return getHeaderField("content-type");
        } catch (IOException e) {
            return null;
        }
    }

    @Override // javax.microedition.io.HttpConnection
    public String getURL() {
        if (this.a == null) {
            return null;
        }
        return this.a.getURL().toString();
    }

    @Override // javax.microedition.io.InputConnection
    public DataInputStream openDataInputStream() throws IOException {
        return new DataInputStream(openInputStream());
    }

    @Override // javax.microedition.io.OutputConnection
    public DataOutputStream openDataOutputStream() throws IOException {
        return new DataOutputStream(openOutputStream());
    }

    @Override // javax.microedition.io.InputConnection
    public InputStream openInputStream() throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        this.isConnected = true;
        return this.a.getInputStream();
    }

    @Override // javax.microedition.io.OutputConnection
    public OutputStream openOutputStream() throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        this.isConnected = true;
        return this.a.getOutputStream();
    }

    @Override // javax.microedition.io.HttpConnection
    public void setRequestMethod(String str) throws IOException {
        if (this.a == null) {
            throw new IOException();
        }
        if (str.equals(HttpConnection.POST)) {
            this.a.setDoOutput(true);
        }
        this.a.setRequestMethod(str);
    }

    @Override // javax.microedition.io.HttpConnection
    public void setRequestProperty(String str, String str2) throws IOException {
        if (this.a == null || this.isConnected) {
            throw new IOException();
        }
        this.a.setRequestProperty(str, str2);
    }
}
