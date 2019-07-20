package com.jaeyeonling.bowling.domain;

import com.jaeyeonling.bowling.domain.user.User;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames;

    private BowlingGames(final List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames with(final List<User> users) {
        return users.stream()
                .map(BowlingGame::with)
                .collect(collectingAndThen(toList(), BowlingGames::new));
    }

    public List<BowlingGame> getBowlingGames() {
        return Collections.unmodifiableList(bowlingGames);
    }

    public BowlingGame getCurrentBowlingGame() {
        final int minFrameIndex = getMinFrameIndex();

        return bowlingGames.stream()
                .filter(BowlingGame::canPlay)
                .filter(bowlingGame -> bowlingGame.getCurrentFrameIndex() == minFrameIndex)
                .findFirst()
                .orElseThrow();
    }

    public boolean canPlay() {
        return bowlingGames.stream()
                .anyMatch(BowlingGame::canPlay);
    }

    private int getMinFrameIndex() {
        return bowlingGames.stream()
                .mapToInt(BowlingGame::getCurrentFrameIndex)
                .min()
                .orElseThrow();
    }
}