package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {
    private final String name;
    private final List<Score> scores;

    public Player(String name) {
        this.name = name;
        this.scores = IntStream.rangeClosed(1, 10).mapToObj(v -> new Score()).collect(Collectors.toList());
    }

    public String payload() {
        String payload = "| " + this.name + "  |  "; // name should be formatted
        payload += this.scores
                .stream()
                .map(v -> v.payload())
                .reduce("", (acc, cur) -> acc + cur + "|  ");
        return payload;
    }

    public int pitch() {
        return new Random().nextInt(11);
    }

    public void save(int i, Score score) {
        this.scores.set(i, score);
    }
}
