package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
class Engine2D {
    private static final int CAMERA_ACCURACY = 12;
    private static final int CAMERA_SHAKE_SPEED_X = 4680;
    private static final int CAMERA_SHAKE_SPEED_Y = 6120;
    private static final int CAMERA_SHAKE_TIME = 1000;
    static final int COUNTRY_0_ALPHA_COL_50_0 = 13118282;
    static final int COUNTRY_0_ALPHA_COL_50_1 = 16474624;
    static final int COUNTRY_0_ALPHA_COL_75_0 = 13770577;
    static final int COUNTRY_0_ALPHA_COL_75_1 = 11998744;
    public static final int EMPHASIZE_TYPE_EXCITER = 4;
    public static final int EMPHASIZE_TYPE_GOD = 1;
    public static final int EMPHASIZE_TYPE_JUMP = 3;
    public static final int EMPHASIZE_TYPE_NITRO = 0;
    public static final int EMPHASIZE_TYPE_NONE = -1;
    public static final int EMPHASIZE_TYPE_STICKY = 2;
    private static final int GRADIEN_BG_DAWN = 1;
    private static final int GRADIEN_BG_DAY = 0;
    private static final int GRADIEN_BG_DAY_END = 8;
    private static final int GRADIEN_BG_DAY_START = 6;
    private static int[][] GRADIEN_BG_DAY_TIME_COLORS = null;
    private static int[] GRADIEN_BG_DAY_TIME_TYPE = null;
    private static final int GRADIEN_BG_DUSK = 2;
    private static final int GRADIEN_BG_NIGHT = 3;
    public static final int HUD_SIGN_BREAK_GO = 4;
    public static final int HUD_SIGN_EMPTY = -1;
    public static final int HUD_SIGN_FALLING_RAILS = 7;
    public static final int HUD_SIGN_HILL = 1;
    public static final int HUD_SIGN_JUMP = 2;
    public static final int HUD_SIGN_LOOP = 0;
    public static final int HUD_SIGN_SPRINGS = 3;
    public static final int HUD_SIGN_TUNNEL = 5;
    public static final int HUD_SIGN_WATER_SPLASH = 6;
    private static final int HUD_TORNADO_BLINK_TIME_MS = 500;
    private static final int HUD_TORNADO_PRESENTATION_TIME_MS = 4000;
    private static int[] INGAME_BG_LAYERS_PRE_DRAWN_END = null;
    private static final int INGAME_BG_LAYERS_PRE_DRAWN_START = 0;
    private static final int INGAME_BG_LAYER_CLOUDS = 0;
    private static final int INGAME_BG_LAYER_FAR = 2;
    private static int[][] INGAME_BG_LAYER_FILLRECT_NORMAL_COLOR = null;
    private static final int INGAME_BG_LAYER_FILLRECT_NO_COLOR = -1;
    private static int[][] INGAME_BG_LAYER_FILLRECT_WATER_COLOR = null;
    private static final int INGAME_BG_LAYER_FURTHEST = 1;
    private static final int INGAME_BG_LAYER_MIDDLE = 3;
    private static final int INGAME_BG_LAYER_NEAR = 4;
    private static int[][] INGAME_BG_LAYER_NORMAL_SO = null;
    private static final int INGAME_BG_LAYER_SO_COUNT = 8;
    private static int[][][] INGAME_BG_LAYER_SO_IDS = null;
    private static int[][] INGAME_BG_LAYER_SPEED_DIVIDERS_NORMAL = null;
    private static int[][] INGAME_BG_LAYER_SPEED_DIVIDERS_WATER = null;
    private static final int INGAME_BG_LAYER_STARS = 7;
    private static final int INGAME_BG_LAYER_WATER_BACK = 5;
    private static final int INGAME_BG_LAYER_WATER_FRONT = 6;
    private static int[][] INGAME_BG_LAYER_WATER_SO = null;
    private static final int INGAME_CAMERA_X_DURATION = 1000;
    private static final int INGAME_CAMERA_Y_DURATION = 700;
    private static final int INGAME_CAR_CNT_MAX = 4;
    private static final int INGAME_FG_OBJECTS_ANIMATION_COUNT = 3;
    private static final int INGAME_FG_OBJECT_COUNT = 12;
    private static final int INGAME_FIREWORKS_EXPLOSION_MAX_DELAY = 500;
    private static final int INGAME_FIREWORKS_MAX_PARTICLES_PER_EXPLOSION = 20;
    private static final int INGAME_FIREWORKS_MAX_SIMULTANEOUS_EXPLOSIONS = 6;
    private static final int INGAME_FIREWORKS_PARTICLE_DATA_COORD_X = 0;
    private static final int INGAME_FIREWORKS_PARTICLE_DATA_COORD_Y = 1;
    private static final int INGAME_FIREWORKS_PARTICLE_DATA_COUNT = 7;
    private static final int INGAME_FIREWORKS_PARTICLE_DATA_LIFETIME = 5;
    private static final int INGAME_FIREWORKS_PARTICLE_DATA_TIME = 4;
    private static final int INGAME_FIREWORKS_PARTICLE_DATA_TYPE = 6;
    private static final int INGAME_FIREWORKS_PARTICLE_DATA_VEL_X = 2;
    private static final int INGAME_FIREWORKS_PARTICLE_DATA_VEL_Y = 3;
    private static final int INGAME_FIREWORKS_PARTICLE_TYPE_CRACKLE_1 = 1;
    private static final int INGAME_FIREWORKS_PARTICLE_TYPE_CRACKLE_2 = 3;
    private static final int INGAME_FIREWORKS_PARTICLE_TYPE_STARS = 0;
    private static final int INGAME_FLY_CAR_CNT = 8;
    private static final int INGAME_OPENING_STAGE_TIME = 620;
    private static final int INGAME_PARACHUTES_MAX_CNT = 16;
    private static final int INGAME_PARACHUTES_POS_X = 2;
    private static final int INGAME_PARACHUTES_POS_Y = 3;
    private static final int INGAME_PARACHUTES_SIZE = 7;
    private static final int INGAME_PARACHUTES_TIME = 1;
    private static final int INGAME_PARACHUTES_TYPE = 0;
    private static final int INGAME_PARACHUTES_VEL_X = 4;
    private static final int INGAME_PARACHUTES_VEL_Y = 5;
    private static final int INGAME_PARACHUTES_WATER_SPLASH = 6;
    private static final int INGAME_PASSENGER_TYPES = 2;
    private static final int INGAME_PASSENGER_TYPE_GHOST = 2;
    private static final int INGAME_PASSENGER_TYPE_UP_FIRST = 3;
    private static final int INGAME_POLES_SIZE = 2;
    private static final int INGAME_POLE_BACK = 0;
    private static final int INGAME_POLE_FRONT = 1;
    private static final int INGAME_ROLLING_STAGE_TIME = 320;
    private static final int INGAME_SPARKLES_END = 240;
    private static final int INGAME_SPARKLES_MIN_TIMER = 120;
    private static final int INGAME_SPARKLES_START = 0;
    private static final int INGAME_SPARKLE_BUFFER_SIZE = 8;
    private static final int INGAME_SPARKLE_TYPE_BRAKE = 0;
    private static final int INGAME_SPARKLE_TYPE_WATER = 1;
    private static final int INGAME_SPEED_LINE_DATA_COUNT = 4;
    private static final int INGAME_SPEED_LINE_DATA_END_X = 2;
    private static final int INGAME_SPEED_LINE_DATA_END_Y = 3;
    private static final int INGAME_SPEED_LINE_DATA_START_X = 0;
    private static final int INGAME_SPEED_LINE_DATA_START_Y = 1;
    private static final int INTERACTIVE_TUNNEL_BASE_CENTER_ID = 5;
    private static int INTERACTIVE_TUNNEL_BASE_HEIGHT = 0;
    private static final int INTERACTIVE_TUNNEL_BASE_LEFT_ID = 4;
    private static final int INTERACTIVE_TUNNEL_BASE_RIGHT_ID = 6;
    private static final int INTERACTIVE_TUNNEL_BUFFER_SIZE = 15;
    private static final int INTERACTIVE_TUNNEL_ROCK_STATE_DISABLED = 2;
    private static final int INTERACTIVE_TUNNEL_ROCK_STATE_RUNNING = 1;
    private static final int INTERACTIVE_TUNNEL_ROCK_STATE_WAITING = 0;
    private static final int INTERACTIVE_TUNNEL_TOP_CENTER_FLIP_ID = 2;
    private static final int INTERACTIVE_TUNNEL_TOP_CENTER_ID = 1;
    private static final int INTERACTIVE_TUNNEL_TOP_LEFT_ID = 0;
    private static final int INTERACTIVE_TUNNEL_TOP_RIGHT_ID = 3;
    private static int INTERACTIVE_TUNNEL_TOP_SEMIHEIGHT_FP = 0;
    private static int INTERACTIVE_TUNNEL_TOP_SEMIWIDTH_FP = 0;
    public static final int INTERACTIVE_WATER_HEIGHT = 50;
    public static boolean[] INTERACTIVE_WATER_LEVEL = null;
    private static final int INTERACTIVE_WATER_SPLASH_BUFFER_SIZE = 16;
    private static final int INTERACTIVE_WATER_SPLASH_MIN_TIMER = 120;
    private static final int INTERACTIVE_WATER_SPLASH_PASSENGER = 1;
    private static final int INTERACTIVE_WATER_SPLASH_TIME_END = 1000;
    private static final int INTERACTIVE_WATER_SPLASH_TIME_START = 0;
    private static final int INTERACTIVE_WATER_SPLASH_TRAIN = 0;
    public static final int INTERACTIVE_WATER_Y_LIMIT = 100;
    private static final int MAP_COUNTRY_CNT = 3;
    public static final int MAP_TRACKS_PER_CHUNK = 11;
    static final boolean NEW_COLOR_SCHEME = false;
    private static final int NUM_CLOUDS = 5;
    public static final byte PARTICLES_COOR_TYPE_SCREEN = 1;
    public static final byte PARTICLES_COOR_TYPE_WORLD = 0;
    private static final int PARTICLES_LAYER_COUNT = 5;
    public static final int PARTICLES_LAYER_NEAREST = 4;
    private static final int PARTICLES_POOL_COUNT = 240;
    public static final int PARTICLES_SCORE_TIMER = 2000;
    public static final int PARTICLES_STATE_AVAILABLE = 0;
    public static final int PARTICLES_STATE_MOVING = 2;
    public static final int PARTICLES_STATE_RUNNING = 1;
    private static final int PARTICLES_TYPE_COUNT = 9;
    public static final int PARTICLES_TYPE_FLASH = 8;
    public static final int PARTICLES_TYPE_JUMP = 1;
    public static final int PARTICLES_TYPE_PRIZE_3SMILEYS = 3;
    public static final int PARTICLES_TYPE_PRIZE_PERFECT = 5;
    public static final int PARTICLES_TYPE_PRIZE_SAFE = 4;
    public static final int PARTICLES_TYPE_SCORE = 2;
    public static final int PARTICLES_TYPE_SCORE_STARS = 6;
    public static final int PARTICLES_TYPE_SPEEDLINES = 7;
    public static final int PARTICLES_TYPE_TUNNEL = 0;
    private static final int PASSENGERS_IN_CAR = 2;
    private static final int PASSENGERS_UP_ARMS_TIME_PER_PASSENGER_MS = 200;
    public static final int PICK_UPS_SPEED_START = 8;
    public static final boolean PRECALCULATE_SEPARATORS = true;
    static final boolean PRIZES_DRAW_TEXT = false;
    private static final boolean RENDER_TRACK_POSTPROCESS = false;
    private static final boolean RENDER_TRACK_SHADOW = false;
    private static final int ROTATION_FRAMES = 36;
    private static final int ROTATION_FRAME_TO_ANGLE = 10;
    private static final int SAFE_LIMIT = 112;
    public static final int SPEEDLINES_PARTICLES = 0;
    public static final int SPEEDLINES_SPRITES = 2;
    public static final int SPEEDLINES_THRILL = 1;
    public static final int SPEED_SCALE = 30;
    static final int TIME_HUD_FEEDBACK_POSITIVE = 2000;
    static final int TIME_HUD_FEEDBACK_POSITIVE_EXCITER = 4000;
    private static final int TORNADO_PARTICLE_COUNT = 44;
    private static final int TORNADO_PARTICLE_LEAF_COUNT = 40;
    private static final int TORNADO_PARTICLE_TIME_X = 2000;
    private static final int TORNADO_PARTICLE_TIME_Y = 10000;
    private static final int TORNADO_PARTICLE_TYPE_COUNT = 1;
    private static final int TORNADO_PARTICLE_TYPE_LEAF = 0;
    static final int TRACK_ENHANCED_COLORS_VERTICAL_SEPARATOR_PC_SHADOW = 7667712;
    private static final int TRACK_ENHANCED_COLOR_TRANSPARENT = 0;
    private static final int TRACK_ENHANCED_SEPARATOR_START = 3;
    public static final boolean TRACK_MAXIMUM_DETAIL = true;
    private static final int TUNNEL_ROCK_COUNT = 2;
    private static final int TUNNEL_ROCK_GHOST_ID = 1;
    private static final int TUNNEL_ROCK_SPEED = 26;
    private static final int TUNNEL_ROCK_TRAIN_ID = 0;
    public static final boolean USE_CAMERA_LAG = true;
    public static final int USE_SPEEDLINES = 1;
    public static boolean smBeforeBreakGo = false;
    public static boolean smBeforeBreakGoGhost = false;
    public static int smBreakGoHeightFP = 0;
    public static int smBreakGoWidthFP = 0;
    public static int smBreakGoXFP = 0;
    public static int smBreakGoYFP = 0;
    public static int smDeltaTime = 0;
    public static int smDrawCount = 0;
    public static SpriteObject smEmphasizeSO = null;
    public static boolean smEnableCameraYLag = false;
    public static int smFallingTrackUpdateCount = 0;
    public static SpriteObject smIngameBreakGoSO = null;
    public static int smInteractiveBreakGoSegmentID = 0;
    public static int smInteractiveCurrentLevel = 0;
    private static int smInteractiveTunnelCount = 0;
    private static boolean smInteractiveTunnelRockFirstBounce = false;
    private static SpriteObject smInteractiveTunnelRockSO = null;
    private static int smInteractiveTunnelRockSOHeight = 0;
    private static int smInteractiveTunnelRockSOWidth = 0;
    private static int smInteractiveTunnelRockXFallFP = 0;
    private static int smInteractiveTunnelRockYFallFP = 0;
    private static SpriteObject smInteractiveTunnelSO = null;
    public static int smInteractiveWaterScreenY = 0;
    private static SpriteObject smInteractiveWaterSplashSO = null;
    private static int smInteractiveWaterSplashScreenX = 0;
    private static int smInteractiveWaterSplashTimerNewParticle = 0;
    public static int smInteractiveWaterWorldYFP = 0;
    private static int[] smParticlesAllDistance = null;
    private static byte[] smParticlesAnimationID = null;
    private static byte[] smParticlesAnimationIsAnimated = null;
    public static byte[] smParticlesCoorType = null;
    private static int[][] smParticlesDest = null;
    private static int[] smParticlesDrawCount = null;
    private static int[][] smParticlesDrawID = null;
    private static int[] smParticlesFrame = null;
    private static int[] smParticlesLayerID = null;
    public static int smParticlesNumFacesPerPassenger = 0;
    private static int[][] smParticlesScreenPos = null;
    private static int[][] smParticlesSpeedFP = null;
    public static int[] smParticlesSpeedZ = null;
    public static SpriteObject[] smParticlesSprite = null;
    public static byte[] smParticlesState = null;
    private static int[][] smParticlesWorldPos = null;
    public static int smScorseID = 0;
    public static boolean smStartCannonFireEffects = false;
    private static int smTrackEnhancedVerticalSeparatorAcum = 0;
    static final int smVerticalSeparatorMaxDX = 40;
    int[] averagePixels;
    private Image bufferTrack;
    public int debugFrameTime;
    public int debugFrameTimeAvg;
    public int debugFrameTimeMax;
    private int[] dstTrack;
    private Graphics gBackup;
    private int mBreakGoLastDrawX;
    public boolean mBreakGoPlayEffect;
    private int mCameraSpeedX;
    private int mCameraSpeedY;
    private int mCameraTargetX;
    private int mCameraTargetY;
    private int mCameraX;
    private int mCameraY;
    private SpriteObject mCart;
    private SpriteObject mCartHead;
    private SpriteObject mCometSprite;
    private int mCometTimer;
    private int mCometX;
    private int mCometY;
    public boolean mEnableCameraLag;
    public int mHudCurrentSign;
    private SpriteObject mHudDistanceLocator;
    public SpriteObject mHudDistanceLocatorCrash;
    private SpriteObject mHudDistanceMeterEnd;
    private SpriteObject mHudDistanceMeterMiddle;
    private SpriteObject mHudDistanceMeterStart;
    private SpriteObject mHudDistanceTornadoSO;
    private ImageFont mHudFont;
    public int mHudLastSignIdx;
    private SpriteObject mHudLeds;
    private SpriteObject mHudPanel;
    private boolean[] mHudPickUpsBlink;
    private int[] mHudPickUpsBlinkTime;
    private int[] mHudPickUpsMaxTime;
    public String[] mHudPickUpsText;
    public int mHudScoreSmileOffX;
    public int mHudScoreSmileY;
    private SpriteObject mHudScoreSmiley;
    private SpriteObject mHudScoreSmileyNegative;
    private int mHudSignBlinkTimer;
    private SpriteObject mHudSignCrash;
    public SpriteObject mHudSigns;
    private SpriteObject mHudSignsCursor;
    private boolean mHudSpeedMeterBroken;
    private SpriteObject mHudSpeedMeterStar;
    private SpriteObject mHudSpeedStar;
    private SpriteObject mHudStar;
    private boolean mHudTornadoDraw;
    private int mHudTornadoPresentationTimer;
    private int mIngameBGCount;
    private int[] mIngameBGLayerMaxY;
    private int[] mIngameBGLayerMinY;
    private int mIngameBGLayerWaterWorldYFP;
    private int[] mIngameBGLayersSOHeight;
    private int[] mIngameBGLayersSOPivotY;
    private int[] mIngameBGLayersSOWidth;
    private int[] mIngameBGLayersScreenY;
    private int[] mIngameBGLayersSpriteAnim;
    private int[] mIngameBGLayersSpriteX;
    private int[] mIngameBGLayersSpriteY;
    private int mIngameBGNextLayerIDToDraw;
    private SpriteObject mIngameBGObjects;
    private int mIngameBGObjectsWorldXFP;
    private SpriteObject mIngameBGSprite;
    private SpriteObject[] mIngameBGs;
    private SpriteObject mIngameCartGhost;
    public int mIngameCrashCoordX;
    public int mIngameCrashCoordY;
    public SpriteObject mIngameCrashPuff;
    private boolean mIngameFGObjectEnabled;
    private int[] mIngameFGObjectPosX;
    private int[] mIngameFGObjectPosY;
    private SpriteObject mIngameFGObjectSO;
    private int mIngameFGObjectTimer;
    private int[] mIngameFGObjectVelX;
    private int[] mIngameFGObjectVelY;
    private int[][][] mIngameFireworkParticleData;
    private int[] mIngameFireworksExplosionDelays;
    private long mIngameFireworksTimerEnd;
    private int mIngameFlyCars;
    private SpriteObject mIngameMultiplierAppear;
    private SpriteObject mIngameMultiplierNumbers;
    private boolean mIngameNegativeScoreFeedbackActive;
    private SpriteObject mIngameOpening;
    private SpriteObject mIngameParachute;
    private int[][] mIngameParachutes;
    private int mIngameParachutesActive;
    private int[][] mIngamePassengerTypes;
    private SpriteObject mIngamePlatformSupport;
    private SpriteObject mIngamePlatformTrack;
    private SpriteObject mIngamePlatformTrackEnd;
    private SpriteObject[][] mIngamePoleHeads;
    private SpriteObject[][] mIngamePoles;
    private boolean mIngamePositiveScoreFeedbackActive;
    private int mIngameScoreFeedbackTimer;
    private SpriteObject mIngameSparkle;
    private SpriteObject mIngameSparkleWater;
    private int mIngameSparklesTimer;
    private Image mIngameStaticBG;
    public int mIngameTimer;
    private Train mIngameTrain;
    private Train mIngameTrainGhost;
    private int mLensFade;
    private SpriteObject mLensSprites;
    private int mLensSunPositionX;
    private int mLensSunPositionXFP;
    private int mLensSunPositionY;
    private int mLensSunPositionYFP;
    private int mLensVectorLength;
    private int mLensVectorX;
    private int mLensVectorY;
    private SpriteObject mPassenger;
    public int[][] mPassengerPosX;
    public int[][] mPassengerPosY;
    private SpriteObject mPickUpBar;
    private int mPickUpBarW;
    private int mPickUpIconX;
    private int mPickUpIconY;
    public SpriteObject mPickUpIcons;
    public int mScorePrizePointsBackup;
    public int mScorePrizeTimer;
    private int mScoreTimeGettingScore;
    private int mScreenCropBottom;
    private int mScreenCropLeft;
    private int mScreenCropRight;
    private int mScreenCropTop;
    public int mScreenHeight;
    public int mScreenWidth;
    private boolean mSpeedStarVisible;
    public boolean mTornadoEnabled;
    private int mTornadoOffX;
    private int[] mTornadoParticleAngle;
    private int[] mTornadoParticleOffX;
    private SpriteObject[] mTornadoParticleSprite;
    private int[] mTornadoParticleState;
    private int[] mTornadoParticleTimerX;
    private int[] mTornadoParticleTimerY;
    private int[] mTornadoParticleType;
    private int[] mTornadoParticleX;
    private int[] mTornadoParticleY;
    private SpriteObject mTornadoSO;
    private int mTornadoSOFrameCount;
    private int mTornadoSOHeight;
    private int mTornadoSOWidth;
    private long mTornadoVelXFP;
    private int mTornadoX;
    private int mTornadoXFP;
    private SpriteObject mWheelSO;
    private int oldPosX;
    private int oldPosY;
    private int pixelLength;
    private int[] pixels;
    private short[] simpleCloudsX;
    private short[] simpleCloudsY;
    private int[] srcTrack;
    private static int TRACK_STICKY_HEIGHT_LOWER = 7;
    private static int[][] TRACK_ENHANCED_COLORS = {new int[]{11865444, 14565250, 11865444, 11998743, 0, 0, 0, 0, 11998743, Statics.TRACK_COUNTRY_0_COLOR_4, 16474624, 16474624, Statics.TRACK_COUNTRY_0_COLOR_4, 11998743, 11998743}, new int[]{54473, 64495, 54473, 32886, 0, 0, 0, 0, Statics.TRACK_COUNTRY_1_COLOR_3, Statics.TRACK_COUNTRY_1_COLOR_4, Statics.TRACK_COUNTRY_1_COLOR_5, Statics.TRACK_COUNTRY_1_COLOR_5, Statics.TRACK_COUNTRY_1_COLOR_4, Statics.TRACK_COUNTRY_1_COLOR_3, Statics.TRACK_COUNTRY_1_COLOR_6}, new int[]{Statics.TRACK_COUNTRY_2_COLOR_0, 16739576, Statics.TRACK_COUNTRY_2_COLOR_0, Statics.TRACK_COUNTRY_2_COLOR_2, 0, 0, 0, 0, Statics.TRACK_COUNTRY_2_COLOR_3, Statics.TRACK_COUNTRY_2_COLOR_4, Statics.TRACK_COUNTRY_2_COLOR_5, Statics.TRACK_COUNTRY_2_COLOR_5, Statics.TRACK_COUNTRY_2_COLOR_4, Statics.TRACK_COUNTRY_2_COLOR_3, Statics.TRACK_COUNTRY_2_COLOR_6}};
    private static int[] TRACK_ENHANCED_COLORS_FALL = {54473, 64495, 54473, 32886, 0, 0, 0, 0, Statics.TRACK_FALL_COLOR_3, Statics.TRACK_FALL_COLOR_4, Statics.TRACK_FALL_COLOR_5, Statics.TRACK_FALL_COLOR_5, Statics.TRACK_FALL_COLOR_4, Statics.TRACK_FALL_COLOR_3, Statics.TRACK_FALL_COLOR_6};
    private static int[][] TRACK_ENHANCED_COLOR_VERTICAL_SEPARATOR = {new int[]{14565250, Statics.TRACK_SEPARATOR_COUNTRY_0_COLOR_1, 11865444, Statics.TRACK_SEPARATOR_COUNTRY_0_COLOR_3}, new int[]{14565250, Statics.TRACK_SEPARATOR_COUNTRY_0_COLOR_1, 11865444, Statics.TRACK_SEPARATOR_COUNTRY_0_COLOR_3}, new int[]{16739576, Statics.TRACK_SEPARATOR_COUNTRY_2_COLOR_1, Statics.TRACK_SEPARATOR_COUNTRY_2_COLOR_2, Statics.TRACK_SEPARATOR_COUNTRY_2_COLOR_3}};
    private static int[] TRACK_ENHANCED_COLOR_VERTICAL_SEPARATOR_FALL = {64495, 32886, 2082233, 47790};
    private static int TRACK_ENHANCED_LINES_COUNT = TRACK_ENHANCED_COLORS[0].length;
    private static int TRACK_ENHANCED_SEPARATOR_HEIGHT = (TRACK_ENHANCED_LINES_COUNT - 3) - 1;
    private static int TRACK_ENHANCED_COLOR_VERTICAL_SEPARATOR_LINES = TRACK_ENHANCED_COLOR_VERTICAL_SEPARATOR[0].length;
    private static int smLastCountryLoaded = -1;
    private static int[] INGAME_BG_LAYER_OBJECT_SO_IDS = {-1};
    private static final int[] INGAME_SPEED_LINE_VARIATION_SPEEDS = {23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67};
    public static boolean smIngameResetNeeded = false;
    public static boolean smDrawHud = true;
    public static boolean smDrawTrain = true;
    public static boolean smDrawPoles = true;
    private static int CLOUD_WIDTH = 16;
    private static int CLOUD_HEIGHT = 10;
    private static final int[] CLOUD_COLORS = {12628208, 12628208, 12628208};
    public static int smLayerStart = 0;
    private static final int[] PICK_UPS_TIDS = {59, 61, 61, 60, 63, 62, 64, -1, 66, 67, 68, 69, -1, -1, -1, -1, 70, -1, -1, -1};
    public static boolean[] TORNADO_LEVEL = {false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, true, true};
    private static int smInteractiveWaterSplashPointer = 0;
    private static int[] smInteractiveWaterSplashWorldX = new int[16];
    private static int[] smInteractiveWaterSplashWorldY = new int[16];
    private static int[] smInteractiveWaterSplashTime = new int[16];
    private static int[] smInteractiveWaterSplashType = new int[16];
    private static int[] smInteractiveTunnelPiecesCount = new int[15];
    private static int[][] smInteractiveTunnelX = new int[15][];
    private static int[] smInteractiveTunnelY = new int[15];
    private static int[] smInteractiveParticlesTunnelY = new int[15];
    private static int[][] smInteractiveTunnelFrameID = new int[15][];
    private static final int[] INTERACTIVE_TUNNEL_BASE_FRAME = {4, 5, 5, 6};
    private static int[] smInteractiveTunnelRockState = new int[2];
    private static int[] smInteractiveTunnelRockXFP = new int[2];
    private static int[] smInteractiveTunnelRockYFP = new int[2];
    private static int[] smInteractiveTunnelRockVelXFP = new int[2];
    private static int[] smInteractiveTunnelRockVelYFP = new int[2];
    public static boolean[] smInteractiveBreakGoSegmentUsed = new boolean[2];
    private static int[] EMPHASIZE_SO_ANIMATION = {-1, -1, 4, 2, 1, 3, -1, -1};
    private static final int[] DIV_CONSTANTS = {2, 3, 4, 8, -2, -4};
    private static final int[] LENS_FLARE_FRAME = {4, 2, 0, 3, 5, 3};
    public boolean debugDrawBG = true;
    public boolean debugDrawFX = true;
    public boolean debugDrawTrains = true;
    public boolean debugDrawTrack = true;
    public boolean debugDrawLayer1 = true;
    public boolean debugDrawLayer2 = true;
    public boolean debugDrawLayer3 = true;
    public boolean debugDrawLayer4 = true;
    public int DEBUG_OPT_ORIGINAL = 0;
    public int DEBUG_OPT_MEDIUM = 1;
    public int DEBUG_OPT_MAX = 2;
    public int DEBUG_OPT_MAX_NOPRINT = 3;
    public int DEBUG_OPT_MAX_NOALIASING = 4;
    public int debugDrawOptimizationMode = this.DEBUG_OPT_MAX_NOPRINT;
    public String[] debugOptimizationStr = {"ORG", "MED", "MAX", "", "NO ANTI"};
    private boolean[] mIngameFlyCarIsGhost = new boolean[8];
    private int[] mIngameFlyCarX = new int[8];
    private int[] mIngameFlyCarY = new int[8];
    private int[] mIngameFlyVelX = new int[8];
    private int[] mIngameFlyVelY = new int[8];
    private int[] mIngameFlyAngle = new int[8];
    private int mIngameSparklesPointer = 0;
    private int[] mIngameSparklesX = new int[8];
    private int[] mIngameSparklesY = new int[8];
    private int[] mIngameSparklesTime = new int[8];
    private int[] mIngameSparklesType = new int[8];
    private boolean[] mIngameSparklesIsGhost = new boolean[8];
    private int mIngameCameraShakeTimer = 0;
    private int mIngameCameraShakeX = 0;
    private int mIngameCameraShakeY = 0;
    public boolean mIngameDrawCrashPuff = false;
    private int mIngameSpeedLineTimer = 0;
    private int[][][][] mIngameSpeedLines = (int[][][][]) Array.newInstance((Class<?>) Integer.TYPE, 4, 4, 5, 4);
    private boolean mIngameSpeedLinesDraw = false;
    private boolean mIngameSpeedLineClearLines = false;
    final int[][] INGAME_TRACK_COLORS = {new int[]{Statics.COLOR_TRACK_CHINA_1_FIRST, 55469}, new int[]{12650752, 16731150}, new int[]{16750848, 16776960}, new int[]{27848, 55469}, new int[]{12650752, 16731150}, new int[]{16750848, 16776960}, new int[]{27848, 16711680}, new int[]{12650752, 16731150}, new int[]{16750848, 16776960}};
    private boolean mIngameFireworksActive = false;
    public boolean mIngameFireworksHasFinished = false;
    private boolean mHudSignShow = true;

