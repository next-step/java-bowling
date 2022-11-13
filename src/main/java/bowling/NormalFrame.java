package bowling;

import java.util.List;

public class NormalFrame implements Frame{

    public static final int MIN_NORMAL_FRAME_NUMBER = 1;
    public static final int MAX_NORMAL_FRAME_NUMBER = 9;

    private final int frameNumber;
    private final Score score = new Score();

    public NormalFrame(int frameNumber, Pin falledPins) {
        this.frameNumber = frameNumber;
        this.score.add(falledPins);
    }

    @Override
    public boolean isFinished() {
        return score.isFinished();
    }

    @Override
    public Frame nextFrame(Pin falledPins) {
        if (frameNumber == MAX_NORMAL_FRAME_NUMBER) {
            return new FinalFrame();
        }
        return new NormalFrame(frameNumber + 1, falledPins);
    }

    @Override
    public Frame bowl(Pin falledPins) {
        score.add(falledPins);
        return null;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public List<Pin> getScores() {
        return score.getTotalScore();
    }
}
