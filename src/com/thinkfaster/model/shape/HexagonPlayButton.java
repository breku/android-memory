package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by brekol on 20.09.15.
 */
public class HexagonPlayButton extends Sprite {

    public HexagonPlayButton(float pX, float pY) {
        super(pX, pY, ResourcesManager.getInstance().getHexagonTextureRegion(), ResourcesManager.getInstance().getVertexBufferObjectManager());
    }
}