    Engine2D() {
    }

    private void HudDrawTimer(Graphics graphics) {
        int iStringWidth = this.mHudFont.stringWidth("0:00:00 ");
        int i = (this.mScreenWidth - iStringWidth) - 20;
        CustomMenuObject.drawPopup(graphics, i - 0, 10, iStringWidth + 10 + 0, this.mHudFont.getHeight(), true);
        GameScreens.drawTimer(graphics, this.mHudFont, GameEngine.smTotalPlayTime, (i + 5) - 0, 10);
    }

    public static void breakGoUsed(int i) {
        smInteractiveBreakGoSegmentUsed[i] = true;
    }

    public static boolean collisionBoxes(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return i2 + i4 >= i6 && i2 <= i6 + i8 && i + i3 >= i5 && i <= i5 + i7;
    }

    public static boolean collisionCircles(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i4 - i;
        int i8 = i5 - i2;
        int i9 = (i7 * i7) + (i8 * i8);
        int i10 = i3 + i6;
        return i9 <= i10 * i10;
    }

    private final void drawNumber(Graphics graphics, int i, int i2, SpriteObject spriteObject, int i3) {
        int width = spriteObject.getWidth();
        int i4 = i3;
        int i5 = i;
        while (i4 != 0) {
            spriteObject.setAnimationFrame(i4 % 10);
            i4 /= 10;
            i5 -= width;
            spriteObject.draw(graphics, i5, i2);
        }
    }

