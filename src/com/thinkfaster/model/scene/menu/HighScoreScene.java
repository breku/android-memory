package com.thinkfaster.model.scene.menu;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.model.Level;
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
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        createTableHeaders();
        createTableBody();
    }

    private void createTableBody() {
        createTableColumnForLevel(Level.SMALL, 280);
        createTableColumnForLevel(Level.MEDIUM, 400);
        createTableColumnForLevel(Level.BIG, 520);
    }

    private void createTableColumnForLevel(final Level level, final int columnPosition) {
        final List<String> highscores = highScoreService.getHighscores(level);
        for (int i = 0; i < highscores.size(); i++) {
            final String highscoreText = String.format("%s", highscores.get(i));
            attachChild(createRow(columnPosition, i, highscoreText));
            attachChild(createLine(i));
        }
    }

    private Map<Level, List<String>> getHighscoreMap() {
        final Map<Level, List<String>> highscoresMap = new HashMap<>();
        for (final Level level : Level.values()) {
            highscoresMap.put(level, highScoreService.getHighscores(level));
        }
        return highscoresMap;
    }

    private void createTableHeaders() {
        attachChild(createGameTypeSprite(280, resourcesManager.getHighscoreSmallGameTypeTextureRegion()));
        attachChild(createGameTypeSprite(400, resourcesManager.getHighscoreMediumGameTypeTextureRegion()));
        attachChild(createGameTypeSprite(520, resourcesManager.getHighscoreBigGameTypeTextureRegion()));
    }

    private Sprite createGameTypeSprite(final int positionX, final ITextureRegion textureRegion) {
        final Sprite sprite = new Sprite(positionX, 350, textureRegion, resourcesManager.getVertexBufferObjectManager());
        sprite.setScale(0.7f);
        return sprite;
    }

    private Line createLine(int i) {
        int y = 260 - i * 50;
        final Line line = new Line(250, y, 540, y, vertexBufferObjectManager);
        line.setColor(Color.BLACK);
        return line;
    }

    private Text createRow(final int positionX, final int positionYIndex, final String highscoreText) {
        return new Text(positionX, 280 - positionYIndex * 50, resourcesManager.getBlackFont(), highscoreText, 30, new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
    }

    private void createBackground() {
        attachChild(new Sprite(ContextConstants.SCREEN_WIDTH / 2, ContextConstants.SCREEN_HEIGHT / 2,
                ResourcesManager.getInstance().getRecordBackgroundTextureRegion(), vertexBufferObjectManager));
    }
}
