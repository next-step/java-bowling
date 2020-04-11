package bowling.application;

import bowling.domain.frame.Bowling;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Result;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private Bowling bowling;

    public Response(Bowling bowling) {
        this.bowling = bowling;
    }

    public List<Result> getResults() {
        return new ArrayList<>(bowling.getResults());
    }

    public int getTotalScore() {
        return bowling.getTotalScore();
    }

    public String getName() {
        return bowling.getName();
    }

    public int getFrameNumberLast() {
        return bowling.getFrameNumber();
    }

    public boolean isEnd() {
        if (bowling.getFrameNumber() < 10) {
            return false;
        }
        FinalFrame finalFrame = (FinalFrame) bowling.getFrames().getLast();
        return finalFrame.isEnd();
    }
}
