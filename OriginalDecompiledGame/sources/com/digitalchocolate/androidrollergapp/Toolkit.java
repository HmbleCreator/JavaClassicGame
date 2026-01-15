package com.digitalchocolate.androidrollergapp;

import com.nokia.mid.ui.DeviceControl;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.MIDIControl;
import javax.microedition.media.control.RateControl;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.rms.RecordStore;

/* loaded from: classes.dex */
public final class Toolkit extends GameCanvas implements PlayerListener {
    private static final int BACKLIGHT_NUMBER = 0;
    private static final int BUTTON_WIDTH = 80;
    private static final boolean CACHE_ALL_TEXTS = false;
    public static final int CHANNEL_VOLUME_MAX = 127;
    public static final int CHANNEL_VOLUME_MIN = 0;
    private static final int CHANNEL_VOLUME_ORIGINAL = -1;
    public static final int CONTINUOUS_LOOPING = -1;
    private static final int DEFAULT_MMAPI_MIDI_RATE_MAX = 1000000;
    private static final int DEFAULT_MMAPI_MIDI_RATE_MIN = 10000;
    private static final int DEFAULT_MMAPI_MIDI_RATE_NORMAL = 100000;
    private static final boolean DISABLE_FREE_IMAGE_RESOURCES = false;
    public static final int DONT_FORCE_WRITING = 0;
    private static final boolean DO_NOT_SUPPRESS_KEY_EVENTS = false;
    public static final int EVENT_KEY_PRESSED = 0;
    public static final int EVENT_KEY_RELEASED = 1;
    public static final int EVENT_KEY_REPEATED = 2;
    public static final int EVENT_MOUSE_WHEEL_SCROLLED_DOWN = 4;
    public static final int EVENT_MOUSE_WHEEL_SCROLLED_UP = 5;
    public static final int EVENT_POINTER_DRAGGED = 2;
    public static final int EVENT_POINTER_PRESSED = 0;
    public static final int EVENT_POINTER_RELEASED = 1;
    public static final int EVENT_SOFT_KEY_PRESSED = 3;
    public static final int FORCE_WRITING = 1;
    public static final int FREE_RESOURCE_CACHE = 2;
    public static final int FREE_RESOURCE_FILES = 4;
    public static final int FREE_TEXT_CACHE = 1;
    public static final String GMG_JAD_PARAM = "DCHOC-GMG-";
    public static final String GMG_LINK_SEPERATOR = "!.!";
    public static final String GMG_URL_NOLINK = "nolink";
    public static final String GMG_URL_NONE = "none";
    public static final int INPUT_TYPE_LOCALIZED_TEXT = 4;
    public static final int INPUT_TYPE_NUMERIC = 2;
    public static final int INPUT_TYPE_PASSWORD = 5;
    public static final int INPUT_TYPE_PHONENUMBER = 3;
    public static final int INPUT_TYPE_TEXT = 1;
    public static final int INPUT_TYPE_USER_NAME = 0;
    public static final int KEY_ACTION = 12;
    public static final int KEY_ACTION_2 = 17;
    public static final int KEY_ACTION_3 = 18;
    public static final int KEY_ACTION_4 = 7;
    public static final int KEY_DOWN = 15;
    public static final int KEY_DOWN_LEFT = 14;
    public static final int KEY_DOWN_RIGHT = 16;
    public static final int KEY_LEFT = 11;
    public static final int KEY_RIGHT = 13;
    public static final int KEY_UP = 9;
    public static final int KEY_UP_LEFT = 8;
    public static final int KEY_UP_RIGHT = 10;
    private static final int LANGUAGE_ID_SHIFT = 24;
    public static final int LANGUAGE_NAME_INDEX = 0;
    private static final int LAST_PRESSED_KEYS_ARRAY_SIZE = 5;
    private static final int LAST_RELEASED_KEYS_ARRAY_SIZE = 5;
    private static final int LAST_REPEATED_KEYS_ARRAY_SIZE = 5;
    private static final int LEVEL_ON = 100;
    public static final int MASK_RESOURCE_INDEX = 65535;
    public static final int MASTER_VOLUME_MAX = 100;
    public static final int MASTER_VOLUME_MIN = 0;
    private static final String MIDI_CONTROL = "MIDIControl";
    private static final int MIDI_RATE_MAX = 100;
    private static final int MIDI_RATE_MIN = 1;
    private static final int MIDI_RATE_NORMAL = 10;
    public static final int NETWORK_USAGE_LOCATION_ID_CONNECTION_QUERY_LONG = 1;
    public static final int NETWORK_USAGE_LOCATION_ID_CONNECTION_QUERY_SHORT = 0;
    public static final int NETWORK_USAGE_LOCATION_ID_GET_MORE_GAMES_QUERY = 2;
    public static final int NOT_NUMBER_KEY = -1;
    private static final int NO_PACKING = 0;
    public static final int NO_SOFT_KEY = -1;
    private static final int OFFSET_RESOURCES_NUMBER = 4;
    private static final int OFFSET_TABLE = 8;
    private static final boolean PREFETCH_ON_LOADING = false;
    private static final boolean PRELOAD_SOUNDS = false;
    public static final int PROPERTY_COUNT = 25;
    private static final String PROPERTY_FILE_NAME = "p";
    public static final int PROPERTY_GMG_STATE_NONE_DEFINATION = 25;
    public static final int PROPERTY_ID_ = 9;
    public static final int PROPERTY_ID_ASSET_SERVER_URL = 21;
    public static final int PROPERTY_ID_CARRIER_ID = 2;
    public static final int PROPERTY_ID_DEMO_PLAY_COUNT = 12;
    public static final int PROPERTY_ID_DEMO_TIME_LIMIT = 11;
    public static final int PROPERTY_ID_DEMO_URL = 10;
    public static final int PROPERTY_ID_DEVICE_ID = 1;
    public static final int PROPERTY_ID_FEEDBACK_URL = 18;
    public static final int PROPERTY_ID_FREE_TRIAL_UPGRADE_URL = 20;
    public static final int PROPERTY_ID_GAME_SERVER_URL = 13;
    public static final int PROPERTY_ID_GET_MORE_GAMES_URL = 5;
    public static final int PROPERTY_ID_INGAME_ADVERTISING = 15;
    public static final int PROPERTY_ID_INITIAL_STARTUP_LANGUAGE = 7;
    public static final int PROPERTY_ID_ML_SERVER = 4;
    public static final int PROPERTY_ID_NETWORK_COST_TERMS_ID = 22;
    public static final int PROPERTY_ID_NUMERAL_COUNTRY_CODE = 8;
    public static final int PROPERTY_ID_PROMO_URL = 14;
    public static final int PROPERTY_ID_SMS_SHORT_CODE_URL = 19;
    public static final int PROPERTY_ID_USE_CHEAT_CODES = 16;
    public static final int PROPERTY_ID_USE_CHOCOLATE_CLUB = 23;
    public static final int PROPERTY_ID_USE_HYBRID_ML = 3;
    public static final int PROPERTY_ID_USE_MMS_TAF = 17;
    public static final int PROPERTY_ID_USE_PACKAGER_DROPPABLE_CONTENT = 24;
    public static final int PROPERTY_ID_USE_TELL_A_FRIEND = 6;
    public static final String PROPERTY_JAD_ATTRIBUTE = "DCHOC-";
    private static final String RESOURCE_PACK_FILE_NAME = "r";
    private static final boolean RESOURCE_STREAM_MEMORY_LEAK = false;
    public static final int SELECT_SOFTKEY_INDEX = 1;
    public static final int SHIFT_FILE_ID = 16;
    private static final int SK_AREA_NOT_CALCULATED = -1;
    public static final int SK_ID_ACCEPT = 26;
    public static final int SK_ID_BACK = 5;
    public static final int SK_ID_BUY = 20;
    public static final int SK_ID_BYE = 28;
    public static final int SK_ID_CANCEL = 11;
    public static final int SK_ID_CHANGE = 4;
    public static final int SK_ID_CONTINUE = 15;
    public static final int SK_ID_DISCARD = 25;
    public static final int SK_ID_DONE = 10;
    public static final int SK_ID_EDIT = 3;
    public static final int SK_ID_ERASE = 8;
    public static final int SK_ID_EXIT = 6;
    public static final int SK_ID_GET_IT = 24;
    public static final int SK_ID_GET_IT_BROWSE = 34;
    public static final int SK_ID_GO = 40;
    public static final int SK_ID_GO_TO_WAP = 31;
    public static final int SK_ID_GREENHOUSE = 39;
    public static final int SK_ID_HELP = 27;
    public static final int SK_ID_HIRE = 18;
    public static final int SK_ID_MANAGE = 17;
    public static final int SK_ID_MENU = 13;
    public static final int SK_ID_NEXT = 12;
    public static final int SK_ID_NO = 7;
    public static final int SK_ID_OK = 1;
    public static final int SK_ID_PAUSE = 9;
    public static final int SK_ID_PLACE = 22;
    public static final int SK_ID_PLAY = 32;
    public static final int SK_ID_POWER_UP = 38;
    public static final int SK_ID_QUIT = 35;
    public static final int SK_ID_RANKING = 37;
    public static final int SK_ID_REGISTER = 30;
    public static final int SK_ID_RESTART = 36;
    public static final int SK_ID_RETRY = 23;
    public static final int SK_ID_SAVE = 16;
    public static final int SK_ID_SELECT = 0;
    public static final int SK_ID_SEND = 21;
    public static final int SK_ID_SKIP = 14;
    public static final int SK_ID_START = 19;
    public static final int SK_ID_STATS = 33;
    public static final int SK_ID_SYNCHRONIZE = 29;
    public static final int SK_ID_YES = 2;
    public static final int SK_POSITION_BACK_KEY = 4;
    public static final int SK_POSITION_BY_TYPE = 0;
    public static final int SK_POSITION_LEFT = 1;
    public static final int SK_POSITION_MIDDLE = 2;
    public static final int SK_POSITION_RIGHT = 3;
    public static final int SK_TYPE_BACK = 2;
    public static final int SK_TYPE_EDIT = 1;
    public static final int SK_TYPE_ERASE = 4;
    public static final int SK_TYPE_EXIT = 3;
    public static final int SK_TYPE_OK = 0;
    private static final int SOFTKEY_POOL_BUTTON = 2;
    private static final int SOFTKEY_POOL_IMAGE = 2;
    private static final int SOFTKEY_POOL_LABEL = 1;
    private static final int SOFTKEY_POOL_SHIFT = 24;
    private static final int SOFTKEY_POOL_TID = 0;
    private static final int SOFTKEY_POOL_TYPE = 3;
    public static final int SOUND_FORMAT_AMR_INDEX = 2;
    public static final int SOUND_FORMAT_ILLEGAL = -1;
    public static final int SOUND_FORMAT_MIDI_INDEX = 0;
    public static final int SOUND_FORMAT_MMF_INDEX = 5;
    public static final int SOUND_FORMAT_MP3_INDEX = 3;
    public static final int SOUND_FORMAT_OTT_INDEX = 4;
    public static final int SOUND_FORMAT_SPF_INDEX = 6;
    public static final int SOUND_FORMAT_WAV_INDEX = 1;
    public static final int SOUND_INGAME_VOLUME_DEFAULT = 3;
    private static final boolean SOUND_LOOPING_BUG = false;
    public static final int SOUND_VOLUME_MAX = 5;
    public static final int SOUND_VOLUME_OFF = 0;
    public static final int SOURCE_FILESYSTEM = 0;
    public static final int SOURCE_RECORDSTORE = 1;
    public static final int SOURCE_URL = 2;
    public static final int TOOLKIT_EVENT_KEY_PRESSED = 3;
    public static final int TOOLKIT_EVENT_PAUSE = 0;
    public static final int TOOLKIT_EVENT_RESUME = 1;
    public static final int TOOLKIT_EVENT_UPDATE_LOGIC = 2;
    private static final int TURN_OFF = 0;
    private static final int UNICODE_ARABIC_CHARACTER_RANGE_START_INDEX = 1536;
    private static final int UNICODE_ARABIC_CHARACTER_RANGE_STOP_INDEX = 1791;
    private static final boolean USE_DEFAULT_VOLUME = true;
    private static final boolean USE_IMAGE_CACHING = false;
    private static final boolean USE_INGAME_VOLUME_CONTROL = false;
    private static final boolean USE_LOCALIZED_RESOURCES = true;
    private static final boolean USE_RESOURCE_CACHING = false;
    private static final boolean USE_TEXT_CACHING = false;
    private static final int VIRTUAL_KEY_ICON_HEIGHT = 0;
    private static final int VIRTUAL_KEY_ICON_WIDTH = 0;
    private static final String VOLUME_CONTROL = "VolumeControl";
    private static final int VOLUME_STEP_MMAPI = 20;
    private static int m_controllerPrevDir;
    private static boolean showLeftSoftKey;
    private static boolean showRightSoftKey;
    private static String smCurrentKeyMappingLanguage;
    private static char[][][] smKeyMappings;
    private static MIDIControl smMIDIController;
    private static RateControl smRateController;
    private static int sm_backSoftKey;
    private static int sm_bufferedPressedKeyIndex;
    private static int[] sm_bufferedPressedKeys;
    private static int sm_bufferedReleasedKeyIndex;
    private static int[] sm_bufferedReleasedKeys;
    private static int sm_bufferedRepeatedKeyIndex;
    private static int[] sm_bufferedRepeatedKeys;
    private static Player sm_currentMusic;
    private static int sm_currentMusicRID;
    private static Player sm_currentSoundEffect;
    private static int sm_currentSoundEffectRID;
    private static int sm_current_master_volume;
    private static int[] sm_current_midi_channels_volume;
    private static int sm_current_rate;
    private static int sm_dataSource;
    private static Graphics sm_graphics;
    private static boolean sm_ignoreStopEvents;
    private static int sm_inputType;
    private static Toolkit sm_instance;
    private static String[] sm_languageCode;
    private static String[][] sm_languageName;
    private static int sm_lastPressedKeyIndex;
    private static int[] sm_lastPressedKeys;
    private static IButton sm_leftButton;
    private static int sm_leftSoftKey;
    private static String sm_leftSoftKeyLabel;
    private static int sm_leftSoftKeyType;
    private static int sm_leftSoftKeyWidth;
    private static ILicenseManager sm_licenseManager;
    private static DChocMIDlet sm_listener;
    private static byte[] sm_loadedFileData;
    private static String sm_loadedFileName;
    private static Hashtable sm_localizedResourceMapping;
    private static IMouse sm_mouse;
    private static int sm_musicLoopsLeft;
    private static int sm_musicVolume;
    private static boolean sm_okToPaint;
    private static IPaymentManager sm_paymentManager;
    private static boolean[] sm_prevoiusControllerEvents;
    private static String[] sm_properties;
    private static boolean sm_readOnly;
    private static IRenderingPlatform sm_renderingPlatform;
    private static Hashtable sm_resourceCache;
    private static Image sm_returnSoftkeyIcon;
    private static IButton sm_rightButton;
    private static int sm_rightSoftKey;
    private static String sm_rightSoftKeyLabel;
    private static int sm_rightSoftKeyType;
    private static int sm_rightSoftKeyWidth;
    private static int sm_screenHeight;
    private static boolean sm_screenRatioNotSupported;
    private static String[] sm_screenRatioNotSupportedText;
    private static int sm_screenWidth;
    private static int sm_selectedLanguage;
    private static IServerSocketConnection sm_serverSocketConnection;
    private static boolean sm_settingAnotherDisplayable;
    private static boolean sm_settingAsCurrent;
    private static boolean sm_skipSounds;
    private static Font sm_softKeyFont;
    private static Hashtable sm_softKeyPool;
    private static ImageFont sm_softkeyImageFont;
    private static Player[][] sm_soundCache;
    private static int sm_soundEffectLoopsLeft;
    private static int sm_soundEffectVolume;
    private static Vector[] sm_soundEffects;
    private static long sm_soundTimeStamp;
    private static Hashtable sm_textCache;
    private static char[] sm_textIndex;
    private static int sm_thirdSoftKey;
    private static String sm_thirdSoftKeyLabel;
    private static int sm_thirdSoftKeyType;
    private static ITwitterConnection sm_twitterConnection;
    private static boolean sm_vibraEnabled;
    private static IVirtualKeypad sm_virtualKeypad;
    private static IWcrmClient sm_wcrmClient;
    public static long ssTime;
    private static Image virtual_keypad;
    private Image sm_buffer;
    private Image[] sm_letterboxImage;
    private int sm_screenX;
    private int sm_screenY;
    private static int sm_leftButtonXMargin = 7;
    private static int sm_leftButtonYMargin = 7;
    private static int sm_rightButtonXMargin = 7;
    private static int sm_rightButtonYMargin = 7;
    private static int sm_rightButtonScreenAnchor = 40;
    private static int sm_leftButtonScreenAnchor = 36;
    private static int sm_softkeyAreaHeight = -1;
    public static boolean isKeyPressed = false;
    public static boolean isKeyReleased = false;
    public static boolean isKeyRepeated = false;
    private static final String[] SOUND_FORMATS = {"audio/midi", "audio/wav", "audio/amr", "audio/mpeg"};
    private static final String[] SOUND_FILE_EXTENSIONS = {".mid", ".wav", ".amr", ".mp3"};
    private static boolean USE_INGAME_RATE_CONTROL = false;
    private static boolean USE_INGAME_MASTER_VOLUME_CONTROL = false;
    private static boolean USE_INGAME_CHANNEL_VOLUME_CONTROL = false;
    private static boolean sm_backlightEnabled = true;
    private static final char[] NUMBERLIKE_SYMBOLS = {'#', 8722, '-'};
    public static String smResourcePath = "";
    private static int FIXED_INDEX = 1000;
    private static final int[] SOUND_RID_INDICES = {ResourceIDs.RID_SND_TITLE, ResourceIDs.RID_SND_EFFECT_VOLUME_CHANGED, ResourceIDs.RID_SND_GAME};

