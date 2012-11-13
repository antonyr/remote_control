package com.indix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InputParser {
    private Map<Input, String> inputMap;

    public InputParser() {
        inputMap = new HashMap<Input, String>();
    }

    public void addLine(Input inputType, String inputValue) {
        inputMap.put(inputType, inputValue);
    }

    public RemoteControl createRemoteControl() {
        return new RemoteControl(minimumChannelNumber(), maximumChannelNumber(),
                blockedChannels(), channelsToBeVisited());
    }

    private List<Integer> channelsToBeVisited() {
        String[] splitValues = inputMap.get(Input.CHANNELS_TO_VISIT).split(" ");
        Integer totalChannelsToBeVisited = Integer.valueOf(splitValues[0]);
        assert totalChannelsToBeVisited == splitValues.length - 1;
        List<Integer> channelsToBeVisited = new ArrayList<Integer>(totalChannelsToBeVisited);
        for (int i = 1; i < splitValues.length; i++)
            channelsToBeVisited.add(Integer.valueOf(splitValues[i]));
        return channelsToBeVisited;
    }

    private Set<Integer> blockedChannels() {
        Set<Integer> blockedChannels = new HashSet<Integer>();
        String[] splitValues = inputMap.get(Input.BLOCKED_CHANNELS).split(" ");
        assert Integer.valueOf(splitValues[0]) == splitValues.length - 1;
        for (int i = 1; i < splitValues.length; i++) {
            blockedChannels.add(Integer.valueOf(splitValues[i]));
        }
        return blockedChannels;
    }

    private Integer maximumChannelNumber() {
        String[] splitValues = parseMinMax();
        return Integer.valueOf(splitValues[1]);
    }

    private String[] parseMinMax() {
        String[] splitValues = inputMap.get(Input.MIN_MAX).split(" ");
        assert splitValues.length == 2;
        return splitValues;
    }

    private Integer minimumChannelNumber() {
        String[] splitValues = parseMinMax();
        return Integer.valueOf(splitValues[0]);
    }
}
