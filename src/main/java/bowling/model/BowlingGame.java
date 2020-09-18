package bowling.model;

import bowling.model.frame.Frame;
import bowling.model.frame.Frames;

import java.util.stream.Stream;

public class BowlingGame {

    private Frames frames;
    private int playFrameNo;

    private BowlingGame(Frames frames, int playFrameNo) {
        this.frames = frames;
        this.playFrameNo = playFrameNo;
    }

    public static BowlingGame of() {
        return new BowlingGame(new Frames(), 1);
    }

    public static BowlingGame of(Frames frames, int playFrameNo) {
        return new BowlingGame(frames, playFrameNo);
    }

    public void bowling(int countOfPins) {
        playFrameNo = frames.playBowling(playFrameNo, countOfPins);
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int getPlayFrameNo() {
        return playFrameNo;
    }

    public Stream<Frame> getFrames() {
        return frames.getFrames();
    }

}
