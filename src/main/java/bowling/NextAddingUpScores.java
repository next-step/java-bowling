package bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NextAddingUpScores {

    private final static int MAX_SIZE = 2;

    private final Queue<Score> scores;

    private NextAddingUpScores(final LinkedList<Score> scores) {
        this.scores = new LinkedList<>(scores);
    }

    public static NextAddingUpScores newInstance(final List<Score> scores) {
        validateSize(scores);

        LinkedList<Score> nextAddingUpScores = new LinkedList<>();
        nextAddingUpScores.addAll(scores);

        return new NextAddingUpScores(nextAddingUpScores);
    }

    private static void validateSize(final List<Score> scores) {
        if(scores.size() > MAX_SIZE) {
            throw new IllegalArgumentException("Next adding up scores size must be lower than 2.");
        }
    }

    public void add(final Score score) {
        if(scores.size() >= 2) {
            scores.poll();
        }
        scores.add(score);
    }
}
