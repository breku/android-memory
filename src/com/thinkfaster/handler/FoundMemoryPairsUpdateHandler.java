package com.thinkfaster.handler;

import com.thinkfaster.model.shape.MemoryPair;
import org.andengine.engine.handler.IUpdateHandler;

import java.util.List;

/**
 * Created by brekol on 19.09.15.
 */
public class FoundMemoryPairsUpdateHandler extends AbstractGameUpdateHandler {


    public FoundMemoryPairsUpdateHandler(List<MemoryPair> memoryPairs) {
        super(memoryPairs);
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {
        for (MemoryPair memoryPair : memoryPairs) {
            if (memoryPair.twoItemsClicked()) {
                memoryPair.setFound(true);
            }
        }
    }

    @Override
    public void reset() {

    }
}
