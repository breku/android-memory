package com.thinkfaster.handler;

import android.util.Log;
import com.thinkfaster.model.Level;
import com.thinkfaster.model.MemoryConfiguration;
import com.thinkfaster.model.shape.MemoryPair;

import java.util.List;

/**
 * Created by brekol on 19.09.15.
 */
public class FinishGameUpdateHandler extends AbstractGameUpdateHandler {

    private static final String TAG = "FinishGameUpdateHandler";
    private final Level level;
    private final MemoryConfiguration memoryConfiguration;
    private final int numberOfPairsToFinish;

    public FinishGameUpdateHandler(List<MemoryPair> memoryPairs, Level level, MemoryConfiguration memoryConfiguration) {
        super(memoryPairs);
        this.level = level;
        this.memoryConfiguration = memoryConfiguration;
        numberOfPairsToFinish = (level.getNumberOfItemsX() * level.getNumberOfItemsY()) / 2;
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {

        if (getNumberOfFoundPairs() == numberOfPairsToFinish && !memoryConfiguration.isGameFinished()) {
            Log.i(TAG, ">> Game has beed finished");
            memoryConfiguration.setGameFinished(true);
        }
    }

    private int getNumberOfFoundPairs() {
        int result = 0;
        for (MemoryPair memoryPair : memoryPairs) {
            if (memoryPair.isFound()) {
                result++;
            }
        }
        return result;
    }
}
