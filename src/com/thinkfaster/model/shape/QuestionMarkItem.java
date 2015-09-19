package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.util.ContextConstants;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by brekol on 18.09.15.
 */
public class QuestionMarkItem extends Sprite {

    private final AnimalId animalId;

    public QuestionMarkItem(AnimalId animalId, float pX, float pY) {
        super(pX, pY, ResourcesManager.getInstance().getQuestionMarkGameTextureRegion(), ResourcesManager.getInstance().getVertexBufferObjectManager());
        this.animalId = animalId;
        setScale(ContextConstants.MEMORY_ITEM_SCALE);
    }
}
