package com.thinkfaster.model;

/**
 * Created by brekol on 12.09.15.
 */
public enum Level {
    SMALL(0, 3, 3, 0.5f),
    MEDIUM(1, 4, 4, 0.4f),
    BIG(2, 8, 4, 0.3f);

    private final int gameTypeId;
    private final int numberOfItemsX;
    private final int numberOfItemsY;
    private final float itemScale;

    Level(int gameTypeId, int numberOfItemsX, int numberOfItemsY, float itemScale) {
        this.gameTypeId = gameTypeId;
        this.numberOfItemsX = numberOfItemsX;
        this.numberOfItemsY = numberOfItemsY;
        this.itemScale = itemScale;
    }

    public float getItemScale() {
        return itemScale;
    }

    public int getNumberOfItemsX() {
        return numberOfItemsX;
    }

    public int getNumberOfItemsY() {
        return numberOfItemsY;
    }

    public int getGameTypeId() {
        return gameTypeId;
    }

    public static Level getLevel(int gameTypeId) {
        for (Level level : values()) {
            if (level.getGameTypeId() == gameTypeId) {
                return level;
            }
        }
        throw new IllegalArgumentException("Wrong gameTypeId");
    }
}
