package javax.microedition.lcdui;

/* loaded from: classes.dex */
public class List implements Choice {
    public List(String str, int i, String[] strArr, Object obj) {
    }

    @Override // javax.microedition.lcdui.Choice
    public int append(String str, Image image) {
        return 0;
    }

    @Override // javax.microedition.lcdui.Choice
    public void delete(int i) {
    }

    @Override // javax.microedition.lcdui.Choice
    public void deleteAll() {
    }

    @Override // javax.microedition.lcdui.Choice
    public int getFitPolicy() {
        return 0;
    }

    @Override // javax.microedition.lcdui.Choice
    public Font getFont(int i) {
        return null;
    }

    @Override // javax.microedition.lcdui.Choice
    public Image getImage(int i) {
        return null;
    }

    @Override // javax.microedition.lcdui.Choice
    public int getSelectedFlags(boolean[] zArr) {
        return 0;
    }

    @Override // javax.microedition.lcdui.Choice
    public int getSelectedIndex() {
        return 0;
    }

    @Override // javax.microedition.lcdui.Choice
    public String getString(int i) {
        return null;
    }

    @Override // javax.microedition.lcdui.Choice
    public void insert(int i, String str, Image image) {
    }

    @Override // javax.microedition.lcdui.Choice
    public boolean isSelected(int i) {
        return false;
    }

    @Override // javax.microedition.lcdui.Choice
    public void set(int i, String str, Image image) {
    }

    public void setCommandListener(CommandListener commandListener) {
    }

    @Override // javax.microedition.lcdui.Choice
    public void setFitPolicy(int i) {
    }

    @Override // javax.microedition.lcdui.Choice
    public void setFont(int i, Font font) {
    }

    public void setSelectCommand(Command command) {
    }

    @Override // javax.microedition.lcdui.Choice
    public void setSelectedFlags(boolean[] zArr) {
    }

    @Override // javax.microedition.lcdui.Choice
    public void setSelectedIndex(int i, boolean z) {
    }

    @Override // javax.microedition.lcdui.Choice
    public int size() {
        return 0;
    }
}
