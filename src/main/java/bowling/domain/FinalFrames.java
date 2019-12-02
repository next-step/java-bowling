package bowling.domain;

import java.util.List;

public class FinalFrames {

    private static final int MIN_SIZE = 0;
    private static final int BONUS_GAME_SIZE = 1;
    private static final int PREVIOUS_VALUE = 0;
    private static final int CORRECTION_VALUE = 1;

    private List<Frame> frames;

    private FinalFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public static FinalFrames of(List<Frame> frames) {
        return new FinalFrames(frames);
    }

    public FinalFrames next(int countOfHit) {
        return of(createFrame(countOfHit));
    }

    private List<Frame> createFrame(int countOfHit) {
        if (frames.size() > BONUS_GAME_SIZE) {
            frames.add(bonusFrame(countOfHit));
            return frames;
        }
        frames.add(createFinalFrame(countOfHit));
        return frames;
    }

    private Frame createFinalFrame(int countOfHit) {
        if (frames.size() > MIN_SIZE) {
            Frame frame = frames.get(frames.size() - CORRECTION_VALUE);
            if (frame.isRemain()) {
                return frame.nextFinalFrame(countOfHit);
            }
        }
        return Frame.frame(countOfHit);
    }

    private Frame bonusFrame(int countOfHit) {
        if (isBonus()) {
            Frame frame = frames.get(frames.size() - CORRECTION_VALUE);
            return frame.bonusFrame(countOfHit);
        }
        throw new IllegalArgumentException("게임 끝");
    }

    public boolean isBonus() {
        int previousScore = PREVIOUS_VALUE;
        for (Frame finalFrame : frames) {
            if (finalFrame.isBonus(previousScore)) {
                return true;
            }
            previousScore += finalFrame.getCountOfHit();
        }
        return false;
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }

}
