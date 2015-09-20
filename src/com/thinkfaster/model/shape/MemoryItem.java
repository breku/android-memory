package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.util.ContextConstants;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by brekol on 12.09.15.
 */
public class MemoryItem extends AnimatedSprite {

    private final AnimalId animalId;
    private boolean isClicked;
    private boolean itemVisible;

    public MemoryItem(AnimalId animalId, int positionX, int positionY) {
        super(positionX, positionY, ResourcesManager.getInstance().getAnimalTiledTexture(animalId.getTextureId()), ResourcesManager.getInstance().getVertexBufferObjectManager());
        this.animalId = animalId;
        initialize();
    }

    public boolean isItemVisible() {
        return itemVisible;
    }

    public void setItemVisible(boolean itemVisible) {
        this.itemVisible = itemVisible;
        setZIndex(itemVisible ? 10 : 0);
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("animalId", animalId)
                .append("positionX", getX())
                .append("positionY", getX())
                .toString();
    }

    public AnimalId getAnimalId() {
        return animalId;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    private void initialize() {
        setScale(ContextConstants.MEMORY_ITEM_SCALE);
        setCurrentTileIndex(animalId.getTileId());
    }
}
