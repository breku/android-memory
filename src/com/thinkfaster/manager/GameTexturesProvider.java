package com.thinkfaster.manager;

import com.thinkfaster.model.Level;
import com.thinkfaster.model.shape.MemoryItem;
import com.thinkfaster.util.ContextConstants;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.IBackground;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by brekol on 12.09.15.
 */
public class GameTexturesProvider {

    private static final ResourcesManager RESOURCES_MANAGER = ResourcesManager.getInstance();
    private Random random = new Random();

    public IBackground getBackground() {
        return new Background(Color.GREEN);
    }

    public List<MemoryItem> getMemoryItems(Level level) {
        final List<MemoryItem> result = new ArrayList<>();
        final int itemOffsetX = 70;
        final int itemOffsetY = 70;
        for (int positionX = 0; positionX < level.getNumberOfItemsX(); positionX++) {
            for (int positionY = 0; positionY < level.getNumberOfItemsY(); positionY++) {
                final int x = level.getLevelOffsetX() + itemOffsetX + (int) (positionX * 310 * level.getItemScale());
                final int y = itemOffsetY + (int) (positionY * 250 * level.getItemScale());
                final MemoryItem memoryItem = createMemoryItem(x, y, level);
                result.add(memoryItem);
            }
        }
        return result;
    }

    private ITiledTextureRegion getAnimalTiledTexture(int animalID) {
        return RESOURCES_MANAGER.getAnimalTiledTextureRegionList().get(animalID);
    }

    private int getRandomAnimalNumber() {
        return random.nextInt(ContextConstants.NUMBER_OF_ANIMALS);
    }

    private MemoryItem createMemoryItem(int x, int y, Level level) {
        final ITiledTextureRegion animalTiledTexture = getAnimalTiledTexture(getRandomAnimalNumber());
        animalTiledTexture.setCurrentTileIndex(random.nextInt(ContextConstants.NUMBER_OF_ANIMALS_PER_IMAGE));
        final MemoryItem memoryItem = new MemoryItem(x, y, animalTiledTexture);
        memoryItem.setScale(level.getItemScale());
        return memoryItem;
    }


}
