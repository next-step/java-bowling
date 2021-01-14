package bowling.domain;

public class NormalFrame extends BaseFrame {

    NormalFrame(int frameNo) {
        super(frameNo, new NormalPinMarker());
    }

    @Override
    public Status getStatus() {
        if (pinMarker.isStrike()) return Status.Strike;
        if (pinMarker.getCountOfMarks() == 1 ) return Status.SecondBowl;
        if (pinMarker.isSpare()) return Status.Spare;
        if (pinMarker.getCountOfAllFallDownPins() == 0) return Status.Gutter;
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


