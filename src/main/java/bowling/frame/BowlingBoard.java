package bowling.frame;

import bowling.bowler.Bowler;

import java.util.LinkedList;
import java.util.Objects;

import static bowling.frame.Frame.FINAL_FRAME_NUMBER;
import static bowling.frame.Frame.INCREASE_FRAME_NUMBER;

public class BowlingBoard {

    private final Bowler bowler;
    private final LinkedList<Frame> frames;

    private BowlingBoard(Bowler bowler) {
        this.bowler = bowler;
        this.frames = new LinkedList<>();
        this.frames.add(NormalFrame.first());
    }

    public static BowlingBoard start(Bowler bowler) {
        return new BowlingBoard(bowler);
    }

    public Frame bowl(String fellPins) {
        Frame frame = getLastFrame().bowl(fellPins);

        if (canMoveNextFrame() && getLastFrame().isFinish()) {
            frames.add(next(getNextFrameNumber()));
        }
        return frame;
    }

    public Frame next(int nextFrameNumber) {
        validateNextFrame();
        if (nextFrameNumber == FINAL_FRAME_NUMBER) {
            return FinalFrame.create();
        }
        return NormalFrame.create(nextFrameNumber);
    }

    private void validateNextFrame() {
        if (!canMoveNextFrame()) {
            throw new RuntimeException("게임이 종료되었습니다.");
        }
    }

    private boolean canMoveNextFrame() {
        return getNextFrameNumber() <= FINAL_FRAME_NUMBER;
    }

    private int getNextFrameNumber() {
        return getFrameNumber() + INCREASE_FRAME_NUMBER;
    }

    public int getFrameNumber() {
        return getLastFrame().getFrameNumber();
    }

    public Frame getLastFrame() {
        return frames.getLast();
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }

    public String getBowlerName() {
        return bowler.getName();
    }

    public boolean isEnd() {
        return !canMoveNextFrame() && getLastFrame().isFinish();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingBoard that = (BowlingBoard) o;
        return Objects.equals(bowler, that.bowler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowler);
    }
}
