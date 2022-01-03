package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bowling.engine.Bonus;
import bowling.engine.BonusScores;
import bowling.engine.Score;
import bowling.engine.Shot;
import bowling.engine.collection.FirstClassImmutableList;

public class FrameBonus extends FirstClassImmutableList<BonusScores> implements Bonus {
    public static final Bonus NONE = new FrameBonus(Collections.emptyList(), ClearBonusScores.byNone());

    private static final int MAX_PREVIOUS_BONUS_COUNT = 2;

    private final BonusScores currentBonus;

    private FrameBonus(List<BonusScores> previousBonuses, BonusScores currentBonus) {
        super(previousBonuses);
        this.currentBonus = currentBonus;
    }

    public static FrameBonus of(List<BonusScores> previousBonuses, BonusScores current) {
        if (previousBonuses == null || current == null) {
            throw new IllegalArgumentException("previous of current cannot be null");
        }

        if (previousBonuses.size() > MAX_PREVIOUS_BONUS_COUNT) {
            throw new IllegalArgumentException("previous bonus cannot be larger than 2");
        }

        Stream<BonusScores> previous = previousBonuses.stream()
                .filter(BonusScores::remain);

        if (current.remain()) {
            List<BonusScores> bonuses = Stream.concat(previous, Stream.of(current))
                    .collect(Collectors.toList());
            return new FrameBonus(bonuses, current);
        }

        return new FrameBonus(previous.collect(Collectors.toList()), current);
    }

    public static FrameBonus of(BonusScores current) {
        return of(Collections.emptyList(), current);
    }

    public static FrameBonus of(List<BonusScores> previousBonuses) {
        return of(previousBonuses, ClearBonusScores.byNone());
    }

    @Override
    public Bonus applyBonus(Shot shot) {
        stream().forEach(bonus -> bonus.appendBonus(shot));
        return this;
    }

    @Override
    public Score score() {
        return currentBonus.sum();
    }

    @Override
    public List<BonusScores> remainBonus() {
        return stream().filter(BonusScores::remain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean remain() {
        return stream().anyMatch(BonusScores::remain);
    }
}
