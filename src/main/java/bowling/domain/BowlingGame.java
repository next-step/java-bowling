package bowling.domain;

import lombok.Getter;

public class BowlingGame {
    @Getter
    private Player player;

    public BowlingGame(String playerName) {
        this.player = new Player(playerName);
    }

    public void play(int clearPinCount) {
        player.play(clearPinCount);
    }

    public int getLastFrameNumber() {
        return player.getLastFrameNumber();
    }

    public boolean isEnd() {
        return player.isEnd();
    }
}
