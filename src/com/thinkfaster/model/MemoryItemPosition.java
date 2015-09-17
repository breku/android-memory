package com.thinkfaster.model;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Created by brekol on 17.09.15.
 */
public class MemoryItemPosition implements Comparable<MemoryItemPosition> {

    private int positionX;
    private int positionY;

    public MemoryItemPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    @Override
    public int compareTo(MemoryItemPosition memoryItemPosition) {
        return new CompareToBuilder().append(this.positionX, memoryItemPosition.getPositionX()).append(this.positionY, memoryItemPosition.getPositionY()).build();
    }
}
