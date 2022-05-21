package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {
    private final String name;
    private final List<String> scores;

    public Score(String name) {
        this.name = name;
        this.scores = IntStream.rangeClosed(1, 10).mapToObj(v -> String.format("%-4s", "")).collect(Collectors.toList());
    }

    public String payload() {
        String payload = "| " + this.name + "  |  ";
        return payload + String.join("|  ", this.scores) + "|";
    }
}
