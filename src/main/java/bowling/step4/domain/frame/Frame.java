package bowling.step4.domain.frame;

import bowling.step4.domain.ScoresType;
import bowling.step4.domain.scores.Scores;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class Frame {
    public static final int EMPTY_CALC = -1;

    protected final int frame;
    protected Scores scores;

    protected Frame(int frame, Scores scores) {
        this.frame = frame;
        this.scores = scores;
    }

    public Scores getScores() {
        return scores;
    }

    public int getValue() {
        return frame;
    }

    public int calculateScore() {
        return Stream.of(calculatorOfEmpty(!ScoresType.FULL.of(scores), () -> EMPTY_CALC),
                         calculatorOfEmpty(ScoresType.STRIKE.of(scores), this::calculateScoreOfStrike),
                         calculatorOfEmpty(ScoresType.SPARED.of(scores), this::calculateScoreOfSpared))
                     .filter(Objects::nonNull)
                     .findFirst()
                     .orElse(scores::totalScore)
                     .get();
    }

    private Supplier<Integer> calculatorOfEmpty (boolean type, Supplier<Integer> calculator) {
        return type ? calculator : null;
    }

    abstract protected int calculateScoreOfSpared();

    abstract protected int calculateScoreOfStrike();

    abstract protected int calculateScoreOfTwoStrike(int totalScore);

    abstract public void createNextFrameOfScores(Scores scores);

    abstract public Frame getNextFrame();
}