package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class Entity {
    public static int[] ENTITIES_COLOR = null;
    public static final int ENTITY_PICKUP_HEIGHT_COUNT = 40;
    private static final int ENTITY_PICKUP_RADIUS = 18;
    private static final int ENTITY_STATE_DISABLED = 2;
    private static final int ENTITY_STATE_PICKEDUP = 1;
    private static final int ENTITY_STATE_RUNNING = 0;
    public static final int ENTITY_TYPE_PICKUP_EXCITER = 2;
    public static final int ENTITY_TYPE_PICKUP_GOD = 4;
    public static final int ENTITY_TYPE_PICKUP_JUMP = 5;
    public static final int ENTITY_TYPE_PICKUP_NITRO = 0;
    public static final int ENTITY_TYPE_PICKUP_SMILEY = 1;
    public static final int ENTITY_TYPE_PICKUP_SPRINGS = 6;
    public static final int ENTITY_TYPE_PICKUP_STICKY = 3;
    private static final int SPRITE_CATALOG_COUNT = 9;
    private static final int SPRITE_CATALOG_PICKUP_EXCITER_ID = 2;
    private static final int SPRITE_CATALOG_PICKUP_GOLD_ID = 4;
    private static final int SPRITE_CATALOG_PICKUP_JUMP_ID = 5;
    private static final int SPRITE_CATALOG_PICKUP_NITRO_ID = 0;
    private static final int SPRITE_CATALOG_PICKUP_PICKEDUP_ID = 7;
    private static final int SPRITE_CATALOG_PICKUP_SMILEY_ID = 1;
    private static final int SPRITE_CATALOG_PICKUP_SPRINGS_ID = 6;
    private static final int SPRITE_CATALOG_PICKUP_STICKY_ID = 3;
    private static final int SPRITE_CATALOG_SMILEY_PICKEDUP_ID = 8;
    private static Entity[] smEntities;
    private static int smEntitiesCount;
    private static int smEntitiesCurrentID;
    public static int smNumExciters;
    public static int smNumGod;
    public static int smNumNitros;
    public static int smNumStickies;
    public static int smNumWinged;
    private static int smSmilesCollectedInARow;
    private static int smSmileyLastCollectedID;
    private static SpriteObject[] smSpritesCatalog;
    private static boolean[] smSpritesCatalogEnabled;
    private int mBoundingBoxHeightFP;
    private int mBoundingBoxWidthFP;
    private int mColor;
    private int mFSMAliveCount;
    private int mHeight;
    private int mScreenOffsetX;
    private int mScreenOffsetY;
    private int mScreenX;
    private int mScreenY;
    private SpriteObject mSprite;
    public int mTrackID;
    private int mTrackScoreID;
    private int mType;
    private boolean mVisible;
    private int mWorldXFP;
    private int mWorldYFP;
    private static int ENTITY_FSM_VISIBLE = 0;
    private static int ENTITY_FSM_COUNT = 2;
    private static Train[] smTrain = new Train[ENTITY_FSM_COUNT];
    public static int smSmileysCount = 0;
    private static final int[] ENTITY_BB_RADIUS = {18, 12, 18, 18, 18, 18, 18};
    public static int[] ENTITY_PICKUP_HEIGHT = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39};
    private int[] mState = new int[ENTITY_FSM_COUNT];
    private boolean[] mCollidable = new boolean[ENTITY_FSM_COUNT];

    public Entity(int i, int i2, int i3) throws IOException {
        int i4;
        if (i2 == 1) {
            int i5 = smSmileysCount;
            smSmileysCount = i5 + 1;
            this.mTrackScoreID = i5;
        }
        this.mTrackID = i;
        this.mWorldXFP = TrackModel.getTrackPointX(i);
        this.mWorldYFP = TrackModel.getTrackPointY(i);
        this.mType = i2;
        init();
        this.mBoundingBoxWidthFP = ENTITY_BB_RADIUS[this.mType] << 13;
        this.mBoundingBoxHeightFP = ENTITY_BB_RADIUS[this.mType] << 13;
        this.mScreenOffsetX = this.mBoundingBoxWidthFP >> 13;
        this.mScreenOffsetY = this.mBoundingBoxHeightFP >> 13;
        int i6 = TrackModel.smTrackSlope[i];
        if (Util.sin(i6) >= 0 && Util.cos(i6) >= 0) {
            this.mWorldXFP -= this.mBoundingBoxWidthFP;
            this.mWorldYFP -= this.mBoundingBoxHeightFP;
            i4 = -1;
        } else if (Util.sin(i6) >= 0 || Util.cos(i6) < 0) {
            if (Util.sin(i6) >= 0 && Util.cos(i6) < 0) {
                this.mWorldXFP -= this.mBoundingBoxWidthFP;
            }
            i4 = 1;
        } else {
            this.mWorldYFP -= this.mBoundingBoxHeightFP;
            i4 = -1;
        }
        this.mWorldYFP += (ENTITY_PICKUP_HEIGHT[i3] << 12) * i4 * 28;
        this.mHeight = i4 * ENTITY_PICKUP_HEIGHT[i3];
    }

    private void changeState(int i, int i2) throws IOException {
        this.mState[i] = i2;
        switch (this.mState[i]) {
            case 0:
                this.mCollidable[i] = true;
                if (i == ENTITY_FSM_VISIBLE) {
                }
                break;
            case 1:
                if (this.mType != 1) {
                    if (DCThrillOld.smGameState == 50 && GameEngine.smGameEngineState == 1) {
                        int i3 = this.mType + 21;
                        if (this.mType != 6 && !DCThrillOld.smTutorialSeen[i3]) {
                            DCThrillOld.setTutorialTextBox(null, i3, false);
                        }
                    }
                    switch (this.mType) {
                        case 0:
                            smNumNitros++;
                            break;
                        case 2:
                            if (i == ENTITY_FSM_VISIBLE) {
                                smTrain[i].accumSmiley(10);
                                smNumExciters++;
                                int gameAreaWidth = Game.getGameAreaWidth() + GameEngine.smEngine2d.mHudScoreSmileOffX;
                                int i4 = GameEngine.smEngine2d.mHudScoreSmileY;
                                int iIngameGetCameraX = GameEngine.smEngine2d.ingameGetCameraX();
                                int iIngameGetCameraY = GameEngine.smEngine2d.ingameGetCameraY();
                                for (int i5 = 0; i5 < 10; i5++) {
                                    int i6 = (i5 / GameEngine.smTrain.mCarCnt) % GameEngine.smTrain.mCarCnt;
                                    int i7 = i5 % 2;
                                    int frameFromAngle = Engine2D.getFrameFromAngle(GameEngine.smTrain.mCarAngle[i6]);
                                    Engine2D.particlesAddMovement(Engine2D.particlesAddParticle(2, ((GameEngine.smTrain.getCarX(i6) - GameEngine.smTrain.mCamPointX) >> 12) + iIngameGetCameraX + GameEngine.smEngine2d.mPassengerPosX[frameFromAngle][i7], ((GameEngine.smTrain.getCarY(i6) - GameEngine.smTrain.mCamPointY) >> 12) + iIngameGetCameraY + GameEngine.smEngine2d.mPassengerPosY[frameFromAngle][i7], 4), gameAreaWidth, i4, Util.rnd(2000) + 1000);
                                }
                                break;
                            }
                            break;
                        case 3:
                            smNumStickies++;
                            break;
                        case 4:
                            smNumGod++;
                            break;
                        case 5:
                            smNumWinged++;
                            break;
                    }
                    smTrain[i].convoyPickUpEnable(this.mType);
                } else if (i == ENTITY_FSM_VISIBLE) {
                    smTrain[i].accumSmiley(1);
                    Engine2D.particlesAddMovement(Engine2D.particlesAddParticle(2, GameEngine.smEngine2d.ingameGetCameraX(), GameEngine.smEngine2d.ingameGetCameraY(), 4), Game.getGameAreaWidth() + GameEngine.smEngine2d.mHudScoreSmileOffX, GameEngine.smEngine2d.mHudScoreSmileY, 1000);
                    if (RollerGameEngine.smGameMode == 0) {
                        if (this.mTrackScoreID == smSmileyLastCollectedID + 1) {
                            smSmilesCollectedInARow++;
                        } else {
                            smSmilesCollectedInARow = 1;
                        }
                        smSmileyLastCollectedID = this.mTrackScoreID;
                        if (!ResultsCareerSimple.smGetJump && smSmilesCollectedInARow == 3) {
                            Engine2D.particlesAddMovement(Engine2D.particlesAddParticle(3, GameEngine.smEngine2d.ingameGetCameraX(), GameEngine.smEngine2d.ingameGetCameraY(), 4), Game.getGameAreaWidth() + GameEngine.smEngine2d.mHudScoreSmileOffX, GameEngine.smEngine2d.mHudScoreSmileY, 1000);
                            ResultsCareerSimple.smGetJump = true;
                        }
                    }
                }
                this.mCollidable[i] = false;
                if (i == ENTITY_FSM_VISIBLE) {
                    if (this.mType == 6) {
                        smSpritesCatalogEnabled[this.mType] = true;
                        smSpritesCatalog[this.mType].setAnimation(0, 1, true);
                        break;
                    } else {
                        this.mSprite = smSpritesCatalog[this.mType == 1 ? '\b' : (char) 7];
                        this.mSprite.setAnimation(0, 1, true);
                        break;
                    }
                }
                break;
            case 2:
                if (this.mType == 6) {
                    smSpritesCatalogEnabled[this.mType] = false;
                } else if (i == ENTITY_FSM_VISIBLE) {
                    this.mVisible = false;
                }
                this.mCollidable[i] = false;
                this.mFSMAliveCount--;
                break;
        }
    }

    public static void drawEntities(Graphics graphics) {
        for (int i = smEntitiesCurrentID; i < smEntitiesCount; i++) {
            if (smEntities[i].mVisible) {
                smEntities[i].draw(graphics);
            }
        }
    }

    public static void drawEntitiesPreview(Graphics graphics, int i, int i2, int i3) {
        for (int i4 = 0; i4 < smEntitiesCount; i4++) {
            int i5 = ((TrackModel.smTrackPointX[smEntities[i4].mTrackID] >> 12) / i) - i2;
            int i6 = ((TrackModel.smTrackPointY[smEntities[i4].mTrackID] >> 12) / i) + i3 + (smEntities[i4].mHeight * 3);
            GameEngine.smEngine2d.mPickUpIcons.setAnimation(smEntities[i4].mType, 0, true);
            GameEngine.smEngine2d.mPickUpIcons.draw(graphics, i5, i6);
        }
    }

    private void init() throws IOException {
        this.mSprite = smSpritesCatalog[this.mType];
        this.mVisible = true;
        for (int i = 0; i < ENTITY_FSM_COUNT; i++) {
            changeState(i, 0);
        }
        this.mFSMAliveCount = ENTITY_FSM_COUNT;
    }

    public static void initManager() throws IOException {
        for (int i = 0; i < 9; i++) {
            if (i == 6) {
                smSpritesCatalogEnabled[i] = false;
                smSpritesCatalog[i].setAnimationFrame(0);
            } else {
                smSpritesCatalogEnabled[i] = true;
            }
        }
        for (int i2 = 0; i2 < smEntitiesCount; i2++) {
            smEntities[i2].init();
        }
        smSmileyLastCollectedID = -1;
        smSmilesCollectedInARow = 0;
    }

    public static void loadManager() {
        if (smEntities != null) {
            releaseManager();
        }
        smSpritesCatalog = new SpriteObject[9];
        smSpritesCatalogEnabled = new boolean[9];
        smSpritesCatalog[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_TURBO, false), true);
        smSpritesCatalog[1] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SMILEY, false), true);
        smSpritesCatalog[2] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_EXCITER, false), true);
        smSpritesCatalog[3] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_MAGNET, false), true);
        smSpritesCatalog[4] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_EXTRA_LIFE, false), true);
        smSpritesCatalog[5] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_WINGED_JUMP, false), true);
        smSpritesCatalog[6] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_E_SPRING, false), true);
        smSpritesCatalog[7] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_ACTIVE, false), true);
        smSpritesCatalog[8] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SMILEY_ACTIVE, false), true);
    }

    public static void populateManager(Vector vector) {
        smEntitiesCount = vector.size();
        if (smEntitiesCount <= 0) {
            smEntities = null;
            return;
        }
        smEntities = new Entity[vector.size()];
        smEntitiesCurrentID = 0;
        vector.copyInto(smEntities);
    }

    public static int powerUpApproaching(int i) {
        if (smEntities == null || smEntities[0].mType == 6 || smEntities[0].mWorldXFP - i >= (smEntities[0].mBoundingBoxWidthFP << 2)) {
            return -1;
        }
        return smEntities[0].mType;
    }

    public static void releaseManager() {
        smEntities = null;
        for (int i = 0; i < 9; i++) {
            if (smSpritesCatalog[i] != null) {
                smSpritesCatalog[i].freeResources();
            }
        }
        smSpritesCatalog = null;
    }

    public static void setTrains(Train[] trainArr) {
        smTrain = trainArr;
    }

    private void update(int i) throws IOException {
        int i2 = this.mWorldXFP >> 12;
        int i3 = this.mWorldYFP >> 12;
        for (int i4 = 0; i4 < ENTITY_FSM_COUNT; i4++) {
            if (this.mState[i4] == 1 && i4 == ENTITY_FSM_VISIBLE) {
                if (this.mSprite.isFinishedAnimation()) {
                    changeState(i4, 2);
                }
            } else if (smTrain[i4] != null && this.mState[i4] == 0) {
                for (int i5 = 0; this.mCollidable[i4] && i5 < smTrain[i4].mCarCnt; i5++) {
                    int frameFromAngle = Engine2D.getFrameFromAngle(smTrain[i4].mCarAngle[i5]);
                    if (Engine2D.collisionCircles(ENTITY_BB_RADIUS[this.mType] + i2, ENTITY_BB_RADIUS[this.mType] + i3, ENTITY_BB_RADIUS[this.mType], (smTrain[i4].getCarX(i5) - (Util.sin(frameFromAngle) * 12)) >> 12, (smTrain[i4].getCarY(i5) - (Util.cos(frameFromAngle) * 12)) >> 12, 20)) {
                        changeState(i4, 1);
                    }
                }
            }
        }
    }

    public static void updateEntities(int i, int i2, int i3) throws IOException {
        for (int i4 = 0; i4 < 9; i4++) {
            if (smSpritesCatalogEnabled[i4]) {
                smSpritesCatalog[i4].logicUpdate(i);
            }
        }
        for (int i5 = smEntitiesCurrentID; i5 < smEntitiesCount; i5++) {
            if (smEntities[i5].mFSMAliveCount > 0) {
                smEntities[i5].update(i);
                if (smEntities[i5].mVisible) {
                    smEntities[i5].mScreenX = ((smEntities[i5].mWorldXFP - smTrain[ENTITY_FSM_VISIBLE].mCamPointX) >> 12) + i2 + smEntities[i5].mScreenOffsetX;
                    smEntities[i5].mScreenY = ((smEntities[i5].mWorldYFP - smTrain[ENTITY_FSM_VISIBLE].mCamPointY) >> 12) + i3 + smEntities[i5].mScreenOffsetY;
                }
            }
        }
    }

    public void draw(Graphics graphics) {
        if (this.mSprite != null) {
            int width = this.mSprite.getWidth();
            int height = this.mSprite.getHeight();
            int i = GameEngine.smEngine2d.mScreenWidth;
            int i2 = GameEngine.smEngine2d.mScreenHeight;
            if (this.mScreenX + width < 0 || this.mScreenX - width > i || this.mScreenY + height < 0 || this.mScreenY - height > i2) {
                return;
            }
            this.mSprite.draw(graphics, this.mScreenX, this.mScreenY);
        }
    }
}
