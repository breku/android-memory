package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by brekol on 12.09.15.
 */
public class MemoryItem extends AnimatedSprite{

    public MemoryItem(int positionX, int positionY, ITiledTextureRegion pTiledTextureRegion) {
        super(positionX,positionY, pTiledTextureRegion, ResourcesManager.getInstance().getVertexBufferObjectManager());
    }


}
