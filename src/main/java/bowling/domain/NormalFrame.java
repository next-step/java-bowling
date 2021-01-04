package bowling.domain;

public class NormalFrame extends BaseFrame {

    NormalFrame(int frameNo) {
        super(frameNo, new NormalFramePinMarks());
    }

    @Override
    public Status getStatus() {
        if (pinMarks.isFirstStrike()) return Status.Strike;
        if (pinMarks.getCountOfAllFallDownPins() == PinMark.MAX_PINS) return Status.Spare;
        if (pinMarks.getCountOfAllFallDownPins() == 0) return Status.Gutter;
        return Status.Miss;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public Frame createNext() {
        if (isNextFrameFinal()) {
            next = Frame.createFinal(frameNo + 1);
            return next;
        }

        next = Frame.createNormal(frameNo + 1);
        return next;
    }

    private boolean isNextFrameFinal() {
        return frameNo == 9;
    }

}
