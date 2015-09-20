package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import org.andengine.audio.sound.Sound;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

/**
 * Created by brekol on 20.09.15.
 */
public class SoundButton extends AnimatedSprite {

    private static final int MUTE_TILE_INTEX = 0;
    private static final int SOUND_ON_TILE_INTEX = 1;
    private final Sound clickSound;
    private boolean clicked;

    public SoundButton(float pX, float pY) {
        super(pX, pY, ResourcesManager.getInstance().getSoundButtonsTiledTextureRegion(), ResourcesManager.getInstance().getVertexBufferObjectManager());
        setScale(0.5f);
        setColor(new Color(0.51f, 0.51f, 0.51f));
        clickSound = ResourcesManager.getInstance().getStartGameSound();
    }

    public void playSound() {
        clickSound.play();
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        if (!clicked) {
            switch (pSceneTouchEvent.getAction()) {
                case TouchEvent.ACTION_UP:
                    clicked = true;
                    return true;
            }
        }
        return false;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void changeSoundSettings() {
        final int newTileIndex = (getCurrentTileIndex() + 1) % 2;
        setCurrentTileIndex(newTileIndex);
    }

    public void setMusicOn() {
        setCurrentTileIndex(SOUND_ON_TILE_INTEX);
    }

    public boolean isSoundOn() {
        return getCurrentTileIndex() == SOUND_ON_TILE_INTEX;
    }

    public void setMusicOff() {
        setCurrentTileIndex(MUTE_TILE_INTEX);
    }
}
