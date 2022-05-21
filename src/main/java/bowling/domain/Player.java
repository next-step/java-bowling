package bowling.domain;

import bowling.view.Output;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {
    private final String name;
    private final List<Optional<Score>> scores;

    public Player(String name) {
        this.name = name;
        Optional<Score> empty = Optional.empty();
        this.scores = IntStream.rangeClosed(1, 10).mapToObj(v -> empty).collect(Collectors.toList());
    }

    public String payload() {
        String payload = "| " + this.name + "  |  "; // name should be formatted
        payload += this.scores
                .stream()
                .map(v -> Score.payload(v))
                .reduce("", (acc, cur) -> acc + cur + "|  ");
        return payload;
    }

    public static int pitch() {
        return new Random().nextInt(11);
    }

    public void save(int index, Score score) {
        this.scores.set(index, Optional.of(score));
    }

    public void plays() {
        ListIterator<Optional<Score>> iterator = this.scores.listIterator();
        Optional<Score> score = iterator.next();
        while (iterator.hasNext()) {
            boolean present = score.isPresent();
            Score newScore = Score.play(score);
            iterator.set(Optional.of(newScore));
            score = Optional.of(newScore);
            System.out.println("Frame " + iterator.nextIndex());
            Output.print("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
            Output.print(this.payload());
            if (present) {
                score = iterator.next();
            }
        }
    }
}
