package com.thinkfaster.service;

import android.util.Log;
import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.model.Level;
import com.thinkfaster.model.shape.MemoryItem;
import com.thinkfaster.model.shape.MemoryPair;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.IBackground;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.util.adt.color.Color;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by brekol on 12.09.15.
 */
public class GameItemsProvider {

    private static final String TAG = "GameItemsProvider";

    private final ResourcesManager resourcesManager;
    private Randomizer randomizer = new Randomizer();

    public GameItemsProvider(ResourcesManager resources_manager) {
        this.resourcesManager = resources_manager;
    }


    public IBackground getBackground() {
        return new Background(Color.BLUE);
    }

    public List<MemoryPair> getMemoryPairs(Level level) {
        Log.i(TAG,">> Getting memory pairs for level=" + level.name());
        final List<MemoryPair> result = new ArrayList<>();

        final Set<Pair<Integer, Integer>> animalIds = randomizer.getRandomAnimalIds(level);
        final Set<Pair<Integer, Integer>> animalPositions = randomizer.getRandomAnimalPositions(level);
        final Iterator<Pair<Integer, Integer>> positionIterator = animalPositions.iterator();
        for (Pair<Integer, Integer> animalId : animalIds) {

            final Pair<Integer, Integer> position1 = positionIterator.next();
            final Pair<Integer, Integer> position2 = positionIterator.next();

            final MemoryItem memoryItem1 = createMemoryItem(animalId, level, position1);
            final MemoryItem memoryItem2 = createMemoryItem(animalId, level, position2);
            result.add(new MemoryPair(memoryItem1, memoryItem2));
        }
        Log.i(TAG,"<< Getting memory pairs finished with result=" + result);
        return result;
    }

    private MemoryItem createMemoryItem(Pair<Integer, Integer> animalId, Level level, Pair<Integer, Integer> position) {
        Log.d(TAG,String.format(">> Creating memory item with animalId=%s level=%s position=%s",animalId,level.name(),position));
        final ITiledTextureRegion animalTiledTexture = resourcesManager.getAnimalTiledTexture(animalId.getLeft());
        int positionX = calculatePositionX(position.getLeft(),level.getLevelOffsetX());
        int positionY = calculatePositionY(position.getRight());

        final MemoryItem memoryItem = new MemoryItem(positionX, positionY, animalTiledTexture);
        memoryItem.setScale(level.getItemScale());
        memoryItem.setCurrentTileIndex(animalId.getRight());
        Log.d(TAG,String.format("<< Memory item created=%s",memoryItem));
        return memoryItem;
    }

    private int calculatePositionX(int coordX, int levelOffsetX) {
        final int offsetX = 100;
        return offsetX + levelOffsetX + coordX * 120;
    }

    private int calculatePositionY(int coordY) {
        final int offsetY = 60;
        return offsetY + coordY * 110;
    }


}
