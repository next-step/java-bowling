package bowling.dto;

import bowling.domain.frame.Frame;

public class FrameResult {

    private int frameNumber;

    private String state;

    private ScoreDto score;

    public FrameResult(Frame frame) {
        this.frameNumber = frame.number().number();
        this.state = frame.currentState().stateInString();
        this.score = new ScoreDto(frame.score());
    }

    public int frameNumber() {
        return frameNumber;
    }

    public String state() {
        return state;
    }

    public ScoreDto score() {
        return score;
    }
}
