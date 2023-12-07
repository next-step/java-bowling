package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FrameBoard {
    private final PlayerName playerName;
    private final List<Frame> frames;
    private Ball bonusBall;

    public FrameBoard(PlayerName playerName, List<Frame> frames, Ball bonusBall) {
        this.playerName = playerName;
        this.frames = frames;
        this.bonusBall = bonusBall;
    }

    public static FrameBoard init(PlayerName playerName) {
        List<Frame> frames = initFrames();
        return new FrameBoard(playerName, frames, Ball.init());
    }

    private static List<Frame> initFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            frames.add(Frame.init());
        }
        return frames;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void applyFirstBallOf(int frameIndex, Ball firstBall) {
        Frame frame = frames.get(frameIndex);
        frame.applyFirstBall(firstBall);
    }

    public void applySecondBallOf(int frameIndex, Ball secondBall) {
        Frame frame = frames.get(frameIndex);
        frame.applySecondBall(secondBall);
    }

    public boolean needLastFrameBonus() {
        Frame lastFrame = frames.get(frames.size() - 1);
        return lastFrame.isStrike() || lastFrame.isSpare();
    }

    public void applyBonusBall(Ball bonusBall) {
        this.bonusBall = bonusBall;
    }

    public Ball getBonusBall() {
        return bonusBall;
    }

    public boolean hasEmptyFrame() {
        return frames.stream().anyMatch(Frame::isEmpty);
    }

    public int getNextFrameIndes() {
        frames.stream()
                .filter(Frame::isEmpty)
                .findFirst()
                .map(frame -> frame.)
    }
}
