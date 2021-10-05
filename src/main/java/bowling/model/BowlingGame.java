package bowling.model;

import java.util.ArrayList;
import java.util.List;

import static bowling.controller.Main.scoreResult;

import bowling.CannotBowlException;

public class BowlingGame {
    private static final int AFTER_FINAL_FRAME = 11;
    private static final int BEFORE_FIRST_FRAME = 0;

    private List<Round> rounds;

    public BowlingGame() {
        this.rounds = new ArrayList<>();
    }

    public BowlingGame(Round round) {
        this();
        this.rounds.add(round);
    }

    public State bowl(int countOfPin) throws CannotBowlException {
        return currentFrame().bowl(countOfPin);
    }

    public List<Integer> getScore() {
        List<Integer> scores = currentFrame().getScore();

        int lastScore = getLastScore();
        List<Integer> calculatedScores = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            lastScore += scores.get(i);
            calculatedScores.add(lastScore);
        }
        createNextFrame();

        return calculatedScores;
    }

    private int getLastScore() {
        if (scoreResult.size() == BEFORE_FIRST_FRAME) {
            return 0;
        }

        return scoreResult.get(scoreResult.size() - 1);
    }

    private void createNextFrame() {
        if (currentFrame().isFinish(getFrameNo())) {
            Round round = currentFrame().next(getFrameNo());
            rounds.add(round);
        }
    }

    private Round currentFrame() {
        if (getFrameNo() == BEFORE_FIRST_FRAME) {
            rounds.add(new NormalRound());
        }

        return rounds.get(rounds.size() - 1);
    }

    public boolean isEndGame() {
        return getFrameNo() == AFTER_FINAL_FRAME;
    }

    public int getFrameNo() {
        return rounds.size();
    }


}
