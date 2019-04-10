package com.hyoj.bowling;

import com.hyoj.bowling.console.InputConsole;
import com.hyoj.bowling.console.OutputConsole;
import com.hyoj.bowling.domain.GameResult;
import java.util.stream.IntStream;

public class BowlingConsoleApp {
    public static void main(String[] args) {
        final String playerName = InputConsole.enterPlayerName();
        final GameResult gameResult = new GameResult(playerName);

        IntStream.rangeClosed(1, GameResult.MAX_FRAMES_COUNT - 1)
            .forEach(i -> gameResult.addFrame(
                InputConsole::enterKnockDownPinsCount, OutputConsole::printGameResult));

        gameResult.addFinalFrame(InputConsole::enterKnockDownPinsCount, OutputConsole::printGameResult);
    }
}
