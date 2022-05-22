package bowling.domain;

import bowling.view.Output;

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

    public void plays() {
        ListIterator<Optional<Score>> iterator = this.scores.listIterator();
        while (iterator.hasNext()) {
            int i = iterator.nextIndex();
            Optional<Score> score = this.scores.get(i);
            Score newScore = Score.play(score);
            this.scores.set(i, Optional.of(newScore));
            System.out.println("Frame " + (i + 1));
            Output.print("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
            Output.print(this.payload());
            if (newScore.done()) {
                iterator.next();
            }
        }
    }
}
