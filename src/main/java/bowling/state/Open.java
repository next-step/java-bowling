package bowling.state;

import bowling.domain.Bowling;
import bowling.domain.frame.Frame;
import bowling.domain.score.BowlingScore;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created By mand2 on 2020-12-19.
 */
public class Open implements BowlingState {

    private final Frame frame;

    private Open(Frame frame) {
        this.frame = frame;
    }

    public static BowlingState of(Frame frame) {
        return new Open(frame);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public boolean isFinalPlayable() {
        return false;
    }

    @Override
    public List<BowlingScore> getScoreBoard() {
        return this.frame.getScoreList().stream()
                .map(score -> {
                    if (Objects.isNull(score)) {
                        return BowlingScore.EMPTY;
                    }
                    return BowlingScore.getBowlingScore(score, false);
                }).collect(Collectors.toList());
    }
}
