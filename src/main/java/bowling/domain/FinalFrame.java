package bowling.domain;

import java.util.List;

import bowling.engine.FirstClassList;
import bowling.engine.Frame;
import bowling.engine.Shot;

import static bowling.domain.ShotResult.STRIKE;

public class FinalFrame extends NormalFrame {
    protected FinalFrame(List<Shot> shots) {
        super(FrameSequence.FINAL, shots);
    }

    static Frame of(List<Shot> shots) {
        int sum = sum(shots.stream());
        long strikeCount = shots.stream()
                .filter(STRIKE::equals).count();
        if ((sum > FrameScore.DOUBLE_SCORE && strikeCount < ShotResult.DOUBLE_STRIKE_COUNT)
                || sum > FrameScore.TURKEY_SCORE) {
            throw new IllegalStateException("invalid score: " + sum);
        }

        return new FinalFrame(shots);
    }

    public static Frame first(Shot first) {
        if (first == null) {
            throw new IllegalArgumentException("shot results cannot be null");
        }

        return of(List.of(first));
    }

    public static Frame strike() {
        return first(STRIKE);
    }

    @Override
    public Frame second(Shot second) {
        if (second == null) {
            throw new IllegalArgumentException("shot results cannot be null");
        }

        return of(append(second).collect());
    }

    @Override
    public Frame third(Shot third) {
        if (!isClear() && stream().noneMatch(STRIKE::equals)) {
            throw new IllegalStateException("the third shot is allowed after clear");
        }

        if (size() != NUMBER_OF_SHOT) {
            throw new IllegalStateException("the third shot is allowed after second shot");
        }

        return of(append(third).collect());
    }

    @Override
    public boolean completed() {
        boolean hasThirdChance = isClear() || elementOf(FirstClassList.HEAD) == STRIKE;
        return hasThirdChance ? size() == NUMBER_OF_FINAL_SHOT : super.completed();
    }
}
