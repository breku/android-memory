package com.thinkfaster.service;

import com.thinkfaster.model.Level;
import com.thinkfaster.model.shape.AnimalId;
import com.thinkfaster.util.ContextConstants;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by brekol on 17.09.15.
 */
public class Randomizer {

    private Random random = new Random();

    public Set<AnimalId> getRandomAnimalIds(Level level) {
        final int totalNumberOfItems = (level.getNumberOfItemsX() * level.getNumberOfItemsY()) / 2;
        final Set<AnimalId> result = new HashSet<>();
        while (result.size() < totalNumberOfItems) {
            int animalId = random.nextInt(ContextConstants.NUMBER_OF_ANIMALS);
            int textureTileId = random.nextInt(ContextConstants.NUMBER_OF_ANIMALS_PER_IMAGE);
            result.add(new AnimalId(animalId, textureTileId));
        }
        return result;
    }

    public Set<AnimalId> getRandomAnimalPositions(Level level) {
        final Set<AnimalId> result = new HashSet<>();
        final int totalNumberOfPositions = (level.getNumberOfItemsX() * level.getNumberOfItemsY());

        while (result.size() < totalNumberOfPositions) {
            final int coordX = random.nextInt(level.getNumberOfItemsX());
            final int coordY = random.nextInt(level.getNumberOfItemsY());
            result.add(new AnimalId(coordX, coordY));
        }
        return result;
    }
}
