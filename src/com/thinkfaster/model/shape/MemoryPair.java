package com.thinkfaster.model.shape;

import org.andengine.audio.sound.Sound;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by brekol on 14.09.15.
 */
public class MemoryPair {

    private final MemoryItem item1;
    private final MemoryItem item2;
    private final Sound sound;
    private boolean found;

    public MemoryPair(MemoryItem item1, MemoryItem item2, Sound sound) {
        this.item1 = item1;
        this.item2 = item2;
        this.sound = sound;
    }

    public boolean twoItemsVisible() {
        return item1.isItemVisible() && item2.isItemVisible();
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public MemoryItem getItem1() {
        return item1;
    }

    public MemoryItem getItem2() {
        return item2;
    }

    public void playSound() {
        sound.play();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("item1", item1)
                .append("item2", item2)
                .toString();
    }
}
