package com.brekol.model.shape;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * User: Breku
 * Date: 20.10.13
 */
public class GameButton extends Sprite {

    private boolean clicked = false;

    public GameButton(final float pX, final float pY, final ITextureRegion pTextureRegion, VertexBufferObjectManager vbo) {
        super(pX, pY, pTextureRegion, vbo);
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        switch (pSceneTouchEvent.getAction()) {
            case TouchEvent.ACTION_UP:
                clicked = true;
                return true;
        }
        return false;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void clearState() {
        clicked = false;
    }
}
