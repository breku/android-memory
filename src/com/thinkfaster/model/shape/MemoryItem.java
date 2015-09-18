package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

/**
 * Created by brekol on 12.09.15.
 */
public class MemoryItem extends AnimatedSprite {

    public MemoryItem(int positionX, int positionY, ITiledTextureRegion pTiledTextureRegion) {
        super(positionX, positionY, pTiledTextureRegion, ResourcesManager.getInstance().getVertexBufferObjectManager());
    }

    @Override
    public String toString() {
        return String.format("MemoryItem: positionX=%f, positionY=%f,", getX(), getY());
    }
}
