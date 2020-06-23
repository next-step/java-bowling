package bowling.domain;

import bowling.domain.exceptions.AlreadyStartedPlayerException;
import bowling.domain.exceptions.NormalFrameFinishedException;
import bowling.domain.exceptions.NotStartedPlayerException;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.PlayerFrames;

import java.util.List;
import java.util.Objects;

public class Player {
    private static final int MAX_NORMAL_FRAME_SIZE = 9;

    private final String name;
    private final PlayerFrames playerFrames;
    private final Frame currentFrame;

    Player(String name, PlayerFrames playerFrames, Frame currentFrame) {
        this.name = name;
        this.playerFrames = playerFrames;
        this.currentFrame = currentFrame;
    }

    public static Player createByName(String playerName) {
        return new Player(playerName, PlayerFrames.createEmpty(), null);
    }

    public Player bowlFirst(int numberOfHitPin) {
        validateBowlFirst();

        NormalFrame firstFrame = NormalFrame.start(numberOfHitPin);

        return new Player(name, playerFrames.lastValue(firstFrame), firstFrame);
    }

    public List<FrameResults> calculateResult() {
        validateIsStarted();

        return this.playerFrames.calculateResult();
    }

    public boolean isCurrentFrameCompleted() {
        validateIsStarted();
        return this.currentFrame.isCompleted();
    }

    public Player bowlCurrentFrame(int numberOfHitPin) {
        Frame frame = this.currentFrame.bowl(numberOfHitPin);

        return new Player(name, this.playerFrames.lastValue(frame), frame);
    }

    public Player nextFrame(int numberOfHitPin) {
        validateNextFrame();

        Frame nextFrame = this.currentFrame.next(numberOfHitPin);

        return new Player(name, this.playerFrames.lastValue(nextFrame), nextFrame);
    }

    public Player finalFrame(int numberOfHitPin) {
        FinalFrame finalFrame = FinalFrame.firstBowl(numberOfHitPin, currentFrame);
        return new Player(name, this.playerFrames.lastValue(finalFrame), finalFrame);
    }

    private void validateNextFrame() {
        if (this.playerFrames.size() == MAX_NORMAL_FRAME_SIZE && this.currentFrame.isCompleted()) {
            throw new NormalFrameFinishedException("9 프레임 이후부터는 일반적인 방법으로 진행할 수 없습니다.");
        }
    }

    private void validateIsStarted() {
        if (this.currentFrame == null) {
            throw new NotStartedPlayerException("초구를 굴리지 않은 상태에서 할 수 없는 동작입니다.");
        }
    }

    private void validateBowlFirst() {
        if (this.currentFrame != null) {
            throw new AlreadyStartedPlayerException("이미 투구한 참가자는 초구를 굴릴 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(playerFrames, player.playerFrames) &&
                Objects.equals(currentFrame, player.currentFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, playerFrames, currentFrame);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", playerFrames=" + playerFrames +
                ", currentFrame=" + currentFrame +
                '}';
    }
}
