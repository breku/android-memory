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

    @Override
    public void reset() {
        // intentionally left blank
    }

    /**
     * Without already found items
     *
     * @return
     */
    protected int getNumberOfVisibleActiveItems() {
        int result = 0;
        for (MemoryPair memoryPair : memoryPairs) {
            if (!memoryPair.isFound()) {
                if (memoryPair.getItem1().isItemVisible()) {
                    result++;
                }
                if (memoryPair.getItem2().isItemVisible()) {
                    result++;
                }
            }
        }
        return result;
    }
}