    public Toolkit() {
        super(false);
        setFullScreenMode(true);
        sm_lastPressedKeys = new int[5];
        sm_bufferedPressedKeys = new int[5];
        sm_bufferedReleasedKeys = new int[5];
        sm_bufferedRepeatedKeys = new int[5];
        sm_lastPressedKeyIndex = -1;
        sm_bufferedPressedKeyIndex = -1;
        sm_bufferedReleasedKeyIndex = -1;
        sm_bufferedRepeatedKeyIndex = -1;
    }

    public static void create(DChocMIDlet dChocMIDlet) {
        if (sm_instance == null) {
            sm_instance = new Toolkit();
        }
        sm_listener = dChocMIDlet;
    }

    private static Player createPlayer(int i) {
        try {
            Player playerCreatePlayer = Manager.createPlayer(new ByteArrayInputStream(getResourceBytes(i)), SOUND_FORMATS[getSoundFormat(i)]);
            playerCreatePlayer.addPlayerListener(sm_instance);
            return playerCreatePlayer;
        } catch (Exception e) {
            return null;
        }
    }

    private static Player[] createPlayers(int i, int i2) {
        return null;
    }

    public static void createSoftKey(int i, int i2, Image image, int i3) {
        sm_softKeyPool.put(new Integer(i | 0), new Integer(i2));
        sm_softKeyPool.put(new Integer(16777216 | i), getText(i2));
        if (image != null) {
            sm_softKeyPool.put(new Integer(33554432 | i), image);
        }
        sm_softKeyPool.put(new Integer(50331648 | i), new Integer(i3));
    }

