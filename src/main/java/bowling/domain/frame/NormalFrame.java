package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalFrame implements Frame {

    private final List<DownedPin> downedPins;
    private Score score;

    public NormalFrame() {
        this.downedPins = new ArrayList<>();
    }

    @Override
    public void record(int numDownedPins) {
        FrameStatus.record(downedPins, numDownedPins);

        if (isEnd()) {
            this.score = new Score(downedPins);
        }
    }

    @Override
    public boolean isEnd() {
        return FrameStatus.isEnd(downedPins);
    }

    @Override
    public int calculateScore() {
        return score.calculateScore();
    }

    @Override
    public boolean needAdditionalScore() {
        if (score == null) {
            return false;
        }

        return !score.hasFixedScore();
    }

    public void recordAdditionalScore(int numDownedPin) {
        score.record(numDownedPin);
    }

    public FrameStatus decideStatus() {
        return FrameStatus.decideStatus(downedPins);
    }

    public List<DownedPin> getDownedPins() {
        return Collections.unmodifiableList(downedPins);
    }
}
