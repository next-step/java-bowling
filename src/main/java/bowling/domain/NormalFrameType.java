package bowling.domain;

public class NormalFrameType implements FrameType {
    @Override
    public boolean isContinue(Pitches pitches) {
        return !isStrike(pitches) && !isEndPitch(pitches);
    }

    private boolean isStrike(Pitches pitches) {
        return pitches.count() == 1 && pitches.sum() == 10;
    }

    private boolean isEndPitch(Pitches pitches) {
        return pitches.count() == 2;
    }
}
