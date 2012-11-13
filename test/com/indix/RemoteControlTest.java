package com.indix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RemoteControlTest {
    @Test
    public void shouldCalculateNumberOfBackwardClicks() {
        Set<Integer> blockedChannels = new HashSet<Integer>();
        blockedChannels.add(104);
        List<Integer> channelsToBeVisited = new ArrayList<Integer>();
        RemoteControl remoteControl = new RemoteControl(103, 108, blockedChannels, channelsToBeVisited);
        assertEquals(3, remoteControl.calculateBackwardClicks(107, 103));
    }

    @Test
    public void shouldCalculateBackThroughClicks() {
        List<Integer> channelsToBeVisited = new ArrayList<Integer>() {
            {
                add(1);
                add(100);
                add(1);
                add(101);
            }
        };
        RemoteControl remoteControl = new RemoteControl(0, 200, new HashSet<Integer>(), channelsToBeVisited);
        assertEquals(2, remoteControl.calculateBackThroughClicks(100, 101));
    }

    @Test
    public void shouldCalculateForwardClicks(){
        Set<Integer> blockedChannels = new HashSet<Integer>();
        blockedChannels.add(104);
        RemoteControl remoteControl = new RemoteControl(103, 108, blockedChannels, null);
        assertEquals(2, remoteControl.calculateForwardClicks(107, 103));
    }

    @Test
    public void shouldIncrement() {
        RemoteControl remoteControl = new RemoteControl(10, 20, new HashSet<Integer>(), null);
        assertEquals(11, remoteControl.up(10).intValue());
    }

    @Test
    public void shouldIncrementWithBlockedChannelsOn() {
        Set<Integer> blockedChannels = new HashSet<Integer>();
        blockedChannels.add(15);
        RemoteControl remoteControl = new RemoteControl(10, 20, blockedChannels, null);
        assertEquals(16, remoteControl.up(14).intValue());
    }

    @Test
    public void shouldIncrementBeyondMaxLimit() {
        RemoteControl remoteControl = new RemoteControl(10, 20, null, null);
        assertEquals(10, remoteControl.up(20).intValue());
    }


    @Test
    public void shouldDecrement() {
        RemoteControl remoteControl = new RemoteControl(10, 20, new HashSet<Integer>(), null);
        assertEquals(15, remoteControl.down(16).intValue());
    }

    @Test
    public void shouldDecrementWithBlockedChannelsOn() {
        Set<Integer> blockedChannels = new HashSet<Integer>();
        blockedChannels.add(15);
        RemoteControl remoteControl = new RemoteControl(10, 20, blockedChannels, null);
        assertEquals(14, remoteControl.down(16).intValue());
    }

    @Test
    public void shouldDecrementBeyondMaxLimit() {
        RemoteControl remoteControl = new RemoteControl(10, 20, null, null);
        assertEquals(20, remoteControl.down(10).intValue());
    }
}
