package com.thinkfaster.handler;

import android.util.Log;
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

    private static final String TAG = "HideClickedItemsUpdateHandler";
    private final Engine engine;
    private boolean delayHandlerRegistered;

    public HideClickedItemsUpdateHandler(List<MemoryPair> memoryPairs, Engine engine) {
        super(memoryPairs);
        this.engine = engine;
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {
        if (getNumberOfVisibleActiveItems() > 1 && !delayHandlerRegistered) {
            delayHandlerRegistered = true;
            engine.registerUpdateHandler(new TimerHandler(MEMORY_ITEM_SHOWTIME, new ITimerCallback() {
                @Override
                public void onTimePassed(TimerHandler pTimerHandler) {
                    Log.i(TAG, ">> Hiding items");
                    for (MemoryPair memoryPair : memoryPairs) {
                        if (!memoryPair.isFound()) {
                            memoryPair.getItem1().setClicked(false);
                            memoryPair.getItem2().setClicked(false);
                            memoryPair.getItem1().setItemVisible(false);
                            memoryPair.getItem2().setItemVisible(false);
                        }
                    }
                    delayHandlerRegistered = false;
                    Log.i(TAG, "<< Hiding items finished");
                }
            }));
        }


    }

}
