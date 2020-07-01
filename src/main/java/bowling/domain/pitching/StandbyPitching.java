package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;

public class StandbyPitching implements Pitching {

    @Override
    public boolean isFinished(Frame frame) {
        return false;
    }

    @Override
    public Pitching pitch(FallenPinNumber fallenPinNumber) {
        if (fallenPinNumber.isStrike()) {
            return StrikePitching.of(fallenPinNumber);
        }

        return FirstPitching.of(fallenPinNumber);
    }

    @Override
    public String getPitchingIdentical() {
        return "StandbyPitching";
    }
}
