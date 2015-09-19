package com.thinkfaster.handler;

import com.thinkfaster.model.shape.MemoryPair;
import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;

import java.util.List;

import static com.thinkfaster.util.ContextConstants.MEMORY_ITEM_SHOWTIME;

/**
 * Created by brekol on 19.09.15.
 */
public class HideClickedItemsUpdateHandler extends AbstractGameUpdateHandler {

    private final Engine engine;

    public HideClickedItemsUpdateHandler(List<MemoryPair> memoryPairs, Engine engine) {
        super(memoryPairs);
        this.engine = engine;
    }

    private boolean delayHandlerRegistered;

    @Override
    public void onUpdate(float pSecondsElapsed) {
        if(getNumberOfClickedItems() > 1 && !delayHandlerRegistered ){
            delayHandlerRegistered = true;
            engine.registerUpdateHandler(new TimerHandler(MEMORY_ITEM_SHOWTIME, new ITimerCallback() {
                @Override
                public void onTimePassed(TimerHandler pTimerHandler) {
                    for (MemoryPair memoryPair : memoryPairs) {
                        if (!memoryPair.isFound()) {
                            memoryPair.getItem1().setClicked(false);
                            memoryPair.getItem2().setClicked(false);
                            memoryPair.getItem1().setZIndex(0);
                            memoryPair.getItem2().setZIndex(0);
                        }
                    }
                    delayHandlerRegistered = false;
                }
            }));
        }


    }

    @Override
    public void reset() {

    }
}
