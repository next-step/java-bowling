package bowling.domain;

import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.dto.PlayResult;

public class Player {

    private final PlayerName name;

    private final Frames frames;

    private Player(PlayerName name, Frames frames) {
        this.name = name;
        this.frames = frames;
    }

    public Player(String name, int totalNumberOfFrame) {
        this(new PlayerName(name), Frames.init(totalNumberOfFrame));
    }

    public void addPinCounts(int pintCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 전체 프레임을 다 play하셨습니다.");
        }
        frames.addPinCount(pintCount);
    }

    public FrameNumber currentFrameNumber() {
        return frames.currentFrameNumber();
    }

    public boolean isDone() {
        return frames.isDone();
    }

    public PlayResult playResult() {
        return new PlayResult(name, frames);
    }
}
