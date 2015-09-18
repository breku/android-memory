package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.util.ContextConstants;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by brekol on 12.09.15.
 */
public class MemoryItem extends AnimatedSprite {

    private final AnimalId animalId;
    private boolean isClicked;

    public MemoryItem(AnimalId animalId, int positionX, int positionY, ITiledTextureRegion pTiledTextureRegion) {
        super(positionX, positionY, pTiledTextureRegion, ResourcesManager.getInstance().getVertexBufferObjectManager());
        this.animalId = animalId;
        initialize();
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        if (!isClicked) {
            switch (pSceneTouchEvent.getAction()) {
                case TouchEvent.ACTION_UP:
                    isClicked = true;
                    return true;
            }

        }
        return false;
    }

    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    @Override
    public String toString() {
        return String.format("MemoryItem: positionX=%f, positionY=%f,", getX(), getY());
    }

    public AnimalId getAnimalId() {
        return animalId;
    }

    public boolean isClicked() {
        return isClicked;
    }

    private void initialize() {
        setScale(ContextConstants.MEMORY_ITEM_SCALE);
        setCurrentTileIndex(animalId.getRight());
    }
}
