package com.thinkfaster.model;

/**
 * Created by brekol on 12.09.15.
 */
public enum Level {
    SMALL(0, 4, 4, 150, 0.42f),
    MEDIUM(1, 5, 4, 80, 0.42f),
    BIG(2, 6, 4, 0, 0.42f);

    private final int gameTypeId;
    private final int numberOfItemsX;
    private final int numberOfItemsY;
    private final int levelOffsetX;
    private final float itemScale;

    Level(int gameTypeId, int numberOfItemsX, int numberOfItemsY, int levelOffsetX, float itemScale) {
        this.gameTypeId = gameTypeId;
        this.numberOfItemsX = numberOfItemsX;
        this.numberOfItemsY = numberOfItemsY;
        this.levelOffsetX = levelOffsetX;
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

    public int getLevelOffsetX() {
        return levelOffsetX;
    }
}
