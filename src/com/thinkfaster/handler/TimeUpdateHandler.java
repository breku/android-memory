package com.thinkfaster.handler;

import com.thinkfaster.model.shape.MemoryPair;
import org.andengine.entity.text.Text;

import java.util.List;

/**
 * Created by brekol on 19.09.15.
 */
public class TimeUpdateHandler extends AbstractGameUpdateHandler {

    private final Text timerText;

    private int firstRunCounter = 0;
    private long startTime;

    public TimeUpdateHandler(List<MemoryPair> memoryPairs, Text timerText) {
        super(memoryPairs);
        this.timerText = timerText;
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {
        if (firstRunCounter++ == 1) {
            startTime = System.currentTimeMillis();
        }
        updateTime();
    }

    private void updateTime() {
        long elapsedTime = (System.currentTimeMillis() - startTime) / 10;
        timerText.setText(elapsedTime / 100 + "." + (elapsedTime % 100) / 10);
    }
}
