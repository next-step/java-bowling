package bowling.domain.game;

import bowling.domain.score.FrameScore;

import java.util.Objects;

public abstract class Frame {
    private final FrameNumber frameNumber;
    private final FrameScore frameScore;

    protected Frame(FrameNumber frameNumber, FrameScore frameScore) {
        Objects.requireNonNull(frameNumber, "프레임 번호가 유효하지 않습니다.");
        Objects.requireNonNull(frameScore, "프레임 점수가 유효하지 않습니다.");
        this.frameNumber = frameNumber;
        this.frameScore = frameScore;
    }

    public abstract Frame next();
}
