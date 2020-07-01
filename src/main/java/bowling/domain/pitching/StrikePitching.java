package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;
import bowling.util.StringUtils;

import java.util.Objects;

public class StrikePitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;
    private FallenPinNumber secondFallenPinNumber;
    private Pitching beforePitching;

    private StrikePitching(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
        this.secondFallenPinNumber = secondFallenPinNumber;
    }

    public static StrikePitching of(FallenPinNumber firstFallenPinNumber) {
        return of(firstFallenPinNumber, FallenPinNumber.of(0));
    }

    public static StrikePitching of(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        return new StrikePitching(firstFallenPinNumber, secondFallenPinNumber);
    }

    @Override
    public boolean isFinished(Frame frame) {
        beforePitching = frame.getPitching();
        String identical = beforePitching.getPitchingIdentical();
        return frame.isFinalFrame() && notFinishCondition(identical);
    }

    private boolean notFinishCondition(String identical) {
        return StringUtils.equals(identical, "StandbyPitching");
    }

    @Override
    public Pitching pitch(FallenPinNumber fallenPinNumber) {
        String identical = beforePitching.getPitchingIdentical();
        if (StringUtils.equals(identical, "StrikePitching")) {
            return BonusPitching.of(firstFallenPinNumber, secondFallenPinNumber, fallenPinNumber);
        }

        throw new RuntimeException("이미 끝난 프레임입니다.");
    }

    @Override
    public String getPitchingIdentical() {
        return "StrikePitching";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StrikePitching that = (StrikePitching) o;
        return Objects.equals(firstFallenPinNumber, that.firstFallenPinNumber) &&
                Objects.equals(secondFallenPinNumber, that.secondFallenPinNumber) &&
                Objects.equals(beforePitching, that.beforePitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPinNumber, secondFallenPinNumber, beforePitching);
    }
}
