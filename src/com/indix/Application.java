package com.indix;

public class Application {
    private RemoteControl remoteControl;

    public void addLines(String firstLine, String secondLine, String thirdLine) {
        InputParser parser = new InputParser();
        parser.addLine(Input.MIN_MAX, firstLine);
        parser.addLine(Input.BLOCKED_CHANNELS, secondLine);
        parser.addLine(Input.CHANNELS_TO_VISIT, thirdLine);
        remoteControl = parser.createRemoteControl();
    }

    public int calculateMinimumClicksRequired() {
        return remoteControl.calculateMinimumClicksRequired();
    }
}
