package bowling.domain.bowl;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.List;

public interface Bowl {

    Bowl pitch(Pin pin);

    boolean canPitch();

    Score score();

    boolean typeEquals(BowlType bowlType);

    List<Pin> pins();
}
