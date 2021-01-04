package bowling.domain;

public class FinalFrame extends BaseFrame {

    FinalFrame(int frameNo) {
        super(frameNo, new FinalFramePinMarks());
    }

    @Override
    public Status getStatus() {
        if (pinMarks.isFirstStrike()) return Status.Strike;
        if (pinMarks.getCountOfMarks() == 2 && pinMarks.getCountOfAllFallDownPins() == PinMark.MAX_PINS) return Status.Spare;
        if (pinMarks.getCountOfAllFallDownPins() == 0) return Status.Gutter;
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
