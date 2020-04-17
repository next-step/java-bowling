package bowling.bowlinggame;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

/**
 * player와 player가 사용하는 frames을 가지는 클래스
 * 게임 진행을 제어한다.
 */
public class BowlingGame {
    private final Frames frames;
    private final Player player;

    public BowlingGame(Frames frames, Player player) {
        this.frames = frames;
        this.player = player;
    }

    public Frames getFrames() {
        return frames;
    }

    public Player getPlayer() {
        return player;
    }

    boolean isOver() {
        return frames.isOver();
    }

    public void addNextFrame() {
        frames.addNextFrame();
    }

    public Frame getLastFrame() {
        return frames.getLast();
    }
}
