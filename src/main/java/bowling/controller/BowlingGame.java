package bowling.controller;

import bowling.domain.GameInfo;
import bowling.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingGame {
    private static final int FIRST_INDEX = 0;

    private final List<GameInfo> gameInfos;
    private int index;

    private BowlingGame(List<String> names) {
        this(names.stream()
                .map(Player::from)
                .map(GameInfo::of)
                .collect(Collectors.toList()), FIRST_INDEX);
    }

    private BowlingGame(List<GameInfo> gameInfos, int index) {
        this.gameInfos = gameInfos;
        this.index = index;
    }

    public static BowlingGame create(List<String> names) {
        return new BowlingGame(names);
    }

    public boolean isGameEnd() {
        return gameInfos.stream().allMatch(GameInfo::isGameEnd);
    }

    public GameInfo currentGameInfo() {
        return gameInfos.get(currentGameIndex());
    }

    private int currentGameIndex() {
        return index;
    }

    public void changeTurn() {
        index++;
        if (index == gameInfos.size()) {
            index = FIRST_INDEX;
        }
    }

    public List<GameInfo> gameInfos() {
        return gameInfos;
    }
}
