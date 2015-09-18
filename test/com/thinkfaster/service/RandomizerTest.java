package com.thinkfaster.service;

import com.thinkfaster.model.Level;
import com.thinkfaster.model.shape.AnimalId;
import com.thinkfaster.util.ContextConstants;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

public class RandomizerTest {

    private Randomizer uut;

    @Before
    public void init() {
        uut = new Randomizer();
    }

    @Test
    public void shouldHaveCorrectSizeForSmallLevel() {
        // when
        final Set<AnimalId> animalIds = uut.getRandomAnimalIds(Level.SMALL);

        // then
        assertThat(animalIds).hasSize(8);
    }

    @Test
    public void shouldHaveCorrectSizeForMediumLevel() {
        // when
        final Set<AnimalId> animalIds = uut.getRandomAnimalIds(Level.MEDIUM);

        // then
        assertThat(animalIds).hasSize(10);
    }

    @Test
    public void shouldHaveCorrectSizeForBigLevel() {
        // when
        final Set<AnimalId> animalIds = uut.getRandomAnimalIds(Level.BIG);

        // then
        assertThat(animalIds).hasSize(12);
    }

    @Test
    public void animalIdsShouldBeLowerThanTotalAnimalNumber() {
        // when
        final Set<AnimalId> animalIds = uut.getRandomAnimalIds(Level.BIG);

        // then
        for (AnimalId animalId : animalIds) {
            assertThat(animalId.getLeft()).isLessThan(ContextConstants.NUMBER_OF_ANIMALS);
        }
    }

    @Test
    public void tileIdsShouldBeLowerThanTotalTilesPerAnimal() {
        // when
        final Set<AnimalId> animalIds = uut.getRandomAnimalIds(Level.BIG);

        // then
        for (AnimalId animalId : animalIds) {
            assertThat(animalId.getRight()).isLessThan(ContextConstants.NUMBER_OF_ANIMALS_PER_IMAGE);
        }
    }

    @Test
    public void shouldGenerateCorrrectAnimalPositionsForSmallLevel() {
        // given

        // when
        final Set<AnimalId> animalPositions = uut.getRandomAnimalPositions(Level.SMALL);

        // then
        assertThat(animalPositions).hasSize(16);
        assertThat(animalPositions).contains(
                new ImmutablePair<>(0, 0), new ImmutablePair<>(0, 1), new ImmutablePair<>(0, 2), new ImmutablePair<>(0, 3),
                new ImmutablePair<>(1, 0), new ImmutablePair<>(1, 1), new ImmutablePair<>(1, 2), new ImmutablePair<>(1, 3),
                new ImmutablePair<>(2, 0), new ImmutablePair<>(2, 1), new ImmutablePair<>(2, 2), new ImmutablePair<>(2, 3),
                new ImmutablePair<>(3, 0), new ImmutablePair<>(3, 1), new ImmutablePair<>(3, 2), new ImmutablePair<>(3, 3));
    }

    @Test
    public void shouldGenerateCorrrectAnimalPositionsForMediumLevel() {
        // given

        // when
        final Set<AnimalId> animalPositions = uut.getRandomAnimalPositions(Level.MEDIUM);

        // then
        assertThat(animalPositions).hasSize(20);
        assertThat(animalPositions).contains(
                new ImmutablePair<>(0, 0), new ImmutablePair<>(0, 1), new ImmutablePair<>(0, 2), new ImmutablePair<>(0, 3),
                new ImmutablePair<>(1, 0), new ImmutablePair<>(1, 1), new ImmutablePair<>(1, 2), new ImmutablePair<>(1, 3),
                new ImmutablePair<>(2, 0), new ImmutablePair<>(2, 1), new ImmutablePair<>(2, 2), new ImmutablePair<>(2, 3),
                new ImmutablePair<>(3, 0), new ImmutablePair<>(3, 1), new ImmutablePair<>(3, 2), new ImmutablePair<>(3, 3),
                new ImmutablePair<>(4, 0), new ImmutablePair<>(4, 1), new ImmutablePair<>(4, 2), new ImmutablePair<>(4, 3));
    }

    @Test
    public void shouldGenerateCorrrectAnimalPositionsForBigLevel() {
        // given

        // when
        final Set<AnimalId> animalPositions = uut.getRandomAnimalPositions(Level.BIG);

        // then
        assertThat(animalPositions).hasSize(24);
        assertThat(animalPositions).contains(
                new ImmutablePair<>(0, 0), new ImmutablePair<>(0, 1), new ImmutablePair<>(0, 2), new ImmutablePair<>(0, 3),
                new ImmutablePair<>(1, 0), new ImmutablePair<>(1, 1), new ImmutablePair<>(1, 2), new ImmutablePair<>(1, 3),
                new ImmutablePair<>(2, 0), new ImmutablePair<>(2, 1), new ImmutablePair<>(2, 2), new ImmutablePair<>(2, 3),
                new ImmutablePair<>(3, 0), new ImmutablePair<>(3, 1), new ImmutablePair<>(3, 2), new ImmutablePair<>(3, 3),
                new ImmutablePair<>(4, 0), new ImmutablePair<>(4, 1), new ImmutablePair<>(4, 2), new ImmutablePair<>(4, 3),
                new ImmutablePair<>(5, 0), new ImmutablePair<>(5, 1), new ImmutablePair<>(5, 2), new ImmutablePair<>(5, 3));
    }


}