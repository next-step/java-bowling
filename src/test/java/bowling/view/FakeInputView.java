package bowling.view;

import java.util.List;

public class FakeInputView implements InputView {
    private final List<String> playerNames;
    private final List<Integer> turnScores;

    private int playerNameIndex = 0;
    private int turnScoreIndex = 0;

    public FakeInputView(final List<String> playerNames, final List<Integer> turnScores) {
        this.playerNames = playerNames;
        this.turnScores = turnScores;
    }

    @Override
    public int inputPlayerSize() {
        return playerNames.size();
    }

    @Override
    public String inputName() {
        return playerNames.get(playerNameIndex++);
    }

    @Override
    public int inputTurnScore(final String currentPlayerName) {
        return turnScores.get(turnScoreIndex++);
    }
}
