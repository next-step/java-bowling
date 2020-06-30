package bowling.game.frame;

import bowling.game.Score;

import java.util.Optional;

public interface Frame {
    void bowl(final int pinCount);

    boolean hasRemainChance();

    Frame createNextFrame();

    FrameNumber getFrameNumber();

    boolean isLastFrame();

    String getStates();

    Optional<Score> calculateScore();

    Optional<Score> calculateBonusScore(Score beforeScore);
}
