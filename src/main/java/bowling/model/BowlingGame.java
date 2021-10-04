package bowling.model;

import bowling.CannotBowlException;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private static final int AFTER_FINAL_FRAME = 11;
    private static final int BEFORE_FIRST_FRAME = 0;

    private List<Integer> scores;
    private List<Round> rounds;

    public BowlingGame() {
        this.scores = new ArrayList<>();
        this.rounds = new ArrayList<>();
    }

    public BowlingGame(Round round) {
        this();
        this.rounds.add(round);
    }

    public BowlingGame(List<Integer> scores, List<Round> rounds) {
        this.scores = scores;
        this.rounds = rounds;
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

        this.scores.addAll(calculatedScores);
        return calculatedScores;
    }

    private int getLastScore() {
        if (scores.size() == BEFORE_FIRST_FRAME) {
            return 0;
        }

        return scores.get(scores.size() - 1);
    }

    private void createNextFrame() {
        if (currentFrame().isFinish()) {
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
        if (getFrameNo() == AFTER_FINAL_FRAME) {
            return true;
        }

        return false;
    }

    public int getFrameNo() {
        return rounds.size();
    }


}
