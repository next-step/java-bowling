package bowling.domain;

public class FinalFrame extends BaseFrame {

    FinalFrame(int frameNo) {
        super(frameNo, new FinalPinMarker());
    }

    @Override
    public Status getStatus() {
        if (pinMarker.isStrike()) return Status.Strike;
        if (pinMarker.getCountOfMarks() == 2 && pinMarker.getCountOfAllFallDownPins() == PinMark.MAX_PINS) return Status.Spare;
        if (pinMarker.getCountOfAllFallDownPins() == 0) return Status.Gutter;
        return Status.Miss;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public Frame createNext() {
        return null;
    }

}
