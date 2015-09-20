package com.thinkfaster.model.scene.menu;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.model.scene.BaseScene;
import com.thinkfaster.model.shape.HexagonPlayButton;
import com.thinkfaster.model.shape.SoundButton;
import com.thinkfaster.service.SoundService;
import com.thinkfaster.util.ContextConstants;
import com.thinkfaster.util.SceneType;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationByModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.color.Color;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class MainMenuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {
    private final int NEW_GAME = 0;
    private final int ABOUT = 2;
    private final int EXIT = 3;
    private final int RECORDS = 4;

    private MenuScene menuScene;
    private SoundService soundService;
    private SoundButton soundButton;

    @Override
    public void initializeServices() {
        soundService = new SoundService();
    }

    @Override
    public void createScene(Object... objects) {
        clear();
        createBackground();
        createButtons();
        createPentagon();
        createSoundButtons();
    }

    @Override
    public void onBackKeyPressed() {
        System.exit(0);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MENU;
    }

    @Override
    public void disposeScene() {

    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch (pMenuItem.getID()) {
            case NEW_GAME:
                SceneManager.getInstance().loadGameTypeScene();
                break;
            case ABOUT:
                SceneManager.getInstance().loadAboutScene();
                break;
            case EXIT:
                System.exit(0);
            case RECORDS:
                SceneManager.getInstance().loadHighScoreSceneFrom(SceneType.MENU);
                break;
            default:
                return false;
        }
        return false;
    }

    private void createSoundButtons() {
        soundButton = new SoundButton(600, 280);
        if (soundService.isMusicOn()) {
            soundButton.setMusicOn();
        }else{
            soundButton.setMusicOff();
        }
        registerTouchArea(soundButton);
        attachChild(soundButton);
    }

    private void createPentagon() {
        final HexagonPlayButton hexagonPlayButton1 = createHexagon();
        final HexagonPlayButton hexagonPlayButton2 = createHexagon();

        hexagonPlayButton1.registerEntityModifier(createRotationModifier(30));
        hexagonPlayButton2.registerEntityModifier(createRotationModifier(-30));
        attachChild(hexagonPlayButton1);
        attachChild(hexagonPlayButton2);
    }

    private HexagonPlayButton createHexagon() {
        final HexagonPlayButton result = new HexagonPlayButton(400, 280);
        result.setColor(Color.GREEN);
        return result;
    }

    private LoopEntityModifier createRotationModifier(int rotationAngle) {
        return new LoopEntityModifier(
                new SequenceEntityModifier(
                        new RotationByModifier(1, rotationAngle),
                        new DelayModifier(0.02f)));
    }

    private void clear() {
        clearChildScene();
        clearEntityModifiers();
        clearTouchAreas();
        clearUpdateHandlers();
    }

    private void createBackground() {
        attachChild(new Sprite(ContextConstants.SCREEN_WIDTH / 2, ContextConstants.SCREEN_HEIGHT / 2, resourcesManager.getMenuBackgroundTextureRegion(), vertexBufferObjectManager));
    }

    private void createButtons() {
        menuScene = new MenuScene(camera);
        menuScene.setPosition(0, 0);

        final IMenuItem newGameItem = new ScaleMenuItemDecorator(new SpriteMenuItem(NEW_GAME, ResourcesManager.getInstance().getPlayButtonTextureRegion(), vertexBufferObjectManager), 1.2f, 1);
        final IMenuItem recordsItem = new ScaleMenuItemDecorator(new SpriteMenuItem(RECORDS, ResourcesManager.getInstance().getButtonHighScoreTextureRegion(), vertexBufferObjectManager), 1.2f, 1);

        menuScene.addMenuItem(newGameItem);
        menuScene.addMenuItem(recordsItem);

        menuScene.buildAnimations();
        menuScene.setBackgroundEnabled(false);

        newGameItem.setPosition(400, 280);
        recordsItem.setPosition(210, 327);

        menuScene.setOnMenuItemClickListener(this);

        setChildScene(menuScene);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (soundButton.isClicked()) {
            soundButton.changeSoundSettings();
            soundService.saveMusicSettings(soundButton.isSoundOn());
            soundButton.setClicked(false);
        }
    }
}
