package com.thinkfaster.service;

/**
 * Created by brekol on 20.09.15.
 */
public class SoundService {

    private static final String MUSIC_KEY = "MUSIC_KEY";
    private DatabaseService databaseService = new DatabaseService();

    public boolean isMusicOn() {
        return databaseService.getBoolean(MUSIC_KEY, true);
    }

    public void saveMusicSettings(boolean musicOn) {
        databaseService.putBoolean(MUSIC_KEY, musicOn);
    }
}
