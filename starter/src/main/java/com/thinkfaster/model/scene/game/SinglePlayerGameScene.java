package com.thinkfaster.model.scene.game;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.matcher.ClassTouchAreaMacher;
import com.thinkfaster.util.ContextConstants;
import com.thinkfaster.util.SceneType;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.sprite.Sprite;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class SinglePlayerGameScene extends AbstractGameScene {


    private HUD gameHUD;


    /**
     * @param objects objects[0] - levelDifficulty
     *                objects[1] - mathParameter
     *                objects[2] - multiplayer
     */
    public SinglePlayerGameScene(Object... objects) {
        super(objects);
    }


    @Override
    public void createScene(Object... objects) {
        init(objects);
        createBackground();
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

    private void init(Object... objects) {
        clearUpdateHandlers();
        clearTouchAreas();


    }

    private void createBackground() {
        unregisterTouchAreas(new ClassTouchAreaMacher(Sprite.class));
        clearChildScene();
        attachChild(new Sprite(ContextConstants.SCREEN_WIDTH / 2, ContextConstants.SCREEN_HEIGHT / 2,
                ResourcesManager.getInstance().getBackgroundGameTextureRegion(), vertexBufferObjectManager));


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
