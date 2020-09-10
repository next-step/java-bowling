package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;

public class Miss implements FrameStatus {

    private final FrameScore score;

    public Miss(FrameScore score) {
        this.score = score;
    }

    @Override
    public String makeSymbol() {
        return FrameSymbol.of(score.getFirstScore()) + "|" +
                FrameSymbol.of(score.getLastScore());
    }
}
