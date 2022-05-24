package bowling.domain;

import bowling.view.Output;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Scores {
    private List<Score> scores;

    public Scores() {
        this.scores = IntStream.rangeClosed(1, 10).mapToObj(v -> new Score()).collect(Collectors.toList());
    }

    public List<Score> scores() {
        return Collections.unmodifiableList(this.scores);
    }

    public void plays(Player player) {
        ListIterator<Score> iterator = this.scores.listIterator();
        while (iterator.hasNext()) {
            int i = iterator.nextIndex();
            Score score = this.scores.get(i);
            Score newScore = Score.play(score);
            this.scores.set(i, newScore);
            Output.printFrame(i + 1, player);
            if (newScore.done()) {
                iterator.next();
            }
        }
    }
}
