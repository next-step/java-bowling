package bowling.domain;

import java.util.List;

public class Game {

    private BowlingGames bowlingGames;
    private int cursor;

    private Game(BowlingGames bowlingGames, int cursor) {
        this.bowlingGames = bowlingGames;
        this.cursor = cursor;
    }

    public static Game from(List<String> names) {
        return new Game(BowlingGames.from(names), 0);
    }

    private Bowling getCurrent() {
        return bowlingGames.get(cursor);
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

        if (bowlingGames.matchLastIndex(cursor)) {
            cursor = 0;
        }

        return frame;
    }

    public boolean isEnd() {
        return bowlingGames.isEnd();
    }
}
