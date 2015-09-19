package com.thinkfaster.handler;

import com.thinkfaster.model.shape.MemoryPair;
import org.andengine.engine.handler.IUpdateHandler;

import java.util.List;

/**
 * Created by brekol on 19.09.15.
 */
public abstract class AbstractGameUpdateHandler implements IUpdateHandler {

    protected final List<MemoryPair> memoryPairs;
    public AbstractGameUpdateHandler(List<MemoryPair> memoryPairs) {
        this.memoryPairs = memoryPairs;
    }

    protected int getNumberOfClickedItems() {
        int numberOfItemsClicked = 0;
        for (MemoryPair memoryPair : memoryPairs) {
            if (!memoryPair.isFound()) {
                if (memoryPair.getItem1().isClicked()) {
                    numberOfItemsClicked++;
                }
                if(memoryPair.getItem2().isClicked()){
                    numberOfItemsClicked++;
                }
            }
        }
        return numberOfItemsClicked;
    }
}
