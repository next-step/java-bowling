package bowling.model;

import bowling.model.frame.Frame;
import bowling.model.frame.Frames;

import java.util.stream.Stream;

public class BowlingGame {

    private final Frames frames;
    private int playFrameNo;

    private BowlingGame(Frames frames, int playFrameNo) {
        this.frames = frames;
        this.playFrameNo = playFrameNo;
    }

    public static BowlingGame of() {
        return new BowlingGame(new Frames(), 1);
    }

    public void bowling(int countOfPins) {
        playFrameNo = frames.playBowling(playFrameNo, countOfPins);
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int getFramesSize() {
        return frames.getFramesSize();
    }

    public Stream<Frame> getFrames() {
        return frames.getFrames();
    }

    public boolean isEndOf(int playFrameNo) {
        return frames.isEndOf(playFrameNo);
    }

}
