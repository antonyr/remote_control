package com.indix;


import com.indix.utils.MathUtils;

import java.util.List;
import java.util.Set;

public class RemoteControl {
    public static final int MAX_POSSIBLE_LENGTH = 5;
    private final Integer minChannel;
    private final Integer maxChannel;
    private Set<Integer> blockedChannels;
    private List<Integer> channelsToBeVisited;

    public RemoteControl(Integer minChannel, Integer maxChannel,
                         Set<Integer> blockedChannels, List<Integer> channelsToBeVisited) {
        this.minChannel = minChannel;
        this.maxChannel = maxChannel;
        this.blockedChannels = blockedChannels;
        this.channelsToBeVisited = channelsToBeVisited;
    }

    public Integer getMinimumChannel() {
        return minChannel;
    }

    public Integer getMaximumChannel() {
        return maxChannel;
    }

    public Set<Integer> getBlockedChannels() {
        return blockedChannels;
    }

    public List<Integer> getChannelsToBeVisited() {
        return channelsToBeVisited;
    }

    protected Integer up(Integer fromChannel) {
        if (fromChannel.equals(maxChannel))
            return minChannel;
        else if (blockedChannels.contains(++fromChannel))
            return up(fromChannel);
        return fromChannel;
    }

    protected Integer down(Integer fromChannel) {
        if (fromChannel.equals(minChannel))
            return maxChannel;
        else if (blockedChannels.contains(--fromChannel))
            return down(fromChannel);
        return fromChannel;
    }

    public int calculateMinimumClicksRequired() {
        int clickCount = 0;
        for (int i = 0; i < channelsToBeVisited.size(); i++) {
            if (i == 0) {
                clickCount += length(channelsToBeVisited.get(i));
                continue;
            }
            int backChannelNumber = isBackChannelFound(i) ? 0 : channelsToBeVisited.get(i - 2);
            clickCount += numberOfClicksRequired(backChannelNumber, channelsToBeVisited.get(i - 1), channelsToBeVisited.get(i));
        }
        return clickCount;
    }

    protected int numberOfClicksRequired(Integer backChannelNumber, Integer fromChannel, Integer toChannel) {
        int backwardClicks = calculateBackwardClicks(fromChannel, toChannel);
        int backThroughClicks = calculateBackThroughClicks(backChannelNumber, toChannel);
        int forwardClicks = calculateForwardClicks(fromChannel, toChannel);
        return MathUtils.min(backwardClicks, forwardClicks, backThroughClicks, length(toChannel));
    }

    private boolean isBackChannelFound(int i) {
        return i < 2;
    }

    protected int calculateForwardClicks(Integer fromChannel, Integer toChannel) {
        int count = 0;
        for (int i = 0; i <= MAX_POSSIBLE_LENGTH; i++) {
            if (fromChannel.equals(toChannel)) return count;
            fromChannel = up(fromChannel);
            ++count;
        }
        return count;
    }

    protected int calculateBackThroughClicks(Integer backChannelNumber, Integer toChannel) {
        return calculateForwardClicks(backChannelNumber, toChannel) + 1;
    }

    protected int calculateBackwardClicks(Integer fromChannel, Integer toChannel) {
        int count = 0;
        for (int i = MAX_POSSIBLE_LENGTH; i >= 0; i--) {
            if (fromChannel.equals(toChannel)) return count;
            count++;
            fromChannel = down(fromChannel);
        }
        return count;
    }

    private Integer length(Integer channelNumber) {
        return String.valueOf(channelNumber).length();
    }
}
