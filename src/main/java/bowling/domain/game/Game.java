package bowling.domain.game;

import bowling.domain.State.Pin;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import static bowling.view.OutputView.PLAYER_FORMAT;

public class Game {
    private final Player player;
    private final Frames frames;

    public Game(Player player, Frames frames) {
        validate(player, frames);
        this.player = player;
        this.frames = frames;
    }

    private void validate(Player player, Frames frames) {
        if (player == null) {
            throw new IllegalArgumentException("player는 null 일 수 없습니다.");
        }
        if (frames == null) {
            throw new IllegalArgumentException("frames는 null 일 수 없습니다.");
        }
    }

    public boolean isDone() {
        return frames.isDone();
    }

    public FrameNumber currentFrameNumber() {
        return frames.currentNumber();
    }

    public static Game enter(Player player) {
        return new Game(player, Frames.initialize());
    }

    public void bowl(Pin pin) {
        if (isDone()) {
            throw new IllegalArgumentException("종료된 게임은 더이상 볼을 굴릴 수 없습니다.");
        }

        frames.bowl(pin);
    }

    @Override
    public String toString() {
        return String.format(PLAYER_FORMAT + frames.toString(), player.toString());
    }
}
