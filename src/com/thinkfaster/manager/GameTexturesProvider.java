package com.thinkfaster.manager;

import com.thinkfaster.model.Level;
import com.thinkfaster.model.shape.MemoryItem;
import com.thinkfaster.util.ContextConstants;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by brekol on 12.09.15.
 */
public class GameTexturesProvider {

    private static final ResourcesManager RESOURCES_MANAGER = ResourcesManager.getInstance();

    private ITiledTextureRegion getAnimalTiledTexture(int animalID) {
        return RESOURCES_MANAGER.getAnimalTiledTextureRegionList().get(animalID);        
    }

    private Random random = new Random();

    private int getRandomAnimalNumber(){
        return random.nextInt(ContextConstants.NUMBER_OF_ANIMALS);
    }

    public List<MemoryItem> getMemoryItems(Level level){
        final List<MemoryItem> result = new ArrayList<>();
        final int offsetX = 100;
        final int offsetY = 50;
        for (int positionX = 0; positionX < level.getNumberOfItemsX(); positionX++) {
            for (int positionY = 0; positionY < level.getNumberOfItemsY(); positionY++) {
                final int x = offsetX + (int)(positionX * 200 * level.getItemScale());
                final int y = offsetY + (int)(positionY * 300 * level.getItemScale());
                final MemoryItem memoryItem = createMemoryItem(x, y, level);
                result.add(memoryItem);
            }
        }
        return result;
    }

    private MemoryItem createMemoryItem(int x, int y, Level level) {
        final ITiledTextureRegion animalTiledTexture = getAnimalTiledTexture(getRandomAnimalNumber());
        animalTiledTexture.setCurrentTileIndex(random.nextInt(ContextConstants.NUMBER_OF_ANIMALS_PER_IMAGE));
        final MemoryItem memoryItem = new MemoryItem(x, y, animalTiledTexture);
        memoryItem.setScale(level.getItemScale());
        return memoryItem;
    }


}
