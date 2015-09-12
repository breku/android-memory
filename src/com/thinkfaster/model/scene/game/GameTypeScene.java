package com.thinkfaster.model.scene.game;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.model.scene.BaseScene;
import com.thinkfaster.util.ContextConstants;
import com.thinkfaster.util.SceneType;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;

/**
 * User: Breku
 * Date: 08.10.13
 */
public class GameTypeScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private MenuScene menuScene;

    public GameTypeScene() {
        super();
    }

    private static final int SMALL_SIZE = 1;
    private static final int MEDIUM_SIZE = 2;
    private static final int BIG_SIZE = 3;

    @Override
    public void createScene(Object... objects) {
        createBackground();
        createButtons();

    }


    private void createBackground() {
        attachChild(new Sprite(ContextConstants.SCREEN_WIDTH / 2, ContextConstants.SCREEN_HEIGHT / 2, resourcesManager.getBackgroundGameTypeTextureRegion(), vertexBufferObjectManager));
    }

    private void createButtons() {
        menuScene = new MenuScene(camera);
        menuScene.setPosition(0, 0);
        menuScene.setBackgroundEnabled(false);
        menuScene.buildAnimations();


        final IMenuItem smallSizeGame = new ScaleMenuItemDecorator(new SpriteMenuItem(SMALL_SIZE, ResourcesManager.getInstance().getButtonNewGameTextureRegion(), vertexBufferObjectManager), 1.2f, 1);
        final IMenuItem mediumSizeGame = new ScaleMenuItemDecorator(new SpriteMenuItem(MEDIUM_SIZE, ResourcesManager.getInstance().getButtonAboutTextureRegion(), vertexBufferObjectManager), 1.2f, 1);
        final IMenuItem bigSizeGame = new ScaleMenuItemDecorator(new SpriteMenuItem(BIG_SIZE, ResourcesManager.getInstance().getButtonExitTextureRegion(), vertexBufferObjectManager), 1.2f, 1);


        menuScene.addMenuItem(smallSizeGame);
        menuScene.addMenuItem(mediumSizeGame);
        menuScene.addMenuItem(bigSizeGame);

        menuScene.buildAnimations();
        menuScene.setBackgroundEnabled(false);

        smallSizeGame.setPosition(210, 397);
        mediumSizeGame.setPosition(210, 257);
        bigSizeGame.setPosition(210, 187);











        menuScene.setOnMenuItemClickListener(this);

        setChildScene(menuScene);


    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuScene(this);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.GAMETYPE;
    }

    @Override
    public void disposeScene() {
        ResourcesManager.getInstance().unloadGameTypeTextures();
    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {

        return false;

    }
}
