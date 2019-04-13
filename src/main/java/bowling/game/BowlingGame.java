package bowling.game;

import bowling.player.Player;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player) {
        this.player = player;
        this.frames = new Frames();
    }

    public void pitch(PinScore pinScore) {
        this.frames.pitch(pinScore);
    }

    public boolean isEnd() {
        return this.frames.isEnd();
    }

    public int getCurrentFrameOrder() {
        return this.frames.getCurrentFrameOrder();
    }

    @Override
    public String toString() {
        return "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n" +
                Frames.FRAME_DELIMETER + String.format(Frames.FRAME_FORMAT, player) + this.frames.toString();
    }
}
