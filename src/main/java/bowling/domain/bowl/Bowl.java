package bowling.domain.bowl;

import bowling.domain.pitch.PitchResult;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public interface Bowl {

    boolean isEnd();

    Bowl pitch(Pins pins);

    Score score();

    Score calculateScore(Score before);

    PitchResult getPitchResult();
}
