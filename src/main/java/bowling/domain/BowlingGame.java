package bowling.domain;

import java.util.List;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-08-11 21:09
 */
public class BowlingGame {
    private Player player;
    private Frames frames;

    private BowlingGame(Player player) {
        this.player = player;
        this.frames = new Frames();
    }

    public static BowlingGame of(Player player) {
        return new BowlingGame(player);
    }

    public boolean play(int fallCount) {
        return frames.bowl(fallCount);
    }

    public List<String> displayState() {
        return frames.displayState();
    }

    public List<Integer> displayScore() {
        return frames.displayScore();
    }

    public int currentFrameNumber() {
        return frames.currentFrameNumber();
    }

    public Player getPlayerName() {
        return player;
    }

    public boolean matchFrameNumber(int number) {
        return frames.matchFrameNumber(number);
    }

    public boolean matchPlayer(Player player) {
        return this.player.equals(player);
    }

    public boolean isGameOver() {
        return frames.isGameOver();
    }
}
