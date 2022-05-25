package bowling.domain;

import bowling.view.Output;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Frames {
    private List<Score> scores;
    private List<Subtotal> subtotals;

    public Frames() {
        this.scores = IntStream.rangeClosed(1, 10).mapToObj(v -> new Score()).collect(Collectors.toList());
        this.subtotals = Stream.iterate(0, i -> i < 10, i -> i + 1)
                .map(i -> new Subtotal(0, 2))
                .collect(Collectors.toList());
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
            Subtotal prevSubtotal = this.subtotals.get(i > 0 ? i - 1 : 0);
            prevSubtotal.evaluateBonus(newScore);
            Output.printFrame(i + 1, player);
            if (newScore.done()) {
                Subtotal newSubtotal = Subtotal.create(newScore, i > 0 ? prevSubtotal.score() : 0);
                this.subtotals.set(i, newSubtotal);
                iterator.next();
            }
        }
        handleLast(player);
    }

    private void handleLast(Player player) {
        Subtotal prevSubtotal = this.subtotals.get(9);
        prevSubtotal.setLast();
        Score tenthScore = this.scores.get(9);
        if (tenthScore.scoreType() == ScoreType.STRIKE || tenthScore.scoreType() == ScoreType.SPARE) {
            Score finalScore = Score.playBonus(tenthScore);
            this.scores.set(9, finalScore);
            Subtotal newSubtotal = Subtotal.create(finalScore, prevSubtotal.score());
            this.subtotals.set(9, newSubtotal);
            Output.printFrame(11, player);
            return;
        }
        Output.printFrame(10, player);
    }

    public List<Subtotal> subtotals() {
        return this.subtotals;
    }
}
