package bowling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class NextAddingUpScores {

    private final static int MAX_SIZE = 2;
    private final static int SPARE_ADDING_UP_COUNT = 1;

    private final Queue<Score> scores;

    private NextAddingUpScores(final Queue<Score> scores) {
        this.scores = new LinkedList<>(scores);
    }

    public static NextAddingUpScores newInstance(final List<Score> scores) {
        List<Score> addingUpScores = getAddingUpScores(scores);

        LinkedList<Score> nextAddingUpScores = new LinkedList<>();
        nextAddingUpScores.addAll(addingUpScores);

        return new NextAddingUpScores(nextAddingUpScores);
    }

    private static List<Score> getAddingUpScores(final List<Score> scores) {
        return scores.stream()
                .limit(MAX_SIZE)
                .collect(Collectors.toList());
    }

    public NextAddingUpScores update(final List<Score> scores) {
        scores.forEach(this::add);
        return new NextAddingUpScores(this.scores);
    }

    private void add(final Score score) {
        if (scores.size() >= MAX_SIZE) {
            scores.poll();
        }
        scores.add(score);
    }

    public int sumAddingUpStrikeCase() {
        return Score.sum(new ArrayList<>(scores));
    }

    public int sumAddingUpSpareCase() {
        List<Score> addingUpScore = scores.stream()
                .limit(SPARE_ADDING_UP_COUNT)
                .collect(Collectors.toList());

        return Score.sum(addingUpScore);
    }
}
