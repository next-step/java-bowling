package bowling.domain;

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
}
