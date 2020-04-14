package bowling.domain;

import lombok.Getter;

public class BowlingGame {
    @Getter
    private Bowling bowling;

    public BowlingGame(String playerName) {
        this.bowling = new Bowling(playerName);
    }

    public void play(int clearPinCount) {
        bowling.play(clearPinCount);
    }

    public int getLastFrameNumber() {
        return bowling.getLastFrameNumber();
    }

    public boolean isEnd() {
        return bowling.isEnd();
    }
}
