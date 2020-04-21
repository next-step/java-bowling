package bowling.domain;

import java.util.List;

public class Game {
    private static final int FIRST_INDEX = 0;
    private static final int ONE = 1;

    private PlayerFrames playerFrames;
    private int currentPlayerIndex;

    public Game(List<String> playerNames) {
        playerFrames = new PlayerFrames(playerNames);
        currentPlayerIndex = FIRST_INDEX;
    }

    public boolean isFinished() {
        return playerFrames.isFinished();
    }

    public boolean addPin(int count) {
        boolean result = playerFrames.addPinCount(currentPlayerIndex, count);
        if (playerFrames.isDone(currentPlayerIndex)) {
            currentPlayerIndex = nextIndex();
        }

        return result;
    }

    public String getCurrentPlayerName() {
        return playerFrames.getPlayerName(currentPlayerIndex);
    }

    public PlayerFrames getPlayerFrames() {
        return new PlayerFrames(playerFrames);
    }

    private int nextIndex() {
        return (currentPlayerIndex + ONE) % playerFrames.size();
    }
}
