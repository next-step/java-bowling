package bowling.domain;

public class FinalFrameType implements FrameType {
    @Override
    public boolean isContinue(Pitches pitches) {
        return !isOpen(pitches) && !isEndPitch(pitches);
    }

    private boolean isOpen(Pitches pitches) {
        return pitches.count() == 2 && pitches.sum() < 10;
    }

    private boolean isEndPitch(Pitches pitches) {
        return pitches.count() == 3;
    }
}
