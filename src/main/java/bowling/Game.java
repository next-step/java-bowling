package bowling;

public class Game {
    public Game(String playerName) {
        if (playerName.length() > 3) {
            throw new IllegalPlayerNameException();
        }
    }

    public boolean isFinish() {
        return false;
    }

    public int getCurrentFrameNumber() {
        return 0;
    }

    public GameStatus throwBall(int fallingPins) {
        return null;
    }

    public GameStatus start() {
        return null;
    }
}
