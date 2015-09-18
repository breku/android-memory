package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.util.ContextConstants;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

/**
 * Created by brekol on 18.09.15.
 */
public class QuestionMarkItem extends Sprite {

    private final AnimalId animalId;
    private boolean isClicked;
    private boolean animalFound;

    public QuestionMarkItem(AnimalId animalId, float pX, float pY) {
        super(pX, pY, ResourcesManager.getInstance().getQuestionMarkGameTextureRegion(), ResourcesManager.getInstance().getVertexBufferObjectManager());
        this.animalId = animalId;
        setScale(ContextConstants.MEMORY_ITEM_SCALE);
    }

    public void resetClick() {
        isClicked = false;
    }

    public boolean isClicked() {
        return isClicked;
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

    public boolean isAnimalFound() {
        return animalFound;
    }

    public void setAnimalFound(boolean animalFound) {
        this.animalFound = animalFound;
    }

    public AnimalId getAnimalId() {
        return animalId;
    }
}
