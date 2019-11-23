package com.seok2.bowling.frame.game.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import com.seok2.bowling.frame.domain.Board;
import com.seok2.bowling.pin.domain.Pin;
import com.seok2.bowling.user.domain.User;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class BowlingGame {

    private final List<Board> game;

    private BowlingGame(List<Board> game) {
        this.game = game;
    }

    public BowlingGame of(String users) {
        return Arrays.stream(users.split(","))
            .map(User::of)
            .map(Board::init)
            .collect(collectingAndThen(toList(), BowlingGame::new));
    }

    public void roll(Pin felled) {
        getCurrent().roll(felled);
    }

    public Board getCurrent() {
        return game.stream()
            .min(Comparator.comparing(Board::size))
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isGameOver() {
        return game.stream()
            .map(Board::isGameOver)
            .reduce((b1, b2) -> b1 || b2)
            .orElseThrow(IllegalAccessError::new);
    }
}
