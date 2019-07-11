package com.jaeyeonling.bowling;

import com.jaeyeonling.bowling.domain.BowlingGame;
import com.jaeyeonling.bowling.domain.frame.KnockdownPins;
import com.jaeyeonling.bowling.domain.user.User;
import com.jaeyeonling.bowling.view.console.ConsoleInputView;
import com.jaeyeonling.bowling.view.console.ConsoleOutputView;

public class Application {

    public static void main(final String... args) {
        final User user = User.of(ConsoleInputView.readUsername());
        final BowlingGame bowlingGame = BowlingGame.with(user);

        while (bowlingGame.canPlay()) {
            ConsoleOutputView.printBowlingGame(bowlingGame);

            final int inputKnockdownPins = ConsoleInputView.readKnockdownPins(bowlingGame.getCurrentFrameIndex());
            final KnockdownPins knockdownPins = KnockdownPins.valueOf(inputKnockdownPins);
            bowlingGame.bowl(knockdownPins);
        }

        ConsoleOutputView.printResult(bowlingGame);
    }
}
