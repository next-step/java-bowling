package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;

public class Playing implements FrameStatus {
    private final FrameScore score;

    public Playing (FrameScore score) {
        this.score = score;
    }

    @Override
    public String makeSymbol() {
        return String.valueOf(score.getFirstScore());
    }
}
