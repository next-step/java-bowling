package bowling.domain;

import java.util.List;

public class Game {
    private static final int FIRST_INDEX = 0;
    private static final int ONE = 1;

    private PlayerFrames playerFrames;
    private int currentIndex;

    public Game(List<String> playerNames) {
        playerFrames = new PlayerFrames(playerNames);
        currentIndex = FIRST_INDEX;
    }

    public boolean isFinished() {
        return playerFrames.isFinished();
    }

    public boolean addPin(int count) {
        boolean result = playerFrames.addPinCount(currentIndex, count);
        if (playerFrames.isDone(currentIndex)) {
            currentIndex = nextIndex();
        }

        return result;
    }

    public String getCurrentPlayerName() {
        return playerFrames.getPlayerName(currentIndex);
    }

    public PlayerFrames getPlayerFrames() {
        return new PlayerFrames(playerFrames);
    }

    private int nextIndex() {
        return (currentIndex + ONE) % playerFrames.size();
    }
}
