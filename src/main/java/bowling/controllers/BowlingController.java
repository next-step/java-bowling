package bowling.controllers;

import bowling.domain.Bowling;
import bowling.domain.Player;
import bowling.views.InputView;
import bowling.views.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingController {
    public static void run() {
        Bowling bowling = new Bowling(players(InputView.playersCount()));
        OutputView.print(bowling);

        while (bowling.playing()) {
            bowling = bowling.play(InputView.knockedPinsCount(bowling.currentPlayer()));
            OutputView.print(bowling);
        }
    }

    private static List<Player> players(int count) {
        final List<Player> players = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            players.add(new Player(InputView.name(i)));
        }

        return players;
    }
}