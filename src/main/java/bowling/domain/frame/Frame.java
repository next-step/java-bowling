package bowling.domain.frame;

import bowling.domain.Game;
import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.pitch.Pitch;

import java.util.List;

public interface Frame {

    Frame play(KnockedPins knockedPins);

    boolean isSpare(KnockedPins knockedPinsA, KnockedPins knockedPinsB);

    boolean isPlaying();

    DefaultFrame createNextFrame();

    DefaultFrame createFinalFrame();

    Score cacluateAdditionalScore(Score beforeScore, List<Frame> frames);

    Score calculateScore(Game game);

    String convert();

    List<Pitch> getPitches();

    Score additionalScore(Score beforeScore, List<Frame> frames);

    int getFirstCount();

    int getSecondCount();
}
