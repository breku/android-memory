package com.thinkfaster.handler;

import android.util.Log;
import com.thinkfaster.model.shape.MemoryItem;
import com.thinkfaster.model.shape.MemoryPair;

import java.util.List;

/**
 * Changes visibility of clicked items
 */
public class ShowClickedItemsUpdateHandler extends AbstractGameUpdateHandler {

    private static final String TAG = "ShowClickedItemsUpdateHandler";

    public ShowClickedItemsUpdateHandler(List<MemoryPair> memoryPairs) {
        super(memoryPairs);
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {

        for (MemoryPair memoryPair : memoryPairs) {

            if (canShowItem(memoryPair.getItem1())) {
                showItem(memoryPair.getItem1());
            }
            if (canShowItem(memoryPair.getItem2())) {
                showItem(memoryPair.getItem2());
            }
        }
    }


    private boolean canShowItem(MemoryItem memoryItem) {
        return getNumberOfVisibleActiveItems() < 2 && memoryItem.isClicked() && !memoryItem.isItemVisible();
    }

    private void showItem(MemoryItem memoryItem) {
        Log.i(TAG, ">> Showing item " + memoryItem);
        memoryItem.setItemVisible(true);
    }
}
