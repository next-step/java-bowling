package bowling;

import java.util.LinkedList;

public class CumulativeScores {
    private static final LinkedList<Integer> cumulativeScores = new LinkedList<>();

    public void add(int score) {
        cumulativeScores.addLast(score);
    }

    public void sumAtLast(int score) {
        int lastScore = cumulativeScores.removeLast();
        cumulativeScores.addLast(lastScore + score);
    }
}
