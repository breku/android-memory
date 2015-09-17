package com.thinkfaster.service;

import com.thinkfaster.model.Level;
import com.thinkfaster.util.ContextConstants;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by brekol on 17.09.15.
 */
public class Randomizer {

    private Random random = new Random();

    public Set<Pair<Integer, Integer>> getRandomAnimalIds(Level level) {
        final int totalNumberOfItems = (level.getNumberOfItemsX() * level.getNumberOfItemsY()) / 2;
        final Set<Pair<Integer, Integer>> result = new HashSet<>();
        while (result.size() < totalNumberOfItems) {
            int animalId = random.nextInt(ContextConstants.NUMBER_OF_ANIMALS);
            int textureTileId = random.nextInt(ContextConstants.NUMBER_OF_ANIMALS_PER_IMAGE);
            result.add(new ImmutablePair<>(animalId, textureTileId));
        }
        return result;
    }

    public Set<Pair<Integer, Integer>> getRandomAnimalPositions(Level level) {
        final Set<Pair<Integer, Integer>> result = new HashSet<>();
        final int totalNumberOfPositions = (level.getNumberOfItemsX() * level.getNumberOfItemsY());

        while (result.size() < totalNumberOfPositions) {
            final int coordX = random.nextInt(level.getNumberOfItemsX());
            final int coordY = random.nextInt(level.getNumberOfItemsY());
            result.add(new ImmutablePair<>(coordX, coordY));
        }
        return result;


    }
}
