package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private List<Bowling> bowlingList;
    private int cursor;

    private Game(List<Bowling> bowlingList, int cursor) {
        this.bowlingList = bowlingList;
        this.cursor = cursor;
    }

    public static Game from(List<String> names) {
        List<Bowling> bowlingList = names.stream()
                .map(Bowling::from)
                .collect(Collectors.toList());

        return new Game(bowlingList, 0);
    }

    private Bowling getCurrent() {
        return bowlingList.get(cursor);
    }

    private Bowling findBowlingWithIsNotEnd() {
        if (isEnd()) {
            throw new IllegalArgumentException("볼링 게임은 종료되었습니다.");
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
