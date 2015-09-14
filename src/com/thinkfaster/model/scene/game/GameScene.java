package com.thinkfaster.model.scene.game;

import com.thinkfaster.manager.GameTexturesProvider;
import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.matcher.ClassTouchAreaMacher;
import com.thinkfaster.model.Level;
import com.thinkfaster.model.shape.MemoryItem;
import com.thinkfaster.util.ContextConstants;
import com.thinkfaster.util.SceneType;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.sprite.Sprite;

import java.util.List;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class GameScene extends AbstractGameScene {


    private HUD gameHUD;
    private GameTexturesProvider gameTexturesProvider;
    private Level currentLevel;

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
        gameTexturesProvider = new GameTexturesProvider();
    }

    @Override
    public void createScene(Object... objects) {
        init(objects);
        createBackground();
        createMemoryItems();
        createHUD();
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

    private void createMemoryItems() {
        final List<MemoryItem> memoryItems = gameTexturesProvider.getMemoryItems(currentLevel);
        for (MemoryItem memoryItem : memoryItems) {
            attachChild(memoryItem);
        }
    }

    private void init(Object... objects) {
        clearUpdateHandlers();
        clearTouchAreas();
        initializeLevel(objects);
    }

    private void initializeLevel(Object[] objects) {
        currentLevel = (Level) objects[0];
    }

    private void createBackground() {
        unregisterTouchAreas(new ClassTouchAreaMacher(Sprite.class));
        clearChildScene();
        setBackground(gameTexturesProvider.getBackground());
    }


    private void createHUD() {
        gameHUD = new HUD();
        camera.setHUD(gameHUD);
    }


    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {


        super.onManagedUpdate(pSecondsElapsed);
    }
}
