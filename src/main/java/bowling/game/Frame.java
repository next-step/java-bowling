package bowling.game;

import java.util.Optional;

public interface Frame {
    int bowl(final int pinCount);

    boolean hasRemainChance();

    Frame createNextFrame();

    FrameNumber getFrameNumber();

    boolean isLastFrame();

    String getStates();

    Optional<Score> calculateScore();

    Optional<Score> calculateBonusScore(Score beforeScore);
}
