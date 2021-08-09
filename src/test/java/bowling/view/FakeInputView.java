package bowling.view;

import java.util.List;

public class FakeInputView implements InputView {
    private final String strName;
    private final List<Integer> turnScores;

    private int turnScoreIndex = 0;

    public FakeInputView(final String strName, final List<Integer> turnScores) {
        this.strName = strName;
        this.turnScores = turnScores;
    }

    @Override
    public String inputName() {
        return strName;
    }

    @Override
    public int inputTurnScore(final int currentFrameNumber) {
        return turnScores.get(turnScoreIndex++);
    }
}
