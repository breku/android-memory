package com.thinkfaster.model.scene.game;

import android.util.Log;
import com.thinkfaster.handler.*;
import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.model.Level;
import com.thinkfaster.model.MemoryConfiguration;
import com.thinkfaster.model.shape.MemoryPair;
import com.thinkfaster.model.shape.QuestionMarkItem;
import com.thinkfaster.service.GameItemsProvider;
import com.thinkfaster.service.HighScoreService;
import com.thinkfaster.util.ContextConstants;
import com.thinkfaster.util.SceneType;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class GameScene extends AbstractGameScene {

    private static final String TAG = "GameScene";

    private HUD gameHUD;
    private GameItemsProvider gameItemsProvider;
    private Level currentLevel;
    private List<MemoryPair> memoryPairs;
    private Text timerText;
    private MemoryConfiguration memoryConfiguration;
    private HighScoreService highScoreService;

    /**
     * @param objects objects[0] - levelDifficulty
     *                objects[1] - mathParameter
     *                objects[2] - multiplayer
     */
    public GameScene(Object... objects) {
        super(objects);
    }

    @Override
    public void initializeServices() {
        gameItemsProvider = new GameItemsProvider(resourcesManager);
        memoryConfiguration = new MemoryConfiguration();
        highScoreService = new HighScoreService();
    }

    @Override
    public void createScene(Object... objects) {
        clear();
        init(objects);
        createBackground();
        createMemoryItems();
        createHUD();
        registerUpdateHandlers();
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuScene(this);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SINGLE_PLAYER_GAME;
    }

    @Override
    public void disposeScene() {
        gameHUD.clearChildScene();
        camera.setHUD(null);
        camera.setCenter(ContextConstants.SCREEN_WIDTH / 2, ContextConstants.SCREEN_HEIGHT / 2);
        camera.setChaseEntity(null);
        ResourcesManager.getInstance().unloadGameTextures();
    }

    private void clear() {
        clearChildScene();
        clearUpdateHandlers();
        clearTouchAreas();
    }

    private void registerUpdateHandlers() {
        final List<IUpdateHandler> updateHandlerList = new LinkedList<>();
        updateHandlerList.add(new FoundMemoryPairsUpdateHandler(memoryPairs));
        updateHandlerList.add(new ShowClickedItemsUpdateHandler(memoryPairs));
        updateHandlerList.add(new HideClickedItemsUpdateHandler(memoryPairs, engine));
        updateHandlerList.add(new TimeUpdateHandler(memoryPairs, timerText));
        updateHandlerList.add(new FinishGameUpdateHandler(memoryPairs, currentLevel, memoryConfiguration));

        for (IUpdateHandler iUpdateHandler : updateHandlerList) {
            registerUpdateHandler(iUpdateHandler);
        }

        Log.i(TAG, ">> Registered handlers: " + updateHandlerList);
    }

    private void createMemoryItems() {
        memoryPairs = gameItemsProvider.getMemoryPairs(currentLevel);
        for (MemoryPair memoryPair : memoryPairs) {
            attachChild(memoryPair.getItem1());
            attachChild(memoryPair.getItem2());
            registerTouchArea(memoryPair.getItem1());
            registerTouchArea(memoryPair.getItem2());
        }

        final List<QuestionMarkItem> questionMarks = gameItemsProvider.getQuestionMarks(memoryPairs);
        for (QuestionMarkItem questionMark : questionMarks) {
            questionMark.setZIndex(1);
            attachChild(questionMark);
        }
    }

    private void init(Object... objects) {

        initializeLevel(objects);
    }

    private void initializeLevel(Object[] objects) {
        currentLevel = (Level) objects[0];
    }

    private void createBackground() {

        setBackground(gameItemsProvider.getBackground());
    }

    private void createHUD() {
        gameHUD = new HUD();

        Text timeText = new Text(380, 440, resourcesManager.getBlackFont(), "Time: ", new TextOptions(HorizontalAlign.LEFT), vertexBufferObjectManager);
        timerText = new Text(490, 440, resourcesManager.getBlackFont(), "0123456789", 30, new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        timerText.setText("00.00");

        gameHUD.attachChild(timeText);
        gameHUD.attachChild(timerText);

        camera.setHUD(gameHUD);
    }

    private double getScore() {
        return Double.parseDouble(String.valueOf(timerText.getText()));
    }

    private void finishGame() {
        final double score = getScore();
        highScoreService.updateScores(score);
        SceneManager.getInstance().loadEndGameScene(score);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (memoryConfiguration.isGameFinished()) {
            finishGame();
        }

        sortChildren();
    }
}
