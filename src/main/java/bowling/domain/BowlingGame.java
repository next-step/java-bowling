package bowling.domain;

import bowling.dto.ResultDto;

public class BowlingGame {
    private Frame frame;
    private int currentFrameNumber;

    public BowlingGame() {
        frame = new NormalFrame();
        currentFrameNumber = 1;
    }

    public boolean isAvailable() {
        return frame.isAvailable();
    }

    public ResultDto bowling(String inputScore) {
        return new ResultDto(currentFrameNumber, frame.addScore(Integer.parseInt(inputScore)));
    }

    public void nextFrameIfAvailable() {
        if (!frame.isAvailable() && !(frame instanceof FinalFrame)) {
            startNewFrame();
        }
    }

    public void startNewFrame() {
        frame = frame.createFrame(++currentFrameNumber);
    }
}
