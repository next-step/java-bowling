package bowling.domain.shot;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.domain.ClearBonusScores;
import bowling.engine.Bonus;
import bowling.engine.BonusScores;
import bowling.engine.Score;
import bowling.engine.Shot;
import bowling.engine.Shots;
import bowling.engine.collection.FirstClassMutableList;

import static bowling.domain.BowlingScore.ACCUMULATION_BASE;

public class FrameShots extends FirstClassMutableList<Shot> implements Shots {
    protected FrameShots(List<Shot> collection) {
        super(collection);
    }

    static Shots byBuilder(List<Shot> shots) {
        return new FrameShots(shots);
    }

    public static Shots of(List<Shot> shots) {
        return FrameShotsBuilder.of(shots).build();
    }

    public static Shots bySingleShot(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the first shot cannot be null");
        }

        return byBuilder(List.of(shot));
    }

    public static Shots ofFinal(List<Shot> shots) {
        return FrameShotsBuilder.ofFinal(shots).build();
    }

    public static FrameShots emptyShot() {
        return new FrameShots(Collections.emptyList());
    }

    static int sum(Stream<Shot> shotStream) {
        return shotStream.map(Shot::toInt)
                .reduce(0, Integer::sum);
    }

    @Override
    public Shots nextShot(Shot shot) {
        Shot nextShot = lastOptional().map(last -> ShotResult.of(last, shot))
                .orElse(shot);
        append(nextShot);

        return this;
    }

    @Override
    public Score score(Bonus bonus) {
        return stream().map(Shot::score)
                .reduce(ACCUMULATION_BASE, Score::add)
                .add(bonus.score());
    }

    @Override
    public boolean isClear() {
        return sum(stream()) == NUMBER_OF_PINS;
    }

    @Override
    public BonusScores clearBonus() {
        return lastOptional()
                .map(Shot::bonusScore)
                .orElse(ClearBonusScores.byNone());
    }
}
