package bowling.model;

import bowling.model.frame.Frame;
import bowling.model.frame.Frames;

import java.util.stream.Stream;

public class BowlingGame {

    private final Frames frames;
    private final User user;
    private int playFrameNo;

    private BowlingGame(Frames frames, User user, int playFrameNo) {
        this.frames = frames;
        this.user = user;
        this.playFrameNo = playFrameNo;
    }

    public static BowlingGame of(User user) {
        return new BowlingGame(new Frames(), user,1);
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

    public String getUserName() {
        return user.getName();
    }

    public boolean isEndOf(int playFrameNo) {
        return frames.isEndOf(playFrameNo);
    }

}