    private static void emphasizeLoad() {
        if (smEmphasizeSO == null) {
            smEmphasizeSO = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_E_NITRO, ResourceIDs.ANM_R_E_HAND, ResourceIDs.ANM_R_E_STICKY, ResourceIDs.ANM_R_E_JUMP_02, ResourceIDs.ANM_R_E_EXCITER}, false), true);
        }
    }

    public static int getFrameFromAngle(int i) {
        return 35 - (((((i + Util.SIN_SAMPLES_DEFAULT) - 5) % Util.SIN_SAMPLES_DEFAULT) * 36) / Util.SIN_SAMPLES_DEFAULT);
    }

    public static final int getLoadingStepCount() {
        return 6;
    }

    private int hsvBlend(int i, int i2, int i3, int i4) {
        return hsvToRgb(linearBlendColors(rgbToHsv(i), rgbToHsv(i2), i3, i4));
    }

    private int hsvToRgb(int i) {
        int i2 = (i >> 16) & 255;
        int i3 = (i >> 8) & 255;
        int i4 = i & 255;
        int i5 = i2 / 42;
        int i6 = (i2 - (i5 * 42)) * 6;
        int i7 = ((255 - i3) * i4) / 255;
        int i8 = ((255 - ((i6 * i3) / 255)) * i4) / 255;
        int i9 = ((255 - (((1 - i6) * i3) / 255)) * i4) / 255;
        switch (i5) {
            case 0:
                return (i9 << 8) | (i4 << 16) | i7;
            case 1:
                return (i8 << 16) | (i4 << 8) | i7;
            case 2:
                return i9 | (i7 << 16) | (i4 << 8);
            case 3:
                return (i7 << 16) | (i8 << 8) | i4;
            case 4:
                return (i9 << 16) | (i7 << 8) | i4;
            default:
                return (i4 << 16) | (i7 << 8) | i8;
        }
    }

    private void hudDrawDistanceMeter(Graphics graphics) {
        int iStringWidth = this.mHudFont.stringWidth("0:00:00 ");
        int width = (this.mScreenWidth - this.mHudPanel.getWidth()) - 30;
        int width2 = RollerGameEngine.smHotSeatMode == 0 ? width - (iStringWidth + 15) : width - this.mHudPanel.getWidth();
        int width3 = this.mHudPanel.getWidth() + 15;
        int height = ((this.mHudPanel.getHeight() - this.mHudDistanceMeterMiddle.getHeight()) / 2) + 5;
        int width4 = this.mHudDistanceMeterMiddle.getWidth();
        this.mHudDistanceMeterStart.setAnimationFrame(1);
        this.mHudDistanceMeterStart.draw(graphics, width3, height);
        this.mHudDistanceMeterEnd.setAnimationFrame(1);
        this.mHudDistanceMeterEnd.draw(graphics, width3 + width2, height);
        int width5 = this.mHudDistanceMeterStart.getWidth() + width3;
        this.mHudDistanceMeterMiddle.setAnimationFrame(1);
        int i = width5;
        for (int i2 = 0; i2 < width2 - (this.mHudDistanceMeterStart.getWidth() * 3); i2 += width4) {
            this.mHudDistanceMeterMiddle.draw(graphics, i, height);
            i += width4;
        }
        this.mHudDistanceMeterMiddle.draw(graphics, ((width3 + width2) - this.mHudDistanceMeterStart.getWidth()) - width4, height);
        int i3 = TrackModel.smTrackPointX[8] >> 12;
        int i4 = (TrackModel.smTrackPointX[this.mIngameTrain.mLastSegment] >> 12) - i3;
        int i5 = width2 - 6;
        int i6 = (((this.mIngameTrain.mCarX[0] >> 12) - i3) * i5) / i4;
        if (i6 > 0) {
            graphics.setClip(width3 + 3, 0, i6, this.mScreenHeight);
            this.mHudDistanceMeterStart.setAnimationFrame(0);
            this.mHudDistanceMeterStart.draw(graphics, width3, height);
            this.mHudDistanceMeterEnd.setAnimationFrame(0);
            this.mHudDistanceMeterEnd.draw(graphics, width3 + i5, height);
            int width6 = this.mHudDistanceMeterStart.getWidth() + width3;
            this.mHudDistanceMeterMiddle.setAnimationFrame(0);
            int i7 = width6;
            for (int i8 = 0; i8 < i5 - (this.mHudDistanceMeterStart.getWidth() * 3); i8 += width4) {
                this.mHudDistanceMeterMiddle.draw(graphics, i7, height);
                i7 += width4;
            }
            this.mHudDistanceMeterMiddle.draw(graphics, ((width3 + i5) - this.mHudDistanceMeterStart.getWidth()) - width4, height);
        }
        graphics.setClip(0, 0, this.mScreenWidth, this.mScreenHeight);
        for (int i9 = 0; i9 < 1; i9++) {
            if (GameEngine.smCrashSegIndices[i9] > -1) {
                this.mHudDistanceLocatorCrash.draw(graphics, ((((TrackModel.smTrackPointX[GameEngine.smCrashSegIndices[i9]] >> 12) - i3) * i5) / i4) + width3, height);
            }
        }
        this.mHudDistanceLocator.draw(graphics, width3 + i6 + 3, height);
        if (TORNADO_LEVEL[smInteractiveCurrentLevel] || Cheats.smTornadoOn) {
            int i10 = (((((this.mTornadoXFP * 2) / 3) >> 12) - i3) * i5) / i4;
            int width7 = this.mHudDistanceTornadoSO.getWidth() >> 1;
            int i11 = i10 < 0 ? 0 : i10 > i5 - width7 ? i5 - width7 : i10;
            if (this.mHudTornadoDraw) {
                this.mHudDistanceTornadoSO.draw(graphics, i11 + width3, height);
            }
        }
    }

    private void hudDrawPickUpsNames(Graphics graphics) {
        int height = Game.smSelectionFont.getHeight() + 5;
        int height2 = ((this.mHudPanel.getHeight() - this.mHudDistanceMeterMiddle.getHeight()) / 2) + 5 + this.mHudDistanceMeterMiddle.getHeight() + 5;
        graphics.setClip(0, 0, this.mScreenWidth, this.mScreenHeight);
        int i = 0;
        while (true) {
            int i2 = height2;
            if (i >= 8) {
                return;
            }
            int i3 = this.mIngameTrain.mPickUpsSorted[i];
            if (i3 > -1) {
                if (this.mHudPickUpsBlink[i] && this.mHudPickUpsText[i3] != null) {
                    Game.smTitleFont.drawString(graphics, this.mHudPickUpsText[i3], (this.mScreenWidth - Game.smTitleFont.stringWidth(this.mHudPickUpsText[i3])) / 2, i2, 20);
                }
                height2 = Game.smTitleFont.getHeight() + i2;
            } else {
                height2 = i2;
            }
            i++;
        }
    }

    private void hudDrawScore(Graphics graphics) {
        SpriteObject spriteObject;
        int i = this.mScreenWidth;
        if (Game.smCurrentState == 58 || Game.smCurrentState == 59 || Game.smCurrentState == 60 || Game.smCurrentState == 21 || this.mIngameTrain.trackEnded()) {
            spriteObject = this.mHudScoreSmiley;
            spriteObject.setAnimationFrame(spriteObject.getFrameCount() - 1);
        } else if (this.mIngameNegativeScoreFeedbackActive || this.mIngameTrain.hasFailed()) {
            spriteObject = this.mHudScoreSmileyNegative;
        } else {
            spriteObject = this.mHudScoreSmiley;
            if (!this.mIngamePositiveScoreFeedbackActive) {
                spriteObject.setAnimationFrame(this.mIngameTrain.mPoints > 0 ? 1 : 0);
            } else if (this.mIngameScoreFeedbackTimer < 0) {
                this.mIngamePositiveScoreFeedbackActive = false;
            } else {
                this.mIngameScoreFeedbackTimer -= smDeltaTime;
                spriteObject.logicUpdate(smDeltaTime);
            }
        }
        int width = this.mHudPanel.getWidth();
        int height = this.mHudPanel.getHeight();
        int i2 = i - (width + 5);
        this.mHudPanel.draw(graphics, i2, 5);
        this.mHudStar.draw(graphics, (this.mHudPanel.getWidth() / 2) + i2, 5);
        spriteObject.draw(graphics, (this.mHudPanel.getWidth() / 2) + i2, 5);
        int smilyes = this.mIngameTrain.getSmilyes();
        int iStringWidth = i2 + 0 + ((width - this.mHudFont.stringWidth(GameScreens.NUMBERS_MAX_WIDTH)) / 2);
        int height2 = ((height + 5) - Game.smTextFont.getHeight()) + 0;
        GameScreens.numbersDraw(graphics, this.mHudFont, smilyes, 2, iStringWidth - 0, height2 + 0, false);
        int iStringWidth2 = this.mHudFont.stringWidth("00") + iStringWidth;
        this.mHudFont.drawString(graphics, "/", iStringWidth2 - 0, height2 + 0, 20);
        GameScreens.numbersDraw(graphics, this.mHudFont, GameEngine.smCurrentLevelTotalScore, 2, (iStringWidth2 + this.mHudFont.stringWidth("/")) - 0, height2 + 0, false);
    }

    private void hudDrawSpeedMeter(Graphics graphics) {
        this.mHudPanel.draw(graphics, 5, 5);
        int iIngameGetSpeedHUD = this.mIngameTrain.mCarCnt == 0 ? 0 : ingameGetSpeedHUD();
        int width = (this.mHudLeds.getWidth() * iIngameGetSpeedHUD) / 320;
        if (this.mIngameTrain.mPickUpsEnabledCount > 0) {
            this.mHudSpeedMeterStar.draw(graphics, (this.mHudPanel.getWidth() >> 1) + 5, 5);
        }
        this.mHudLeds.setAnimationFrame(0);
        this.mHudLeds.draw(graphics, this.mHudLeds.getPivotX() + 5 + 0, 5);
        graphics.setClip(5, 5, width, this.mHudPanel.getHeight());
        this.mHudLeds.setAnimationFrame(1);
        this.mHudLeds.draw(graphics, this.mHudLeds.getPivotX() + 5 + 0, 5);
        graphics.setClip(0, 0, this.mScreenWidth, this.mScreenHeight);
        this.mSpeedStarVisible = false;
        if (iIngameGetSpeedHUD >= 320) {
            this.mHudSpeedStar.draw(graphics, 5, 5);
            this.mSpeedStarVisible = true;
        }
        int width2 = ((this.mHudPanel.getWidth() - this.mHudFont.stringWidth(GameScreens.SPEED_MAX_WIDTH)) / 2) + 5;
        ImageFont imageFont = this.mHudFont;
        if (DCThrillOld.isControllerEnabled()) {
        }
        int i = 5 + 0 + 0;
        GameScreens.numbersDraw(graphics, imageFont, iIngameGetSpeedHUD, 3, width2, (this.mHudPanel.getHeight() + 5) - this.mHudFont.getHeight(), false);
        if (DCThrillOld.isControllerEnabled()) {
            int i2 = Game.smCurrentState != 10 ? DCThrillOld.smTilt : 0;
            int height = this.mHudPanel.getHeight() + 5 + 5;
            int width3 = this.mHudPanel.getWidth();
            int height2 = (this.mHudDistanceMeterMiddle.getHeight() * 1) / 2;
            int iAbs = ((width3 >> 1) * Math.abs(i2)) / 10;
            graphics.setColor(16772015);
            graphics.fillRect(5 - 2, height - 2, width3 + 4, height2 + 4);
            graphics.setColor(1974275);
            graphics.fillRect(5 - 1, height - 1, width3 + 2, height2 + 2);
            graphics.setColor(4475144);
            graphics.fillRect(5, height, width3, height2);
            if (i2 < 0) {
                graphics.setColor(16711680);
                graphics.fillRect(((width3 >> 1) + 5) - iAbs, height, iAbs, height2);
            } else if (i2 > 0) {
                graphics.setColor(65280);
                graphics.fillRect((width3 >> 1) + 5, height, iAbs, height2);
            }
            graphics.setColor(16777215);
            graphics.fillRect((width3 >> 1) + 5, height - 1, 1, height2 + 2);
        }
    }

    private void hudDrawWarningSigns(Graphics graphics) {
        int i;
        int i2 = AnimationFrame.MAX_DURATION;
        int i3 = GameEngine.smTrain.mSegmentIdx[0];
        int length = TrackModel.sm_signs[0].length;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            if (TrackModel.sm_signs[1][i5] < i3) {
                i4++;
            }
        }
        int height = ((this.mScreenHeight - this.mHudSigns.getHeight()) - 0) + 0;
        this.mEnableCameraLag = false;
        if (i4 < length) {
            switch (TrackModel.sm_signs[0][i4]) {
                case 1:
                    if (DCThrillOld.smLevelIdx == 89) {
                        this.mHudCurrentSign = 4;
                        break;
                    } else {
                        this.mHudCurrentSign = 2;
                        break;
                    }
                case 2:
                    this.mHudCurrentSign = 1;
                    break;
                case 3:
                    this.mHudCurrentSign = 0;
                    break;
                case 4:
                    this.mHudCurrentSign = 3;
                    break;
                case 5:
                    this.mHudCurrentSign = 4;
                    break;
                case 6:
                    this.mHudCurrentSign = 5;
                    break;
                case 7:
                    this.mHudCurrentSign = 6;
                    break;
                case 8:
                    this.mHudCurrentSign = 7;
                    break;
                default:
                    this.mHudCurrentSign = -1;
                    break;
            }
            int i6 = TrackModel.smTrackPointX[TrackModel.sm_signs[1][i4]] - this.mIngameTrain.mCarX[0];
            if (TrackModel.sm_signs[0][i4] == 5) {
                i6 = TrackModel.smTrackPointX[(TrackModel.sm_signs[1][i4] - 6) + 1] - this.mIngameTrain.mCarX[0];
            }
            if (this.mHudCurrentSign == 6 || this.mHudCurrentSign == 5 || (this.mHudCurrentSign == 4 && this.mIngameTrain.mState != 4)) {
                int i7 = (this.mIngameTrain.mSpeed * 30) >> 12;
                if ((i6 >> 12) < (this.mScreenWidth * TextIDs.TID_SELECTION_ROLLER_TM1_1) / 100 && i7 > 150) {
                    this.mEnableCameraLag = true;
                }
            }
            if (i6 > 0 && this.mHudCurrentSign != -1) {
                i2 = TrackModel.sm_signs[1][i4];
                if (this.mHudSignShow) {
                    this.mHudSigns.setAnimation(this.mHudCurrentSign, 1, true);
                    this.mHudSigns.draw(graphics, (this.mScreenWidth >> 1) + ((i6 / 15) >> 12), height);
                    if (DCThrillOld.smLevelIdx == 89 && this.mHudCurrentSign == 4) {
                        this.mHudCurrentSign = 2;
                        this.mHudSigns.setAnimation(this.mHudCurrentSign, 1, true);
                        this.mHudSigns.draw(graphics, ((i6 / 15) >> 12) + (this.mScreenWidth >> 1) + (this.mScreenWidth / 6) + 10, height);
                    }
                }
            }
        } else {
            this.mHudCurrentSign = -1;
        }
        int i8 = AnimationFrame.MAX_DURATION;
        int iMin = i2;
        for (int i9 = 0; i9 < 1; i9++) {
            int i10 = GameEngine.smCrashSegIndices[i9];
            if (i10 > i3 && (i = TrackModel.smTrackPointX[i10] - this.mIngameTrain.mCarX[0]) > 0 && i < i8) {
                iMin = Math.min(i10, iMin);
                i8 = i;
            }
        }
        if (i8 != Integer.MAX_VALUE) {
            this.mHudSignCrash.draw(graphics, (this.mScreenWidth >> 1) + ((i8 / 15) >> 12), height);
        }
        if (this.mHudLastSignIdx != iMin && this.mHudLastSignIdx != 0) {
            if (this.mHudCurrentSign >= 0 && this.mHudCurrentSign <= 2 && DCThrillOld.smOnlyTutorialText) {
                DCThrillOld.smOnlyTutorialText = false;
                DCThrillOld.smTutorialSeen[DCThrillOld.smCurrentTutorial] = true;
                this.mHudSignShow = true;
                this.mHudSignBlinkTimer = 0;
            }
            this.mHudSignsCursor.setAnimationFrame(0);
        }
        this.mHudLastSignIdx = iMin;
        this.mHudSignsCursor.draw(graphics, this.mScreenWidth >> 1, height);
    }

    private void hudLoadResources() {
        if (this.mHudScoreSmiley == null) {
            this.mHudScoreSmiley = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_POINTS, false), false);
            this.mHudScoreSmileyNegative = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_POINTS_CRASH, false), false);
        }
        if (this.mHudSigns == null && this.mHudSigns == null) {
            this.mHudSigns = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_G_SIGNS_LOOP, ResourceIDs.ANM_R_G_SIGNS_SLOPE, ResourceIDs.ANM_R_G_SIGNS_BROKEN_RAIL, ResourceIDs.ANM_R_G_SIGNS_RAIL_CHANGE, ResourceIDs.ANM_R_G_SIGNS_SPRING, ResourceIDs.ANM_R_G_SIGNS_TUNNEL, -1, ResourceIDs.ANM_R_G_SIGNS_FALLING_RAILS}, false), false);
            this.mHudSignCrash = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGN_CRASH, false), true);
            this.mHudSignsCursor = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGN_DISAPPEAR, false), false);
            this.mHudSignsCursor.setAnimationFrame(this.mHudSignsCursor.getFrameCount() - 1);
            this.mHudLeds = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_SPEED, false), false);
            this.mHudPanel = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_FRAME, false), false);
            this.mHudSpeedStar = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_SPEED_MAXIMUM, false), true);
            this.mHudStar = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_STAR, false), true);
            this.mHudSpeedMeterStar = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_STAR, false), true);
            this.mHudDistanceLocator = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_TRACK_BAR_STAR, false), true);
            this.mHudDistanceLocatorCrash = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_CRASH, false), true);
            this.mHudDistanceMeterStart = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_TRACK_BAR_LEFT, false), false);
            this.mHudDistanceMeterMiddle = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_TRACK_BAR_CENTER, false), false);
            this.mHudDistanceMeterEnd = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_TRACK_BAR_RIGHT, false), false);
            this.mHudDistanceTornadoSO = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_TORNADO, false), true);
        }
        int length = PICK_UPS_TIDS.length;
        this.mHudPickUpsText = new String[length];
        this.mHudPickUpsBlink = new boolean[8];
        this.mHudPickUpsBlinkTime = new int[8];
        this.mHudPickUpsMaxTime = new int[8];
        for (int i = 0; i < 8; i++) {
            this.mHudPickUpsText[i] = Toolkit.getText(PICK_UPS_TIDS[i]);
            this.mHudPickUpsBlink[i] = false;
            this.mHudPickUpsBlinkTime[i] = 0;
            this.mHudPickUpsMaxTime[i] = 0;
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.mHudPickUpsText[i2] = Toolkit.getText(PICK_UPS_TIDS[i2]);
        }
        this.mHudFont = Game.smTextFont;
        this.mHudFont = Game.smSelectionFont;
        this.mPickUpIcons = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_I_POWERUPS_ICON_NITRO, ResourceIDs.ANM_R_E_SMILES_HUD, ResourceIDs.ANM_R_I_POWERUPS_ICON_EXCITER, ResourceIDs.ANM_R_I_POWERUPS_ICON_STICKY, ResourceIDs.ANM_R_I_POWERUPS_ICON_GOD, ResourceIDs.ANM_R_I_POWERUPS_ICON_WINGED, -1, -1, -1}, true), false);
        this.mPickUpBar = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_I_POWERUPS_BAR_EMPTY, ResourceIDs.ANM_R_I_POWERUPS_BAR_FULL}, true), false);
        this.mPickUpBar.setAnimation(1, 1, true);
        CollisionBox[] collisionBoxes = this.mPickUpBar.getCollisionBoxes();
        int x = collisionBoxes[0].getX();
        int y = collisionBoxes[0].getY();
        int width = collisionBoxes[0].getWidth();
        int height = collisionBoxes[0].getHeight();
        this.mPickUpBarW = collisionBoxes[1].getWidth();
        this.mPickUpIconX = (width / 2) + x;
        this.mPickUpIconY = (height / 2) + y;
    }

    private boolean ingameAddSparkles(Train train) {
        if ((!train.isUserBraking() && !this.mIngameTrain.mPickUpsEnabled[3]) || this.mIngameSparklesTimer < 120) {
            return false;
        }
        for (int i = 0; i < train.mCarCnt; i++) {
            int i2 = this.mIngameSparklesPointer + 1;
            this.mIngameSparklesPointer = i2;
            if (i2 == 8) {
                this.mIngameSparklesPointer = 0;
            }
            this.mIngameSparklesX[this.mIngameSparklesPointer] = train.getCarX(i) + (Util.rand() % 16384);
            this.mIngameSparklesY[this.mIngameSparklesPointer] = train.getCarY(i) + (Util.rand() % 16384);
            this.mIngameSparklesTime[this.mIngameSparklesPointer] = 0;
            this.mIngameSparklesIsGhost[this.mIngameSparklesPointer] = train.mIsGhost;
            this.mIngameSparklesType[this.mIngameSparklesPointer] = 0;
        }
        return true;
    }

    private void ingameDrawBackgroundGradient(Graphics graphics) {
        int iMin = Math.min(0, -(((this.mIngameTrain.mCamPointY / 4) >> 12) + 50));
        MenuObject.drawGradientRect(graphics, 0, iMin, Game.getGameAreaWidth(), (Math.max((Toolkit.getScreenHeight() * 2) + iMin, Toolkit.getScreenHeight()) - iMin) + 1, Statics.BG_COLOR_SIMPLE_TOP, Statics.BG_COLOR_SIMPLE_BOTTOM, 24);
        drawSimpleClouds(graphics);
    }

    private void ingameDrawParallaxBG(Graphics graphics, int i) {
        this.mIngameBGNextLayerIDToDraw++;
        if (i != 1 || this.debugDrawLayer1) {
            if (i != 2 || this.debugDrawLayer2) {
                if (i != 3 || this.debugDrawLayer3) {
                    if ((i != 4 || this.debugDrawLayer4) && i != 3) {
                        ingameDrawParallaxBG(graphics, i, this.mIngameTrain.mCamPointX >> 12, this.mIngameTrain.mCamPointY >> 12);
                    }
                }
            }
        }
    }

    private void ingameDrawParallaxBG(Graphics graphics, int i, int i2, int i3) {
        int i4 = this.mIngameBGLayersScreenY[i];
        int i5 = INGAME_BG_LAYER_NORMAL_SO[0][i];
        int i6 = INGAME_BG_LAYER_SPEED_DIVIDERS_NORMAL[0][i];
        int i7 = INGAME_BG_LAYER_FILLRECT_NORMAL_COLOR[0][i];
        if (this.mIngameBGLayersSOWidth[i5] == 0) {
            return;
        }
        int i8 = i6 - 1;
        int i9 = i8 > 0 ? ((-i2) >> i8) % this.mIngameBGLayersSOWidth[i5] : i8 < 0 ? ((-i2) << (-i8)) % this.mIngameBGLayersSOWidth[i5] : ((-i2) << 1) % this.mIngameBGLayersSOWidth[i5];
        while (i9 < this.mScreenWidth) {
            this.mIngameBGs[i5].draw(graphics, i9, i4);
            if (i5 == (0 == 2 ? 3 : 2)) {
                for (int i10 = 0; i10 < this.mIngameBGCount; i10++) {
                    this.mIngameBGSprite.setAnimation(this.mIngameBGLayersSpriteAnim[i10], -1, false);
                    this.mIngameBGSprite.setAnimationFrame(i10 % this.mIngameBGSprite.getFrameCount());
                    this.mIngameBGSprite.logicUpdate(this.mIngameTimer);
                    this.mIngameBGSprite.draw(graphics, this.mIngameBGLayersSpriteX[i10] + i9, this.mIngameBGLayersSpriteY[i10] + i4);
                }
            }
            i9 += this.mIngameBGLayersSOWidth[i5];
        }
        int i11 = i == 0 ? GRADIEN_BG_DAY_TIME_COLORS[GRADIEN_BG_DAY_TIME_TYPE[DCThrillOld.smSelectedTrack % 11]][1] : i7;
        if (i11 == -1 || i4 >= this.mScreenHeight) {
            return;
        }
        graphics.setColor(i11);
        int i12 = i4 + (this.mIngameBGLayersSOHeight[i5] - this.mIngameBGLayersSOPivotY[i5]);
        if (DCThrillOld.smSelectedCountry == 0) {
            i12 -= 10;
        }
        graphics.fillRect(0, i12, this.mScreenWidth, this.mScreenHeight - i12);
    }

    private void ingameDrawPickUpBar(Graphics graphics, int i, int i2) {
        int width = this.mPickUpBar.getWidth();
        int height = this.mPickUpBar.getHeight();
        int iSin = Util.sin(this.mIngameTrain.mCarAngle[0]);
        int iCos = Util.cos(this.mIngameTrain.mCarAngle[0]);
        int carX = this.mIngameTrain.getCarX(0) - (iSin * 20);
        int carY = this.mIngameTrain.getCarY(0) - (iCos * 20);
        int i3 = ((carX - this.mIngameTrain.mCamPointX) >> 12) + i;
        int i4 = (((carY - this.mIngameTrain.mCamPointY) >> 12) + i2) - 20;
        int i5 = (width - this.mPickUpBarW) / 2;
        int height2 = i4;
        for (int i6 = 0; i6 < 8; i6++) {
            int i7 = this.mIngameTrain.mPickUpsSorted[i6];
            if ((i7 > -1 && i7 < 6) || i7 == 7) {
                this.mPickUpBar.setAnimation(0, 0, true);
                this.mPickUpBar.draw(graphics, i3, height2);
                graphics.setClip((i3 - this.mPickUpBar.getPivotX()) + i5, height2 - height, (this.mIngameTrain.mPickUpsTime[i7] * this.mPickUpBarW) / Tuner.PARAMS_CONVOY_PICKUPS_TIME[i7], height);
                this.mPickUpBar.setAnimation(1, 0, true);
                this.mPickUpBar.draw(graphics, i3, height2);
                graphics.setClip(0, 0, this.mScreenWidth, this.mScreenHeight);
                this.mPickUpIcons.setAnimation(i7, 0, true);
                this.mPickUpIcons.draw(graphics, this.mPickUpIconX + i3, this.mPickUpIconY + height2);
                height2 -= this.mPickUpIcons.getHeight() + 5;
            }
        }
    }

    private void ingameDrawPoles(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
        for (int i6 = i3; i6 < i4; i6++) {
            if (i6 > 8 && i6 < this.mIngameTrain.mLastSegment - 6 && i6 != smInteractiveBreakGoSegmentID - 6) {
                ingameDrawPolesElement(graphics, i6, i, i2, i5);
            }
        }
    }

    private void ingameDrawPolesElement(Graphics graphics, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        SpriteObject spriteObject;
        int i7;
        SpriteObject spriteObject2;
        boolean z = i == 8 && i4 == 1;
        boolean z2 = i == this.mIngameTrain.mLastSegment - 6 && i4 == 1;
        boolean z3 = i == smInteractiveBreakGoSegmentID - 6 && i4 == 1;
        boolean z4 = z || z2 || z3;
        if (TrackModel.smHasPole[i] || z4) {
            int trackPointX = ((TrackModel.getTrackPointX(i) - this.mIngameTrain.mCamPointX) >> 12) + i2;
            int trackPointY = ((TrackModel.getTrackPointY(i) - this.mIngameTrain.mCamPointY) >> 12) + i3;
            if (this.debugDrawOptimizationMode == this.DEBUG_OPT_ORIGINAL || (trackPointX >= this.mScreenCropLeft && trackPointX <= this.mScreenCropRight && trackPointY >= this.mScreenCropTop && trackPointY <= this.mScreenCropBottom)) {
                if (z4) {
                    i5 = trackPointX;
                    i6 = trackPointY;
                } else {
                    int i8 = ((trackPointX - (this.mScreenWidth >> 1)) * 18) / this.mScreenWidth;
                    int i9 = ((trackPointY - (this.mScreenHeight >> 1)) * 25) / this.mScreenHeight;
                    if (i4 == 0) {
                        int i10 = trackPointY - 0;
                        i5 = trackPointX - 0;
                        i6 = i10;
                    } else {
                        int i11 = trackPointY + 0;
                        i5 = trackPointX + 0;
                        i6 = i11;
                    }
                }
                int i12 = -1;
                if (z3 || z4) {
                    spriteObject = null;
                } else if (z4) {
                    spriteObject = this.mIngamePlatformSupport;
                } else {
                    int i13 = i % 3;
                    i12 = i13;
                    spriteObject = this.mIngamePoles[i13][i4];
                }
                if (z3) {
                    spriteObject2 = smIngameBreakGoSO;
                    i7 = 0;
                } else if (!z && !z2) {
                    SpriteObject spriteObject3 = this.mIngamePoleHeads[i12][i4];
                    int iSin = Util.sin(TrackModel.smTrackSlope[i]);
                    int iCos = Util.cos(TrackModel.smTrackSlope[i]);
                    int i14 = ((iSin * ((TRACK_ENHANCED_LINES_COUNT * 3) / 2)) >> 12) + i5;
                    i6 += (((TRACK_ENHANCED_LINES_COUNT * 3) / 2) * iCos) >> 12;
                    i5 = i14;
                    spriteObject2 = spriteObject3;
                    i7 = i4 == 1 ? -6 : 6;
                } else if (z) {
                    spriteObject2 = this.mIngamePlatformTrack;
                    i7 = 0;
                } else if (z2) {
                    spriteObject2 = this.mIngamePlatformTrackEnd;
                    i7 = 0;
                } else {
                    i7 = 0;
                    spriteObject2 = null;
                }
                int i15 = 0;
                int height = spriteObject == null ? this.mScreenHeight : spriteObject.getHeight();
                int i16 = i5;
                for (int i17 = i6; i17 < this.mScreenHeight; i17 += height) {
                    if (spriteObject != null) {
                        spriteObject.draw(graphics, i16, i17);
                    }
                    if (spriteObject2 != null && i17 == i6) {
                        spriteObject2.draw(graphics, i16, i17);
                        if (z3) {
                            this.mBreakGoLastDrawX = i16;
                        }
                    }
                    i15++;
                    i16 += i7;
                }
            }
        }
    }

    private void ingameDrawSimple(Graphics graphics) {
        int iIngameGetCameraX = ingameGetCameraX();
        int iIngameGetCameraY = ingameGetCameraY();
        graphics.setColor(this.INGAME_TRACK_COLORS[DCThrillOld.smSelectedTheme][1]);
        int i = this.mIngameTrain.mSegmentIdx[0];
        int trackPointX = TrackModel.getTrackPointX(i) - this.mIngameTrain.mCamPointX;
        int trackPointY = TrackModel.getTrackPointY(i) - this.mIngameTrain.mCamPointY;
        int i2 = 0;
        int i3 = 0;
        int i4 = trackPointY;
        int i5 = trackPointX;
        while (i2 >= 0 && i2 < this.mScreenWidth) {
            int i6 = i3 + 1;
            int trackPointX2 = TrackModel.getTrackPointX(i + i6) - this.mIngameTrain.mCamPointX;
            int trackPointY2 = TrackModel.getTrackPointY(i + i6) - this.mIngameTrain.mCamPointY;
            int i7 = (trackPointX2 >> 12) + iIngameGetCameraX;
            if (TrackModel.isHole(this.mIngameTrain, i + i6)) {
                i4 = trackPointY2;
                i5 = trackPointX2;
                i3 = i6;
                i2 = i7;
            } else {
                graphics.drawLine(i7, (trackPointY2 >> 12) + iIngameGetCameraY, (i5 >> 12) + iIngameGetCameraX, (i4 >> 12) + iIngameGetCameraY);
                i4 = trackPointY2;
                i5 = trackPointX2;
                i3 = i6;
                i2 = i7;
            }
        }
        int i8 = trackPointY;
        int i9 = trackPointX;
        int i10 = 0;
        int i11 = 0;
        while (i10 >= 0 && i10 < this.mScreenWidth) {
            int i12 = i11 - 1;
            int trackPointX3 = TrackModel.getTrackPointX(i + i12) - this.mIngameTrain.mCamPointX;
            int trackPointY3 = TrackModel.getTrackPointY(i + i12) - this.mIngameTrain.mCamPointY;
            int i13 = (trackPointX3 >> 12) + iIngameGetCameraX;
            if (TrackModel.isHole(this.mIngameTrain, i + i12)) {
                i8 = trackPointY3;
                i9 = trackPointX3;
                i11 = i12;
                i10 = i13;
            } else {
                graphics.drawLine(i13, (trackPointY3 >> 12) + iIngameGetCameraY, (i9 >> 12) + iIngameGetCameraX, (i8 >> 12) + iIngameGetCameraY);
                i8 = trackPointY3;
                i9 = trackPointX3;
                i11 = i12;
                i10 = i13;
            }
        }
        for (int i14 = 0; i14 != this.mIngameTrain.mCarCnt; i14++) {
            this.mCart.setAnimationFrame(getFrameFromAngle(this.mIngameTrain.mCarAngle[i14]));
            this.mCart.draw(graphics, ((this.mIngameTrain.getCarX(i14) - this.mIngameTrain.mCamPointX) >> 12) + iIngameGetCameraX, ((this.mIngameTrain.getCarY(i14) - this.mIngameTrain.mCamPointY) >> 12) + iIngameGetCameraY);
        }
        for (int i15 = 0; i15 != 8; i15++) {
            if (this.mIngameSparklesTime[i15] != 240 && !this.mIngameSparklesIsGhost[i15]) {
                this.mIngameSparkle.setAnimationFrame(0);
                this.mIngameSparkle.logicUpdate(this.mIngameSparklesTime[i15]);
                this.mIngameSparkle.draw(graphics, ((this.mIngameSparklesX[i15] - this.mIngameTrain.mCamPointX) >> 12) + iIngameGetCameraX, ((this.mIngameSparklesY[i15] - this.mIngameTrain.mCamPointY) >> 12) + iIngameGetCameraY);
            }
        }
    }

    private void ingameDrawTrain(Graphics graphics, int i, int i2, Train train) {
        int i3;
        int i4;
        SpriteObject spriteObject = train.mIsGhost ? this.mIngameCartGhost : this.mCart;
        int i5 = this.mScoreTimeGettingScore > 0 ? (this.mScoreTimeGettingScore / 200) - 1 : -1;
        boolean z = false;
        if (train.mTrailState != 0 && !train.mIsGhost) {
            train.trailDraw(graphics, i, i2);
            z = true;
        }
        SpriteObject spriteObject2 = spriteObject;
        for (int i6 = 0; i6 < train.mCarCnt; i6++) {
            int frameFromAngle = getFrameFromAngle(train.mCarAngle[i6]);
            int carX = ((train.getCarX(i6) - train.mCamPointX) >> 12) + i;
            int carY = ((train.getCarY(i6) - train.mCamPointY) >> 12) + i2;
            if (!train.mPickUpsEnabled[3] || train.mIsGhost) {
                i3 = carX;
                i4 = carY;
            } else {
                int i7 = (-frameFromAngle) * 10;
                int iSin = Util.sin(i7);
                int iCos = Util.cos(i7);
                int i8 = TRACK_STICKY_HEIGHT_LOWER;
                int i9 = carY + ((iCos * i8) >> 12);
                i3 = carX + ((iSin * i8) >> 12);
                i4 = i9;
            }
            if (train.mPassengers[i6] > 0) {
                for (int i10 = 0; i10 != 2; i10++) {
                    this.mPassenger.setAnimation(train.mIsGhost ? 2 : ((i6 * 2) + ((i10 + 1) % 2) <= i5 ? 3 : 0) + this.mIngamePassengerTypes[i6][i10], 0, true);
                    this.mPassenger.setAnimationFrame(frameFromAngle);
                    this.mPassenger.draw(graphics, this.mPassengerPosX[frameFromAngle][i10] + i3, this.mPassengerPosY[frameFromAngle][i10] + i4);
                }
            }
            if (!train.mIsGhost) {
                ingameDrawWheels(graphics, frameFromAngle, i3, i4);
            }
            if (!train.mIsGhost) {
                if (i6 == 0) {
                    spriteObject2 = this.mCartHead;
                } else if (i6 == 1) {
                    spriteObject2 = spriteObject;
                }
            }
            spriteObject2.setAnimationFrame(frameFromAngle);
            spriteObject2.draw(graphics, i3, i4);
            if (!train.mIsGhost) {
                for (int i11 = 0; i11 < 8; i11++) {
                    if (train.mPickUpsEnabled[i11] && EMPHASIZE_SO_ANIMATION[i11] != -1) {
                        smEmphasizeSO.setAnimation(EMPHASIZE_SO_ANIMATION[i11], -1, false);
                        smEmphasizeSO.setAnimationFrame(i6 % smEmphasizeSO.getFrameCount());
                        smEmphasizeSO.logicUpdate(this.mIngameTimer);
                        smEmphasizeSO.draw(graphics, i3, i4);
                    }
                }
            }
        }
        if (!z) {
        }
        if (this.mIngameTrain.mState != 0 || train.mIsGhost) {
            return;
        }
        ingameDrawPickUpBar(graphics, i, i2);
    }

    private void ingameDrawTrainAndEffects(Graphics graphics, int i, int i2, boolean z) {
        Train train = z ? this.mIngameTrainGhost : this.mIngameTrain;
        boolean z2 = z ? smBeforeBreakGoGhost : smBeforeBreakGo;
        if (train.mState == 4) {
            return;
        }
        if (z2 && this.mBreakGoLastDrawX > 0) {
            graphics.setClip(0, 0, this.mBreakGoLastDrawX, this.mScreenHeight);
        }
        if (z) {
            ingameDrawTrain(graphics, FP.toInt(this.mIngameTrainGhost.getCarX(0) - this.mIngameTrain.mCamPointX) + i, FP.toInt(this.mIngameTrainGhost.getCarY(0) - this.mIngameTrain.mCamPointY) + i2, this.mIngameTrainGhost);
        } else {
            ingameDrawTrain(graphics, i, i2, this.mIngameTrain);
        }
        for (int i3 = 0; i3 != 8; i3++) {
            if (this.mIngameSparklesTime[i3] != 240) {
                if (this.mIngameSparklesIsGhost[i3] == z) {
                    SpriteObject spriteObject = this.mIngameSparkle;
                    spriteObject.setAnimationFrame(0);
                    spriteObject.logicUpdate(this.mIngameSparklesTime[i3]);
                    spriteObject.draw(graphics, ((this.mIngameSparklesX[i3] - this.mIngameTrain.mCamPointX) >> 12) + i, ((this.mIngameSparklesY[i3] - this.mIngameTrain.mCamPointY) >> 12) + i2);
                } else {
                    SpriteObject spriteObject2 = this.mIngameSparkle;
                    spriteObject2.setAnimationFrame(0);
                    spriteObject2.logicUpdate(this.mIngameSparklesTime[i3]);
                    spriteObject2.draw(graphics, ((this.mIngameSparklesX[i3] - this.mIngameTrain.mCamPointX) >> 12) + i, ((this.mIngameSparklesY[i3] - this.mIngameTrain.mCamPointY) >> 12) + i2);
                }
            }
        }
        if (this.mIngameDrawCrashPuff && !z) {
            this.mIngameCrashPuff.draw(graphics, FP.toInt(this.mIngameCrashCoordX - this.mIngameTrain.mCamPointX) + i, FP.toInt(this.mIngameCrashCoordY - this.mIngameTrain.mCamPointY) + i);
        }
        if (!smBeforeBreakGo || this.mBreakGoLastDrawX <= 0) {
            return;
        }
        graphics.setClip(0, 0, this.mScreenWidth, this.mScreenHeight);
    }

    private void ingameDrawWheels(Graphics graphics, int i, int i2, int i3) {
        if (this.mIngameTrain.mPickUpsEnabled[3]) {
            return;
        }
        int i4 = (-i) * 10;
        int iSin = Util.sin(i4);
        int iCos = (Util.cos(i4) * 11) >> 12;
        int i5 = (iSin * 11) >> 12;
        this.mWheelSO.draw(graphics, i2 - iCos, i3 + i5);
        this.mWheelSO.draw(graphics, iCos + i2, i3 - i5);
    }

    private final void ingameFGObjectDraw(Graphics graphics) {
        if (this.mIngameFGObjectEnabled) {
            int iIngameGetCameraX = ingameGetCameraX();
            int iIngameGetCameraY = ingameGetCameraY();
            int width = this.mIngameFGObjectSO.getWidth();
            int height = this.mIngameFGObjectSO.getHeight();
            int i = 0;
            for (int i2 = 0; i2 != 12; i2++) {
                int iSin = ((this.mIngameFGObjectPosX[i2] - this.mIngameTrain.mCamPointX) >> 12) + iIngameGetCameraX + Util.sin((this.mIngameTimer / 5) + (i2 * 100), this.mIngameFGObjectSO.getWidth() >> 2);
                int i3 = ((this.mIngameFGObjectPosY[i2] - this.mIngameTrain.mCamPointY) >> 12) + iIngameGetCameraY;
                if (i3 < (-height) || iSin < (-width)) {
                    i++;
                } else {
                    this.mIngameFGObjectSO.setAnimation(i2 % 3, -1, false);
                    this.mIngameFGObjectSO.setAnimationFrame(i2 % this.mIngameFGObjectSO.getFrameCount());
                    this.mIngameFGObjectSO.logicUpdate(this.mIngameTimer);
                    this.mIngameFGObjectSO.draw(graphics, iSin, i3);
                }
            }
            this.mIngameFGObjectEnabled = i < 12;
        }
    }

    private final void ingameFGObjectUpdate(int i) {
        if (!this.mIngameFGObjectEnabled) {
            this.mIngameFGObjectTimer += i;
            if (this.mIngameFGObjectTimer >= 1000) {
                ingameFGObjectInit();
                this.mIngameFGObjectTimer = 0;
                this.mIngameFGObjectEnabled = true;
                return;
            }
            return;
        }
        for (int i2 = 0; i2 != 12; i2++) {
            int[] iArr = this.mIngameFGObjectPosX;
            iArr[i2] = iArr[i2] + (this.mIngameFGObjectVelX[i2] * i);
            int[] iArr2 = this.mIngameFGObjectPosY;
            iArr2[i2] = iArr2[i2] + (this.mIngameFGObjectVelY[i2] * i);
        }
    }

    private final void ingameFireworksDraw(Graphics graphics) {
        for (int i = 0; i < this.mIngameFireworkParticleData.length; i++) {
            if (this.mIngameFireworksExplosionDelays[i] >= 0) {
                for (int[] iArr : this.mIngameFireworkParticleData[i]) {
                    if (iArr[4] != -1) {
                        SpriteObject spriteObject = this.mIngameSparkle;
                        spriteObject.setAnimationFrame((((iArr[4] << 1) % iArr[5]) * (spriteObject.getFrameCount() - 1)) / iArr[5]);
                        spriteObject.draw(graphics, FP.toInt(iArr[0]), FP.toInt(iArr[1]));
                    }
                }
            }
        }
    }

    private final void ingameFireworksInitExplosion(int[][] iArr) {
        int gameAreaWidth = Game.getGameAreaWidth();
        int screenHeight = Toolkit.getScreenHeight();
        int fp = FP.toFP((Math.abs(Util.rand()) % (gameAreaWidth - (gameAreaWidth >> 1))) + (gameAreaWidth >> 2));
        int fp2 = FP.toFP((Math.abs(Util.rand()) % (screenHeight - (screenHeight >> 1))) + (screenHeight >> 2));
        for (int[] iArr2 : iArr) {
            int fp3 = FP.toFP(1);
            int iRand = Util.rand() % fp3;
            int iRand2 = Util.rand() % fp3;
            if (Math.abs(Util.rand()) % 2 == 1) {
                if (Math.abs(Util.rand()) % 2 == 1) {
                    iArr2[6] = 1;
                } else {
                    iArr2[6] = 3;
                }
                iRand >>= 3;
                iRand2 >>= 2;
            } else {
                iArr2[6] = 0;
            }
            iArr2[0] = fp;
            iArr2[1] = fp2;
            iArr2[2] = iRand;
            iArr2[3] = iRand2;
            iArr2[4] = 0;
            iArr2[5] = ((Util.rand() % 500) + 2000) - TextIDs.TID_ACHIEVEMENTS_TITLE_20;
        }
    }

    private final void ingameFireworksUpdate(int i) {
        int gameAreaWidth = Game.getGameAreaWidth() >> 3;
        for (int i2 = 0; i2 < this.mIngameFireworkParticleData.length; i2++) {
            if (this.mIngameFireworksExplosionDelays[i2] < 0) {
                int[] iArr = this.mIngameFireworksExplosionDelays;
                iArr[i2] = iArr[i2] + i;
            } else {
                int[][] iArr2 = this.mIngameFireworkParticleData[i2];
                boolean z = false;
                for (int[] iArr3 : iArr2) {
                    if (iArr3[4] != -1) {
                        iArr3[4] = iArr3[4] + i;
                        if (iArr3[4] > iArr3[5]) {
                            iArr3[4] = -1;
                        } else if (iArr3[0] < (-gameAreaWidth) || iArr3[0] > FP.toFP(Game.getGameAreaWidth() + gameAreaWidth) || iArr3[1] < (-gameAreaWidth) || iArr3[1] > FP.toFP(Toolkit.getScreenHeight() + gameAreaWidth)) {
                            iArr3[4] = -1;
                        } else {
                            int fp = FP.toFP(i, 20);
                            iArr3[0] = iArr3[0] + FP.mul(iArr3[2], fp);
                            iArr3[1] = FP.mul(iArr3[3], fp) + iArr3[1];
                            iArr3[2] = FP.mul(iArr3[2], 4096);
                            iArr3[3] = FP.mul(iArr3[3], 4096);
                            iArr3[3] = FP.toFP(1, 100) + iArr3[3];
                            z = true;
                        }
                    }
                }
                if (!z) {
                    ingameFireworksInitExplosion(iArr2);
                    this.mIngameFireworksExplosionDelays[i2] = -(Math.abs(Util.rand()) % 500);
                }
            }
        }
        if (System.currentTimeMillis() >= this.mIngameFireworksTimerEnd) {
            this.mIngameFireworksHasFinished = true;
        }
    }

    private void ingameFreeResources() {
        if (smInteractiveTunnelSO != null) {
            smInteractiveTunnelSO.freeResources();
            smInteractiveTunnelSO = null;
        }
    }

    private void ingameLoadResources() {
        this.mEnableCameraLag = false;
        if (this.mIngameOpening == null) {
            int[] iArr = {ResourceIDs.ANM_R_C_PASSENGER_01_UMBRELLA, ResourceIDs.ANM_R_C_PASSENGER_02_UMBRELLA, ResourceIDs.ANM_R_C_PASSENGER_GHOST_UMBRELLA};
            this.mIngameOpening = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_C_PASSENGER_01_FALL, ResourceIDs.ANM_R_C_PASSENGER_02_FALL, ResourceIDs.ANM_R_C_PASSENGER_GHOST_FALL}, false), false);
            this.mIngameParachute = new SpriteObject(DavinciUtilities.loadAnimations(iArr, false), false);
        }
        if (this.mIngameCartGhost == null) {
            this.mIngameCartGhost = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_C_CART_GHOST, ResourceIDs.ANM_R_C_CART_GHOST, ResourceIDs.ANM_R_C_CART_GHOST}, false), false);
        }
        if (this.mIngameParachutes == null) {
            this.mIngameParachutes = new int[16][];
            for (int i = 0; i != 16; i++) {
                this.mIngameParachutes[i] = new int[7];
            }
            this.mIngamePassengerTypes = new int[4][];
            for (int i2 = 0; i2 != 4; i2++) {
                this.mIngamePassengerTypes[i2] = new int[2];
                for (int i3 = 0; i3 != 2; i3++) {
                    this.mIngamePassengerTypes[i2][i3] = i3 % 2;
                }
            }
        }
        if (smLastCountryLoaded != DCThrillOld.smSelectedCountry) {
            ingameFreeResources();
            smLastCountryLoaded = DCThrillOld.smSelectedCountry;
            int[] iArr2 = {ResourceIDs.ANM_R_G_THEME_01_STATION_NEW, -1, -1};
            int[] iArr3 = {ResourceIDs.ANM_R_G_THEME_01_STATION_NEW, -1, -1};
            this.mIngamePlatformTrack = new SpriteObject(DavinciUtilities.loadAnimation(iArr2[0], false), false);
            this.mIngamePlatformTrackEnd = new SpriteObject(DavinciUtilities.loadAnimation(iArr3[0], false), false);
        }
        if (smIngameBreakGoSO == null) {
            smIngameBreakGoSO = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_I_CANON_45, false), false);
        }
        if (this.mIngamePoles == null) {
            int[][] iArr4 = {new int[]{ResourceIDs.ANM_R_T_TRACK_01_COLUMN_02_DARK, ResourceIDs.ANM_R_T_TRACK_01_COLUMN_02}, new int[]{ResourceIDs.ANM_R_T_TRACK_02_COLUMN_02_DARK, ResourceIDs.ANM_R_T_TRACK_02_COLUMN_02}, new int[]{ResourceIDs.ANM_R_T_TRACK_03_COLUMN_02_DARK, ResourceIDs.ANM_R_T_TRACK_03_COLUMN_02}};
            int[][] iArr5 = {new int[]{ResourceIDs.ANM_R_T_TRACK_01_COLUMN_01_DARK, ResourceIDs.ANM_R_T_TRACK_01_COLUMN_01}, new int[]{ResourceIDs.ANM_R_T_TRACK_02_COLUMN_01_DARK, ResourceIDs.ANM_R_T_TRACK_02_COLUMN_01}, new int[]{ResourceIDs.ANM_R_T_TRACK_03_COLUMN_01_DARK, ResourceIDs.ANM_R_T_TRACK_03_COLUMN_01}};
            this.mIngamePoles = new SpriteObject[3][];
            this.mIngamePoleHeads = new SpriteObject[3][];
            for (int i4 = 0; i4 != 3; i4++) {
                this.mIngamePoles[i4] = new SpriteObject[2];
                this.mIngamePoleHeads[i4] = new SpriteObject[2];
                for (int i5 = 0; i5 != 2; i5++) {
                    this.mIngamePoles[i4][i5] = new SpriteObject(DavinciUtilities.loadAnimation(iArr4[i4][i5], false), false);
                    this.mIngamePoleHeads[i4][i5] = new SpriteObject(DavinciUtilities.loadAnimation(iArr5[i4][i5], false), false);
                }
            }
        }
        if (this.mIngameSparkle == null) {
            this.mIngameSparkle = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_E_SPARKLES, false), true);
        }
        if (this.mIngameCrashPuff == null) {
            this.mIngameCrashPuff = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_E_CAR_CRASH, false), false);
        }
        interactiveLoad();
        tornadoLoad();
        particlesLoad();
    }

    private final void ingameResetCameraShake() {
        this.mIngameCameraShakeTimer = 0;
        this.mIngameCameraShakeX = 0;
        this.mIngameCameraShakeY = 0;
    }

    private static void interactiveAddTunnel(int i, int i2, int i3) {
        int i4 = i >> 12;
        int i5 = (i2 >> 12) - i4;
        smInteractiveTunnelSO.setAnimation(0, -1, false);
        int width = smInteractiveTunnelSO.getWidth();
        smInteractiveTunnelSO.setAnimation(1, -1, false);
        int width2 = smInteractiveTunnelSO.getWidth();
        int i6 = width2 << 12;
        int i7 = width2 * 2;
        int i8 = i7 << 12;
        int i9 = i4 + width;
        int i10 = (i5 - (width * 2)) / i7;
        int i11 = (i10 * i7) + i9;
        smInteractiveTunnelPiecesCount[smInteractiveTunnelCount] = (i10 * 2) + 2;
        smInteractiveTunnelX[smInteractiveTunnelCount] = new int[smInteractiveTunnelPiecesCount[smInteractiveTunnelCount]];
        smInteractiveTunnelFrameID[smInteractiveTunnelCount] = new int[smInteractiveTunnelPiecesCount[smInteractiveTunnelCount]];
        int i12 = i9 << 12;
        smInteractiveTunnelX[smInteractiveTunnelCount][0] = i12;
        smInteractiveTunnelFrameID[smInteractiveTunnelCount][0] = 0;
        int i13 = 0 + 1;
        int i14 = i12;
        for (int i15 = 0; i15 < i10; i15++) {
            smInteractiveTunnelX[smInteractiveTunnelCount][i13] = i14;
            smInteractiveTunnelFrameID[smInteractiveTunnelCount][i13] = 1;
            int i16 = i13 + 1;
            smInteractiveTunnelX[smInteractiveTunnelCount][i16] = i14 + i6;
            smInteractiveTunnelFrameID[smInteractiveTunnelCount][i16] = 2;
            i13 = i16 + 1;
            i14 += i8;
        }
        smInteractiveTunnelX[smInteractiveTunnelCount][i13] = i14;
        smInteractiveTunnelFrameID[smInteractiveTunnelCount][i13] = 3;
        smInteractiveTunnelY[smInteractiveTunnelCount] = i3;
        smInteractiveParticlesTunnelY[smInteractiveTunnelCount] = smInteractiveTunnelY[smInteractiveTunnelCount] - INTERACTIVE_TUNNEL_TOP_SEMIHEIGHT_FP;
        for (int i17 = 0; i17 < 2; i17++) {
            smInteractiveTunnelRockXFP[i17] = smInteractiveTunnelX[smInteractiveTunnelCount][0];
            smInteractiveTunnelRockXFallFP = i14;
            smInteractiveTunnelRockYFP[i17] = (smInteractiveTunnelY[smInteractiveTunnelCount] - (INTERACTIVE_TUNNEL_TOP_SEMIHEIGHT_FP << 1)) - (smInteractiveTunnelRockSOHeight << 11);
        }
        smInteractiveTunnelCount++;
    }

    private static void interactiveAddWaterSplash(int i, int i2, int i3) {
        if (smInteractiveWaterSplashTimerNewParticle >= 120) {
            int i4 = smInteractiveWaterSplashPointer + 1;
            smInteractiveWaterSplashPointer = i4;
            if (i4 == 16) {
                smInteractiveWaterSplashPointer = 0;
            }
            smInteractiveWaterSplashWorldX[smInteractiveWaterSplashPointer] = (i * 3) / 2;
            smInteractiveWaterSplashWorldY[smInteractiveWaterSplashPointer] = (i2 * 3) / 2;
            smInteractiveWaterSplashType[smInteractiveWaterSplashPointer] = i3;
            smInteractiveWaterSplashTime[smInteractiveWaterSplashPointer] = 0;
            smInteractiveWaterSplashTimerNewParticle = 0;
        }
    }

    public static void interactiveInit(int i) {
        if (i > -1) {
            smInteractiveCurrentLevel = i;
            smInteractiveTunnelCount = 0;
            smInteractiveBreakGoSegmentID = -1;
        }
        for (int i2 = 0; i2 < 2; i2++) {
            smInteractiveBreakGoSegmentUsed[i2] = false;
        }
        for (int i3 = 0; i3 < 2; i3++) {
            smInteractiveTunnelRockState[i3] = 0;
            smInteractiveTunnelRockVelXFP[i3] = 0;
            smInteractiveTunnelRockVelYFP[i3] = 0;
            if (smInteractiveTunnelX[0] != null) {
                smInteractiveTunnelRockXFP[i3] = smInteractiveTunnelX[0][0] - (INTERACTIVE_TUNNEL_TOP_SEMIWIDTH_FP * 2);
                smInteractiveTunnelRockXFallFP = smInteractiveTunnelX[0][smInteractiveTunnelX[0].length - 1];
                smInteractiveTunnelRockYFallFP = (smInteractiveTunnelY[0] - (INTERACTIVE_TUNNEL_TOP_SEMIHEIGHT_FP << 1)) - (smInteractiveTunnelRockSOHeight << 11);
                smInteractiveTunnelRockYFP[i3] = smInteractiveTunnelRockYFallFP - (INTERACTIVE_TUNNEL_TOP_SEMIHEIGHT_FP << 1);
            }
        }
        smInteractiveTunnelRockFirstBounce = false;
        smEnableCameraYLag = false;
    }

    public static void interactiveLoad() {
        if (smInteractiveTunnelSO == null) {
            smInteractiveTunnelSO = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_G_THEME_01_TUNNEL_TOP_LEFT, ResourceIDs.ANM_R_G_THEME_01_TUNNEL_TOP_CENTER, ResourceIDs.ANM_R_G_THEME_01_TUNNEL_TOP_CENTER_FLIP, ResourceIDs.ANM_R_G_THEME_01_TUNNEL_TOP_RIGHT, ResourceIDs.ANM_R_G_THEME_01_TUNNEL_LEFT, ResourceIDs.ANM_R_G_THEME_01_TUNNEL_CENTER, ResourceIDs.ANM_R_G_THEME_01_TUNNEL_RIGHT}, false), false);
            smInteractiveTunnelSO.setAnimation(5, -1, false);
            INTERACTIVE_TUNNEL_BASE_HEIGHT = smInteractiveTunnelSO.getHeight();
            smInteractiveTunnelSO.setAnimation(1, -1, false);
            INTERACTIVE_TUNNEL_TOP_SEMIHEIGHT_FP = smInteractiveTunnelSO.getHeight() << 11;
            INTERACTIVE_TUNNEL_TOP_SEMIWIDTH_FP = smInteractiveTunnelSO.getWidth() << 11;
            smInteractiveTunnelRockSO = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_I_ROCK, false), true);
            smInteractiveTunnelRockSOWidth = smInteractiveTunnelRockSO.getWidth();
            smInteractiveTunnelRockSOHeight = smInteractiveTunnelRockSO.getHeight();
        }
        interactiveProcessTrack();
    }

    private static void interactiveProcessTrack() {
        int length = TrackModel.smTrackPointX.length;
        int i = 0;
        while (i < length && !TrackModel.isBreakGo(i)) {
            i++;
        }
        if (i < length) {
            for (int i2 = 0; i2 < 2; i2++) {
                smInteractiveBreakGoSegmentUsed[i2] = false;
            }
            smInteractiveBreakGoSegmentID = i;
        }
        int i3 = 0;
        while (i3 < length) {
            if (TrackModel.isTunnel(i3)) {
                int trackPointX = TrackModel.getTrackPointX(i3);
                int i4 = 0;
                while (i4 < length && TrackModel.isTunnel(i3 + i4)) {
                    i4++;
                }
                int trackPointX2 = TrackModel.getTrackPointX(i3 + i4);
                i3 += i4;
                interactiveAddTunnel(trackPointX, trackPointX2, TrackModel.getTrackPointY(i3));
            } else {
                i3++;
            }
        }
    }

    private int linearBlendColors(int i, int i2, int i3, int i4) {
        int i5 = (i >> 16) & 255;
        int i6 = (i >> 8) & 255;
        int i7 = i & 255;
        return ((i5 + (((((i2 >> 16) & 255) - i5) * i3) / i4)) << 16) | ((i6 + (((((i2 >> 8) & 255) - i6) * i3) / i4)) << 8) | (i7 + ((((i2 & 255) - i7) * i3) / i4));
    }

    private void loadCommonResources() {
        if (this.mCart == null) {
            this.mCart = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_C_CART_02_2, ResourceIDs.ANM_R_C_CART_01_2, ResourceIDs.ANM_R_C_CART_03_2}, false), true);
            this.mCartHead = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_C_CART_02_1, ResourceIDs.ANM_R_C_CART_01_1, ResourceIDs.ANM_R_C_CART_03_1}, false), true);
        }
        if (this.mWheelSO == null) {
            this.mWheelSO = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_C_WELLS_CART_02, false), true);
        }
        if (this.mPassengerPosX == null) {
            this.mPassengerPosX = new int[36][];
            this.mPassengerPosY = new int[36][];
            for (int i = 0; i != 36; i++) {
                this.mPassengerPosX[i] = new int[2];
                this.mPassengerPosY[i] = new int[2];
                this.mCart.setAnimationFrame(i);
                CollisionBox[] collisionBoxes = this.mCart.getCollisionBoxes();
                for (int i2 = 0; i2 != 2; i2++) {
                    int id = collisionBoxes[i2].getId();
                    this.mPassengerPosX[i][id] = collisionBoxes[i2].getX();
                    this.mPassengerPosY[i][id] = collisionBoxes[i2].getY();
                }
            }
            this.mPassenger = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_C_PASSENGER_01, ResourceIDs.ANM_R_C_PASSENGER_02, ResourceIDs.ANM_R_C_PASSENGER_GHOST, ResourceIDs.ANM_R_C_PASSENGER_01_EXTRA, ResourceIDs.ANM_R_C_PASSENGER_02_EXTRA}, false), false);
        }
        if (RollerGameEngine.smTrackMenu) {
            return;
        }
        emphasizeLoad();
        if (this.mIngameFGObjectSO == null) {
            this.mIngameFGObjectSO = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{-1, -1, -1}, false), false);
            this.mIngameFGObjectPosX = new int[12];
            this.mIngameFGObjectPosY = new int[12];
            this.mIngameFGObjectVelX = new int[12];
            this.mIngameFGObjectVelY = new int[12];
        }
    }

    public static void particlesAddMovement(int i, int i2, int i3, int i4) {
        if (i < 240) {
            smParticlesDest[i][0] = i2;
            smParticlesDest[i][1] = i3;
            int i5 = i2 - smParticlesScreenPos[i][0];
            int i6 = i3 - smParticlesScreenPos[i][1];
            smParticlesSpeedFP[i][0] = (i5 << 12) / i4;
            smParticlesSpeedFP[i][1] = (i6 << 12) / i4;
            smParticlesWorldPos[i][0] = smParticlesScreenPos[i][0] << 12;
            smParticlesWorldPos[i][1] = smParticlesScreenPos[i][1] << 12;
            smParticlesAllDistance[i] = (i5 * i5) + (i6 * i6);
            smParticlesState[i] = 2;
        }
    }

    public static int particlesAddParticle(int i, byte b, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        while (i6 < 240 && smParticlesState[i6] != 0) {
            i6++;
        }
        if (i6 < 240) {
            smParticlesState[i6] = 1;
            smParticlesCoorType[i6] = b;
            if (b == 0) {
                smParticlesWorldPos[i6][0] = i2;
                smParticlesWorldPos[i6][1] = i3;
                smParticlesWorldPos[i6][2] = i4;
            } else {
                smParticlesScreenPos[i6][0] = i2;
                smParticlesScreenPos[i6][1] = i3;
            }
            smParticlesSprite[i6].setAnimation(smParticlesAnimationID[i], smParticlesAnimationIsAnimated[smParticlesAnimationID[i]], true);
            smParticlesLayerID[i6] = i5;
        }
        return i6;
    }

    public static int particlesAddParticle(int i, int i2, int i3, int i4) {
        return particlesAddParticle(i, (byte) 1, i2, i3, 0, i4);
    }

    public static int particlesAddParticle(int i, int i2, int i3, int i4, int i5) {
        return particlesAddParticle(i, (byte) 0, i2, i3, i4, i5);
    }

    public static void particlesDraw(Graphics graphics) {
        for (int i = 0; i < 5; i++) {
            particlesDraw(graphics, i);
        }
    }

    public static void particlesDraw(Graphics graphics, int i) {
        for (int i2 = 0; i2 < smParticlesDrawCount[i]; i2++) {
            int i3 = smParticlesDrawID[i][i2];
            if (smParticlesState[i3] == 2 && smParticlesSprite[i3].getFrameCount() > 1) {
                smParticlesSprite[i3].setAnimationFrame(smParticlesFrame[i3]);
            }
            smParticlesSprite[i3].draw(graphics, smParticlesScreenPos[i3][0], smParticlesScreenPos[i3][1]);
        }
    }

    private static void particlesFreeResources() {
        smParticlesState = null;
        smParticlesCoorType = null;
        smParticlesWorldPos = (int[][]) null;
        smParticlesScreenPos = (int[][]) null;
        smParticlesSpeedZ = null;
        smParticlesAllDistance = null;
        smParticlesFrame = null;
        if (smParticlesSprite != null) {
            for (int i = 0; i < 240; i++) {
                if (smParticlesSprite[i] != null) {
                    smParticlesSprite[i].freeResources();
                }
            }
            smParticlesSprite = null;
        }
        smParticlesAnimationID = null;
        smParticlesAnimationIsAnimated = null;
        smParticlesDrawCount = null;
        smParticlesDrawID = (int[][]) null;
        smParticlesDest = (int[][]) null;
        smParticlesSpeedFP = (int[][]) null;
        smParticlesLayerID = null;
    }

    public static void particlesInit() {
        for (int i = 0; i < 240; i++) {
            smParticlesState[i] = 0;
        }
    }

    private static void particlesLoad() {
        if (smParticlesSprite == null) {
            smParticlesState = new byte[240];
            smParticlesCoorType = new byte[240];
            smParticlesWorldPos = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 240, 3);
            smParticlesSpeedZ = new int[240];
            smParticlesScreenPos = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 240, 2);
            smParticlesSprite = new SpriteObject[240];
            smParticlesDest = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 240, 2);
            smParticlesSpeedFP = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 240, 2);
            smParticlesAllDistance = new int[240];
            smParticlesFrame = new int[240];
            smParticlesLayerID = new int[240];
            smParticlesDrawCount = new int[5];
            smParticlesDrawID = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 5, 240);
            smParticlesAnimationID = new byte[9];
            smParticlesAnimationIsAnimated = new byte[9];
            int[] iArr = new int[9];
            smParticlesAnimationID[0] = (byte) 0;
            iArr[0] = 983047;
            int i = 0 + 1;
            smParticlesAnimationID[8] = (byte) i;
            iArr[i] = 1048614;
            int i2 = i + 1;
            smParticlesAnimationID[1] = (byte) i2;
            iArr[i2] = 1048576;
            int i3 = i2 + 1;
            smParticlesAnimationID[2] = (byte) i3;
            iArr[i3] = 983046;
            int i4 = i3 + 1;
            smParticlesAnimationID[6] = (byte) i4;
            iArr[i4] = 983047;
            int i5 = i4 + 1;
            smParticlesAnimationID[3] = (byte) i5;
            iArr[i5] = 852049;
            int i6 = i5 + 1;
            smParticlesAnimationID[4] = (byte) i6;
            iArr[i6] = 852050;
            int i7 = i6 + 1;
            smParticlesAnimationID[5] = (byte) i7;
            iArr[i7] = 852045;
            int i8 = i7 + 1;
            int[] iArr2 = new int[i8];
            System.arraycopy(iArr, 0, iArr2, 0, i8);
            Animation[] animationArrLoadAnimations = DavinciUtilities.loadAnimations(iArr2, false);
            SpriteObject spriteObject = new SpriteObject(animationArrLoadAnimations, true);
            for (int i9 = 0; i9 < i8; i9++) {
                spriteObject.setAnimation(i9, 1, true);
                smParticlesAnimationIsAnimated[i9] = (byte) (spriteObject.getFrameCount() == 1 ? -1 : 1);
            }
            for (int i10 = 0; i10 < 240; i10++) {
                smParticlesState[i10] = 0;
                smParticlesSprite[i10] = new SpriteObject(animationArrLoadAnimations, true);
            }
        }
        particlesInit();
    }

    private void particlesUpdate(int i, int i2, int i3) {
        boolean z;
        for (int i4 = 0; i4 < 5; i4++) {
            smParticlesDrawCount[i4] = 0;
        }
        for (int i5 = 0; i5 < 240; i5++) {
            int i6 = smParticlesLayerID[i5];
            if (smParticlesState[i5] != 0) {
                if (smParticlesState[i5] == 2) {
                    boolean z2 = false;
                    for (int i7 = 0; i7 < 2; i7++) {
                        int[] iArr = smParticlesWorldPos[i5];
                        iArr[i7] = iArr[i7] + (smParticlesSpeedFP[i5][i7] * i);
                        smParticlesScreenPos[i5][i7] = smParticlesWorldPos[i5][i7] >> 12;
                        if ((smParticlesDest[i5][i7] > smParticlesScreenPos[i5][i7] && smParticlesSpeedFP[i5][i7] < 0) || (smParticlesDest[i5][i7] < smParticlesScreenPos[i5][i7] && smParticlesSpeedFP[i5][i7] > 0)) {
                            particlesAddParticle(6, smParticlesDest[i5][0], smParticlesDest[i5][1], i6);
                            z2 = true;
                        }
                    }
                    if (smParticlesSprite[i5].getFrameCount() > 1) {
                        int i8 = smParticlesDest[i5][0] - smParticlesScreenPos[i5][0];
                        int i9 = smParticlesDest[i5][1] - smParticlesScreenPos[i5][1];
                        smParticlesFrame[i5] = smParticlesAllDistance[i5] != 0 ? (((i8 * i8) + (i9 * i9)) * (smParticlesSprite[i5].getFrameCount() - 1)) / smParticlesAllDistance[i5] : 0;
                    }
                    z = z2;
                } else {
                    z = false;
                }
                if (!z && smParticlesState[i5] != 2) {
                    smParticlesSprite[i5].logicUpdate(i);
                }
                boolean zIsFinishedAnimation = smParticlesSprite[i5].isFinishedAnimation();
                if (z || zIsFinishedAnimation) {
                    smParticlesState[i5] = 0;
                } else {
                    if (smParticlesCoorType[i5] == 0) {
                        smParticlesScreenPos[i5][0] = ((smParticlesWorldPos[i5][0] - this.mIngameTrain.mCamPointX) >> 12) + i2;
                        smParticlesScreenPos[i5][1] = ((smParticlesWorldPos[i5][1] - this.mIngameTrain.mCamPointY) >> 12) + i3;
                    }
                    int[] iArr2 = smParticlesDrawID[i6];
                    int[] iArr3 = smParticlesDrawCount;
                    int i10 = iArr3[i6];
                    iArr3[i6] = i10 + 1;
                    iArr2[i10] = i5;
                }
            }
        }
    }

    private int rgbToHsv(int i) {
        int i2 = (i >> 16) & 255;
        int i3 = (i >> 8) & 255;
        int i4 = i & 255;
        int iMax = Math.max(Math.max(i2, i3), i4);
        int iMin = Math.min(Math.min(i2, i3), i4);
        return ((iMax == iMin ? 0 : iMax == i2 ? (((i3 - i4) * 42) / (iMax - iMin)) & 255 : iMax == i3 ? ((((i4 - i2) * 42) / (iMax - iMin)) + 85) & 255 : ((((i2 - i3) * 42) / (iMax - iMin)) + TextIDs.TID_TUTORIAL_TUNNEL) & 255) << 16) | ((iMax == 0 ? 0 : 255 - ((iMin * 255) / iMax)) << 8) | iMax;
    }

    private void tornadoAddParticle(int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < 44 && this.mTornadoParticleState[i4] != 0) {
            i4++;
        }
        if (i4 < 44) {
            this.mTornadoParticleType[i4] = i;
            this.mTornadoParticleTimerX[i4] = i2;
            this.mTornadoParticleTimerY[i4] = i3;
            this.mTornadoParticleState[i4] = 1;
            this.mTornadoParticleOffX[i4] = 0;
        }
    }

    private void tornadoDrawParticles(Graphics graphics, boolean z) {
        if (this.mTornadoX >= this.mTornadoOffX * (-3) || !this.mTornadoEnabled) {
            for (int i = 0; i < 44; i++) {
                if (this.mTornadoParticleState[i] == 1 && ((this.mTornadoParticleAngle[i] < 180 && z) || (this.mTornadoParticleAngle[i] >= 180 && !z))) {
                    SpriteObject spriteObject = this.mTornadoParticleSprite[this.mTornadoParticleType[i]];
                    spriteObject.setAnimation(0, -1, false);
                    spriteObject.setAnimationFrame(i % spriteObject.getFrameCount());
                    spriteObject.logicUpdate(this.mIngameTimer);
                    int iIngameGetCameraX = this.mTornadoParticleX[i];
                    if (!this.mTornadoEnabled) {
                        iIngameGetCameraX += ingameGetCameraX() >> 1;
                    }
                    spriteObject.draw(graphics, iIngameGetCameraX, this.mTornadoParticleY[i]);
                }
            }
        }
    }

    private void tornadoDrawTornado(Graphics graphics) {
        int i = 0;
        while (i < this.mScreenHeight) {
            int i2 = i / this.mTornadoSOHeight;
            int iIngameGetCameraX = this.mTornadoX - (this.mTornadoOffX * i2);
            if (!this.mTornadoEnabled) {
                iIngameGetCameraX += ingameGetCameraX();
            }
            if (iIngameGetCameraX > 0) {
                this.mTornadoSO.setAnimation(0, -1, false);
                this.mTornadoSO.setAnimationFrame(i2 % this.mTornadoSOFrameCount);
                this.mTornadoSO.logicUpdate(this.mIngameTimer);
                this.mTornadoSO.draw(graphics, iIngameGetCameraX - this.mTornadoSOWidth, i);
                graphics.setColor(Statics.TORNADO_COLOR);
                graphics.fillRect(0, i, iIngameGetCameraX - this.mTornadoSOWidth, this.mTornadoSOHeight);
            }
            i += this.mTornadoSOHeight;
        }
    }

    public void cometReset() {
        this.mCometSprite.setAnimationFrame(0);
        this.mCometTimer = Util.rnd(8000) + 8000;
        this.mCometX = (this.mScreenWidth / 5) + Util.rnd((this.mScreenWidth * 3) / 5);
        this.mCometY = Util.rnd(this.mScreenHeight >> 2) + 0;
    }

    public void drawSimpleClouds(Graphics graphics) {
        int i;
        int i2;
        if (this.simpleCloudsX == null) {
            initSimpleClouds();
        }
        for (int i3 = 0; i3 < 5; i3++) {
            int i4 = (this.mIngameTrain.mCamPointX / 2) >> 12;
            int i5 = (this.mIngameTrain.mCamPointY / 2) >> 12;
            if ((-i4) + this.mScreenWidth + this.simpleCloudsX[i3] < -10) {
                this.simpleCloudsX[i3] = (short) (Math.abs(Util.rand() % this.mScreenWidth) + i4);
                this.simpleCloudsY[i3] = (short) Math.abs(Util.rand() % (((this.mScreenHeight * 3) / 2) >> 1));
            }
            int i6 = CLOUD_WIDTH;
            int i7 = CLOUD_HEIGHT;
            if (this.simpleCloudsY[i3] % 2 == 0) {
                int i8 = this.mScreenWidth / 8;
                i = this.mScreenHeight / 18;
                i2 = i8;
            } else {
                i = i7;
                i2 = i6;
            }
            graphics.setColor(14405040);
            int i9 = this.simpleCloudsX[i3] + (-i4) + this.mScreenWidth;
            int i10 = this.simpleCloudsY[i3] + (-i5);
            if (i9 > (-i2) && i9 < this.mScreenWidth + i2 && i10 > (-i) && i10 < this.mScreenHeight + i) {
                graphics.fillArc(i9, i10, i2, i, 0, Util.SIN_SAMPLES_DEFAULT);
            }
        }
    }

    public final void finalizeLoading() {
        this.mIngamePlatformTrack.setAnimationFrame(Game.smProfile[Game.smNumProfile].mSelectedTheme / 3);
        this.mIngamePlatformTrackEnd.setAnimationFrame(Game.smProfile[Game.smNumProfile].mSelectedTheme / 3);
        this.mHudCurrentSign = -1;
    }

    public void hudDraw(Graphics graphics) {
        if (Game.smCurrentState == 57) {
            return;
        }
        if (Game.smCurrentState != 58 && Game.smCurrentState != 21) {
            hudDrawDistanceMeter(graphics);
            hudDrawWarningSigns(graphics);
            hudDrawSpeedMeter(graphics);
        }
        if ((Game.smCurrentState == 58 || Game.smCurrentState == 21) && !ResultsCareerSimple.smDrawBigTrophie) {
            return;
        }
        if (RollerGameEngine.smHotSeatMode == 0) {
            HudDrawTimer(graphics);
        } else {
            hudDrawScore(graphics);
        }
        if (this.mIngameTrain.mState == 0) {
            hudDrawPickUpsNames(graphics);
        }
    }

    public void hudInit() {
        if (TORNADO_LEVEL[smInteractiveCurrentLevel] || Cheats.smTornadoOn) {
            this.mHudTornadoPresentationTimer = 0;
            this.mHudTornadoDraw = false;
        }
        this.mHudScoreSmileOffX = ((-this.mHudPanel.getWidth()) - 5) + (this.mHudPanel.getWidth() / 2);
        this.mHudScoreSmileY = (this.mHudPanel.getHeight() / 2) + 5;
        GameEngine.smTrain.mPickUpSpeedTimer = 2000;
        GameEngine.smTrain.mPickUpIndexSpeed = -1;
        this.mHudCurrentSign = -1;
        this.mHudSignsCursor.setAnimationFrame(this.mHudSignsCursor.getFrameCount() - 1);
    }

    public void ingameAttachTrain(Train train, Train train2) {
        previewReset();
        this.mIngameTrain = train;
        this.mIngameTrainGhost = train2;
        this.mIngameTrain.mEngine2D = this;
        if (this.mIngameTrainGhost != null) {
            this.mIngameTrainGhost.mEngine2D = this;
        }
        for (int i = 0; i != 8; i++) {
            this.mIngameSparklesTime[i] = 240;
        }
        initSimpleClouds();
        this.mIngameFlyCars = 0;
        this.mIngameParachutesActive = 0;
        this.mIngameTimer = 0;
        this.mHudSpeedMeterBroken = false;
        ingameCalculateCamera(false);
        ingameResetEffects();
        this.mHudLastSignIdx = 0;
        Entity.setTrains(new Train[]{train, train2});
    }

    public void ingameCalculateCamera(boolean z) {
        this.mScreenWidth = Game.getGameAreaWidth();
        this.mScreenHeight = Toolkit.getScreenHeight();
        Train train = GameEngine.smTrain;
        int iMin = Math.min(train.mVelX, 28672);
        int iMin2 = Math.min(train.mVelY, 16384);
        if (RollerGameEngine.smTrackMenu) {
            iMin2 = 0;
            iMin = 0;
        }
        int i = (this.mScreenWidth * 13) / 100;
        int i2 = (this.mScreenHeight * 13) / 100;
        this.mCameraTargetX = ((this.mScreenWidth >> 1) - ((iMin * i) / 28672)) << 12;
        this.mCameraTargetY = (((this.mScreenHeight * 50) / 100) - ((iMin2 * i2) / 16384)) << 12;
        if (this.mIngameTrain.hasFailed()) {
            this.mCameraTargetY -= (this.mScreenHeight >> 3) << 12;
        } else if (this.mEnableCameraLag) {
            this.mCameraTargetX += ((this.mScreenWidth * TextIDs.TID_HELP_ACHIEVEMENTS) / 200) << 12;
        } else if (smEnableCameraYLag) {
            this.mCameraTargetY += (this.mScreenHeight >> 2) << 12;
        } else if (this.mScreenWidth > this.mScreenHeight) {
            this.mCameraTargetY += (this.mScreenHeight >> 3) << 12;
        }
        interactiveUpdate();
        if (z) {
            this.mCameraSpeedX = (this.mCameraTargetX - this.mCameraX) / 1000;
            this.mCameraSpeedY = (this.mCameraTargetY - this.mCameraY) / 350;
        } else {
            this.mCameraSpeedY = 0;
            this.mCameraSpeedX = 0;
            this.mCameraX = this.mCameraTargetX;
            this.mCameraY = this.mCameraTargetY;
        }
    }

    public void ingameDraw(Graphics graphics) {
        SpriteObject spriteObject;
        smDrawCount = 0;
        this.mScreenWidth = Game.getGameAreaWidth();
        this.mScreenHeight = Toolkit.getScreenHeight();
        this.mScreenCropLeft = -112;
        this.mScreenCropTop = -112;
        this.mScreenCropRight = this.mScreenWidth + 112;
        this.mScreenCropBottom = this.mScreenHeight + 112;
        int iIngameGetCameraX = ingameGetCameraX();
        int iIngameGetCameraY = ingameGetCameraY();
        ingameDrawBackgroundGradient(graphics);
        if (Game.smCurrentState != 57) {
            ingameDrawTrack(graphics);
        }
        int i = 0;
        while (i < this.mIngameFlyCars) {
            SpriteObject spriteObject2 = this.mIngameFlyCarIsGhost[i] ? this.mIngameCartGhost : (i == this.mIngameFlyCars - 1 && this.mIngameTrain.hasFailed()) ? this.mCartHead : this.mCart;
            int frameFromAngle = getFrameFromAngle(this.mIngameFlyAngle[i]);
            int i2 = ((this.mIngameFlyCarX[i] - this.mIngameTrain.mCamPointX) >> 12) + iIngameGetCameraX;
            int i3 = ((this.mIngameFlyCarY[i] - this.mIngameTrain.mCamPointY) >> 12) + iIngameGetCameraY;
            if (spriteObject2 != this.mIngameCartGhost) {
                ingameDrawWheels(graphics, frameFromAngle, i2, i3);
            }
            spriteObject2.setAnimationFrame(frameFromAngle);
            spriteObject2.draw(graphics, i2, i3);
            i++;
        }
        for (int i4 = 0; i4 != this.mIngameParachutesActive; i4++) {
            int[] iArr = this.mIngameParachutes[i4];
            int i5 = iArr[0];
            int i6 = iArr[1];
            if (i6 < 320) {
                spriteObject = this.mPassenger;
                spriteObject.setAnimation(i5, -1, false);
                spriteObject.logicUpdate(i6);
            } else if (i6 < INGAME_OPENING_STAGE_TIME) {
                spriteObject = this.mIngameOpening;
                spriteObject.setAnimation(i5, -1, false);
                spriteObject.logicUpdate(i6 - 320);
            } else {
                spriteObject = this.mIngameParachute;
                spriteObject.setAnimation(i5, -1, false);
                spriteObject.logicUpdate(i6 - INGAME_OPENING_STAGE_TIME);
            }
            spriteObject.draw(graphics, ((iArr[2] - this.mIngameTrain.mCamPointX) >> 12) + iIngameGetCameraX, ((iArr[3] - this.mIngameTrain.mCamPointY) >> 12) + iIngameGetCameraY);
        }
        interactiveDraw(graphics, iIngameGetCameraX, iIngameGetCameraY);
        if (TORNADO_LEVEL[smInteractiveCurrentLevel] || Cheats.smTornadoOn) {
            tornadoDraw(graphics);
        }
        ingameFGObjectDraw(graphics);
    }

    public void ingameDrawTrack(Graphics graphics) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z2;
        int i11;
        int i12;
        boolean z3;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        boolean z4;
        int i22;
        int i23;
        int i24;
        int i25;
        boolean z5;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        boolean z6;
        int i32;
        int i33;
        int iMul;
        int i34;
        int i35;
        int i36;
        int i37;
        boolean z7;
        int i38;
        int i39;
        int i40;
        int i41;
        int i42;
        int i43;
        int i44;
        int i45;
        int i46;
        boolean z8;
        int[] iArr;
        int[] iArr2;
        int i47;
        int i48;
        int i49;
        int i50;
        int i51;
        int i52;
        boolean z9;
        int iIngameGetCameraX = ingameGetCameraX();
        int iIngameGetCameraY = ingameGetCameraY();
        graphics.setStrokeStyle(0);
        int length = TrackModel.smPieceEndIdx.length;
        int i53 = 0;
        int i54 = 0;
        boolean z10 = false;
        int i55 = 0;
        boolean z11 = false;
        int i56 = 0;
        int i57 = 0;
        int i58 = 0;
        int i59 = 0;
        boolean z12 = false;
        int i60 = 0;
        int i61 = 0;
        int i62 = 0;
        int i63 = 0;
        int i64 = 0;
        int i65 = 0;
        int i66 = 0;
        int i67 = 0;
        int i68 = 0;
        int i69 = 0;
        while (i57 != length) {
            int i70 = TrackModel.smPieceEndIdx[i57];
            int trackPointX = ((TrackModel.getTrackPointX(i58) - this.mIngameTrain.mCamPointX) >> 12) + iIngameGetCameraX;
            int trackPointX2 = ((TrackModel.getTrackPointX(i70 - 1) - this.mIngameTrain.mCamPointX) >> 12) + iIngameGetCameraX;
            if (i57 != length - 1 && ((trackPointX <= trackPointX2 && trackPointX < this.mScreenCropRight && trackPointX2 >= this.mScreenCropLeft) || (trackPointX > trackPointX2 && trackPointX2 < this.mScreenCropRight && trackPointX >= this.mScreenCropLeft))) {
                i = i64;
                i3 = i61;
                i4 = i63;
                i5 = i70;
                z = z12;
                i7 = i65;
                i8 = i60;
                i9 = i62;
                i10 = i54;
                i11 = i69;
                z2 = true;
                i12 = i68;
                z3 = z10;
                i13 = i56;
                i14 = i59;
                i15 = i67;
                i16 = i53;
                int i71 = i66;
                i6 = i55;
                i2 = i71;
            } else if (z11) {
                ingameDrawPoles(graphics, iIngameGetCameraX, iIngameGetCameraY, i55, i70, 0);
                ingameDrawPoles(graphics, iIngameGetCameraX, iIngameGetCameraY, i55, i70, 1);
                if (1 != 0) {
                    int trackPointX3 = TrackModel.getTrackPointX(i55) - this.mIngameTrain.mCamPointX;
                    int trackPointY = TrackModel.getTrackPointY(i55) - this.mIngameTrain.mCamPointY;
                    int i72 = i55 + 1;
                    int iSin = (Util.sin(TrackModel.smTrackSlope[i72]) * 28) + trackPointX3;
                    int iCos = (Util.cos(TrackModel.smTrackSlope[i72]) * 28) + trackPointY;
                    i27 = i56;
                    z5 = z12;
                    i26 = iCos;
                    i22 = iSin;
                    i23 = trackPointY;
                    i24 = trackPointX3;
                    i19 = iSin;
                    i20 = trackPointY;
                    i21 = trackPointX3;
                    z4 = z10;
                    i30 = i60;
                    i25 = iCos;
                    i17 = i72;
                    i18 = i68;
                    i31 = i64;
                    i29 = i66;
                    i28 = i61;
                } else {
                    i17 = i55;
                    i18 = i68;
                    i19 = i69;
                    i20 = i53;
                    i21 = i54;
                    z4 = z10;
                    i22 = i63;
                    i23 = i62;
                    i24 = i67;
                    i25 = i65;
                    z5 = z12;
                    i26 = i59;
                    i27 = i56;
                    i28 = i61;
                    i29 = i66;
                    i30 = i60;
                    i31 = i64;
                }
                while (i17 < i70) {
                    if (this.mIngameTrainGhost == null || z5 || this.mIngameTrainGhost.mSegmentIdx[0] != i17 - 8) {
                        z6 = z5;
                    } else {
                        ingameDrawTrainAndEffects(graphics, iIngameGetCameraX, iIngameGetCameraY, true);
                        z6 = true;
                    }
                    if (i17 - 8 == this.mIngameTrain.mSegmentIdx[0] || TrackModel.smTrackPointX.length - i17 < 8 || (this.mIngameTrain.mSegmentIdx[0] < i55 && i17 == i55 + 1)) {
                        if (this.mIngameTrainGhost != null && !z6 && this.mIngameTrainGhost.mSegmentIdx[0] - this.mIngameTrain.mSegmentIdx[0] < 15) {
                            ingameDrawTrainAndEffects(graphics, iIngameGetCameraX, iIngameGetCameraY, true);
                            z6 = true;
                        }
                        ingameDrawTrainAndEffects(graphics, iIngameGetCameraX, iIngameGetCameraY, false);
                    }
                    int trackPointX4 = TrackModel.getTrackPointX(i17) - this.mIngameTrain.mCamPointX;
                    int trackPointY2 = TrackModel.getTrackPointY(i17) - this.mIngameTrain.mCamPointY;
                    int iSin2 = Util.sin(TrackModel.smTrackSlope[i17]);
                    int iCos2 = Util.cos(TrackModel.smTrackSlope[i17]);
                    if (TrackModel.smTrackPointTimer[0][i17] < 0) {
                        int i73 = trackPointX4 - i21;
                        i33 = TrackModel.smTrackPointOffY[0][i17] + trackPointY2;
                        int iMul2 = FP.mul(iSin2, i73) + i33;
                        iMul = trackPointX4 - FP.mul(iCos2, i73);
                        i32 = iMul2;
                    } else {
                        i32 = i20;
                        i33 = trackPointY2;
                        iMul = i21;
                    }
                    boolean z13 = smDrawTrain;
                    if (!z13) {
                        if (TrackModel.isFall(this.mIngameTrain, i17)) {
                            graphics.setColor(16776960);
                        } else if (((((i17 + 50) >> 1) / 50) & 1) == 0) {
                            graphics.setColor(linearBlendColors(0, 0, ((i17 + 50) >> 1) % 50, 50));
                        } else {
                            graphics.setColor(linearBlendColors(0, 0, ((i17 + 50) >> 1) % 50, 50));
                        }
                    }
                    if (!TrackModel.isHole(this.mIngameTrain, i17) || TrackModel.isFall(this.mIngameTrain, i17)) {
                        if (z13 && smDrawTrain) {
                            int i74 = -40;
                            int i75 = -40;
                            int i76 = this.mScreenWidth + 40;
                            int i77 = 40 + this.mScreenHeight;
                            if (this.debugDrawOptimizationMode >= this.DEBUG_OPT_MAX) {
                                int i78 = (trackPointX4 >> 12) + iIngameGetCameraX;
                                int i79 = (trackPointY2 >> 12) + iIngameGetCameraY;
                                if (i78 < i74 || i78 > i76 || i79 < i75 || i79 > i77) {
                                    i45 = i31 + 1;
                                    z9 = true;
                                } else {
                                    i45 = i31;
                                    z9 = false;
                                }
                                boolean z14 = z9;
                                i46 = i18 + 1;
                                z8 = z14;
                            } else {
                                i45 = i31;
                                i46 = i18;
                                z8 = false;
                            }
                            boolean z15 = this.debugDrawOptimizationMode != this.DEBUG_OPT_MAX_NOALIASING;
                            if (TrackModel.isFall(this.mIngameTrain, i17)) {
                                iArr = TRACK_ENHANCED_COLORS_FALL;
                                iArr2 = TRACK_ENHANCED_COLOR_VERTICAL_SEPARATOR_FALL;
                            } else {
                                iArr = TRACK_ENHANCED_COLORS[2];
                                iArr2 = TRACK_ENHANCED_COLOR_VERTICAL_SEPARATOR[2];
                            }
                            if (this.debugDrawOptimizationMode != this.DEBUG_OPT_ORIGINAL && this.debugDrawOptimizationMode == this.DEBUG_OPT_MEDIUM) {
                                int angle = ((Util.getAngle(iSin2, iCos2, false) * Util.SIN_SAMPLES_DEFAULT) >> 12) % 90;
                                z15 = angle > 10 && angle < 80;
                            }
                            for (int i80 = 0; i80 < TRACK_ENHANCED_LINES_COUNT && !z8; i80++) {
                                int i81 = iSin2 * i80;
                                int i82 = iCos2 * i80;
                                if (iArr[i80] != 0) {
                                    graphics.setColor(iArr[i80]);
                                    if (this.debugDrawOptimizationMode >= this.DEBUG_OPT_MAX) {
                                        int i83 = ((trackPointX4 + i81) >> 12) + iIngameGetCameraX;
                                        int i84 = ((i33 + i82) >> 12) + iIngameGetCameraY;
                                        int i85 = ((i81 + iMul) >> 12) + iIngameGetCameraX;
                                        int i86 = ((i82 + i32) >> 12) + iIngameGetCameraY;
                                        graphics.drawLine(i83, i84, i85, i86);
                                        if (z15 && i80 + 1 < TRACK_ENHANCED_LINES_COUNT && iArr[i80 + 1] != 0) {
                                            graphics.drawLine(i83, i84 + 1, i85, i86 + 1);
                                        }
                                        if (!z15 && i80 == 0) {
                                            graphics.drawLine(i83, i84 - 1, i85, i86 - 1);
                                        }
                                    } else {
                                        if (this.debugDrawOptimizationMode != this.DEBUG_OPT_ORIGINAL) {
                                            i51 = 0;
                                            i52 = z15 ? 1 : 0;
                                        } else {
                                            i51 = -1;
                                            i52 = 1;
                                        }
                                        int i87 = i21;
                                        for (int i88 = i51; i88 <= i52; i88++) {
                                            int i89 = ((((i88 << 12) + trackPointX4) + i81) >> 12) + iIngameGetCameraX;
                                            int i90 = ((i33 + i82) >> 12) + iIngameGetCameraY;
                                            int i91 = (i81 >> 12) + iIngameGetCameraX;
                                            int i92 = ((i32 + i82) >> 12) + iIngameGetCameraY;
                                            if (this.debugDrawOptimizationMode == this.DEBUG_OPT_ORIGINAL || (i89 < this.mScreenCropRight && (i87 >> 12) + i91 > this.mScreenCropLeft && i90 < this.mScreenCropBottom && i92 > this.mScreenCropTop)) {
                                                int i93 = i87;
                                                for (int i94 = i51; i94 <= i52; i94++) {
                                                    i93 = (i94 << 12) + iMul;
                                                    graphics.drawLine(i89, i90, (i93 >> 12) + i91, i92);
                                                }
                                                i87 = i93;
                                            }
                                        }
                                    }
                                }
                            }
                            int i95 = TRACK_ENHANCED_SEPARATOR_HEIGHT * iSin2;
                            int i96 = TRACK_ENHANCED_SEPARATOR_HEIGHT * iCos2;
                            int i97 = iSin2 * 3;
                            int i98 = 3 * iCos2;
                            if (TrackModel.trackHasVerticalSeparator[i17]) {
                                int i99 = ((iMul + i97) >> 12) + iIngameGetCameraX;
                                int i100 = ((i32 + i98) >> 12) + iIngameGetCameraY;
                                int i101 = ((i95 + (iMul + i97)) >> 12) + iIngameGetCameraX;
                                int i102 = (((i32 + i98) + i96) >> 12) + iIngameGetCameraY;
                                int i103 = i27;
                                int i104 = i28;
                                int i105 = i30;
                                int i106 = i29;
                                for (int i107 = 0; i107 < TRACK_ENHANCED_COLOR_VERTICAL_SEPARATOR_LINES; i107++) {
                                    int i108 = iArr2[i107];
                                    graphics.setColor(i108);
                                    int i109 = (i107 * iCos2) >> 12;
                                    int i110 = (-(i107 * iSin2)) >> 12;
                                    int i111 = i99 + i109;
                                    int i112 = i100 + i110;
                                    int i113 = i109 + i101;
                                    int i114 = i110 + i102;
                                    if (i108 == iArr2[0]) {
                                        i106 = i114;
                                        i105 = i113;
                                        i104 = i112;
                                        i103 = i111;
                                    } else if (this.debugDrawOptimizationMode >= this.DEBUG_OPT_MAX) {
                                        graphics.drawLine(i111, i112, i113, i114);
                                        if (z15 || i107 == 0) {
                                            graphics.drawLine(i111, i112 - 1, i113, i114 - 1);
                                        }
                                    } else if (this.debugDrawOptimizationMode == this.DEBUG_OPT_ORIGINAL || (i111 < this.mScreenCropRight && i113 > this.mScreenCropLeft && i112 < this.mScreenCropBottom && i114 > this.mScreenCropTop)) {
                                        if (!z15 || (i107 == TRACK_ENHANCED_COLOR_VERTICAL_SEPARATOR_LINES - 1 && i108 == iArr2[0])) {
                                            graphics.drawLine(i111, i112, i113, i114);
                                        } else {
                                            for (int i115 = -1; i115 < 1; i115++) {
                                                int i116 = i111 + i115;
                                                for (int i117 = -1; i117 < 1; i117++) {
                                                    graphics.drawLine(i116, i112, i113 + i117, i114);
                                                }
                                            }
                                        }
                                    }
                                }
                                graphics.setColor(iArr2[0]);
                                graphics.drawLine(i103, i104, i105 - 1, i106);
                                i47 = i106;
                                i48 = i105;
                                i49 = i104;
                                i50 = i103;
                            } else {
                                i47 = i29;
                                i48 = i30;
                                i49 = i28;
                                i50 = i27;
                            }
                            i38 = i50;
                            i39 = i26;
                            i44 = i22;
                            i41 = i23;
                            i43 = i24;
                            i37 = i48;
                            z7 = z4;
                            i40 = i45;
                            int i118 = i46;
                            i42 = i49;
                            i36 = i47;
                            i18 = i118;
                        } else if (z4) {
                            graphics.drawLine((trackPointX4 >> 12) + iIngameGetCameraX, (i33 >> 12) + iIngameGetCameraY, (i19 >> 12) + iIngameGetCameraX, (i25 >> 12) + iIngameGetCameraY);
                            i36 = i29;
                            i37 = i30;
                            z7 = false;
                            i38 = i27;
                            i39 = i25;
                            i40 = i31;
                            i41 = i33;
                            i42 = i28;
                            i43 = trackPointX4;
                            i44 = i19;
                        } else {
                            int i119 = (iSin2 * 28) + trackPointX4;
                            int i120 = (iCos2 * 28) + i33;
                            if (TrackModel.smTrackPointTimer[0][i17] < 0) {
                                int i121 = (iCos2 * 28) + i32;
                                i35 = (iSin2 * 28) + iMul;
                                i34 = i121;
                            } else {
                                i34 = i25;
                                i35 = i19;
                            }
                            if (Util.getLength(trackPointX4 - i35, i33 - i34) <= 155648 || Util.getLength(i19 - i24, i25 - i23) <= 155648) {
                                i26 = i34;
                                i22 = i35;
                            } else {
                                int i122 = (trackPointX4 >> 12) + iIngameGetCameraX;
                                int i123 = (i33 >> 12) + iIngameGetCameraY;
                                int i124 = (i35 >> 12) + iIngameGetCameraX;
                                int i125 = (i34 >> 12) + iIngameGetCameraY;
                                if ((i122 >= 0 || i124 >= 0) && ((i122 <= this.mScreenWidth || i124 <= this.mScreenWidth) && ((i123 >= 0 || i125 >= 0) && (i123 <= this.mScreenHeight || i125 <= this.mScreenHeight)))) {
                                    graphics.drawLine(i122, i123, i124, i125);
                                }
                                int i126 = (iMul >> 12) + iIngameGetCameraX;
                                int i127 = (i32 >> 12) + iIngameGetCameraY;
                                int i128 = (i119 >> 12) + iIngameGetCameraX;
                                int i129 = (i120 >> 12) + iIngameGetCameraY;
                                if ((i126 >= 0 || i128 >= 0) && ((i126 <= this.mScreenWidth || i128 <= this.mScreenWidth) && ((i127 >= 0 || i129 >= 0) && (i127 <= this.mScreenHeight || i129 <= this.mScreenHeight)))) {
                                    graphics.drawLine(i126, i127, i128, i129);
                                }
                                i26 = i25;
                                i22 = i19;
                                i23 = i33;
                                i24 = trackPointX4;
                            }
                            if (TrackModel.isFall(this.mIngameTrain, i17)) {
                                graphics.setColor(16776960);
                            } else if (((i17 / 50) & 1) == 0) {
                                graphics.setColor(linearBlendColors(0, 0, i17 % 50, 50));
                            } else {
                                graphics.setColor(linearBlendColors(0, 0, i17 % 50, 50));
                            }
                            int i130 = (trackPointX4 >> 12) + iIngameGetCameraX;
                            int i131 = (i33 >> 12) + iIngameGetCameraY;
                            int i132 = (iMul >> 12) + iIngameGetCameraX;
                            int i133 = (i32 >> 12) + iIngameGetCameraY;
                            if ((i130 >= 0 || i132 >= 0) && ((i130 <= this.mScreenWidth || i132 <= this.mScreenWidth) && ((i131 >= 0 || i133 >= 0) && (i131 <= this.mScreenHeight || i133 <= this.mScreenHeight)))) {
                                graphics.drawLine((trackPointX4 >> 12) + iIngameGetCameraX, (i33 >> 12) + iIngameGetCameraY, (iMul >> 12) + iIngameGetCameraX, (i32 >> 12) + iIngameGetCameraY);
                                graphics.drawLine((trackPointX4 >> 12) + iIngameGetCameraX, (i33 >> 12) + iIngameGetCameraY + 1, (iMul >> 12) + iIngameGetCameraX, (i32 >> 12) + iIngameGetCameraY + 1);
                            }
                            int i134 = (i19 >> 12) + iIngameGetCameraX;
                            int i135 = (i25 >> 12) + iIngameGetCameraY;
                            int i136 = (i35 >> 12) + iIngameGetCameraX;
                            int i137 = (i34 >> 12) + iIngameGetCameraY;
                            if ((i134 >= 0 || i136 >= 0) && ((i134 <= this.mScreenWidth || i136 <= this.mScreenWidth) && ((i135 >= 0 || i137 >= 0) && (i135 <= this.mScreenHeight || i137 <= this.mScreenHeight)))) {
                                graphics.drawLine((i19 >> 12) + iIngameGetCameraX, (i25 >> 12) + iIngameGetCameraY, (i35 >> 12) + iIngameGetCameraX, (i34 >> 12) + iIngameGetCameraY);
                                graphics.drawLine((i19 >> 12) + iIngameGetCameraX, (i25 >> 12) + iIngameGetCameraY + 1, (i35 >> 12) + iIngameGetCameraX, (i34 >> 12) + iIngameGetCameraY + 1);
                            }
                            i40 = i31;
                            i36 = i29;
                            i37 = i30;
                            i43 = i24;
                            i38 = i27;
                            i39 = i26;
                            i42 = i28;
                            i41 = i23;
                            i44 = i22;
                            z7 = z4;
                        }
                    } else if (z4 || z13) {
                        i40 = i31;
                        i36 = i29;
                        i37 = i30;
                        i43 = i24;
                        i38 = i27;
                        i39 = i26;
                        i42 = i28;
                        i41 = i23;
                        i44 = i22;
                        z7 = z4;
                    } else {
                        graphics.drawLine((i24 >> 12) + iIngameGetCameraX, (i23 >> 12) + iIngameGetCameraY, (i19 >> 12) + iIngameGetCameraX, (i25 >> 12) + iIngameGetCameraY);
                        graphics.drawLine((iMul >> 12) + iIngameGetCameraX, (i32 >> 12) + iIngameGetCameraY, (i22 >> 12) + iIngameGetCameraX, (i26 >> 12) + iIngameGetCameraY);
                        graphics.drawLine((iMul >> 12) + iIngameGetCameraX, (i32 >> 12) + iIngameGetCameraY, (i19 >> 12) + iIngameGetCameraX, (i25 >> 12) + iIngameGetCameraY);
                        i17--;
                        i36 = i29;
                        i37 = i30;
                        i43 = i24;
                        i38 = i27;
                        i39 = i26;
                        i41 = i23;
                        z7 = true;
                        i40 = i31;
                        i42 = i28;
                        i44 = i22;
                    }
                    i17++;
                    i22 = i44;
                    i23 = i41;
                    i24 = i43;
                    i20 = trackPointY2;
                    i21 = trackPointX4;
                    z4 = z7;
                    i26 = i39;
                    i28 = i42;
                    i27 = i38;
                    z5 = z6;
                    i31 = i40;
                    i29 = i36;
                    i30 = i37;
                }
                if (i55 <= 8) {
                    ingameDrawPolesElement(graphics, 8, iIngameGetCameraX, iIngameGetCameraY, 1);
                }
                if (i70 >= this.mIngameTrain.mLastSegment - 6) {
                    ingameDrawPolesElement(graphics, this.mIngameTrain.mLastSegment - 6, iIngameGetCameraX, iIngameGetCameraY, 1);
                }
                if (smInteractiveBreakGoSegmentID - 6 >= i55 && smInteractiveBreakGoSegmentID - 6 < i70) {
                    ingameDrawPolesElement(graphics, smInteractiveBreakGoSegmentID - 6, iIngameGetCameraX, iIngameGetCameraY, 1);
                }
                i12 = i18;
                i2 = i29;
                i3 = i28;
                i13 = i27;
                i5 = i58;
                i6 = i70;
                z2 = false;
                z = z5;
                i = i31;
                i8 = i30;
                i4 = i22;
                i9 = i23;
                i7 = i25;
                i11 = i19;
                i16 = i20;
                i15 = i24;
                i14 = i26;
                z3 = z4;
                i10 = i21;
            } else {
                i = i64;
                i2 = i66;
                i3 = i61;
                i4 = i63;
                i5 = i70;
                i6 = i70;
                z = z12;
                i7 = i65;
                i8 = i60;
                i9 = i62;
                i10 = i54;
                z2 = z11;
                i11 = i69;
                i12 = i68;
                z3 = z10;
                i13 = i56;
                i14 = i59;
                i15 = i67;
                i16 = i53;
            }
            i57++;
            i53 = i16;
            i54 = i10;
            z10 = z3;
            i67 = i15;
            i65 = i7;
            i69 = i11;
            i59 = i14;
            i63 = i4;
            i62 = i9;
            z12 = z;
            i58 = i5;
            i56 = i13;
            i61 = i3;
            i68 = i12;
            i64 = i;
            z11 = z2;
            i60 = i8;
            int i138 = i6;
            i66 = i2;
            i55 = i138;
        }
        Entity.drawEntities(graphics);
    }

    public final void ingameFGObjectInit() {
        for (int i = 0; i != 12; i++) {
            int width = this.mIngameFGObjectSO.getWidth();
            int height = this.mIngameFGObjectSO.getHeight();
            this.mIngameFGObjectPosX[i] = (this.mIngameTrain.getCarX(0) + (this.mScreenWidth << 11)) - ((Util.rnd(width) * i) << 12);
            this.mIngameFGObjectPosY[i] = (this.mIngameTrain.getCarY(0) + (this.mScreenHeight << 11)) - ((height - (Util.rnd(height) * i)) << 12);
            this.mIngameFGObjectVelX[i] = -((width << 1) + Util.rnd(width));
            this.mIngameFGObjectVelY[i] = (-Util.rnd(height)) - (height << 4);
            if (i % 3 == 0) {
                int[] iArr = this.mIngameFGObjectVelX;
                iArr[i] = iArr[i] * (-1);
            }
        }
    }

    public final void ingameFGObjectReset() {
        this.mIngameFGObjectEnabled = false;
        this.mIngameFGObjectTimer = 0;
    }

    public final void ingameFireworksInit(int i) {
        this.mIngameFireworksHasFinished = false;
        this.mIngameFireworksTimerEnd = System.currentTimeMillis() + i;
        this.mIngameFireworksExplosionDelays = new int[6];
        this.mIngameFireworkParticleData = (int[][][]) Array.newInstance((Class<?>) Integer.TYPE, 6, 20, 7);
        for (int i2 = 0; i2 < this.mIngameFireworkParticleData.length; i2++) {
            ingameFireworksInitExplosion(this.mIngameFireworkParticleData[i2]);
            this.mIngameFireworksExplosionDelays[i2] = -(Math.abs(Util.rand()) % 500);
        }
        this.mIngameFireworksActive = true;
    }

    public int ingameGetCameraX() {
        return (this.mCameraX + this.mIngameCameraShakeX) >> 12;
    }

    public int ingameGetCameraY() {
        return (this.mCameraY + this.mIngameCameraShakeY) >> 12;
    }

    public int ingameGetSpeedHUD() {
        return (this.mIngameTrain.mSpeed * 30) >> 12;
    }

    public void ingameInitFlyingCar(int i, int i2, int i3, int i4, boolean z, int i5, boolean z2) {
        int i6 = this.mIngameTrain.mCarAngle[i5];
        if (z) {
            if (i5 == 0 && !z2) {
                this.mHudSpeedMeterBroken = true;
            }
            int i7 = this.mIngameFlyCars;
            this.mIngameFlyCars = i7 + 1;
            this.mIngameFlyCarIsGhost[i7] = z2;
            this.mIngameFlyCarX[i7] = i;
            this.mIngameFlyCarY[i7] = i2;
            this.mIngameFlyVelX[i7] = 0;
            this.mIngameFlyVelY[i7] = 448;
            this.mIngameFlyAngle[i7] = i6;
        }
        for (int i8 = 0; i8 != i4; i8++) {
            int i9 = this.mIngameParachutesActive;
            this.mIngameParachutesActive = i9 + 1;
            int frameFromAngle = getFrameFromAngle(i6);
            int i10 = this.mPassengerPosX[frameFromAngle][i8] + i;
            int i11 = this.mPassengerPosY[frameFromAngle][i8] + i2;
            this.mIngameParachutes[i9][0] = z2 ? 2 : this.mIngamePassengerTypes[i5][i8];
            this.mIngameParachutes[i9][1] = ((Math.abs(Util.rand()) >> 5) % TextIDs.TID_ACHIEVEMENTS_TITLE_20) + 50;
            this.mIngameParachutes[i9][2] = i10;
            this.mIngameParachutes[i9][3] = i11;
            this.mIngameParachutes[i9][4] = (Util.rand() >> 5) % 200;
            this.mIngameParachutes[i9][5] = Math.abs((Util.rand() >> 5) % 200) + 300;
            this.mIngameParachutes[i9][6] = 0;
        }
    }

    public void ingameInitParallax() {
        this.mIngameBGObjectsWorldXFP = 0;
    }

    public final void ingameResetEffects() {
        ingameResetCameraShake();
        this.mIngameNegativeScoreFeedbackActive = false;
        this.mIngamePositiveScoreFeedbackActive = false;
    }

    public void ingameScoreIncreaseNotify(int i) {
        this.mIngamePositiveScoreFeedbackActive = true;
        this.mIngameNegativeScoreFeedbackActive = false;
        this.mIngameScoreFeedbackTimer = i > 1 ? 4000 : 2000;
    }

    public void ingameUpdateSpeedLines(int i) {
        int height = this.mCart.getHeight() >> 2;
        int height2 = this.mCart.getHeight() / 3;
        if (this.mIngameTrain.mSpeed >= Tuner.SPEED_LINE_APPEAR_LIMIT && !this.mIngameSpeedLinesDraw) {
            this.mIngameSpeedLinesDraw = true;
            this.mIngameSpeedLineClearLines = true;
            this.mIngameSpeedLineTimer = 60000 + (Util.rand() % 60000);
        } else if (this.mIngameTrain.mSpeed < Tuner.SPEED_LINE_DISAPPEAR_LIMIT) {
            this.mIngameSpeedLinesDraw = false;
        }
        if (this.mIngameSpeedLinesDraw) {
            this.mIngameSpeedLineTimer += i;
            for (int i2 = 0; i2 < this.mIngameSpeedLines.length && i2 < this.mIngameTrain.mCarCnt; i2++) {
                int[][][] iArr = this.mIngameSpeedLines[i2];
                for (int i3 = 0; i3 < iArr.length; i3++) {
                    int[][] iArr2 = iArr[i3];
                    if (this.mIngameSpeedLineClearLines) {
                        for (int[] iArr3 : iArr2) {
                            for (int i4 = 0; i4 < iArr3.length; i4++) {
                                iArr3[i4] = Integer.MIN_VALUE;
                            }
                        }
                    } else {
                        for (int length = iArr2.length - 2; length >= 0; length--) {
                            int[] iArr4 = iArr2[length];
                            int[] iArr5 = iArr2[length + 1];
                            iArr5[0] = iArr4[0];
                            iArr5[1] = iArr4[1];
                            iArr5[2] = iArr4[2];
                            iArr5[3] = iArr4[3];
                            if (length == 0) {
                                int i5 = (INGAME_SPEED_LINE_VARIATION_SPEEDS[(i2 + i3) % INGAME_SPEED_LINE_VARIATION_SPEEDS.length] * this.mIngameSpeedLineTimer) / 1000;
                                int fp = FP.toFP(height2);
                                int iMul = FP.mul(Util.cos(i5), fp >> 1);
                                int fp2 = (FP.toFP(height) + ((i3 * fp) / 4)) - (fp >> 1);
                                int iCos = Util.cos(this.mIngameTrain.mCarAngle[i2]);
                                int iSin = Util.sin(this.mIngameTrain.mCarAngle[i2]);
                                int iMul2 = FP.mul(iSin, fp2) - FP.mul(iCos, iMul);
                                int iMul3 = FP.mul(iSin, iMul) + FP.mul(iCos, fp2);
                                int carX = this.mIngameTrain.getCarX(i2) - iMul2;
                                int carY = this.mIngameTrain.getCarY(i2) - iMul3;
                                if (iArr4[2] == 0) {
                                    iArr4[0] = carX;
                                    iArr4[1] = carY;
                                } else {
                                    iArr4[0] = iArr4[2];
                                    iArr4[1] = iArr4[3];
                                }
                                iArr4[2] = carX;
                                iArr4[3] = carY;
                            }
                        }
                    }
                }
            }
            this.mIngameSpeedLineClearLines = false;
        }
    }

    public void initSimpleClouds() {
        this.simpleCloudsX = new short[5];
        this.simpleCloudsY = new short[5];
        for (int i = 0; i < 5; i++) {
            this.simpleCloudsX[i] = -4096;
        }
    }

    public void interactiveDraw(Graphics graphics, int i, int i2) {
        int i3 = (this.mIngameTrain.mFirstCarPrevX * 3) / 2;
        for (int i4 = 0; i4 < smInteractiveTunnelCount; i4++) {
            int i5 = ((smInteractiveTunnelY[i4] - this.mIngameTrain.mCamPointY) >> 12) + i2;
            int i6 = 0;
            while (i6 < smInteractiveTunnelPiecesCount[i4]) {
                if (this.mIngameTrain.getCarX(0) >= smInteractiveTunnelX[i4][i6] && i3 < smInteractiveTunnelX[i4][i6]) {
                    if (i6 != 0 && i6 == smInteractiveTunnelPiecesCount[i4] - 1) {
                        this.mIngameTrain.mParticlesJumpTimer = 2000;
                    }
                    int i7 = i6 == 0 ? smInteractiveTunnelX[i4][i6] - INTERACTIVE_TUNNEL_TOP_SEMIWIDTH_FP : i6 == smInteractiveTunnelPiecesCount[i4] - 1 ? smInteractiveTunnelX[i4][i6] + INTERACTIVE_TUNNEL_TOP_SEMIWIDTH_FP : i6 % 2 == 0 ? smInteractiveTunnelX[i4][i6] : -1;
                    if (i7 > -1) {
                        if (2 > 0) {
                            particlesAddParticle(8, i7, smInteractiveParticlesTunnelY[i4], 0, 4);
                        }
                        particlesAddParticle(0, i7, smInteractiveParticlesTunnelY[i4], 0, 4);
                    }
                }
                int i8 = ((smInteractiveTunnelX[i4][i6] - this.mIngameTrain.mCamPointX) >> 12) + i;
                smInteractiveTunnelSO.setAnimation(smInteractiveTunnelFrameID[i4][i6], -1, true);
                smInteractiveTunnelSO.draw(graphics, i8, i5);
                smInteractiveTunnelSO.setAnimation(INTERACTIVE_TUNNEL_BASE_FRAME[smInteractiveTunnelFrameID[i4][i6]], -1, true);
                for (int i9 = i5; i9 <= this.mScreenHeight; i9 += INTERACTIVE_TUNNEL_BASE_HEIGHT) {
                    smInteractiveTunnelSO.draw(graphics, i8, i9);
                }
                i6++;
            }
        }
        if (smInteractiveTunnelCount <= 0 || smInteractiveTunnelRockState[0] != 1) {
            return;
        }
        smInteractiveTunnelRockSO.draw(graphics, ((smInteractiveTunnelRockXFP[0] - this.mIngameTrain.mCamPointX) >> 12) + i, ((smInteractiveTunnelRockYFP[0] - this.mIngameTrain.mCamPointY) >> 12) + i2);
    }

    public void interactiveUpdate() {
        if (smInteractiveTunnelCount > 0) {
            if (smInteractiveTunnelRockState[0] == 0 && smInteractiveTunnelRockXFP[0] - this.mIngameTrain.getCarX(0) < 163840) {
                smEnableCameraYLag = true;
            } else if (smEnableCameraYLag && this.mIngameTrain.getCarX(0) - smInteractiveTunnelX[0][smInteractiveTunnelPiecesCount[0] - 1] > 163840) {
                smEnableCameraYLag = false;
            }
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= 2) {
                    break;
                }
                Train train = i2 == 1 ? this.mIngameTrainGhost : this.mIngameTrain;
                if (train != null) {
                    smInteractiveTunnelRockSO.logicUpdate(smDeltaTime);
                    int i3 = 0;
                    while (true) {
                        int i4 = i3;
                        if (i4 < smFallingTrackUpdateCount) {
                            if (smInteractiveTunnelRockState[i2] == 0 && train.getCarX(0) >= smInteractiveTunnelRockXFP[i2]) {
                                smInteractiveTunnelRockState[i2] = 1;
                                smInteractiveTunnelRockVelXFP[i2] = train.mVelX;
                            }
                            if (smInteractiveTunnelRockState[i2] == 1) {
                                smInteractiveTunnelRockSO.logicUpdate(i4 * 20);
                                int[] iArr = smInteractiveTunnelRockXFP;
                                iArr[i2] = iArr[i2] + smInteractiveTunnelRockVelXFP[i2];
                                int[] iArr2 = smInteractiveTunnelRockVelYFP;
                                iArr2[i2] = iArr2[i2] + Train.GRAVITY_CONST;
                                int[] iArr3 = smInteractiveTunnelRockYFP;
                                iArr3[i2] = iArr3[i2] + smInteractiveTunnelRockVelYFP[i2];
                                if (smInteractiveTunnelRockXFP[i2] >= smInteractiveTunnelRockXFallFP) {
                                    if (Math.abs(smInteractiveTunnelRockYFP[i2] - train.getCarY(0)) > (this.mScreenHeight << 13)) {
                                        smInteractiveTunnelRockState[i2] = 2;
                                    }
                                    int i5 = train.mCarCnt;
                                    int i6 = 0;
                                    if (!train.mPickUpsEnabled[4]) {
                                        int i7 = 0;
                                        while (true) {
                                            int i8 = i7;
                                            int i9 = i6;
                                            if (i8 >= train.mCarCnt) {
                                                break;
                                            }
                                            if (collisionBoxes(smInteractiveTunnelRockXFP[i2], smInteractiveTunnelRockYFP[i2], smInteractiveTunnelRockSOWidth, smInteractiveTunnelRockSOHeight, train.getCarX(i8) - 81920, train.getCarY(i8) - 163840, 163840, 163840)) {
                                                this.mIngameTrain.dropCart(i8, true);
                                                i6 = i9 + 1;
                                            } else {
                                                i6 = i9;
                                            }
                                            i7 = i8 + 1;
                                        }
                                        if (i2 == 0) {
                                        }
                                    }
                                } else if (smInteractiveTunnelRockYFP[i2] > smInteractiveTunnelRockYFallFP) {
                                    smInteractiveTunnelRockYFP[i2] = smInteractiveTunnelRockYFallFP;
                                    smInteractiveTunnelRockVelYFP[i2] = -(smInteractiveTunnelRockVelYFP[i2] >> 2);
                                    if (i2 == 0 && !smInteractiveTunnelRockFirstBounce) {
                                        smInteractiveTunnelRockVelXFP[i2] = Math.max(smInteractiveTunnelRockVelXFP[i2] >> 1, (train.mVelX * 3) >> 1);
                                        smInteractiveTunnelRockFirstBounce = true;
                                        this.mIngameCrashPuff.setAnimationFrame(0);
                                        this.mIngameCrashCoordX = smInteractiveTunnelRockXFP[i2];
                                        this.mIngameCrashCoordY = smInteractiveTunnelRockYFP[i2];
                                        this.mIngameDrawCrashPuff = true;
                                        this.mIngameCameraShakeTimer = 1000;
                                    }
                                }
                            }
                            i3 = i4 + 1;
                        }
                    }
                }
                i = i2 + 1;
            }
        }
        if (TrackModel.smTrackFallingStartId > -1) {
            int i10 = 0;
            while (i10 < 2) {
                Train train2 = i10 == 1 ? this.mIngameTrainGhost : this.mIngameTrain;
                if (train2 != null && train2.mSegmentIdx[0] >= TrackModel.smTrackFallingStartId && train2.mSegmentIdx[0] <= TrackModel.smTrackFallingEndId + 50) {
                    for (int i11 = TrackModel.smTrackFallingStartId; i11 <= TrackModel.smTrackFallingEndId; i11++) {
                        if (TrackModel.smTrackPointTimer[i10][i11] > 0) {
                            int[] iArr4 = TrackModel.smTrackPointTimer[i10];
                            iArr4[i11] = iArr4[i11] - (smFallingTrackUpdateCount * 20);
                            if (TrackModel.smTrackPointTimer[i10][i11] == 0) {
                                TrackModel.smTrackPointTimer[i10][i11] = -1;
                            }
                        } else if (TrackModel.smTrackPointTimer[i10][i11] < 0) {
                            for (int i12 = 0; i12 < smFallingTrackUpdateCount; i12++) {
                                int[] iArr5 = TrackModel.smTrackPointVelY[i10];
                                iArr5[i11] = iArr5[i11] + Train.GRAVITY_CONST;
                                int[] iArr6 = TrackModel.smTrackPointOffY[i10];
                                iArr6[i11] = iArr6[i11] + TrackModel.smTrackPointVelY[i10][i11];
                                if (!TrackModel.isHole(train2, i10) && TrackModel.smTrackPointOffY[i10][i11] >= 229376) {
                                    byte[] bArr = TrackModel.smTrackFlags[i10];
                                    bArr[i11] = (byte) (bArr[i11] | 1);
                                    int i13 = train2.mCarCnt - 1;
                                    while (i13 >= 0 && !TrackModel.isHole(train2, train2.mSegmentIdx[i13])) {
                                        i13--;
                                    }
                                    if (i13 < 0 && i10 == 0) {
                                        train2.accumScore(train2.mCarCnt * 15);
                                    }
                                }
                            }
                        }
                    }
                }
                i10++;
            }
        }
    }

    public void lensFlare_destroy() {
        this.mLensSprites.freeResources();
        this.mLensSprites = null;
    }

    public void lensFlare_draw(Graphics graphics) {
        if (GRADIEN_BG_DAY_TIME_TYPE[DCThrillOld.smSelectedTrack] == 0) {
            this.mLensSprites.setAnimationFrame(1);
            this.mLensSprites.draw(graphics, this.mLensSunPositionX, this.mLensSunPositionY);
            int i = this.mLensVectorX * this.mLensVectorLength;
            int i2 = this.mLensVectorY * this.mLensVectorLength;
            if (this.mLensFade >= 0) {
                this.mLensSprites.setAnimationFrame(6);
                this.mLensSprites.draw(graphics, this.mLensSunPositionX + (i >> 8), this.mLensSunPositionY + (i2 >> 8));
            }
            for (int length = DIV_CONSTANTS.length - 1; length >= 0; length--) {
                int i3 = (i / DIV_CONSTANTS[length]) >> 8;
                int i4 = (i2 / DIV_CONSTANTS[length]) >> 8;
                this.mLensSprites.setAnimationFrame(LENS_FLARE_FRAME[length]);
                this.mLensSprites.draw(graphics, i3 + this.mLensSunPositionX, i4 + this.mLensSunPositionY);
            }
        }
    }

    public void lensFlare_init() {
        this.mLensSunPositionXFP = TrackModel.getTrackPointX(TrackModel.smHeightMinPointID);
        this.mLensSunPositionYFP = TrackModel.smHeightMin - 573440;
        this.mLensVectorX = 0;
        this.mLensVectorY = 0;
        this.mLensVectorLength = 0;
        this.mLensFade = -1;
    }

    public void lensFlare_tick(int i, int i2, int i3) {
        if (GRADIEN_BG_DAY_TIME_TYPE[DCThrillOld.smSelectedTrack] == 0) {
            this.mLensSunPositionX = ((this.mLensSunPositionXFP - this.mIngameTrain.mCamPointX) >> 12) + i2;
            this.mLensSunPositionY = ((this.mLensSunPositionYFP - this.mIngameTrain.mCamPointY) >> 12) + i3;
            this.mLensVectorX = i2 - this.mLensSunPositionX;
            this.mLensVectorY = i3 - this.mLensSunPositionY;
            int i4 = (this.mLensVectorX * this.mLensVectorX) + (this.mLensVectorY * this.mLensVectorY);
            this.mLensVectorLength = 2;
            for (int i5 = 0; i5 < 10; i5++) {
                this.mLensVectorLength = (this.mLensVectorLength >> 1) + (i4 / (this.mLensVectorLength << 1));
            }
            if (this.mLensVectorLength != 0) {
                this.mLensVectorX = (this.mLensVectorX << 8) / this.mLensVectorLength;
                this.mLensVectorY = (this.mLensVectorY << 8) / this.mLensVectorLength;
            }
            if (this.mLensVectorLength <= (this.mScreenHeight >> 1)) {
                this.mLensFade = 0;
                return;
            }
            if (this.mLensVectorLength > this.mScreenHeight + (this.mScreenHeight >> 1)) {
                this.mLensFade = -1;
            } else if (this.mLensVectorLength > this.mScreenHeight) {
                this.mLensFade = 2;
            } else if (this.mLensVectorLength > (this.mScreenHeight >> 1)) {
                this.mLensFade = 1;
            }
        }
    }

    public byte[] loadFile(String str) {
        return null;
    }

    public final void loadResources(int i) {
        switch (i) {
            case 0:
                if (!RollerGameEngine.smTrackMenu) {
                    ingameLoadResources();
                    break;
                }
                break;
            case 1:
                loadCommonResources();
                break;
            case 5:
                if (!RollerGameEngine.smTrackMenu) {
                    hudLoadResources();
                    break;
                }
                break;
        }
    }

    public void previewReset() {
        this.mCart.setAnimation(DCThrillOld.smSelectedCart, -1, false);
        this.mCartHead.setAnimation(DCThrillOld.smSelectedCart, -1, false);
        this.mIngameCartGhost.setAnimation(DCThrillOld.smSelectedCartGhost, -1, false);
    }

    public void renderProcessBlur(int[] iArr, int[] iArr2) {
        int i;
        int i2;
        int i3;
        for (int i4 = 0; i4 < this.pixelLength; i4++) {
            if ((i4 - 1) - this.mScreenWidth < 0 || i4 + 1 + this.mScreenWidth > this.pixelLength - 1) {
                iArr2[i4] = iArr[i4];
            } else {
                this.averagePixels[0] = iArr[i4];
                this.averagePixels[1] = iArr[i4 - 1];
                this.averagePixels[2] = iArr[i4 + 1];
                this.averagePixels[3] = iArr[i4 - this.mScreenWidth];
                this.averagePixels[4] = iArr[this.mScreenWidth + i4];
                this.averagePixels[5] = iArr[(i4 - 1) - this.mScreenWidth];
                this.averagePixels[6] = iArr[i4 + 1 + this.mScreenWidth];
                this.averagePixels[7] = iArr[(i4 + 1) - this.mScreenWidth];
                this.averagePixels[8] = iArr[(i4 - 1) + this.mScreenWidth];
                int[] iArr3 = {76, 4, 4, 4, 4, 2, 2, 2, 2};
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                for (int i9 = 0; i9 < this.averagePixels.length; i9++) {
                    i8 += (((this.averagePixels[i9] >> 24) & 255) * iArr3[i9]) / 100;
                    i7 += (((this.averagePixels[i9] >> 16) & 255) * iArr3[i9]) / 100;
                    i6 += (((this.averagePixels[i9] >> 8) & 255) * iArr3[i9]) / 100;
                    i5 += ((this.averagePixels[i9] & 255) * iArr3[i9]) / 100;
                }
                if (i8 != 0) {
                    i2 = (i6 * 255) / i8;
                    int i10 = (i5 * 255) / i8;
                    i3 = (i7 * 255) / i8;
                    i = i10;
                } else {
                    i = i5;
                    i2 = i6;
                    i3 = i7;
                }
                iArr2[i4] = i + (i2 << 8) + (i3 << 16) + (i8 << 24);
            }
        }
    }

    public void reset() throws IOException {
        TrackModel.reset();
        scoreInit();
        hudInit();
        Entity.initManager();
        interactiveInit(-1);
        tornadoInit();
        particlesInit();
        ingameFGObjectReset();
        smIngameBreakGoSO.setAnimationFrame(0);
        smBreakGoWidthFP = smIngameBreakGoSO.getWidth() << 12;
        smBreakGoHeightFP = smIngameBreakGoSO.getHeight() << 12;
        if (smInteractiveBreakGoSegmentID > -1) {
            int i = smInteractiveBreakGoSegmentID - 6;
            smBreakGoXFP = TrackModel.getTrackPointX(i) << 12;
            smBreakGoYFP = TrackModel.getTrackPointY(i) - smBreakGoHeightFP;
            smBreakGoXFP -= smIngameBreakGoSO.getPivotX() << 12;
            this.mBreakGoLastDrawX = -1;
            smBeforeBreakGo = true;
        }
        smStartCannonFireEffects = false;
        this.mBreakGoPlayEffect = false;
    }

    public void scoreInit() {
        this.mScorePrizeTimer = -1;
        this.mScoreTimeGettingScore = 0;
    }

    public void screenSizeChanged() {
    }

    public void tornadoDraw(Graphics graphics) {
        tornadoDrawParticles(graphics, true);
        tornadoDrawTornado(graphics);
        tornadoDrawParticles(graphics, false);
    }

    public void tornadoFreeResources() {
        if (this.mTornadoSO != null) {
            this.mTornadoSO.freeResources();
            this.mTornadoSO = null;
            for (int i = 0; i < 1; i++) {
                if (this.mTornadoParticleSprite[i] != null) {
                    this.mTornadoParticleSprite[i].freeResources();
                }
            }
            this.mTornadoParticleSprite = null;
            this.mTornadoParticleAngle = null;
            this.mTornadoParticleX = null;
            this.mTornadoParticleOffX = null;
            this.mTornadoParticleY = null;
            this.mTornadoParticleTimerX = null;
            this.mTornadoParticleTimerY = null;
            this.mTornadoParticleType = null;
            this.mTornadoParticleState = null;
        }
    }

    public void tornadoInit() {
        this.mTornadoX = Statics.TORNADO_INIT_X;
        this.mTornadoXFP = this.mTornadoX << 12;
        for (int i = 0; i < 44; i++) {
            this.mTornadoParticleState[i] = 0;
        }
        for (int i2 = 0; i2 < 40; i2++) {
            tornadoAddParticle(0, Util.rnd(2000), Util.rnd(10000));
        }
        this.mTornadoEnabled = GameEngine.smGameEngineState == 4;
        if (!TORNADO_LEVEL[smInteractiveCurrentLevel] || GameEngine.smGameEngineState == 4) {
            return;
        }
        this.mCameraX = ((this.mScreenWidth / 2) + 300) << 12;
    }

    public void tornadoLoad() {
        if (this.mTornadoSO == null) {
            this.mTornadoSO = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_E_TORNADO_01, false), true);
            this.mTornadoSOWidth = this.mTornadoSO.getWidth();
            this.mTornadoSOHeight = this.mTornadoSO.getHeight();
            this.mTornadoSOFrameCount = this.mTornadoSO.getFrameCount();
            this.mTornadoOffX = this.mTornadoSOWidth / 4;
            this.mTornadoParticleSprite = new SpriteObject[1];
            this.mTornadoParticleSprite[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_THEME_01_ANIMATION_LEAF, false), true);
            this.mTornadoParticleAngle = new int[44];
            this.mTornadoParticleX = new int[44];
            this.mTornadoParticleOffX = new int[44];
            this.mTornadoParticleY = new int[44];
            this.mTornadoParticleTimerX = new int[44];
            this.mTornadoParticleTimerY = new int[44];
            this.mTornadoParticleType = new int[44];
            this.mTornadoParticleState = new int[44];
        }
    }

    public void tornadoUpdate(int i, int i2, int i3) {
        if (!this.mIngameTrain.trackEnded() && this.mTornadoEnabled) {
            if (this.mIngameTrain.getCarX(0) - this.mTornadoXFP > (this.mScreenWidth << 11)) {
                this.mTornadoVelXFP = (this.mIngameTrain.getCarX(0) - this.mTornadoXFP) / (GameEngine.smGameEngineState == 4 ? 1 : 3);
            } else {
                this.mTornadoVelXFP += (Tuner.PARAMS_TORNADO_ACCELERATION_X_FP * i) / 1000;
                if (this.mTornadoVelXFP > Tuner.PARAMS_TORNADO_MAX_VEL_X_FP[smInteractiveCurrentLevel] * 2) {
                    this.mTornadoVelXFP = Tuner.PARAMS_TORNADO_MAX_VEL_X_FP[smInteractiveCurrentLevel] * 2;
                }
            }
            this.mTornadoXFP = (int) (this.mTornadoXFP + ((this.mTornadoVelXFP * i) / 1000));
            this.mTornadoX = ((this.mTornadoXFP - this.mIngameTrain.mCamPointX) >> 12) + i2;
            int i4 = this.mIngameTrain.mCarCnt;
            if (this.mIngameTrain.mCarCnt > 0 && this.mTornadoXFP >= this.mIngameTrain.getCarX(this.mIngameTrain.mCarCnt - 1)) {
                int i5 = 0;
                for (int i6 = this.mIngameTrain.mCarCnt - 1; i6 > -1 && this.mTornadoXFP >= this.mIngameTrain.getCarX(i6); i6--) {
                    if (this.mTornadoXFP - ((((((this.mIngameTrain.getCarY(i6) - this.mIngameTrain.mCamPointY) >> 12) + i3) / this.mTornadoSOHeight) * this.mTornadoOffX) << 12) >= this.mIngameTrain.getCarX(i6) - 81920) {
                        this.mIngameTrain.dropCart(i6, true);
                        i5++;
                    }
                }
            }
        }
        for (int i7 = 0; i7 < 44; i7++) {
            if (this.mTornadoParticleState[i7] == 1) {
                int[] iArr = this.mTornadoParticleTimerX;
                iArr[i7] = iArr[i7] + i;
                this.mTornadoParticleAngle[i7] = ((this.mTornadoParticleTimerX[i7] % 2000) * Util.SIN_SAMPLES_DEFAULT) / 2000;
                int[] iArr2 = this.mTornadoParticleTimerY;
                iArr2[i7] = iArr2[i7] + i;
                int i8 = (this.mScreenHeight * 5) / 4;
                this.mTornadoParticleY[i7] = i8 - (((this.mTornadoParticleTimerY[i7] % 10000) * i8) / 10000);
                if (this.mTornadoParticleAngle[i7] > 90 && this.mTornadoParticleAngle[i7] < 270) {
                    this.mTornadoParticleOffX[i7] = (5 - (this.mTornadoParticleY[i7] / this.mTornadoSOHeight)) * this.mTornadoOffX;
                }
                this.mTornadoParticleX[i7] = Util.cos(this.mTornadoParticleAngle[i7], this.mTornadoX + this.mTornadoParticleOffX[i7]);
                if (this.mTornadoX < 0) {
                    int[] iArr3 = this.mTornadoParticleX;
                    iArr3[i7] = iArr3[i7] + this.mTornadoX;
                }
            }
        }
    }

    public void updateCamera(int i) {
        if (this.mCameraSpeedX > 0 && this.mCameraTargetX > this.mCameraX) {
            this.mCameraX += this.mCameraSpeedX * i;
            if (this.mCameraTargetX < this.mCameraX) {
                this.mCameraX = this.mCameraTargetX;
            }
        } else if (this.mCameraSpeedX < 0 && this.mCameraTargetX < this.mCameraX) {
            this.mCameraX += this.mCameraSpeedX * i;
            if (this.mCameraTargetX > this.mCameraX) {
                this.mCameraX = this.mCameraTargetX;
            }
        }
        if (this.mCameraSpeedY > 0 && this.mCameraTargetY > this.mCameraY) {
            this.mCameraY += this.mCameraSpeedY * i;
            if (this.mCameraTargetY < this.mCameraY) {
                this.mCameraY = this.mCameraTargetY;
                return;
            }
            return;
        }
        if (this.mCameraSpeedY >= 0 || this.mCameraTargetY >= this.mCameraY) {
            return;
        }
        this.mCameraY += this.mCameraSpeedY * i;
        if (this.mCameraTargetY > this.mCameraY) {
            this.mCameraY = this.mCameraTargetY;
        }
    }

    public void updateHud(int i) {
        this.mHudSignCrash.logicUpdate(i);
        this.mHudSignsCursor.logicUpdate(i);
        if (this.mIngameTrain.mPickUpsEnabledCount > 0) {
            this.mHudSpeedMeterStar.logicUpdate(i);
        }
        if (this.mSpeedStarVisible) {
            this.mHudSpeedStar.logicUpdate(i);
        }
        this.mHudDistanceLocatorCrash.logicUpdate(i);
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = this.mIngameTrain.mPickUpsSorted[i2];
            if (i3 > -1) {
                int[] iArr = this.mHudPickUpsBlinkTime;
                iArr[i2] = iArr[i2] + i;
                if (i3 >= 8) {
                    this.mHudPickUpsBlink[i2] = true;
                    this.mIngameTrain.mPickUpSpeedTimer -= i;
                    if (this.mIngameTrain.mPickUpSpeedTimer <= 0) {
                        if (this.mIngameTrain.mPickUpIndex > 0) {
                            this.mIngameTrain.mPickUpIndex--;
                        }
                        this.mIngameTrain.mPickUpsSorted[i2] = -1;
                        this.mIngameTrain.mPickUpIndexSpeed = -1;
                        this.mHudPickUpsBlink[i2] = false;
                    }
                } else if (this.mHudPickUpsBlinkTime[i2] >= this.mHudPickUpsMaxTime[i2]) {
                    this.mHudPickUpsBlink[i2] = !this.mHudPickUpsBlink[i2];
                    if (this.mHudPickUpsBlink[i2]) {
                        this.mHudPickUpsMaxTime[i2] = 666;
                    } else {
                        this.mHudPickUpsMaxTime[i2] = 150;
                    }
                    this.mHudPickUpsBlinkTime[i2] = 0;
                }
            } else {
                this.mHudPickUpsBlinkTime[i2] = 0;
                this.mHudPickUpsBlink[i2] = false;
                this.mHudPickUpsMaxTime[i2] = 0;
            }
        }
        if (TORNADO_LEVEL[smInteractiveCurrentLevel] || Cheats.smTornadoOn) {
            this.mHudTornadoPresentationTimer += i;
            if (this.mHudTornadoPresentationTimer >= 4000) {
                this.mHudTornadoDraw = true;
                if (!GameEngine.smTornadoAnimationFinish) {
                    GameEngine.smTornadoAnimationFinish = true;
                }
            } else {
                this.mHudTornadoDraw = (this.mHudTornadoPresentationTimer / 500) % 2 == 1;
            }
        }
        if (DCThrillOld.smOnlyTutorialText) {
            this.mHudSignBlinkTimer -= i;
            if (this.mHudSignBlinkTimer <= 0) {
                if (this.mHudSignShow) {
                    this.mHudSignBlinkTimer = 200;
                } else {
                    this.mHudSignBlinkTimer = 600;
                }
                this.mHudSignShow = !this.mHudSignShow;
            }
        }
    }

    public void updateIngame(int i) {
        if (smIngameResetNeeded) {
            reset();
            smIngameResetNeeded = false;
        }
        smDeltaTime = i;
        this.mIngameTimer += i;
        for (int i2 = 0; i2 != this.mIngameFlyCars; i2++) {
            int[] iArr = this.mIngameFlyVelY;
            iArr[i2] = iArr[i2] + (i >> 2);
            int[] iArr2 = this.mIngameFlyCarX;
            iArr2[i2] = iArr2[i2] + (this.mIngameFlyVelX[i2] * i);
            int[] iArr3 = this.mIngameFlyCarY;
            iArr3[i2] = iArr3[i2] + (this.mIngameFlyVelY[i2] * i);
        }
        for (int i3 = 0; i3 != 8; i3++) {
            int[] iArr4 = this.mIngameSparklesTime;
            iArr4[i3] = iArr4[i3] + i;
            if (this.mIngameSparklesTime[i3] > 240) {
                this.mIngameSparklesTime[i3] = 240;
            }
        }
        this.mIngameSparklesTimer += i;
        boolean zIngameAddSparkles = ingameAddSparkles(this.mIngameTrain);
        if (this.mIngameTrainGhost != null) {
            zIngameAddSparkles |= ingameAddSparkles(this.mIngameTrainGhost);
        }
        if (zIngameAddSparkles) {
            this.mIngameSparklesTimer = 0;
        }
        for (int i4 = 0; i4 != this.mIngameParachutesActive; i4++) {
            int[] iArr5 = this.mIngameParachutes[i4];
            if (iArr5[1] < INGAME_OPENING_STAGE_TIME) {
                iArr5[1] = iArr5[1] + i;
                if (iArr5[1] >= INGAME_OPENING_STAGE_TIME) {
                    iArr5[4] = iArr5[4] >> 3;
                    iArr5[5] = iArr5[5] >> 3;
                }
            } else {
                iArr5[1] = iArr5[1] + i;
            }
            if (iArr5[1] < INGAME_OPENING_STAGE_TIME) {
                iArr5[5] = iArr5[5] + (i >> 2);
            }
            iArr5[2] = iArr5[2] + (iArr5[4] * i);
            iArr5[3] = iArr5[3] + (iArr5[5] * i);
        }
        if (this.mIngameDrawCrashPuff) {
            if (this.mIngameCrashPuff.isFinishedAnimation()) {
                this.mIngameDrawCrashPuff = false;
            } else {
                this.mIngameCrashPuff.logicUpdate(i);
            }
        }
        if (this.mIngameTrain.getNewFallenCarCount() > 0 && this.mIngameTrain.mPuffEnabled) {
            this.mIngameNegativeScoreFeedbackActive = true;
            this.mIngameCrashPuff.setAnimationFrame(0);
            this.mIngameCrashCoordX = this.mIngameTrain.getCarX(0);
            this.mIngameCrashCoordY = this.mIngameTrain.getCarY(0);
            this.mIngameDrawCrashPuff = true;
            this.mIngameCameraShakeTimer = 1000;
        }
        if (this.mIngameCameraShakeTimer > 0) {
            this.mIngameCameraShakeTimer -= i;
            if (this.mIngameCameraShakeTimer <= 0) {
                ingameResetCameraShake();
            } else {
                this.mIngameCameraShakeX = FP.mul(Util.cos(((1000 - this.mIngameCameraShakeTimer) * CAMERA_SHAKE_SPEED_X) / 1000), ((FP.toFP(Game.getGameAreaWidth()) >> 6) * this.mIngameCameraShakeTimer) / 1000);
                this.mIngameCameraShakeY = FP.mul(Util.cos(((1000 - this.mIngameCameraShakeTimer) * CAMERA_SHAKE_SPEED_Y) / 1000), ((FP.toFP(Toolkit.getScreenHeight()) >> 6) * this.mIngameCameraShakeTimer) / 1000);
            }
        }
        int iIngameGetCameraX = ingameGetCameraX();
        int iIngameGetCameraY = ingameGetCameraY();
        this.mIngameTrain.convoyUpdate(i);
        if (this.mIngameTrainGhost != null) {
            this.mIngameTrainGhost.convoyUpdate(i);
        }
        Entity.updateEntities(i, iIngameGetCameraX, iIngameGetCameraY);
        this.mIngameBGObjectsWorldXFP = (DCThrillOld.smSelectedCountry == 2 ? -5000 : 5000) + this.mIngameBGObjectsWorldXFP;
        int gameAreaWidth = (Game.getGameAreaWidth() * 2) << 12;
        int iAbs = Math.abs(this.mIngameBGObjectsWorldXFP - this.mIngameTrain.getCarX(0));
        int carX = iAbs > 0 ? (this.mIngameBGObjectsWorldXFP - this.mIngameTrain.getCarX(0)) / iAbs : 1;
        if (iAbs > gameAreaWidth) {
            this.mIngameBGObjectsWorldXFP = (gameAreaWidth * (-carX)) + this.mIngameTrain.getCarX(0);
        }
        if (TORNADO_LEVEL[smInteractiveCurrentLevel] || Cheats.smTornadoOn) {
            tornadoUpdate(i, iIngameGetCameraX, iIngameGetCameraY);
        }
        particlesUpdate(i, iIngameGetCameraX, iIngameGetCameraY);
        if (this.mScorePrizeTimer == -1 && this.mIngameTrain.mPointsTarget > this.mScorePrizePointsBackup) {
            this.mScorePrizeTimer = 0;
        }
        if (this.mIngameTrain.mPoints != this.mIngameTrain.mPointsTarget) {
            this.mScoreTimeGettingScore += i;
            if (this.mScoreTimeGettingScore > this.mIngameTrain.mPassengerCnt * 200) {
                this.mScoreTimeGettingScore = this.mIngameTrain.mPassengerCnt * 200;
            }
        } else if (this.mScoreTimeGettingScore > 0) {
            this.mScoreTimeGettingScore -= i;
            if (this.mScoreTimeGettingScore < 0) {
                this.mScoreTimeGettingScore = 0;
            }
        }
        if (!this.mIngameTrain.mIsBraking && this.mIngameTrain.mState != 4) {
            this.mWheelSO.logicUpdate((Math.abs((FP.mul(this.mIngameTrain.mSpeed, Util.cos(this.mIngameTrain.mCarAngle[0])) * 30) >> 12) * 50) / 100);
        }
        ingameFGObjectUpdate(i);
    }
}
