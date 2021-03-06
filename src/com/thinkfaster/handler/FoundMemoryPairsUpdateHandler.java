package com.thinkfaster.handler;

import android.util.Log;
import com.thinkfaster.model.shape.MemoryPair;
import com.thinkfaster.service.SoundService;

import java.util.List;

/**
 * Mark items as found if they make a match
 */
public class FoundMemoryPairsUpdateHandler extends AbstractGameUpdateHandler {

    private static final String TAG = "FoundMemoryPairsUpdateHandler";

    private SoundService soundService = new SoundService();

    public FoundMemoryPairsUpdateHandler(List<MemoryPair> memoryPairs) {
        super(memoryPairs);
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {
        for (MemoryPair memoryPair : memoryPairs) {
            if (!memoryPair.isFound() && memoryPair.twoItemsVisible()) {
                Log.i(TAG, ">> Pair found: " + memoryPair);
                memoryPair.setFound(true);
                if (soundService.isMusicOn()) {
                    memoryPair.playSound();
                }
            }
        }
    }
}
