package com.thinkfaster.manager;

import android.graphics.Color;
import com.thinkfaster.util.ContextConstants;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class ResourcesManager {

    private static final ResourcesManager INSTANCE = new ResourcesManager();
    private BaseGameActivity activity;
    private Engine engine;
    private Camera camera;
    private VertexBufferObjectManager vertexBufferObjectManager;

    private BitmapTextureAtlas splashTextureAtlas, menuFontTextureAtlas, gameFontTextureAtlas, greenFontTextureAtlas,
            chalkFontTextureAtlas, loadingTextureAtlas;
    private BuildableBitmapTextureAtlas menuTextureAtlas, aboutTextureAtlas, endGameTextureAtlas,
            recordTextureAtlas, gameTypeTextureAtlas, gameTextureAtlas;
    // Game
    private ITextureRegion backgroundGameTextureRegion;
    private List<ITiledTextureRegion> animalTiledTextureRegionList;
    private List<BuildableBitmapTextureAtlas> gameTextureAtlasList;
    // Splash
    private ITextureRegion splashTextureRegion;
    // Menu
    private ITextureRegion buttonAboutTextureRegion, buttonExitTextureRegion, buttonNewGameTextureRegion,
            buttonOptionsTextureRegion, menuBackgroundTextureRegion, buttonMultiplayerTextureRegion;
    // Help
    private ITextureRegion aboutBackgroundTextureRegion;
    // Options
    private ITextureRegion optionsBackgroundTextureRegion, optionsTextureRegion;
    // EndGame
    private ITextureRegion endGameBackgroundTextureRegion;
    // HighScore
    private ITextureRegion recordBackgroundTextureRegion, buttonHighScoreTextureRegion;
    //Loading
    private ITextureRegion loadingTextureRegion;
    // Game Type
    private ITextureRegion backgroundGameTypeTextureRegion, starGoldTextureRegion, starWhiteTextureRegion,
            awesomeTextureRegion, lockTextureRegion, playButtonTextureRegion;
    private List<Sound> winSoundList, loseSoundList, halfWinSoundList;
    private Sound startGameSound, goodClickSound, wrongClickSound;
    private Font whiteFont, blackFont, greenFont, chalkFont;

    public List<ITiledTextureRegion> getAnimalTiledTextureRegionList() {
        return animalTiledTextureRegionList;
    }

    public static void prepareManager(Engine engine, BaseGameActivity activity, Camera camera, VertexBufferObjectManager vertexBufferObjectManager) {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vertexBufferObjectManager = vertexBufferObjectManager;
    }

    public static ResourcesManager getInstance() {
        return INSTANCE;
    }


    public void loadAboutResources() {
        loadAboutGraphics();
    }

    public void loadMainMenuResources() {
        loadMainMenuGraphics();
        loadWhiteFont();
        loadBlackFont();
        loadGreenFont();
        loadChalkFont();
    }

    public void loadGameResources() {
        loadGameGraphics();
        loadGameMusic();
        loadEndGameResources();
    }

    public void loadEndGameResources() {
        loadEndGameGraphics();
    }

    public Engine getEngine() {
        return engine;
    }

    public void loadRecordResources() {
        loadHighScoreGraphics();
    }

    public void loadGameTypeResources() {
        loadGameTypeGraphics();
    }

    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.jpg", 0, 0);
        splashTextureAtlas.load();
    }

    public void loadMenuTextures() {
        menuTextureAtlas.load();
    }

    public void unloadSplashScreen() {
        splashTextureAtlas.unload();
        splashTextureRegion = null;
    }


    public void unloadAboutTextures() {
        aboutTextureAtlas.unload();
    }

    public void unloadEndGameTextures() {
        endGameTextureAtlas.unload();
    }

    public void unloadRecordsTextures() {
        recordTextureAtlas.unload();
    }

    public void unloadGameTypeTextures() {
        gameTypeTextureAtlas.unload();
    }

    public void unloadGameTextures() {
        gameTextureAtlas.unload();
        for (BuildableBitmapTextureAtlas atlas : gameTextureAtlasList) {
            atlas.unload();
        }
    }

    public void unloadMenuTextures() {
        menuTextureAtlas.unload();
    }

    public BaseGameActivity getActivity() {
        return activity;
    }

    public Camera getCamera() {
        return camera;
    }

    public VertexBufferObjectManager getVertexBufferObjectManager() {
        return vertexBufferObjectManager;
    }

    public ITextureRegion getSplashTextureRegion() {
        return splashTextureRegion;
    }

    public ITextureRegion getButtonAboutTextureRegion() {
        return buttonAboutTextureRegion;
    }

    public ITextureRegion getButtonExitTextureRegion() {
        return buttonExitTextureRegion;
    }

    public ITextureRegion getButtonNewGameTextureRegion() {
        return buttonNewGameTextureRegion;
    }

    public ITextureRegion getButtonOptionsTextureRegion() {
        return buttonOptionsTextureRegion;
    }

    public ITextureRegion getMenuBackgroundTextureRegion() {
        return menuBackgroundTextureRegion;
    }

    public ITextureRegion getAboutBackgroundTextureRegion() {
        return aboutBackgroundTextureRegion;
    }

    public ITextureRegion getOptionsBackgroundTextureRegion() {
        return optionsBackgroundTextureRegion;
    }

    public ITextureRegion getOptionsTextureRegion() {
        return optionsTextureRegion;
    }

    public Font getWhiteFont() {
        return whiteFont;
    }

    public Font getBlackFont() {
        return blackFont;
    }

    public Font getGreenFont() {
        return greenFont;
    }

    public Font getChalkFont() {
        return chalkFont;
    }

    public ITextureRegion getEndGameBackgroundTextureRegion() {
        return endGameBackgroundTextureRegion;
    }

    public ITextureRegion getRecordBackgroundTextureRegion() {
        return recordBackgroundTextureRegion;
    }

    public ITextureRegion getButtonHighScoreTextureRegion() {
        return buttonHighScoreTextureRegion;
    }

    public ITextureRegion getBackgroundGameTypeTextureRegion() {
        return backgroundGameTypeTextureRegion;
    }

    public ITextureRegion getBackgroundGameTextureRegion() {
        return backgroundGameTextureRegion;
    }

    public ITextureRegion getLoadingTextureRegion() {
        return loadingTextureRegion;
    }

    public void loadLoadingResources() {
        if (loadingTextureAtlas != null) {
            loadingTextureAtlas.load();
            return;
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/loading/");
        loadingTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        loadingTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(loadingTextureAtlas, activity, "background.png", 0, 0);
        loadingTextureAtlas.load();
    }

    public ITiledTextureRegion getAnimalTiledTexture(int animalId) {
        return animalTiledTextureRegionList.get(animalId);
    }

    private void loadAboutGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/about/");
        aboutTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        aboutBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(aboutTextureAtlas, activity, "background.jpg");

        try {
            aboutTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            aboutTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
    }

    private void loadMainMenuGraphics() {

        if (menuTextureAtlas != null) {
            menuTextureAtlas.load();
            return;
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        menuBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "background.jpg");
        buttonAboutTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_help.png");
        buttonExitTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_exit.png");
        buttonNewGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_new.png");
        buttonOptionsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_options.png");
        buttonHighScoreTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_high.png");
        buttonMultiplayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_multiplayer.png");

        try {
            menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            menuTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
    }

    private void loadWhiteFont() {
        if (menuFontTextureAtlas != null) {
            return;
        }
        FontFactory.setAssetBasePath("font/");
        menuFontTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        whiteFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), menuFontTextureAtlas, activity.getAssets(), "ChalkPaint.ttf", 50, true, Color.WHITE, 2, Color.WHITE);
        menuFontTextureAtlas.load();
        whiteFont.load();
    }

    private void loadBlackFont() {
        if (gameFontTextureAtlas != null) {
            return;
        }
        FontFactory.setAssetBasePath("font/");
        gameFontTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        blackFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), gameFontTextureAtlas, activity.getAssets(), "font1.ttf", 50, true, Color.BLACK, 2, Color.BLACK);
        gameFontTextureAtlas.load();
        blackFont.load();
    }

    private void loadGreenFont() {
        if (greenFontTextureAtlas != null) {
            return;
        }
        FontFactory.setAssetBasePath("font/");
        greenFontTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        greenFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), greenFontTextureAtlas, activity.getAssets(), "ChalkPaint.ttf", 50, true, Color.GREEN, 2, Color.GREEN);
        greenFontTextureAtlas.load();
        greenFont.load();
    }

    private void loadChalkFont() {
        if (chalkFontTextureAtlas != null) {
            return;
        }
        FontFactory.setAssetBasePath("font/");
        chalkFontTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        chalkFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), chalkFontTextureAtlas, activity.getAssets(), "ChalkPaint.ttf", 50, true, Color.WHITE, 2, Color.WHITE);
        chalkFontTextureAtlas.load();
        chalkFont.load();
    }

    private void loadGameGraphics() {

        if (gameTextureAtlas != null) {
            gameTextureAtlas.load();
            for (BuildableBitmapTextureAtlas atlas : gameTextureAtlasList) {
                atlas.load();
            }
            return;
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
        gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        backgroundGameTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "background.png");


        animalTiledTextureRegionList = new ArrayList<>();
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/animals/");


        gameTextureAtlasList = new ArrayList<>();
        for (int i = 0; i < ContextConstants.NUMBER_OF_ANIMALS + 1; i++) {
            gameTextureAtlasList.add(new BuildableBitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR));
        }
        for (int i = 0; i < ContextConstants.NUMBER_OF_ANIMALS; i++) {
            animalTiledTextureRegionList.add(BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlasList.get(i), activity, i + ".jpg", 2, 2));
        }

        try {
            for (int i = 0; i < ContextConstants.NUMBER_OF_ANIMALS + 1; i++) {
                gameTextureAtlasList.get(i).build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
                gameTextureAtlasList.get(i).load();
            }
            gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            gameTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }

    }

    private void loadGameMusic() {

        SoundFactory.setAssetBasePath("mfx/other/");
        winSoundList = new ArrayList<Sound>();
        loseSoundList = new ArrayList<Sound>();
        halfWinSoundList = new ArrayList<Sound>();
        try {
            winSoundList.add(SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "win.ogg"));

            loseSoundList.add(SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "lose.ogg"));
            loseSoundList.add(SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "lose1.ogg"));

            halfWinSoundList.add(SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "halfwin0.ogg"));
            halfWinSoundList.add(SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "halfwin1.ogg"));
            halfWinSoundList.add(SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "halfwin2.ogg"));

            startGameSound = SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "go.ogg");
            goodClickSound = SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "goodClick.ogg");
            wrongClickSound = SoundFactory.createSoundFromAsset(getEngine().getSoundManager(), activity, "wrongClick.ogg");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void loadEndGameGraphics() {
        if (endGameTextureAtlas != null) {
            endGameTextureAtlas.load();
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/endgame/");
        endGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);

        endGameBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(endGameTextureAtlas, activity, "background.png");

        try {
            endGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
            endGameTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
    }

    private void loadHighScoreGraphics() {
        if (recordTextureAtlas != null) {
            recordTextureAtlas.load();
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/highscore/");

        recordTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
        recordBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(recordTextureAtlas, activity, "background.png");

        try {
            recordTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
            recordTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }


    }

    private void loadGameTypeGraphics() {
        if (gameTypeTextureAtlas != null) {
            gameTypeTextureAtlas.load();
        }

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/gametype/");
        gameTypeTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);

        backgroundGameTypeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "background.png");

        starGoldTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "goldStar.png");
        starWhiteTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "whiteStar.png");
        awesomeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "awesome.png");
        lockTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "lock.png");
        playButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTypeTextureAtlas, activity, "playButton.png");

        try {
            gameTypeTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1, 1, 1));
            gameTypeTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
    }
}
