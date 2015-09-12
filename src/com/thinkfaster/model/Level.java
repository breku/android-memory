package com.thinkfaster.model;

/**
 * Created by brekol on 12.09.15.
 */
public enum Level {
    SMALL(0),
    MEDIUM(1),
    BIG(2);
    private final int gameTypeId;

    Level(int gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public int getGameTypeId() {
        return gameTypeId;
    }

    public static Level getLevel(int gameTypeId){
        for (Level level : values()) {
            if(level.getGameTypeId() == gameTypeId){
                return level;
            }
        }
        throw new IllegalArgumentException("Wrong gameTypeId");
    }
}
