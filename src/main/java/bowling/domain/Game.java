package bowling.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private List<Bowling> bowlingList;
    private int cursor;

    private Game(List<String> names) {
        bowlingList = names.stream()
                .map(Bowling::from)
                .collect(Collectors.toList());

        cursor = 0;
    }

    public static Game from(List<String> names) {
        return new Game(names);
    }

    private Bowling getCurrent() {
        return bowlingList.get(cursor);
    }

    private Bowling findBowlingWithIsNotEnd() {
        if (isEnd()) {
            throw new RuntimeException("Test");
        }

        Bowling bowling = getCurrent();

        while (bowling.isEnd()) {
            cursor++;
            bowling = getCurrent();
        }

        return bowling;
    }

    public String getPlayerName() {
        return getCurrent().getPlayerName();
    }

    public Frame hit(int count) {
        Bowling bowling = findBowlingWithIsNotEnd();

        Frame frame = bowling.hit(count);

        if (frame.isFinish()) {
            cursor++;
        }

        if (cursor == bowlingList.size()) {
            cursor = 0;
        }

        return frame;
    }

    public boolean isEnd() {
        return bowlingList.stream()
                .allMatch(Bowling::isEnd);
    }
}
