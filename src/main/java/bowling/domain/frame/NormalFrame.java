package bowling.domain.frame;

import bowling.domain.framestatus.Empty;
import bowling.domain.framestatus.FrameManagement;
import bowling.domain.framestatus.FrameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {

    private int frameNo;
    private List<Pin> pins;
    private FrameStatus frameStatus;

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pins = new ArrayList<>();
        this.frameStatus = new Empty();
    }

    @Override
    public void bowl(int countOfHit) {
        validate(countOfHit);
        if (pins.size() == 0) {
            frameStatus = new FrameManagement(countOfHit).getFrameStatus();
            pins.add(new Pin(countOfHit));
            return;
        }
        frameStatus = new FrameManagement(getCurrentHit(), countOfHit).getFrameStatus();
        pins.add(new Pin(countOfHit));
    }

    private void validate(int count) {
        if (getCurrentHit() + count > 10) {
            throw new IllegalArgumentException("잘 못된 투구 입니다.");
        }
    }

    private int getCurrentHit() {
        return pins.stream()
                .mapToInt(Pin::getCountOfHit)
                .sum();
    }

    public FrameStatus getFrameStatus() {
        return frameStatus;
    }

    public int size() {
        return pins.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame normalFrame = (NormalFrame) o;
        return frameNo == normalFrame.frameNo &&
                Objects.equals(pins, normalFrame.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, pins);
    }
}
