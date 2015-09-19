package com.thinkfaster.model.scene.game;

import com.thinkfaster.handler.FoundMemoryPairsUpdateHandler;
import com.thinkfaster.handler.HideClickedItemsUpdateHandler;
import com.thinkfaster.handler.ShowClickedItemsUpdateHandler;
import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.matcher.ClassTouchAreaMacher;
import com.thinkfaster.model.Level;
import com.thinkfaster.model.shape.MemoryPair;
import com.thinkfaster.model.shape.QuestionMarkItem;
import com.thinkfaster.service.GameItemsProvider;
import com.thinkfaster.util.ContextConstants;
import com.thinkfaster.util.SceneType;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;

import java.util.List;

import static com.thinkfaster.util.ContextConstants.MEMORY_ITEM_SHOWTIME;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class GameScene extends AbstractGameScene {


    private List<QuestionMarkItem> questionMarks;
    private HUD gameHUD;
    private GameItemsProvider gameItemsProvider;
    private Level currentLevel;
    private List<MemoryPair> memoryPairs;
    private boolean showingItems;

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

    private void clear() {
        clearChildScene();
        clearUpdateHandlers();
        clearTouchAreas();
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

    private void registerUpdateHandlers() {
        registerUpdateHandler(new FoundMemoryPairsUpdateHandler(memoryPairs));
        registerUpdateHandler(new ShowClickedItemsUpdateHandler(memoryPairs));
        registerUpdateHandler(new HideClickedItemsUpdateHandler(memoryPairs,engine));
    }

    private void createMemoryItems() {
        memoryPairs = gameItemsProvider.getMemoryPairs(currentLevel);
        for (MemoryPair memoryPair : memoryPairs) {
            attachChild(memoryPair.getItem1());
            attachChild(memoryPair.getItem2());
            registerTouchArea(memoryPair.getItem1());
            registerTouchArea(memoryPair.getItem2());
        }

        questionMarks = gameItemsProvider.getQuestionMarks(memoryPairs);
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
        camera.setHUD(gameHUD);
    }





    private void hideMemoryItems() {
        showingItems = true;

        registerUpdateHandler(new TimerHandler(MEMORY_ITEM_SHOWTIME, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                for (MemoryPair memoryPair : memoryPairs) {
                    if (!memoryPair.isFound()) {
                        memoryPair.getItem1().setClicked(false);
                        memoryPair.getItem2().setClicked(false);
                        memoryPair.getItem1().setZIndex(0);
                        memoryPair.getItem2().setZIndex(0);
                    }
                }
                showingItems = false;
            }
        }));
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {




        sortChildren();
        super.onManagedUpdate(pSecondsElapsed);
    }


}
