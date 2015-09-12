package com.thinkfaster.model.scene.menu;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.matcher.ClassIEntityMatcher;
import com.thinkfaster.model.scene.BaseScene;
import com.thinkfaster.util.SceneType;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import static com.thinkfaster.util.ContextConstants.SCREEN_HEIGHT;
import static com.thinkfaster.util.ContextConstants.SCREEN_WIDTH;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class OptionsScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private final int USERNAME = 0;
    private MenuScene menuScene;


    @Override
    public void createScene(Object... objects) {
        createBackground();
        createLeftSideMenuLabels();
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuScene(this);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.OPTIONS;
    }

    @Override
    public void disposeScene() {
        ResourcesManager.getInstance().unloadOptionsTextures();
    }

    public void createUsernameInput(Context context) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final EditText input = new EditText(context);
    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {

        switch (pMenuItem.getID()) {
            case USERNAME:
                createUsernameInput();
                break;
            default:
                return false;
        }
        return false;
    }

    private void updateUsernameOnScreen(String username) {
        Text usernameText = (Text) getChildByMatcher(new ClassIEntityMatcher(Text.class));
        usernameText.setText(username);
    }


    private void createUsernameInput() {
        activity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        createUsernameInput(activity);
                    }
                }
        );
    }


    private void createBackground() {
        attachChild(new Sprite(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, resourcesManager.getOptionsBackgroundTextureRegion(), vertexBufferObjectManager));
        attachChild(new Sprite(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, resourcesManager.getOptionsTextureRegion(), vertexBufferObjectManager));
    }

    private void createLeftSideMenuLabels() {
        menuScene = new MenuScene(camera);
        menuScene.setPosition(0, 0);


        final IMenuItem usernameLabel = new ScaleMenuItemDecorator(new TextMenuItem(USERNAME, resourcesManager.getWhiteFont(), "Username", vertexBufferObjectManager), 1.2f, 1);

        menuScene.addMenuItem(usernameLabel);

        menuScene.buildAnimations();
        menuScene.setBackgroundEnabled(false);

        usernameLabel.setPosition(200, 350);

        menuScene.setOnMenuItemClickListener(this);
        setChildScene(menuScene);
    }
}
