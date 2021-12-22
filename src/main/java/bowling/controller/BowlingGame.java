package bowling.controller;

import bowling.domain.GameInfo;
import bowling.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingGame {
    private static final int FIRST_INDEX = 0;

    private final List<GameInfo> gameInfos;
    private final int index;

    private BowlingGame(String name) {
        this(Stream.of(name)
                .map(Player::from)
                .map(GameInfo::of)
                .collect(Collectors.toList()), FIRST_INDEX);
    }

    private BowlingGame(List<GameInfo> gameInfos, int index) {
        this.gameInfos = gameInfos;
        this.index = index;
    }

    public static BowlingGame create(String name) {
        return new BowlingGame(name);
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
}
