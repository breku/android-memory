package com.thinkfaster.handler;

import com.thinkfaster.model.shape.MemoryPair;

import java.util.List;

/**
 * Created by brekol on 19.09.15.
 */
public class ShowClickedItemsUpdateHandler extends AbstractGameUpdateHandler {

    public ShowClickedItemsUpdateHandler(List<MemoryPair> memoryPairs) {
        super(memoryPairs);
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {

        for (MemoryPair memoryPair : memoryPairs) {
            if (getNumberOfClickedItems() < 3) {
                if (memoryPair.getItem1().isClicked()) {
                    memoryPair.getItem1().setZIndex(10);
                }
                if (memoryPair.getItem2().isClicked()) {
                    memoryPair.getItem2().setZIndex(10);
                }
            }
        }
    }

    @Override
    public void reset() {

    }
}