    public static void createSoftKeyButton(int i, int i2, IButton iButton, int i3) {
        createSoftKey(i, i2, null, i3);
        sm_softKeyPool.put(new Integer(33554432 | i), iButton);
    }

    public static void doRepaint() {
        sm_okToPaint = true;
        sm_instance.repaint();
        sm_instance.serviceRepaints();
        sm_okToPaint = false;
    }

    private void drawFPS(Graphics graphics) {
        int color = graphics.getColor();
        int screenWidth = getScreenWidth() / 4;
        int screenWidth2 = getScreenWidth() / 16;
        graphics.setColor(16777215);
        graphics.fillRect(getScreenWidth() - screenWidth, 0, screenWidth, screenWidth2);
        graphics.setColor(0);
        graphics.drawString("FPS:" + DChocMIDlet.getFPS(), (getScreenWidth() - screenWidth) + 5, 5, 20);
        graphics.setColor(color);
    }

    private static void drawMouseCursor(Graphics graphics) {
    }

    private static void drawScreenRatioNotSupportedWarningScreen(Graphics graphics) {
        graphics.setColor(16777215);
        graphics.fillRect(0, 0, sm_instance.getWidth(), sm_instance.getHeight());
        graphics.setColor(0);
        graphics.setFont(sm_softKeyFont);
        if (sm_screenRatioNotSupportedText == null) {
            sm_screenRatioNotSupportedText = splitText(replaceParameters(getText(TextIDs.TID_GEN_INVALID_ASPECT_RATIO), new String[]{getText(50)}), sm_screenWidth - (sm_screenWidth / 8), sm_softKeyFont);
        }
        int height = (sm_instance.getHeight() / 2) - ((sm_screenRatioNotSupportedText.length * sm_softKeyFont.getHeight()) / 2);
        for (int i = 0; i < sm_screenRatioNotSupportedText.length; i++) {
            graphics.drawString(sm_screenRatioNotSupportedText[i], (sm_instance.getWidth() / 2) - (sm_softKeyFont.stringWidth(sm_screenRatioNotSupportedText[i]) / 2), height, 20);
            height += sm_softKeyFont.getHeight();
        }
    }

    private static void drawSoftKeyLabels(Graphics graphics) {
        if (isSoftKeyVisible()) {
            int screenWidth = getScreenWidth();
            int screenHeight = getScreenHeight() - 7;
            graphics.setClip(0, 0, getScreenWidth(), getScreenHeight());
            if (showLeftSoftKey && sm_leftSoftKey != -1) {
                sm_leftSoftKeyWidth = sm_softkeyImageFont.stringWidth(sm_leftSoftKeyLabel) + 7;
                sm_softkeyImageFont.drawString(graphics, sm_leftSoftKeyLabel, 0 + 7, screenHeight, 36);
                sm_leftSoftKeyWidth = Math.max(sm_leftSoftKeyWidth, getScreenWidth() >> 2);
            }
            if (!showRightSoftKey || sm_rightSoftKey == -1) {
                return;
            }
            sm_rightSoftKeyWidth = sm_softkeyImageFont.stringWidth(sm_rightSoftKeyLabel) + 7;
            sm_softkeyImageFont.drawString(graphics, sm_rightSoftKeyLabel, screenWidth - sm_rightSoftKeyWidth, screenHeight, 36);
            sm_rightSoftKeyWidth = Math.max(sm_rightSoftKeyWidth, getScreenWidth() >> 2);
        }
    }

    public static void drawVirtualKeypad(Graphics graphics) {
        graphics.setClip(0, 0, getScreenWidth(), sm_instance.getHeight());
        graphics.setColor(0, 0, 0);
        graphics.fillRect(0, getScreenHeight(), getScreenWidth(), virtual_keypad.getHeight());
        graphics.drawImage(virtual_keypad, 0, getScreenHeight(), 20);
    }

    public static void enableWritingToRecordStore(boolean z) {
        sm_readOnly = !z;
    }

    private static void fetchRateController() {
        if (sm_currentMusic != null) {
            smRateController = (RateControl) sm_currentMusic.getControl("RateControl");
        } else {
            smRateController = null;
        }
    }

    public static void forceBacklightOn() {
        if (sm_backlightEnabled) {
            DeviceControl.setLights(0, 100);
        }
    }

    public static void freeHeap() {
        sm_loadedFileName = null;
        sm_loadedFileData = null;
    }

    public static void freeHeap(int i) {
        if ((i & 4) != 0) {
            sm_loadedFileName = null;
            sm_loadedFileData = null;
        }
    }

    public static void freeResource(int i) {
    }

    public static void freeSoftKey(int i) {
        sm_softKeyPool.remove(new Integer(i | 0));
        sm_softKeyPool.remove(new Integer(16777216 | i));
        sm_softKeyPool.remove(new Integer(33554432 | i));
        sm_softKeyPool.remove(new Integer(50331648 | i));
    }

    public static void freeText(int i) {
    }

    public static boolean getBacklightControlEnabled() {
        return sm_backlightEnabled;
    }

    public static IButton getButton(int i) {
        return (IButton) sm_softKeyPool.get(new Integer(33554432 | i));
    }

    public static int getChannelVolume(int i) {
        if (sm_currentMusic == null || sm_current_midi_channels_volume == null) {
            return 0;
        }
        return sm_current_midi_channels_volume[i];
    }

    public static String getChocolateClubCode() {
        int i = (Integer.parseInt(Statics.APPLICATION_ID) * 10000) + Integer.parseInt(getToolkitProperty(2));
        int i2 = 1;
        String str = "";
        do {
            int i3 = (i / i2) % 36;
            i -= i3 * i2;
            i2 *= 36;
            str = i3 > 9 ? "" + ((char) ((i3 - 10) + 65)) + str : i3 + str;
        } while (i > 0);
        return str;
    }

    public static int getCurrentRate() {
        return sm_current_rate;
    }

    public static IFacebookConnection getFacebookConnection(String str) {
        return null;
    }

    public static IHTTPConnection getHTTPConnection() {
        return null;
    }

    public static IHighScore getHighScoreManager() {
        return new ReferenceHighScore();
    }

    public static Image getImage(int i) {
        return getImage(i, (byte) -1);
    }

    public static Image getImage(int i, byte b) throws IOException {
        int iIntValue;
        Integer num;
        if (i != -1) {
            if (sm_localizedResourceMapping == null || (num = (Integer) sm_localizedResourceMapping.get(getSelectedLanguageCode() + i)) == null) {
                iIntValue = i;
            } else {
                iIntValue = num.intValue();
                if (iIntValue == -1) {
                    return null;
                }
            }
            if (0 == 0) {
                byte[] resourceBytes = getResourceBytes(iIntValue);
                return Image.createImage(resourceBytes, 0, resourceBytes.length);
            }
        }
        return null;
    }

    private static int getIndex(int i) {
        return -1;
    }

