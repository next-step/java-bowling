package bowling.domain;

import java.util.List;

public class Game {
    private PlayerFrames playerFrames;
    private GameRule gameRule;

    public Game(List<String> playerNames) {
        playerFrames = new PlayerFrames(playerNames);
        gameRule = new GameRule(playerNames.size());
    }

    public boolean isFinished() {
        return playerFrames.isFinished();
    }

    public boolean addPin(int count) {
        int turn = gameRule.getTurn();
        boolean result = playerFrames.addPinCount(turn, count);

        if (playerFrames.isDone(turn)) {
            gameRule.changeTurn();
        }

        return result;
    }

    public String getCurrentPlayerName() {
        return playerFrames.getPlayerName(gameRule.getTurn());
    }

    public PlayerFrames getPlayerFrames() {
        return new PlayerFrames(playerFrames);
    }
}
