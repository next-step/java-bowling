package bowling.domain.status;

import bowling.domain.score.Score;

public class Ready extends FrameStatus{

    private boolean isFinal;

    public Ready(boolean isFinal) {
        this.isFinal = isFinal;
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        if (isFinal) {
            return new FinalFirstBowl(countOfPin);
        }

        if (countOfPin == 10) {
            return new Strike();
        }

        return new FirstBowl(countOfPin);
    }

    @Override
    public Score findScore() {
        return new Score(0,0);
    }
}
