package bowling.domain;

import java.util.HashMap;

public class Score {
    private final String name;
    private final HashMap<Integer, Integer> scores;

    public Score(String name) {
        this.name = name;
        this.scores = new HashMap<>(10);
    }
}
