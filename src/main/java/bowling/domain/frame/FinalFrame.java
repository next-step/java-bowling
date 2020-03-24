package bowling.domain.frame;

import bowling.domain.framestatus.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {

    private int frameNo;
    private List<Pin> pins;
    private FrameStatus frameStatus;
    private boolean bonus;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.bonus = false;
        this.pins = new ArrayList<>();
        this.frameStatus = new Empty();
    }

    @Override
    public void bowl(int countOfHit) {
        validate();
        if (pins.size() == 0) {
            frameStatus = new FinalFrameManagement(countOfHit).getFrameStatus();
            validateBonus(frameStatus);
            pins.add(new Pin(countOfHit));
            return;
        }
        frameStatus = new FinalFrameManagement(getFrameStatus(), countOfHit).getFrameStatus();
        validateBonus(frameStatus);
        pins.add(new Pin(countOfHit));
    }

    public void bowlByBonus(int countOfHit) {
        validate();
        frameStatus = new FinalFrameManagement(countOfHit).getFrameStatus();
        pins.add(new Pin(countOfHit));
    }

    private void validate() {
        if (pins.size() > 3) {
            throw new IllegalArgumentException("잘 못된 투구 입니다.");
        }
    }

    private void validateBonus(FrameStatus frameStatus) {
        if (frameStatus instanceof StrikeFinalFrame) {
            this.bonus = true;
        }

        if (frameStatus instanceof SpareFinalFrame) {
            this.bonus = true;
        }
    }

    public boolean isBonus() {
        return bonus;
    }

    @Override
    public int size() {
        return pins.size();
    }

    @Override
    public FrameStatus getFrameStatus() {
        return frameStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return frameNo == that.frameNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo);
    }
}
