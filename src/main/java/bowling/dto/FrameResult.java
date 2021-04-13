package bowling.dto;

import bowling.domain.Score;
import bowling.domain.frame.Frame;

public class FrameResult {

    private int frameNumber;

    private String state;

    private int fullyCalculatedScore;

    public FrameResult(Frame frame) {
        this.frameNumber = frame.number().number();
        this.state = frame.currentState().stateInString();
        Score score = frame.score();
        if(score.isUnFinished() || !score.isDoneCalculating()){
            this.fullyCalculatedScore =
        }
        this.fullyCalculatedScore = score.scoreInInt();
    }

    public int frameNumber() {
        return frameNumber;
    }

    public String state() {
        return state;
    }
}
