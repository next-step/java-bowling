package com.jaeyeonling.bowling;

import com.jaeyeonling.bowling.domain.BowlingGame;
import com.jaeyeonling.bowling.domain.BowlingGames;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.user.User;
import com.jaeyeonling.bowling.view.ConsoleInputView;
import com.jaeyeonling.bowling.view.ConsoleOutputView;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final int FIRST_INDEX = 1;

    public static void main(final String... args) {
        final BowlingGames bowlingGames = BowlingGames.with(readUsers());

        while (bowlingGames.canPlay()) {
            ConsoleOutputView.printBowlingGames(bowlingGames);

            final BowlingGame bowlingGame = bowlingGames.getCurrentBowlingGame();

            final int inputKnockdownPins = ConsoleInputView.readKnockdownPins(bowlingGame.getUser());
            final KnockdownPins knockdownPins = KnockdownPins.valueOf(inputKnockdownPins);
            bowlingGame.bowl(knockdownPins);
        }

        ConsoleOutputView.printResult(bowlingGames);
    }

    private static List<User> readUsers() {
        final int countOfUser = ConsoleInputView.readCountOfUser();

        final List<User> users = new ArrayList<>(countOfUser);
        for (int i = FIRST_INDEX; i <= countOfUser; i++) {
            users.add(User.of(ConsoleInputView.readUsername(i)));
        }

        return users;
    }
}
