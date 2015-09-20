package com.thinkfaster.model.scene.menu;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.model.scene.BaseScene;
import com.thinkfaster.service.HighScoreService;
import com.thinkfaster.util.ContextConstants;
import com.thinkfaster.util.SceneType;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import java.util.List;

/**
 * User: Breku
 * Date: 06.10.13
 */
public class HighScoreScene extends BaseScene implements IOnSceneTouchListener {

    private HighScoreService highScoreService;

    /**
     * Constructor
     *
     * @param objects object[0] - Integer score
     *                object[1] - LevelDifficulty levelDifficulty
     *                object[2] - MathParameter mathParameter
     */
    public HighScoreScene(Object... objects) {
        super(objects);
    }

    @Override
    public void initializeServices() {
        highScoreService = new HighScoreService();
    }

    @Override
    public void createScene(Object... objects) {
        createBackground();
        createHighscoreTable();
        setOnSceneTouchListener(this);
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuScene(this);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.RECORDS;
    }

    @Override
    public void disposeScene() {
        ResourcesManager.getInstance().unloadRecordsTextures();
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionUp()) {
            SceneManager.getInstance().loadMenuScene(this);
        }
        return false;
    }

    private void createHighscoreTable() {
        final List<String> highscores = highScoreService.getHighscores();

        for (int i = 0; i < highscores.size(); i++) {
            final String highscoreText = String.format("%s", highscores.get(i));
            attachChild(createRow(i, highscoreText));
            attachChild(createLine(i));
        }
    }

    private Line createLine(int i) {
        int y = 340 - i * 50;
        final Line line = new Line(280, y, 510, y, vertexBufferObjectManager);
        line.setColor(Color.BLACK);
        return line;
    }

    private Text createRow(final int i, final String highscoreText) {
        return new Text(380, 360- i * 50, resourcesManager.getBlackFont(), highscoreText, 30, new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
    }

    private void createBackground() {
        attachChild(new Sprite(ContextConstants.SCREEN_WIDTH / 2, ContextConstants.SCREEN_HEIGHT / 2,
                ResourcesManager.getInstance().getRecordBackgroundTextureRegion(), vertexBufferObjectManager));
    }
}
