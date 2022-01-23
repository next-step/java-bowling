package bowling.domain.frame;

import bowling.domain.Score;

public interface Frame {
    default boolean isEmpty(Score firstScore) {
        return firstScore == null;
    }

    default boolean isStrike(Score firstScore) {
        return firstScore.equals(new Score(10));
    }

    String convert();

    void makeScore(Score firstScore, int index);

    boolean isSpare(Score scoreA, Score scoreB);

    boolean isLastFrame();

    boolean hasDoneFirstPitch();

    boolean hasDoneSecondPitch();
}
