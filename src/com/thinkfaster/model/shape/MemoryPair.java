package com.thinkfaster.model.shape;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by brekol on 14.09.15.
 */
public class MemoryPair {

    private final MemoryItem item1;
    private final MemoryItem item2;

    public MemoryPair(MemoryItem item1, MemoryItem item2) {
        this.item1 = item1;
        this.item2 = item2;
    }


    public MemoryItem getItem1() {
        return item1;
    }

    public MemoryItem getItem2() {
        return item2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("item1", item1)
                .append("item2", item2)
                .toString();
    }
}
