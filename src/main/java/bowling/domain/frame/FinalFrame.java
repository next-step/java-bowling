package bowling.domain.frame;

import java.util.List;

public class FinalFrame extends Frame {

    public FinalFrame(List<Score> scores) {
        this.scores = scores;
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    void addScore(int score) {

    }
}