    public static char[] getInputKeyMapping(int i, int i2) {
        if (smKeyMappings == null) {
            smKeyMappings = new char[5][][];
        }
        if (smKeyMappings[i2] == null) {
            if (i2 == 0) {
                smKeyMappings[0] = new char[][]{new char[]{'0'}, new char[]{'1'}, new char[]{'A', 'B', 'C', '2'}, new char[]{'D', 'E', 'F', '3'}, new char[]{'G', 'H', 'I', '4'}, new char[]{'J', 'K', 'L', '5'}, new char[]{'M', 'N', 'O', '6'}, new char[]{'P', 'Q', 'R', 'S', '7'}, new char[]{'T', 'U', 'V', '8'}, new char[]{'W', 'X', 'Y', 'Z', '9'}};
            } else if (i2 == 1) {
                smKeyMappings[1] = new char[][]{new char[]{'0'}, new char[]{'1'}, new char[]{'A', 'B', 'C', '2'}, new char[]{'D', 'E', 'F', '3'}, new char[]{'G', 'H', 'I', '4'}, new char[]{'J', 'K', 'L', '5'}, new char[]{'M', 'N', 'O', '6'}, new char[]{'P', 'Q', 'R', 'S', '7'}, new char[]{'T', 'U', 'V', '8'}, new char[]{'W', 'X', 'Y', 'Z', '9'}};
            } else if (i2 == 2) {
                smKeyMappings[2] = new char[][]{new char[]{'0'}, new char[]{'1'}, new char[]{'2'}, new char[]{'3'}, new char[]{'4'}, new char[]{'5'}, new char[]{'6'}, new char[]{'7'}, new char[]{'8'}, new char[]{'9'}};
            } else if (i2 == 3) {
                smKeyMappings[3] = new char[][]{new char[]{'0'}, new char[]{'1'}, new char[]{'2'}, new char[]{'3'}, new char[]{'4'}, new char[]{'5'}, new char[]{'6'}, new char[]{'7'}, new char[]{'8'}, new char[]{'9'}};
            }
        }
        if (i >= 7 && i <= 16) {
            return smKeyMappings[i2][i - 7];
        }
        if (i2 != 0 && i2 != 1) {
        }
        return null;
    }

    public static int getInputType() {
        return sm_inputType;
    }

    public static String getJadPropertyValue(String str) {
        return DChocMIDlet.getInstance().getAppProperty(str);
    }

    public static String[][] getLanguageDescriptions() {
        return sm_languageName;
    }

    public static ILicenseManager getLicenseManager() {
        return null;
    }

    public static int getMasterVolume() {
        if (sm_currentMusic != null) {
            return sm_current_master_volume;
        }
        return 0;
    }

    public static int getMaxSupportedRate() {
        if (sm_currentMusic != null) {
            fetchRateController();
            if (smRateController != null) {
                return smRateController.getMaxRate();
            }
        }
        return 100000;
    }

    public static int getMinSupportedRate() {
        if (sm_currentMusic != null) {
            fetchRateController();
            if (smRateController != null) {
                return smRateController.getMinRate();
            }
        }
        return 100000;
    }

    public static IMouse getMouse() {
        return null;
    }

    public static int getMusicVolume() {
        return sm_musicVolume;
    }

    public static String getNetworkUsageTerms(int i) {
        if (0 != 0) {
            return null;
        }
        return "";
    }

    public static String getNumberAsString(int i) throws IOException {
        StringBuffer stringBufferReverse = new StringBuffer(String.valueOf(i)).reverse();
        String text = getText(300);
        if (text.equals("")) {
            text = ",";
        }
        int i2 = 3;
        for (int i3 = 0; i3 < stringBufferReverse.length() - i2; i3 += 3) {
            stringBufferReverse.insert(i3 + i2, text);
            i2++;
        }
        return stringBufferReverse.reverse().toString();
    }

    public static int getNumberForKey(int i) {
        if (i < 7 || i > 16) {
            return -1;
        }
        return i - 7;
    }

    private static Player getPlayer(int i) {
        return null;
    }

    private static Player[] getPlayers(int i) {
        return null;
    }

    public static IRenderingPlatform getRenderingPlatform() {
        if (sm_renderingPlatform == null) {
            sm_renderingPlatform = new MIDP2RenderingPlatform();
        }
        return sm_renderingPlatform;
    }

    public static DChocByteArray getResourceByteArray(int i) throws IOException {
        int iIntValue;
        Integer num;
        if (i == -1) {
            return null;
        }
        if (sm_localizedResourceMapping == null || (num = (Integer) sm_localizedResourceMapping.get(getSelectedLanguageCode() + i)) == null) {
            iIntValue = i;
        } else {
            iIntValue = num.intValue();
            if (iIntValue == -1) {
                return null;
            }
        }
        int i2 = iIntValue >>> 16;
        int i3 = iIntValue & 65535;
        loadFile("r" + i2);
        int length = sm_loadedFileData.length;
        int resourceInt = getResourceInt(4);
        int resourceInt2 = getResourceInt((i3 * 4) + 8);
        return DChocByteArray.createByteArrayForReading(sm_loadedFileData, resourceInt2, i3 == resourceInt - 1 ? length - resourceInt2 : getResourceInt(((i3 * 4) + 8) + 4) - resourceInt2);
    }

    public static byte[] getResourceBytes(int i) throws IOException {
        int iIntValue;
        Integer num;
        if (i == -1) {
            return null;
        }
        if (sm_localizedResourceMapping == null || (num = (Integer) sm_localizedResourceMapping.get(getSelectedLanguageCode() + i)) == null) {
            iIntValue = i;
        } else {
            iIntValue = num.intValue();
            if (iIntValue == -1) {
                return null;
            }
        }
        loadFile("r" + (iIntValue >>> 16));
        return getResourceBytesFromLoadedFile(iIntValue & 65535);
    }

    private static byte[] getResourceBytesFromLoadedFile(int i) {
        int length = sm_loadedFileData.length;
        int resourceInt = getResourceInt(4);
        int resourceInt2 = getResourceInt((i * 4) + 8);
        int resourceInt3 = i == resourceInt - 1 ? length - resourceInt2 : getResourceInt(((i * 4) + 8) + 4) - resourceInt2;
        byte[] bArr = new byte[resourceInt3];
        System.arraycopy(sm_loadedFileData, resourceInt2, bArr, 0, resourceInt3);
        return bArr;
    }

