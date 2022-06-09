package refactor;

import java.util.*;


public class Scores {
    private static int MAX_SCORE = 10;
    private static Random Random = new Random();
    private final List<Integer> scores;
    private final int remainingTry;

    public Scores() {
        this(new ArrayList(), 2);
    }

    public Scores(List<Integer> scores, int remainingTry) {
        this.scores = scores;
        this.remainingTry = remainingTry;
    }

    public Scores pitch(int numPins) {
        this.scores.add(numPins);
        if (numPins == 10 && sum() != 20) {
            return new Scores(this.scores, remainingTry - 2);
        }
        return new Scores(this.scores, remainingTry - 1);
    }
    public Scores pitchRandom() {
        int numPins = Random.nextInt(remainingPins());
        return pitch(numPins);
    }

    private int remainingPins() {
        if (this.scores.size() == 0) {
            return MAX_SCORE + 1;
        }
        return MAX_SCORE - lastScore() + 1;
    }

    int lastScore() {
        int size = this.scores.size();
        if (size == 0) {
            throw new IllegalArgumentException("No scores yet.");
        }
        return this.scores.get(size - 1);
    }

    @Override
    public String toString() {
        return "Scores{" +
                "scores=" + scores +
                ", remainingTry=" + remainingTry +
                '}';
    }

    public boolean done() {
        return this.remainingTry <= 0;
    }

    public int sum() {
        return this.scores.stream()
                .reduce(0, (acc, cur) -> acc+cur);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores scores1 = (Scores) o;
        return remainingTry == scores1.remainingTry && Objects.equals(scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, remainingTry);
    }

    public List<Integer> scores() {
        return this.scores;
    }

    public boolean isStrike() {
        return this.scores.get(0) == 10;
    }

    public Scores evaluateLastBonus() {
        int remainingTry = this.remainingTry;
        if (sum() >= 10) {
            remainingTry++;
        }
        if (this.scores.size() == 3) {
            return new Scores(this.scores, 0);
        }
        return new Scores(this.scores, remainingTry);
    }
}
