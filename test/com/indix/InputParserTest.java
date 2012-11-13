package com.indix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class InputParserTest {
    @Test
    public void shouldCalculateMaxAndMinChannelNumber() {
        InputParser parser = new InputParser();
        parser.addLine(Input.MIN_MAX, "1 20");
        parser.addLine(Input.BLOCKED_CHANNELS, "2 18 19");
        parser.addLine(Input.CHANNELS_TO_VISIT, "5 15 14 17 1 17");
        RemoteControl remoteControl = parser.createRemoteControl();
        assertEquals(1, remoteControl.getMinimumChannel().intValue());
        assertEquals(20, remoteControl.getMaximumChannel().intValue());
    }

    @Test
    public void shouldCalculateBlockedChannels() {
        Set<Integer> blockedChannels = new HashSet<Integer>() {{
            add(18);
            add(19);
        }};
        InputParser parser = new InputParser();
        parser.addLine(Input.MIN_MAX, "1 20");
        parser.addLine(Input.BLOCKED_CHANNELS, "2 18 19 18");
        parser.addLine(Input.CHANNELS_TO_VISIT, "5 15 14 17 1 17");
        RemoteControl remoteControl = parser.createRemoteControl();
        assertEquals(blockedChannels, remoteControl.getBlockedChannels());
    }

    @Test
    public void shouldPopulateChannelsList() {
        List<Integer> channelsToBeVisited = new ArrayList<Integer>(){
            {
                add(15);
                add(14);
                add(17);
                add(1);
                add(17);
            }
        };
        InputParser parser = new InputParser();
        parser.addLine(Input.MIN_MAX, "1 20");
        parser.addLine(Input.BLOCKED_CHANNELS, "2 18 19");
        parser.addLine(Input.CHANNELS_TO_VISIT, "5 15 14 17 1 17");
        RemoteControl remoteControl = parser.createRemoteControl();
        assertEquals(channelsToBeVisited, remoteControl.getChannelsToBeVisited());
    }

}
