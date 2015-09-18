package com.thinkfaster.model.shape;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by brekol on 18.09.15.
 */
public class AnimalId extends MutablePair<Integer, Integer> {


    private static final String ID_FORMAT = "%s-%s";

    public AnimalId(int textureId, int tileId) {
        super(textureId, tileId);
    }

    public int getTextureId() {
        return super.getLeft();
    }

    public int getTileId() {
        return super.getRight();
    }

    public String getId() {
        return String.format(ID_FORMAT, getTextureId(), getTileId());
    }

    @Override
    public int compareTo(Pair<Integer, Integer> other) {
        if (other instanceof AnimalId) {
            final AnimalId otherAnimalId = (AnimalId) other;
            return new CompareToBuilder().append(this.getId(), otherAnimalId.getId()).build();
        }
        return super.compareTo(other);
    }
}
