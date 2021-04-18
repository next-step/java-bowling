package bowling.domain;

import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.dto.PlayResult;

public class Player {

    private final PlayerName playerName;

    private final Frames frames;

    private Player(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public Player(String playerName, int totalNumberOfFrame) {
        this(new PlayerName(playerName), Frames.init(totalNumberOfFrame));
    }

    public void addPinCount(int pintCount) {
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
        return new PlayResult(playerName, frames);
    }

    public String name() {
        return playerName.name();
    }
}