    private static int getResourceInt(int i) {
        return ((sm_loadedFileData[i] & DChocImage.COLOR_DEPTH_DEFAULT) << 24) | ((sm_loadedFileData[i + 1] & DChocImage.COLOR_DEPTH_DEFAULT) << 16) | ((sm_loadedFileData[i + 2] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (sm_loadedFileData[i + 3] & DChocImage.COLOR_DEPTH_DEFAULT);
    }

    public static DataInputStream getResourceStream(int i) throws IOException {
        if (i != -1) {
            return new DataInputStream(new ByteArrayInputStream(getResourceBytes(i)));
        }
        return null;
    }

    public static int getScreenHeight() {
        return sm_instance.getHeight();
    }

    public static int getScreenWidth() {
        return sm_instance.getWidth();
    }

    public static String getSelectedLanguageCode() {
        return sm_languageCode[sm_selectedLanguage];
    }

    public static int getSelectedLanguageIndex() {
        return sm_selectedLanguage;
    }

    public static IServerSocketConnection getServerSocketConnection() {
        return null;
    }

    public static int getSoftKey(int i) {
        if (i == 1) {
            return sm_leftSoftKey;
        }
        if (i == 3) {
            return sm_rightSoftKey;
        }
        if (i == 4) {
            return sm_backSoftKey;
        }
        return -1;
    }

    public static int getSoftKeyAreaHeight() {
        return sm_softkeyAreaHeight;
    }

    public static int getSoftKeyType(int i) {
        return ((Integer) sm_softKeyPool.get(new Integer(50331648 | i))).intValue();
    }

    public static ImageFont getSoftkeyImageFont() {
        return sm_softkeyImageFont;
    }

    public static int getSoundEffectVolume() {
        return sm_soundEffectVolume;
    }

    private static int getSoundFormat(int i) {
        return 0;
    }

    private static int getSoundLength(int i) {
        return 0;
    }

    public static String getText(int i) throws IOException {
        if (i == -1) {
            return null;
        }
        if (i == -2) {
            return "";
        }
        if (0 != 0) {
            return null;
        }
        int i2 = 65535 & i;
        try {
            loadFile(TextIDs.LANGUAGE_BINARY_FILE + sm_selectedLanguage + "_" + (i >>> 16));
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(getResourceBytesFromLoadedFile(i2)));
            String utf = dataInputStream.readUTF();
            try {
                dataInputStream.close();
                return utf;
            } catch (Exception e) {
                return utf;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static int getToolkitGameAction(int i) {
        int gameAction;
        if (i <= -1000 && i >= -1007) {
            return DChocMIDlet.getInstance().mapControllerEventToGameAction(i);
        }
        if ((i >= 7 && i <= 16) || i == 17 || i == 18) {
            return i;
        }
        if (i != -11111) {
            try {
                gameAction = sm_instance.getGameAction(i);
            } catch (Exception e) {
                gameAction = 0;
            }
        } else {
            gameAction = 0;
        }
        if (gameAction == 0) {
            return 0;
        }
        if (gameAction == 23) {
            return 12;
        }
        if (gameAction == 19) {
            return 9;
        }
        if (gameAction == 20) {
            return 15;
        }
        if (gameAction == 21) {
            return 11;
        }
        return gameAction == 22 ? 13 : 0;
    }

    public static String getToolkitProperty(int i) {
        return sm_properties[i - 1];
    }

    public static ITwitterConnection getTwitterConnection() {
        return null;
    }

    public static IController getUsedController() {
        return AccelerometerController.getInstance();
    }

    public static IPaymentManager getUsedPaymentManager() {
        return null;
    }

    public static IVirtualKeypad getUsedVirtualKeypad() {
        return null;
    }

    private static String getVerifiedGMGProp(String str) {
        if (str == null) {
            return null;
        }
        String str2 = sm_properties[24];
        int i = 0;
        int length = 0;
        while (length < str.length()) {
            i++;
            int iIndexOf = str.indexOf(GMG_LINK_SEPERATOR, length);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            length = iIndexOf + GMG_LINK_SEPERATOR.length();
        }
        String[] strArr = new String[i];
        int length2 = 0;
        boolean z = true;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            int iIndexOf2 = str.indexOf(GMG_LINK_SEPERATOR, length2);
            if (iIndexOf2 == -1) {
                iIndexOf2 = str.length();
            }
            String strSubstring = str.substring(length2, iIndexOf2);
            String jadPropertyValue = getJadPropertyValue(GMG_JAD_PARAM + i2);
            if (jadPropertyValue != null && !jadPropertyValue.equals("")) {
                strSubstring = jadPropertyValue.equals(str2) ? "none" : jadPropertyValue;
            }
            if (!strSubstring.equals("none") && z) {
                z = false;
            }
            strArr[i2] = strSubstring;
            length2 = GMG_LINK_SEPERATOR.length() + iIndexOf2;
        }
        if (z) {
            return null;
        }
        String str3 = strArr[0];
        for (int i3 = 1; i3 < strArr.length; i3++) {
            str3 = str3 + GMG_LINK_SEPERATOR + strArr[i3];
        }
        return str3;
    }

    public static boolean getVibraEnabled() {
        return sm_vibraEnabled;
    }

    public static IWcrmClient getWcrmClient() {
        return null;
    }

    public static void initialize() throws IOException {
        int i;
        sm_properties = new String[25];
        if (!Statics.RESOURCE_PATH.equals("null")) {
            smResourcePath = Statics.RESOURCE_PATH;
        }
        try {
            InputStream resourceAsStream = sm_instance.getClass().getResourceAsStream(smResourcePath + PROPERTY_FILE_NAME);
            if (resourceAsStream != null) {
                DataInputStream dataInputStream = new DataInputStream(resourceAsStream);
                while (true) {
                    try {
                        sm_properties[dataInputStream.readByte() - 1] = dataInputStream.readUTF();
                    } catch (EOFException e) {
                        dataInputStream.close();
                    }
                }
            }
        } catch (Exception e2) {
        }
        for (int i2 = 1; i2 <= 25; i2++) {
            String appProperty = DChocMIDlet.getInstance().getAppProperty(PROPERTY_JAD_ATTRIBUTE + i2);
            if (appProperty != null) {
                sm_properties[i2 - 1] = appProperty;
            }
        }
        Vector vector = new Vector();
        int i3 = 0;
        while (true) {
            setSelectedLanguage(i3);
            vector.addElement(getText(0));
            vector.addElement(getText(1));
            vector.addElement(getText(3));
            i = i3 + 1;
            if (!loadFile(TextIDs.LANGUAGE_BINARY_FILE + i + "_0")) {
                break;
            } else {
                i3 = i;
            }
        }
        sm_languageName = (String[][]) Array.newInstance((Class<?>) String.class, i, 2);
        sm_languageCode = new String[i];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i) {
            int i7 = i6 + 1;
            sm_languageName[i4][0] = (String) vector.elementAt(i6);
            int i8 = i7 + 1;
            sm_languageCode[i4] = (String) vector.elementAt(i7);
            if (sm_languageCode[i4].equals(sm_properties[6])) {
                i5 = i4;
            }
            sm_languageName[i4][1] = (String) vector.elementAt(i8);
            i4++;
            i6 = i8 + 1;
        }
        setSelectedLanguage(i5);
        sm_localizedResourceMapping = new Hashtable();
        try {
            DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(getResourceBytes(ResourceIDs.RID_LOCALIZATION_MAPPING)));
            while (true) {
                try {
                    String utf = dataInputStream2.readUTF();
                    if ("void".equals(utf)) {
                        break;
                    }
                    sm_localizedResourceMapping.put(utf + dataInputStream2.readInt(), new Integer(dataInputStream2.readInt()));
                } catch (EOFException e3) {
                }
            }
            sm_localizedResourceMapping = null;
            dataInputStream2.close();
        } catch (Exception e4) {
        }
        sm_softKeyPool = new Hashtable();
        sm_leftSoftKey = -1;
        sm_rightSoftKey = -1;
        showLeftSoftKey = true;
        showRightSoftKey = true;
        sm_backSoftKey = -1;
    }

    public static boolean isDeviceSoundsEnabled() {
        return true;
    }

    public static boolean isMusicPlaying() {
        return sm_currentMusic != null && sm_currentMusic.getState() == 400;
    }

    private static boolean isNumberlikeSymbol(char c) {
        for (int i = 0; i < NUMBERLIKE_SYMBOLS.length; i++) {
            if (c == NUMBERLIKE_SYMBOLS[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSoftKeyVisible() {
        return true;
    }

    public static boolean isSoundEffectPlaying(int i) {
        return sm_currentSoundEffect != null && sm_currentSoundEffectRID == i && sm_currentSoundEffect.getState() == 400;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean loadFile(String str) throws IOException {
        if (str.equals(sm_loadedFileName)) {
            return true;
        }
        sm_loadedFileName = null;
        sm_loadedFileData = null;
        try {
            switch (sm_dataSource) {
                case 0:
                    sm_loadedFileData = new byte[4];
                    InputStream resourceAsStream = sm_instance.getClass().getResourceAsStream(smResourcePath + str);
                    if (resourceAsStream != null) {
                        resourceAsStream.read(sm_loadedFileData, 0, 4);
                        int resourceInt = getResourceInt(0);
                        sm_loadedFileData = new byte[resourceInt];
                        for (int i = 4; i < resourceInt; i += resourceAsStream.read(sm_loadedFileData, i, resourceInt - i)) {
                        }
                        resourceAsStream.close();
                        sm_loadedFileName = str;
                        return true;
                    }
                    return false;
                case 1:
                    return false;
                case 2:
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static int numberOfSoundsPlaying() {
        int i = isMusicPlaying() ? 0 + 1 : 0;
        return isSoundEffectPlaying(sm_currentSoundEffectRID) ? i + 1 : i;
    }

    public static void pauseMusic() {
        if (sm_currentMusic != null) {
            stopMidi(sm_currentMusic);
        }
    }

    private static Player playMidi(int i, int i2, int i3) {
        Player playerCreatePlayer = null;
        try {
            playerCreatePlayer = createPlayer(i);
            if (playerCreatePlayer.getState() != 300) {
                playerCreatePlayer.prefetch();
            }
            playerCreatePlayer.setLoopCount(i2);
            VolumeControl volumeControl = (VolumeControl) playerCreatePlayer.getControl(VOLUME_CONTROL);
            if (volumeControl != null) {
                volumeControl.setLevel(50);
            }
            playerCreatePlayer.start();
        } catch (Exception e) {
        }
        return playerCreatePlayer;
    }

    public static void playMusic(int i, int i2) {
        if (sm_musicVolume == 0 || i == -1) {
            return;
        }
        resetDynamicSoundSettings();
        stopSoundEffect();
        stopMusic();
        sm_musicLoopsLeft = i2;
        sm_currentMusic = playMidi(i, i2, sm_musicVolume);
        setDynamicSoundSettings();
        sm_currentMusicRID = i;
    }

    public static void playSoundEffect(int i, int i2) {
        if (sm_soundEffectVolume == 0 || i == -1) {
            return;
        }
        stopMusic();
        stopSoundEffect();
        sm_currentSoundEffect = playMidi(i, i2, sm_soundEffectVolume);
        sm_currentSoundEffectRID = i;
    }

    public static void preloadSound(int i) {
        preloadSound(i, 1);
    }

    public static void preloadSound(int i, int i2) {
    }

    public static void processBufferedKeyEvent() {
        if (isKeyPressed) {
            for (int i = 0; i <= sm_bufferedPressedKeyIndex; i++) {
                toolkitEventOccurred(3);
                int i2 = sm_bufferedPressedKeys[i];
                if (i2 == -6) {
                    if (sm_leftSoftKey != -1) {
                        sm_listener.keyEventOccurred(sm_leftSoftKey, 3);
                    }
                } else if (i2 == -7 && sm_rightSoftKey != -1) {
                    sm_listener.keyEventOccurred(sm_rightSoftKey, 3);
                }
                if (i2 == 4) {
                    int i3 = sm_backSoftKey;
                    if (i3 == -1) {
                        i3 = sm_rightSoftKey;
                    }
                    if (i3 == -1) {
                        i3 = sm_leftSoftKey;
                    }
                    if (i3 != -1) {
                        sm_listener.keyEventOccurred(i3, 3);
                    }
                } else {
                    sm_listener.keyEventOccurred(i2, 0);
                }
            }
            isKeyPressed = false;
            sm_bufferedPressedKeyIndex = -1;
        }
        if (isKeyReleased) {
            for (int i4 = 0; i4 <= sm_bufferedReleasedKeyIndex; i4++) {
                int i5 = sm_bufferedReleasedKeys[i4];
                int i6 = 0;
                for (int i7 = 0; i7 <= sm_lastPressedKeyIndex; i7++) {
                    if (sm_lastPressedKeys[i7] == i5) {
                        i6++;
                    }
                    if (i7 + i6 <= sm_lastPressedKeyIndex) {
                        sm_lastPressedKeys[i7] = sm_lastPressedKeys[i7 + i6];
                    }
                }
                sm_lastPressedKeyIndex -= i6;
                if (i5 != -6 && i5 == -7) {
                }
                sm_listener.keyEventOccurred(i5, 1);
            }
            isKeyReleased = false;
            sm_bufferedReleasedKeyIndex = -1;
        }
        if (isKeyRepeated) {
            for (int i8 = 0; i8 <= sm_bufferedRepeatedKeyIndex; i8++) {
                sm_listener.keyEventOccurred(sm_bufferedRepeatedKeys[i8], 2);
            }
            isKeyRepeated = false;
            sm_bufferedRepeatedKeyIndex = -1;
        }
    }

    public static byte[] readRecord(String str) {
        byte[] record = null;
        try {
            RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore(str, false);
            record = recordStoreOpenRecordStore.getRecord(1);
            recordStoreOpenRecordStore.closeRecordStore();
            return record;
        } catch (Exception e) {
            return record;
        }
    }

    public static DChocByteArray readRecordByteArray(String str) {
        byte[] record = readRecord(str);
        if (record != null) {
            return DChocByteArray.createByteArrayForReading(record, 0, record.length);
        }
        return null;
    }

    public static void releaseAllSounds() {
    }

    public static void releaseSound(int i) {
    }

    public static void removeAllSoftKeys() {
        sm_leftSoftKey = -1;
        sm_leftSoftKeyLabel = null;
        sm_rightSoftKey = -1;
        sm_rightSoftKeyLabel = null;
        sm_backSoftKey = -1;
    }

    public static String replaceParameters(String str, String[] strArr) {
        try {
            if (strArr.length == 1) {
                return replaceSubstring(str, "%U", strArr[0]);
            }
            String strReplaceSubstring = str;
            for (int i = 0; i < strArr.length; i++) {
                try {
                    strReplaceSubstring = replaceSubstring(strReplaceSubstring, "%" + i + "U", strArr[i]);
                } catch (Exception e) {
                    return strReplaceSubstring;
                }
            }
            return strReplaceSubstring;
        } catch (Exception e2) {
            return str;
        }
    }

    private static String replaceSubstring(String str, String str2, String str3) {
        int iIndexOf = str.indexOf(str2);
        return iIndexOf >= 0 ? str.substring(0, iIndexOf) + str3 + str.substring(iIndexOf + str2.length()) : str;
    }

    private static void resetDynamicSoundSettings() {
        if (USE_INGAME_CHANNEL_VOLUME_CONTROL) {
            sm_current_midi_channels_volume = new int[16];
            for (int i = 0; i < 16; i++) {
                sm_current_midi_channels_volume[i] = -1;
            }
        }
        if (USE_INGAME_MASTER_VOLUME_CONTROL) {
            sm_current_master_volume = 127;
        }
        if (USE_INGAME_RATE_CONTROL) {
            sm_current_rate = 10;
        }
    }

    public static void resumeMusic() {
        if (sm_musicVolume == 0 || sm_currentMusic == null) {
            return;
        }
        sm_currentMusic = playMidi(sm_currentMusicRID, sm_musicLoopsLeft, sm_musicVolume);
    }

    public static String reverseBracketsInString(String str) {
        if (str.indexOf("(TM)") == -1) {
        }
        return str;
    }

    public static String reverseDigitsInString(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        char cCharAt = str.charAt(0);
        if (cCharAt < UNICODE_ARABIC_CHARACTER_RANGE_START_INDEX || cCharAt > UNICODE_ARABIC_CHARACTER_RANGE_STOP_INDEX) {
            return str;
        }
        int i = 0;
        StringBuffer stringBuffer2 = stringBuffer;
        String str2 = str;
        int i2 = 0;
        while (i2 < str2.length()) {
            if (Character.isDigit(str2.charAt(i2)) || isNumberlikeSymbol(str2.charAt(i2))) {
                while (i2 < str2.length()) {
                    if (Character.isDigit(str2.charAt(i2)) || isNumberlikeSymbol(str2.charAt(i2))) {
                        if (stringBuffer2.length() == 0) {
                            i = i2;
                        }
                        stringBuffer2.append(str2.charAt(i2));
                    } else if (stringBuffer2.length() != 0) {
                        String str3 = str2.substring(0, i) + ((Object) stringBuffer2.reverse()) + str2.substring(i2);
                        i2--;
                        str2 = str3;
                        stringBuffer2 = new StringBuffer();
                    }
                    i2++;
                }
                if (i2 == str2.length() && stringBuffer2.length() != 0 && stringBuffer2 != null) {
                    str2 = str2.substring(0, i) + ((Object) stringBuffer2.reverse()) + str2.substring(i2);
                    stringBuffer2 = null;
                }
            }
            i2++;
        }
        return str2;
    }

    public static void setBacklightControlEnabled(boolean z) {
        sm_backlightEnabled = z;
    }

    public static void setChannelVolume(int i, int i2) {
        if (sm_currentMusic == null || smMIDIController == null) {
            return;
        }
        smMIDIController.setChannelVolume(i, i2);
        sm_current_midi_channels_volume[i] = i2;
    }

    public static void setCurrentSoundEffectVolume(int i, int i2) {
        VolumeControl volumeControl;
        if (sm_currentSoundEffect == null || sm_currentSoundEffect.getState() == 0 || sm_currentSoundEffect.getState() == 100 || (volumeControl = (VolumeControl) sm_currentSoundEffect.getControl(VOLUME_CONTROL)) == null) {
            return;
        }
        volumeControl.setLevel((sm_soundEffectVolume * i) / 100);
    }

    public static void setDefaultSoftKeyButtonMargins(int i, int i2, int i3, int i4) {
        if (i == 1) {
            sm_leftButtonXMargin = i2;
            sm_leftButtonYMargin = i3;
            sm_leftButtonScreenAnchor = i4;
        } else if (i == 3) {
            sm_rightButtonXMargin = i2;
            sm_rightButtonYMargin = i3;
            sm_rightButtonScreenAnchor = i4;
        }
    }

    private static void setDynamicSoundSettings() {
        if (USE_INGAME_CHANNEL_VOLUME_CONTROL && smMIDIController != null) {
            for (int i = 0; i < sm_current_midi_channels_volume.length; i++) {
                if (sm_current_midi_channels_volume[i] != -1) {
                    setChannelVolume(i, sm_current_midi_channels_volume[i]);
                }
            }
        }
        if (USE_INGAME_MASTER_VOLUME_CONTROL && sm_current_master_volume != 100) {
            setMasterVolume(sm_current_master_volume);
        }
        if (USE_INGAME_RATE_CONTROL) {
            setRate(sm_current_rate);
        }
    }

    public static void setInputType(int i) {
        sm_inputType = i;
    }

    public static void setMasterVolume(int i) {
        VolumeControl volumeControl;
        if (sm_currentMusic == null || (volumeControl = (VolumeControl) sm_currentMusic.getControl(VOLUME_CONTROL)) == null) {
            return;
        }
        volumeControl.setLevel((i * 50) / 100);
        sm_current_master_volume = i;
    }

    public static void setMusicVolume(int i) {
        sm_musicVolume = i;
        if (sm_musicVolume == 0) {
            stopMusic();
        } else {
            stopMidi(sm_currentMusic);
            resumeMusic();
        }
    }

    public static void setRate(int i) {
        if (sm_currentMusic != null) {
            fetchRateController();
            if (smRateController != null) {
                if (i < 10) {
                    int iMax = Math.max(getMinSupportedRate(), 10000);
                    sm_current_rate = iMax + (((100000 - iMax) * (i - 1)) / 9);
                } else if (i > 10) {
                    sm_current_rate = (((Math.min(getMaxSupportedRate(), 1000000) - 100000) * (i - 10)) / 90) + 100000;
                } else {
                    sm_current_rate = 100000;
                }
                smRateController.setRate(sm_current_rate);
            }
        }
    }

    public static void setSelectedLanguage(int i) {
        sm_selectedLanguage = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void setSoftKey(int r7, int r8) {
        /*
            r6 = 1
            r5 = 0
            r4 = -1
            r0 = 4
            if (r8 != r0) goto L9
            com.digitalchocolate.androidrollergapp.Toolkit.sm_backSoftKey = r7
        L8:
            return
        L9:
            java.util.Hashtable r0 = com.digitalchocolate.androidrollergapp.Toolkit.sm_softKeyPool
            java.lang.Integer r1 = new java.lang.Integer
            r2 = 16777216(0x1000000, float:2.3509887E-38)
            r2 = r2 | r7
            r1.<init>(r2)
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.util.Hashtable r1 = com.digitalchocolate.androidrollergapp.Toolkit.sm_softKeyPool
            java.lang.Integer r2 = new java.lang.Integer
            r3 = 50331648(0x3000000, float:3.761582E-37)
            r3 = r3 | r7
            r2.<init>(r3)
            java.lang.Object r1 = r1.get(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r8 != 0) goto L68
            if (r1 == 0) goto L33
            if (r1 != r6) goto L54
        L33:
            int r2 = com.digitalchocolate.androidrollergapp.Toolkit.sm_leftSoftKey
            if (r2 == r4) goto L77
            int r3 = com.digitalchocolate.androidrollergapp.Toolkit.sm_leftSoftKeyType
            if (r1 >= r3) goto L51
            r3 = r6
        L3c:
            if (r2 == r4) goto L75
            removeAllSoftKeys()
            if (r3 == 0) goto L63
            r4 = 3
            setSoftKey(r2, r4)
            r2 = r3
        L48:
            if (r2 == 0) goto L6c
            com.digitalchocolate.androidrollergapp.Toolkit.sm_leftSoftKey = r7
            com.digitalchocolate.androidrollergapp.Toolkit.sm_leftSoftKeyType = r1
            com.digitalchocolate.androidrollergapp.Toolkit.sm_leftSoftKeyLabel = r0
            goto L8
        L51:
            r2 = r4
            r3 = r5
            goto L3c
        L54:
            int r2 = com.digitalchocolate.androidrollergapp.Toolkit.sm_rightSoftKey
            if (r2 != r4) goto L5b
            r2 = r4
            r3 = r5
            goto L3c
        L5b:
            int r2 = com.digitalchocolate.androidrollergapp.Toolkit.sm_rightSoftKeyType
            if (r1 <= r2) goto L77
            int r2 = com.digitalchocolate.androidrollergapp.Toolkit.sm_rightSoftKey
            r3 = r5
            goto L3c
        L63:
            setSoftKey(r2, r6)
            r2 = r3
            goto L48
        L68:
            if (r8 == r6) goto L73
            r2 = r5
            goto L48
        L6c:
            com.digitalchocolate.androidrollergapp.Toolkit.sm_rightSoftKey = r7
            com.digitalchocolate.androidrollergapp.Toolkit.sm_rightSoftKeyType = r1
            com.digitalchocolate.androidrollergapp.Toolkit.sm_rightSoftKeyLabel = r0
            goto L8
        L73:
            r2 = r6
            goto L48
        L75:
            r2 = r3
            goto L48
        L77:
            r2 = r4
            r3 = r6
            goto L3c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.Toolkit.setSoftKey(int, int):void");
    }

    public static void setSoftKeyLabel(int i, String str) {
        sm_softKeyPool.put(new Integer(16777216 | i), str);
        if (sm_leftSoftKey == i) {
            sm_leftSoftKeyLabel = str;
        }
        if (sm_rightSoftKey == i) {
            sm_rightSoftKeyLabel = str;
        }
    }

    public static void setSoftKeyPosition(int i, int i2, int i3, int i4) {
        setSoftKey(i, i2);
        ((IButton) sm_softKeyPool.get(new Integer(33554432 | i))).setPosition(i3, i4);
    }

    public static void setSoftKeyVisible(boolean z, boolean z2) {
    }

    public static void setSoftkeyImageFont(ImageFont imageFont) {
        sm_softkeyImageFont = imageFont;
        if (sm_softkeyImageFont != null) {
            sm_softkeyAreaHeight = sm_softkeyImageFont.getHeight() + 7 + 7;
        }
    }

    public static void setSoundEffectVolume(int i) {
        sm_soundEffectVolume = i;
        if (sm_soundEffectVolume == 0) {
            stopSoundEffect();
        }
    }

    public static void setVibraEnabled(boolean z) {
        if (!z) {
            Display.getDisplay(sm_listener).vibrate(0);
        }
        sm_vibraEnabled = z;
    }

    public static void setVisible(boolean z) {
        sm_instance.setFullScreenMode(z);
        if (!z) {
            sm_settingAnotherDisplayable = true;
            return;
        }
        sm_settingAnotherDisplayable = false;
        sm_settingAsCurrent = true;
        DChocMIDlet.setCurrent(sm_instance);
    }

    private static String[] splitText(String str, int i, Font font) {
        int i2;
        int length = str.length();
        Vector vector = new Vector();
        if (str.length() == 0) {
            vector.addElement(str);
        }
        int iIndexOf = 0;
        int i3 = 0;
        while (iIndexOf < length) {
            iIndexOf = str.indexOf("\\n", i3);
            if (iIndexOf == -1) {
                iIndexOf = length;
            }
            int i4 = i3;
            boolean z = false;
            while (!z) {
                int i5 = -1;
                int iCharWidth = 0;
                int i6 = i4;
                while (iCharWidth < i && i6 < iIndexOf) {
                    char cCharAt = str.charAt(i6);
                    iCharWidth += font.charWidth(cCharAt);
                    i6++;
                    if (cCharAt == ' ') {
                        i5 = i6;
                    }
                }
                if (i6 != iIndexOf || iCharWidth > i) {
                    i2 = i5 != -1 ? i5 - 1 : i6 - 1;
                } else {
                    z = true;
                    i2 = i6;
                }
                vector.addElement(str.substring(i4, i2));
                i4 = (!z || i2 >= length) ? i5 != -1 ? i2 + 1 : i2 : i2 + 2;
            }
            i3 = i4;
        }
        String[] strArr = new String[vector.size()];
        for (int i7 = 0; i7 < strArr.length; i7++) {
            strArr[i7] = (String) vector.elementAt(i7);
        }
        return strArr;
    }

    private static void stopMidi(Player player) {
        if (player != null) {
            try {
                player.stop();
                player.deallocate();
                player.close();
            } catch (Exception e) {
            }
        }
    }

    public static void stopMusic() {
        stopMidi(sm_currentMusic);
        sm_currentMusicRID = -1;
        sm_currentMusic = null;
        sm_current_rate = 10;
    }

    public static void stopSoundEffect() {
        if (sm_currentSoundEffect != null) {
            stopMidi(sm_currentSoundEffect);
        }
        sm_currentSoundEffect = null;
        sm_currentSoundEffectRID = -1;
    }

    public static void stopSoundEffect(int i) {
        if (sm_currentSoundEffectRID == i && sm_currentSoundEffect != null) {
            stopMidi(sm_currentSoundEffect);
        }
        if (sm_currentSoundEffectRID == i) {
            sm_currentSoundEffect = null;
            sm_currentSoundEffectRID = -1;
        }
    }

    public static void stopVibration() {
        if (sm_vibraEnabled) {
            Display.getDisplay(sm_listener).vibrate(0);
        }
    }

    public static void toolkitEventOccurred(int i) {
        if (i == 0) {
            for (int i2 = 0; i2 <= sm_lastPressedKeyIndex; i2++) {
                sm_listener.keyEventOccurred(sm_lastPressedKeys[i2], 1);
            }
            sm_lastPressedKeyIndex = -1;
        }
        if (i == 2) {
            sm_listener.getRealDeltaTime();
        }
    }

    public static void translateControllerEventToKeyEvent(int i, int i2, int i3, int i4) {
        if (sm_prevoiusControllerEvents == null) {
            sm_prevoiusControllerEvents = new boolean[8];
        }
        DChocMIDlet dChocMIDlet = DChocMIDlet.getInstance();
        if (sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_LEFT)] && i2 > -200) {
            dChocMIDlet.keyEventOccurred(IController.CONTROLLER_EVENT_LEFT, 1);
            sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_LEFT)] = false;
        } else if (!sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_LEFT)] && i2 < -200) {
            dChocMIDlet.keyEventOccurred(IController.CONTROLLER_EVENT_LEFT, 0);
            sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_LEFT)] = true;
        }
        if (sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_RIGHT)] && i2 < 200) {
            dChocMIDlet.keyEventOccurred(IController.CONTROLLER_EVENT_RIGHT, 1);
            sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_RIGHT)] = false;
        } else if (!sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_RIGHT)] && i2 > 200) {
            dChocMIDlet.keyEventOccurred(IController.CONTROLLER_EVENT_RIGHT, 0);
            sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_RIGHT)] = true;
        }
        if (sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_UP)] && i3 < 200) {
            dChocMIDlet.keyEventOccurred(IController.CONTROLLER_EVENT_UP, 1);
            sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_UP)] = false;
        } else if (!sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_UP)] && i3 > 200) {
            dChocMIDlet.keyEventOccurred(IController.CONTROLLER_EVENT_UP, 0);
            sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_UP)] = true;
        }
        if (sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_DOWN)] && i3 > -200) {
            dChocMIDlet.keyEventOccurred(IController.CONTROLLER_EVENT_DOWN, 1);
            sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_DOWN)] = false;
        } else {
            if (sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_DOWN)] || i3 >= -200) {
                return;
            }
            dChocMIDlet.keyEventOccurred(IController.CONTROLLER_EVENT_DOWN, 0);
            sm_prevoiusControllerEvents[-(FIXED_INDEX + IController.CONTROLLER_EVENT_DOWN)] = true;
        }
    }

    public static void vibrate(int i) {
        if (sm_vibraEnabled) {
            Display.getDisplay(sm_listener).vibrate(i);
        }
    }

    public static void writeRecord(String str, byte[] bArr) {
        try {
            RecordStore.deleteRecordStore(str);
        } catch (Exception e) {
        }
        if (bArr != null) {
            try {
                RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore(str, true);
                recordStoreOpenRecordStore.addRecord(bArr, 0, bArr.length);
                recordStoreOpenRecordStore.closeRecordStore();
            } catch (Exception e2) {
            }
        }
    }

    public static void writeRecord(String str, byte[] bArr, int i) {
        try {
            RecordStore.deleteRecordStore(str);
        } catch (Exception e) {
        }
        if (bArr != null) {
            try {
                RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore(str, true);
                recordStoreOpenRecordStore.addRecord(bArr, 0, bArr.length);
                recordStoreOpenRecordStore.closeRecordStore();
            } catch (Exception e2) {
            }
        }
    }

    public static void writeRecordByteArray(String str, DChocByteArray dChocByteArray, int i) {
        writeRecord(str, dChocByteArray != null ? dChocByteArray.getBytes() : null, i);
    }

    protected void hideNotify() {
        try {
            if (sm_settingAnotherDisplayable) {
                return;
            }
            sm_listener.pauseApp();
        } catch (Throwable th) {
        }
    }

    @Override // javax.microedition.lcdui.Canvas, javax.microedition.lcdui.Displayable
    public void keyEventWithChars(int i, int i2, char[] cArr) {
        sm_listener.keyEventOccurred(i, getToolkitGameAction(i), i2, cArr);
    }

    @Override // javax.microedition.lcdui.Canvas, javax.microedition.lcdui.Displayable
    protected void keyPressed(int i) {
        if (sm_screenRatioNotSupported) {
            return;
        }
        sm_lastPressedKeyIndex++;
        if (sm_lastPressedKeyIndex >= sm_lastPressedKeys.length) {
            int[] iArr = sm_lastPressedKeys;
            sm_lastPressedKeys = new int[iArr.length + 1];
            System.arraycopy(iArr, 0, sm_lastPressedKeys, 0, iArr.length);
        }
        sm_lastPressedKeys[sm_lastPressedKeyIndex] = i;
        if (sm_bufferedPressedKeyIndex < 4) {
            int[] iArr2 = sm_bufferedPressedKeys;
            int i2 = sm_bufferedPressedKeyIndex + 1;
            sm_bufferedPressedKeyIndex = i2;
            iArr2[i2] = i;
        }
        isKeyPressed = true;
    }

    @Override // javax.microedition.lcdui.Canvas, javax.microedition.lcdui.Displayable
    protected void keyReleased(int i) {
        int i2 = 0;
        if (sm_screenRatioNotSupported) {
            return;
        }
        for (int i3 = 0; i3 <= sm_lastPressedKeyIndex; i3++) {
            if (sm_lastPressedKeys[i3] == i) {
                i2++;
            }
            if (i3 + i2 <= sm_lastPressedKeyIndex) {
                sm_lastPressedKeys[i3] = sm_lastPressedKeys[i3 + i2];
            }
        }
        sm_lastPressedKeyIndex -= i2;
        if (sm_bufferedReleasedKeyIndex < 4) {
            int[] iArr = sm_bufferedReleasedKeys;
            int i4 = sm_bufferedReleasedKeyIndex + 1;
            sm_bufferedReleasedKeyIndex = i4;
            iArr[i4] = i;
        }
        isKeyReleased = true;
    }

    @Override // javax.microedition.lcdui.Canvas, javax.microedition.lcdui.Displayable
    protected void keyRepeated(int i) {
        if (sm_screenRatioNotSupported || i == -6 || i == -7) {
            return;
        }
        if (sm_bufferedRepeatedKeyIndex < 4) {
            int[] iArr = sm_bufferedRepeatedKeys;
            int i2 = sm_bufferedRepeatedKeyIndex + 1;
            sm_bufferedRepeatedKeyIndex = i2;
            iArr[i2] = i;
        }
        isKeyRepeated = true;
    }

    public void mouseWheelScrolled(int i) {
    }

    @Override // javax.microedition.lcdui.Displayable
    public void paint(Graphics graphics) {
        if (sm_okToPaint) {
            if (sm_screenRatioNotSupported) {
                drawScreenRatioNotSupportedWarningScreen(graphics);
                return;
            }
            sm_listener.doDraw(graphics);
            drawSoftKeyLabels(graphics);
            sm_listener.doPostDraw(graphics);
        }
    }

    @Override // javax.microedition.media.PlayerListener
    public void playerUpdate(Player player, String str, Object obj) {
    }

    @Override // javax.microedition.lcdui.Canvas, javax.microedition.lcdui.Displayable
    public void pointerDragged(int i, int i2) {
        if (sm_screenRatioNotSupported) {
            return;
        }
        try {
            if (isSoftKeyVisible()) {
            }
            sm_listener.pointerEventOccurred(i, i2, 2);
        } catch (Exception e) {
            Debugger.exceptionCaught(e, "MIDP2TouchScreenCanvas::pointerDragged()");
        }
    }

    @Override // javax.microedition.lcdui.Canvas, javax.microedition.lcdui.Displayable
    public void pointerPressed(int i, int i2) {
        if (System.currentTimeMillis() - ssTime <= 500) {
            return;
        }
        try {
            if (isSoftKeyVisible()) {
            }
            toolkitEventOccurred(3);
        } catch (Exception e) {
            Debugger.exceptionCaught(e, "MIDP2TouchScreenCanvas::pointerPressed()");
        }
        if (sm_screenRatioNotSupported) {
            return;
        }
        if (i2 > getScreenHeight() - getSoftKeyAreaHeight()) {
            boolean z = i < sm_leftSoftKeyWidth;
            boolean z2 = i > getScreenWidth() - sm_rightSoftKeyWidth;
            if (sm_leftSoftKey != -1 && z) {
                sm_listener.keyEventOccurred(sm_leftSoftKey, 3);
            } else if (sm_rightSoftKey != -1 && z2) {
                sm_listener.keyEventOccurred(sm_rightSoftKey, 3);
            }
        } else {
            sm_listener.pointerEventOccurred(i, i2, 0);
        }
        ssTime = System.currentTimeMillis();
    }

    @Override // javax.microedition.lcdui.Canvas, javax.microedition.lcdui.Displayable
    public void pointerReleased(int i, int i2) {
        if (sm_screenRatioNotSupported) {
            return;
        }
        try {
            if (isSoftKeyVisible()) {
            }
            sm_listener.pointerEventOccurred(i, i2, 1);
        } catch (Exception e) {
            Debugger.exceptionCaught(e, "MIDP2TouchScreenCanvas::pointerReleased()");
        }
    }

    protected void showNotify() {
        if (sm_settingAsCurrent) {
            sm_settingAsCurrent = false;
        } else {
            try {
                sm_listener.startApp();
            } catch (Throwable th) {
            }
        }
    }
}
