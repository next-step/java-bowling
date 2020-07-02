package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;

public interface Pitching {

    boolean isFinished(Frame frame);

    Pitching pitch(FallenPinNumber fallenPinNumber);

    String getPitchingIdentical();

    String getPitchingDescription();
}
